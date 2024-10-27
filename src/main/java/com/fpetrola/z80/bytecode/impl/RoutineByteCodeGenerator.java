package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineFinder;
import com.fpetrola.z80.routines.RoutineVisitor;
import com.fpetrola.z80.transformations.*;
import org.cojen.maker.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RoutineByteCodeGenerator {
  public Map<String, Variable> registers = new HashMap<>();
  public Field memory;
  public MethodMaker mm;
  public boolean optimize16Convertion = false;
  private ClassMaker cm;
  private Map<Integer, Label> labels = new HashMap<>();
  private Set<Integer> positionedLabels = new HashSet<>();
  private Map<String, MethodMaker> methods = new HashMap<>();
  public final Routine routine;
  private RandomAccessInstructionFetcher instructionFetcher;
  private int startAddress;
  private Predicate<Integer> hasCodeAt;
  private int endAddress;
  public Register<WordNumber> lastMemPc = new Plain16BitRegister<WordNumber>("lastMemPc");

  public Register<WordNumber> pc;
  public Map<String, Variable> variables = new HashMap<>();
  public Map<String, VirtualRegister> registerByVariable = new HashMap<>();
  public Map<VirtualRegister, Variable> variablesByRegister = new HashMap<>();
  public Map<VirtualRegister<?>, VirtualRegister<?>> commonRegisters = new HashMap<>();
  private Map<Integer, Label> insertLabels = new HashMap<>();
  protected Field initial;
  private PendingFlagUpdate pendingFlag;
  public Instruction currentInstruction;
  private boolean syncEnabled;
  public final boolean useFields ;
  public VirtualRegister<?> currentRegister;

  public static <S> S getRealVariable(S variable) {
    Object variable1 = variable;
    if (variable1 instanceof VariableDelegator)
      variable1 = ((Variable) variable1).get();
    return (S) variable1;
  }

  public void setBranchLabel(Label branchLabel) {
    this.branchLabel = branchLabel;
  }

  private Label branchLabel;

  public RoutineByteCodeGenerator(ClassMaker classMaker, RandomAccessInstructionFetcher randomAccessInstructionFetcher, Predicate<Integer> hasCodeChecker, Register pc, Map<String, MethodMaker> methods, Routine routine, boolean syncEnabled, boolean useFields) {
    this.startAddress = routine.getStartAddress();
    this.endAddress = routine.getEndAddress();
    instructionFetcher = randomAccessInstructionFetcher;
    hasCodeAt = hasCodeChecker;
    this.pc = pc;
    cm = classMaker;
    this.methods = methods;
    this.routine = routine;
    this.syncEnabled = syncEnabled;
    this.useFields = useFields;
  }

  public static String createLabelName(int label) {
    //return "$" + Helper.convertToHex(label);
    return "$" + label;
  }

  public void generate() {
    mm = getMethod(startAddress);
    addInstructions();
    returnFromMethod();
  }

  private void addInstructions() {
    List<InstructionGenerator> generators = new ArrayList<>();

    if (useFields) {
      Arrays.stream(RegisterName.values()).forEach(n -> addField(n.name()));
    } else {
      // Arrays.stream(RegisterName.values()).filter(r -> r.name().length() == 2).forEach(n -> addLocalVariable(n.name()));

      addReg16("AF");
      addReg16("BC");
      addReg16("DE");
      addReg16("HL");
      addReg16("IX");
      addReg16("IY");

      add8BitBoth((SmartComposed16BitRegisterVariable) variables.get("AF"));
      add8BitBoth((SmartComposed16BitRegisterVariable) variables.get("BC"));
      add8BitBoth((SmartComposed16BitRegisterVariable) variables.get("DE"));
      add8BitBoth((SmartComposed16BitRegisterVariable) variables.get("HL"));

      addLowHigh((SmartComposed16BitRegisterVariable) variables.get("IX"), "IXH", "IXL");
      addLowHigh((SmartComposed16BitRegisterVariable) variables.get("IY"), "IYH", "IYL");
    }
    addField("nextAddress");
    //cm.addField(int.class, "initial").public_();
    // initial = mm.field("initial");
    // registers.put("initial", initial);

    //cm.addField(int[].class, "memory").public_();
    memory = mm.field("mem");
    //memory.set(mm.new_(int[].class, 0x10000));
    registers.put("mem", memory);

    Instruction[] lastInstruction = {null};
    final boolean[] ready = new boolean[]{false};

    Consumer<Integer> integerConsumer = address -> {
      Instruction instruction = instructionFetcher.getInstructionAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          boolean contains = routine.contains(address);
          if (contains) {
            if (!ready[0]) {
              //System.out.println(address);
              pc.write(WordNumber.createValue(address));
              int firstAddress = address;

//          GenerateTestSourceInstructionVisitor visitor = new GenerateTestSourceInstructionVisitor(startAddress);
//          instruction.accept(visitor);
//          System.out.println(visitor.result);

              Runnable scopeAdjuster = () -> {
                pc.write(WordNumber.createValue(address));
                new InstructionActionExecutor<>(r -> r.adjustRegisterScope()).executeAction(instruction);
              };

              Runnable labelGenerator = () -> {
                pc.write(WordNumber.createValue(address));
                JumpLabelVisitor jumpLabelVisitor1 = new JumpLabelVisitor();
                instruction.accept(jumpLabelVisitor1);
                int jumpLabel = jumpLabelVisitor1.getJumpLabel();

                //  if (jumpLabel > 0)
                addLabel(address);
              };
              Runnable instructionGenerator = () -> {
                if (!ready[0]) {
                  if (address == 34873)
                    System.out.print("");

                  currentInstruction = instruction;
                  List<Routine> list = routine.innerRoutines.stream().filter(routine1 -> routine1.contains(address)).toList();
                  if (!list.isEmpty()) {
                    Routine first = list.getFirst();
                    if (first.getStartAddress() == address) {
                      invokeTransformedMethod(first.getStartAddress());
                      returnFromMethod();
                    } else {
                      System.out.print("");
                    }
                    //ready[0] = true;
                  } else {
                    lastMemPc.write(WordNumber.createValue(address));
                    pc.write(WordNumber.createValue(address));

                    if (!(instruction instanceof ConditionalInstruction<?, ?>) && pendingFlag != null) {
                      if (!pendingFlag.processed)
                        pendingFlag.update(false);
                    }

                    int label = -1;
                    if (getLabel(address) != null) {
                      label = firstAddress;
                      hereLabel(label);
                    }

//                    if (instruction instanceof Ret && routine.virtualPop.contains(address)) {
//                      mm.invoke("incPops");
//                    }

                    ByteCodeGeneratorVisitor visitor = new ByteCodeGeneratorVisitor(mm, label, this, address, pendingFlag);
                    instruction.accept(visitor);

                    pendingFlag = visitor.pendingFlag;

                    if (!visitor.incPopsAdded && routine.virtualPop.containsKey(address)) {
                      getField("nextAddress").set(routine.virtualPop.get(address) + 1);
                      returnFromMethod();
                    }
                  }
                }

              };

              generators.add(new InstructionGenerator(scopeAdjuster, labelGenerator, instructionGenerator));
            }
          }
        }
        lastInstruction[0] = instruction;
      }
    };
    forEachAddress(integerConsumer);

    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.labelGenerator().run());
    removeExternalLabels();
    generators.forEach(g -> g.instructionGenerator().run());

    positionedLabels.forEach(l -> labels.get(l).here());
  }

  private void addReg16(String name) {
    Variable variable = addLocalVariable(name);
    SmartComposed16BitRegisterVariable smartComposed16BitRegisterVariable = new SmartComposed16BitRegisterVariable(mm, name, variable, this);
    variables.put(name, smartComposed16BitRegisterVariable);
  }

  private void add8BitBoth(SmartComposed16BitRegisterVariable af) {
    addLowHigh(af, af.name().charAt(0) + "", af.name().charAt(1) + "");
  }

  private void addLowHigh(SmartComposed16BitRegisterVariable reg16, String high, String low) {
    Single8BitRegisterVariable variableHigh = new Single8BitRegisterVariable(mm, addLocalVariable(high), reg16, "h", this);
    variables.put(high, variableHigh);
    reg16.setHigh(variableHigh);
    Single8BitRegisterVariable variableLow = new Single8BitRegisterVariable(mm, addLocalVariable(low), reg16, "l", this);
    variables.put(low, variableLow);
    reg16.setLow(variableLow);
  }

  private void addField(String name) {
    // cm.addField(int.class, name).private_().static_();
    Variable field = mm.field(name);
    registers.put(name, field);

    if (name.length() == 2) field = new Composed16BitRegisterVariable(mm, name);

    variables.put(name, field);
  }

  private Variable addLocalVariable(String name) {
    // cm.addField(int.class, name).private_().static_();
//    Variable variable = mm.var(int.class);
    List<String> parametersList = routine.accept(new RoutineRegisterAccumulator<>() {
      public void visitParameter(String register) {
        routineParameters.add(register);
      }
    });
    int index = parametersList.indexOf(name);
    Variable variable;
    if (index != -1) {
      variable = mm.param(index);
    } else {
      variable = mm.var(int.class).set(0);
    }
    return variable.name(name);
  }

  private void removeExternalLabels() {
    List<Integer> collect = labels.keySet().stream().filter(l -> {
      boolean b = l < startAddress;
      return b || !hasCodeAt.test(l);
    }).collect(Collectors.toList());
    collect.forEach(l -> {
      labels.remove(l);
      positionedLabels.remove((Object) l);
    });

  }

  public Label addLabel(int labelLine) {
    Label label = labels.get(labelLine);
    if (label == null) {
      label = mm.label();
      labels.put(labelLine, label);
      positionedLabels.add(labelLine);

      if (insertLabels.get(labelLine) == null) {
        insertLabels.put(labelLine, mm.label());
      }
    }

    return label;
  }

  public Label getLabel(int i) {
    return labels.get(i);
  }

  public boolean isLabelPositioned(int labelName) {
    return positionedLabels.contains(labelName);
  }

  public void hereLabel(int labelName) {
    Label insertLabel = insertLabels.get(labelName);
    if (insertLabel != null) insertLabel.here();

    Label label = getLabel(labelName);
    if (label == null) {
      label = addLabel(labelName);
    }

    label.here();
    positionedLabels.remove((Object) labelName);

  }

  public void forEachAddress(Consumer<Integer> consumer) {
    for (int i = this.startAddress; i <= endAddress; i++)
      consumer.accept(i);
  }

  public MethodMaker getMethod(int jumpLabel) {
    return createMethod(jumpLabel);
  }

  public MethodMaker createMethod(int jumpLabel) {
    return findMethod(jumpLabel, methods, cm);
  }

  public static MethodMaker findMethod(int jumpLabel, Map<String, MethodMaker> methods, ClassMaker classMaker) {
    Routine routineAt = RoutineFinder.routineManager.findRoutineAt(jumpLabel);
    List<String> parametersList = routineAt.accept(new RoutineRegisterAccumulator<>() {
      public void visitParameter(String register) {
        routineParameters.add(register);
      }
    });
    String methodName = createLabelName(jumpLabel);
    MethodMaker methodMaker = methods.get(methodName);
    if (methodMaker == null) {
      Object[] objects = parametersList.stream().map(s -> int.class).toArray();

      Object[] values = routineAt.accept(new RoutineRegisterAccumulator<String>() {
        public void visitReturnValue(String register) {
          routineParameters.add(register);
        }
      }).toArray();

      methodMaker = classMaker.addMethod(values.length == 0 ? void.class : int[].class, methodName, objects).public_();
      methods.put(methodName, methodMaker);
    }

    return methodMaker;
  }

  public <T extends WordNumber> Variable getField(String name) {
    return registers.get(name);
  }

  public <T extends WordNumber> boolean variableExists(VirtualRegister register) {
    register = getTop(register);
    Variable variable = variables.get(getRegisterName(register));
    return variable != null;
  }

  public <T extends WordNumber> void createVariable(VirtualRegister register1) {
    VirtualRegister register = getTop(register1);
    String name = getRegisterName(register);
    registerByVariable.put(name, register);
  }

  public <T extends WordNumber> Variable getVariable(VirtualRegister register1, Supplier<Object> value) {
    VirtualRegister register = getTop(register1);

    String name = getRegisterName(register);
    Variable variable = variables.get(name);
    if (variable != null) {
      Variable set = doSetValue(value, variable);
      variables.put(name, set);
      variablesByRegister.put(register, set);
      return variable;
    } else {
//      System.out.println("creating var: " + name + "= " + value);
      registerByVariable.put(name, register);

      Variable set = setVariable(name, value);

      variables.put(name, set);
      variablesByRegister.put(register, set);

//      getField("PC").sub(var);
      return set;
    }
  }

  public Variable setVariable(String name, Supplier<Object> value) {
    Variable var = mm.var(int.class);
    var.name(name);
    Variable set = doSetValue(value, var);
    return set;
  }

  private Variable doSetValue(Supplier<Object> value, Variable var) {
    Variable set = var;
    Object value1 = getRealVariable(value.get());
    if (value1 != null) set = var.set(value1);
    return set;
  }


  public <T extends WordNumber> Variable getExistingVariable(VirtualRegister<?> register) {
    VirtualRegister topRegister = getTop(register);

//    register.getPreviousVersions().stream().anyMatch(v -> {
//
//      if (v instanceof IVirtual8BitsRegister<?> virtual8BitsRegister) {
//        VirtualComposed16BitRegister virtualComposed16BitRegister = virtual8BitsRegister.getVirtualComposed16BitRegister();
//        if (virtualComposed16BitRegister != null) {
//          return true;
//        }
//      }
//
//      return false;
//    });
    String registerName = getRegisterName(topRegister);

    Variable variable1 = variables.get(registerName);
    if (variable1 instanceof VariableDelegator variable) {
      variable.setRegister(register);
    }
    currentRegister = register;
    return variable1;
  }

  public Label getBranchLabel(Integer minLine) {
    Optional<Map.Entry<Integer, Label>> first = insertLabels.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).filter(e -> e.getKey() >= minLine).findFirst();
    return first.get().getValue();
  }

  public static String getRegisterName(VirtualRegister register) {
    Virtual8BitsRegister.breakInStackOverflow();

    return VirtualComposed16BitRegister.fixIndexNames(register.getName().replace(",", ""));
  }

  public static VirtualRegister getTop(VirtualRegister<?> register) {
    VirtualRegister o = (VirtualRegister) register.getVersionHandler().versions.get(0);
    return o;
    //register = register.getVersionHandler().getBiggestScopeFor(register);

//    if (register == null)
//      return null;
//    while (register.getPreviousVersions().size() == 1) {
//      VirtualRegister<?> previous = register.getPreviousVersions().get(0);
//      boolean initial = previous instanceof InitialVirtualRegister<?>;
//      boolean mixed = previous.getName().contains(",");
//      boolean isLd = false;
//      if (register instanceof Virtual8BitsRegister<?> virtual8BitsRegister)
//        isLd = virtual8BitsRegister.instruction instanceof Ld<?>;
//
//      if (initial || mixed || isLd) break;
//      register = previous;
//    }
    //return register;
  }

  Variable getVariableFromMemory(Object variable, String bits) {
    Object variable1 = getRealVariable(variable);
    if (syncEnabled) {
      List<Object> params = new ArrayList<>();
      params.add(variable1);
      params.add(lastMemPc.read().intValue());
      params.addAll(getListOfAllRegistersForParameters());
      return mm.invoke("mem" + bits, params.toArray());
    } else {
      if (bits.equals("16"))
        return mm.invoke("mem" + bits, variable1);
      else
        return memory.aget(variable1);
    }
  }

  void writeVariableToMemory(Object o, Object variable, String bits) {
    Object variable1 = getRealVariable(variable);
    Object o1 = getRealVariable(o);
    if (syncEnabled) {
      List<Object> params = new ArrayList<>();
      params.add(variable1);
      params.add(o1);
      params.add(lastMemPc.read().intValue());
      params.addAll(getListOfAllRegistersForParameters());

      mm.invoke("wMem" + bits, params.toArray());
    } else {
      if (bits.equals("16"))
        mm.invoke("wMem" + bits, variable1, o1);
      else {
//        memory.aset(variable1, o1);
        memory.aset(variable1, o1 instanceof Variable variable2 ? variable2 : (Integer) o1 & 0xff);
      }
    }
  }

  Variable getExistingVariable(String hl) {
    return getRealVariable(variables.get(hl));
  }

  public Variable invokeTransformedMethod(int jumpLabel) {
    Routine routineAt = RoutineFinder.routineManager.findRoutineAt(jumpLabel);
    Object[] array = routineAt.accept(new RoutineRegisterAccumulator<Variable>() {
      public void visitParameter(String register) {
        routineParameters.add(getVar(register));
      }
    }).toArray();
    Variable invoke = mm.invoke(createLabelName(jumpLabel), array);
    assignReturnValues(routineAt, invoke);
    return invoke;
  }

  private Object[] getAllregistersAsParameters() {
    return getListOfAllRegistersForParameters().toArray();
  }

  private List<Variable> getListOfAllRegistersForParameters() {
    return getListOfAllRegistersNamesForParameters().stream().map(this::getVar).toList();
  }

  private void assignReturnValues(Routine routine, Variable result) {
    List<Variable> resultValues = routine.accept(new RoutineRegisterAccumulator<>() {
      public void visitReturnValue(String register) {
        routineParameters.add(getVar(register));
      }
    });
    int index = 0;
    for (Variable variable : resultValues) {
      variable.set(result.aget(index++));
    }
  }

  private List<String> getListOfAllRegistersNamesForParameters() {
    if (useFields)
      return new ArrayList<>();
    else
      return Arrays.asList("AF", "BC", "DE", "HL", "IX", "IY", "A", "F", "B", "C", "D", "E", "H", "L", "IXL", "IXH", "IYL", "IYH");
  }

  public Variable getVar(String name) {
    return getExistingVariable(name);
  }

  void returnFromMethod() {
    Object[] values = routine.accept(new RoutineRegisterAccumulator<Variable>() {
      public void visitReturnValue(String register) {
        routineParameters.add(getVar(register));
      }
    }).toArray();

    if (useFields || values.length == 0)
      mm.return_();
    else {
      Variable variable1 = mm.new_(int[].class, values.length);
      for (int i = 0; i < values.length; i++) {
        variable1.aset(i, values[i]);
      }
      mm.return_(variable1);
    }
  }

  private static class RoutineRegisterAccumulator<S> implements RoutineVisitor<List<S>> {
    protected final List<S> routineParameters = new ArrayList<>();

    public List<S> getResult() {
      return routineParameters;
    }
  }
}
