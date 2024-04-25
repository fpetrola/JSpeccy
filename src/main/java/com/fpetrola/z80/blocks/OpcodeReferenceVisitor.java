package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.IVirtual8BitsRegister;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.Variable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OpcodeReferenceVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private Object result;
  private boolean isTarget;
  private ByteCodeGenerator byteCodeGenerator;

  public void setCreateInitializer(Function createInitializer) {
    this.createInitializer = createInitializer;
  }

  private Function<VirtualRegister<T>, Object> createInitializer;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    createInitializer = new Function<>() {
      public Object apply(VirtualRegister<T> virtualRegister) {
        List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
        if (!virtualRegister.hasNoPrevious() && isMixRegister(previousVersions.get(0))) {
          VirtualComposed16BitRegister virtualComposed16BitRegister = (VirtualComposed16BitRegister) previousVersions.get(0);
          String name = ByteCodeGenerator.getRegisterName(virtualComposed16BitRegister);
          Variable variable = ByteCodeGeneratorVisitor.initializers.get(name);
          if (variable == null) {
            Variable o = (Variable) processValue(createInitializer, virtualComposed16BitRegister.getHigh());
            Variable o2 = (Variable) processValue(createInitializer, virtualComposed16BitRegister.getLow());
            variable = o.shl(8).or(o2);
            ByteCodeGeneratorVisitor.initializers.put(name, variable);
          }
          return variable;
        } else {
          Object previousVersion = getPreviousVersion(virtualRegister, byteCodeGenerator1);
          if (previousVersion == null && !virtualRegister.hasNoPrevious()) {
            Register previousVersion1 = virtualRegister.getPreviousVersions().get(0);
            for (Map.Entry<String, VirtualRegister> e : byteCodeGenerator1.registerByVariable.entrySet()) {
              if (e.getValue() instanceof VirtualComposed16BitRegister<?>) {
                VirtualComposed16BitRegister<?> value = (VirtualComposed16BitRegister<?>) e.getValue();
                Variable existingVariable = byteCodeGenerator1.getExistingVariable(value);
                if (value.getLow() == previousVersion1)
                  return existingVariable.and(0xFF);
                else if (value.getHigh() == previousVersion1)
                  return existingVariable.shr(8);
              }
            }
            return processValue(this, null);
            //throw new RuntimeException("previous not found");
          } else {
            if (previousVersion == null)
              if (!previousVersions.isEmpty()) {
                previousVersion = previousVersions.get(0);
              } else
                previousVersion = virtualRegister.read().intValue();

            return processValue(this, previousVersion);
          }
        }
      }
    };
  }


  @Override
  public void visitOpcodeReference(OpcodeReference opcodeReference) {
    result = ((WordNumber) opcodeReference.read()).intValue();
  }

  public static boolean isMixRegister(VirtualRegister virtualRegister) {
    return virtualRegister.getName().contains(",");
  }

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1, Function createInitializer) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    this.createInitializer = createInitializer;
  }

  private static <T extends WordNumber> Object getPreviousVersion(VirtualRegister<T> virtualRegister, ByteCodeGenerator byteCodeGenerator1) {
    Virtual8BitsRegister.breakInStackOverflow();
    List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
    if (virtualRegister.hasNoPrevious()) {
      return null;
    } else {
      Optional first = previousVersions.stream().filter(r -> byteCodeGenerator1.variableExists(r)).findFirst();
      // return first.orElse(previousVersions.get(0).read().intValue());
      return first.orElse(null);
    }
  }

  public Object getResult() {
    return result;
  }

  @Override
  public boolean visitVirtualComposed16BitRegister(VirtualComposed16BitRegister virtualComposed16BitRegister) {
    result = processValue(createInitializer, virtualComposed16BitRegister);
    return true;
  }

  public void visitRegister(Register register) {
    result = processValue(createInitializer, register);
  }

  protected <T extends WordNumber> Object processValue(Function<VirtualRegister<T>, Object> createInitializer, Object value) {
    if (value instanceof VirtualRegister<?> virtualRegister) {
      Object initializer = createInitializer.apply((VirtualRegister<T>) virtualRegister);

      if (initializer == null)
        initializer = 12345;

      if (virtualRegister.usesMultipleVersions()) {
        if (!byteCodeGenerator.variableExists(virtualRegister)) {
          int minLine = getMinLineNumber2(virtualRegister);
          int registerLine = getRegisterLine(virtualRegister);

          Label branchLabel = byteCodeGenerator.getBranchLabel(minLine + 1);
          Object finalInitializer = initializer;
          Runnable runnable = () -> {
            byteCodeGenerator.getVariable(virtualRegister, finalInitializer);
            virtualRegister.getPreviousVersions().forEach(p -> ByteCodeGeneratorVisitor.commonRegisters.put((VirtualRegister<WordNumber>) p, (VirtualRegister<WordNumber>) virtualRegister));
          };

          if (minLine == registerLine)
            runnable.run();
          else {
            Label insert = branchLabel.insert(runnable);
            byteCodeGenerator.setBranchLabel(insert);
          }
        }

        initializer = byteCodeGenerator.getExistingVariable(virtualRegister);
      }

      return byteCodeGenerator.getVariable(virtualRegister, initializer);
    } else
      return value;
  }

  private Integer getMinLineNumber(VirtualRegister<?> virtualRegister) {
    return getRegisterLine(virtualRegister);
//    List<VirtualRegister<?>> ancestorsOf1 = getAncestorsOf(virtualRegister);
//    Collections.sort(ancestorsOf1, (c1, c2) -> getRegisterLine(c2) - getRegisterLine(c1));
//
//    for (int i = 0; i < ancestorsOf1.size(); i++) {
//      int finalI = i;
//      long count = ancestorsOf1.stream().filter(r -> r == ancestorsOf1.get(finalI)).count();
//      if (count >= virtualRegister.getPreviousVersions().size())
//        return getRegisterLine(ancestorsOf1.get(i));
//    }
//
//    return ancestorsOf1.stream().map(r -> getRegisterLine(r)).min(Integer::compare).get();
  }

  private List<VirtualRegister<?>> getAncestorsOf(VirtualRegister<?> r) {
    List<VirtualRegister<?>> result1 = new ArrayList<>();
    result1.add(r);
    r.getPreviousVersions().stream().filter(r1 -> getRegisterLine(r1) < getRegisterLine(r)).forEach(r1 -> result1.addAll(getAncestorsOf(r1)));
    return result1;
  }

  private Integer getMinLineNumber2(VirtualRegister<?> virtualRegister) {
    return virtualRegister.getPreviousVersions().stream().map(r -> getRegisterLine(r)).min(Integer::compare).get();
  }

  private int getRegisterLine(VirtualRegister<?> r) {
    String name = r.getName();
    if (name.contains(",")) {
      if (r instanceof VirtualComposed16BitRegister<?> virtualComposed16BitRegister) {
        IVirtual8BitsRegister<?> low = virtualComposed16BitRegister.getLow();
        IVirtual8BitsRegister<?> high = virtualComposed16BitRegister.getHigh();
        return Math.min(getRegisterLine(low), getRegisterLine(high));
      }
    }
    int i = name.indexOf("_");
    i = i != -1 ? Integer.parseInt(name.substring(i + 2)) : 10000000;
    return i;
  }

  public void visitConstantOpcodeReference(ConstantOpcodeReference<T> constantOpcodeReference) {
    result = constantOpcodeReference.read().intValue();
  }

  public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference) {
    Field memoryField = byteCodeGenerator.getField("memory");
    int o = memoryAccessOpcodeReference.getC().read().intValue();
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> o);
    else
      result = getFromMemory(o);
  }

  public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference) {
    Register target = (Register) memoryPlusRegister8BitReference.getTarget();
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(isTarget, byteCodeGenerator);
    target.accept(opcodeReferenceVisitor);

    Variable variable = (Variable) opcodeReferenceVisitor.getResult();

    Variable variablePlusDelta = variable.add(memoryPlusRegister8BitReference.fetchRelative());
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variablePlusDelta);
    else {
      result = getFromMemory(variablePlusDelta);
    }
  }


  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
    Register target = (Register) indirectMemory8BitReference.getTarget();
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    target.accept(opcodeReferenceVisitor);

    Variable variable = (Variable) opcodeReferenceVisitor.getResult();
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variable);
    else {
      result = getFromMemory(variable);
    }
  }

  private Variable getFromMemory(Object variable) {
    Variable get = byteCodeGenerator.memory.aget(variable);
//
//    Variable get = byteCodeGenerator.memory.invoke(Object.class, "get", new Object[]{int.class}, variable);
//    Variable cast = get.cast(Integer.class);
    return get;
  }

  @Override
  public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {
    result = ((T) immutableOpcodeReference.read()).intValue();
  }
}
