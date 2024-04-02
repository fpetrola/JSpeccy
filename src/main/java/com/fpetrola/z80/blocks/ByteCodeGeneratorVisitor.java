package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  private final MethodMaker methodMaker;
  private final ByteCodeGenerator byteCodeGenerator;
  public static Map<Virtual8BitsRegister, Virtual8BitsRegister> commonRegisters = new HashMap<>();

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  public void visitingInc(Inc inc) {
    inc.accept(new VariableInstructionVisitor((s, t) -> t.inc(1), byteCodeGenerator));
  }

  public void visitingXor(Xor xor) {
    xor.accept(new VariableInstructionVisitor((s, t) -> t.set(t.xor(s)), byteCodeGenerator));
  }

  public void visitingAdd16(Add16 add16) {
    add16.accept(new VariableInstructionVisitor((s, t) -> t.set(t.add(s)), byteCodeGenerator));
  }

  public void visitingAdd(Add add) {
    add.accept(new VariableInstructionVisitor((s, t) -> t.set(t.add(s)), byteCodeGenerator));
  }

  public void visitingDec(Dec dec) {
    dec.accept(new VariableInstructionVisitor((s, t) -> t.inc(-1), byteCodeGenerator));
  }

  public void visitingLd(Ld ld) {
    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    ld.getSource().accept(instructionVisitor2);
    Object sourceVariable1 = instructionVisitor2.getResult();

    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer;
    instructionVisitor.setCreateInitializer((BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object>) (byteCodeGenerator1, virtual8BitsRegister) -> {
      return sourceVariable1;
    });
    ld.getTarget().accept(instructionVisitor);
    Object targetVariable1 = instructionVisitor.getResult();

    BiConsumer<Object, Variable> objectVariableBiConsumer = (sourceVariable, targetVariable) -> {
      Class<?> aClass = targetVariable.classType();
      if (!aClass.equals(int.class))
        targetVariable.aset(0, sourceVariable);
      else if (targetVariable instanceof WriteArrayVariable writeArrayVariable)
        writeArrayVariable.set(sourceVariable);
      else if (sourceVariable instanceof Variable variable && variable.name() == null) {
        targetVariable.set(sourceVariable);
      }
    };

    if (targetVariable1 instanceof Variable variable)
      objectVariableBiConsumer.accept(sourceVariable1, variable);
  }

  protected static <T extends WordNumber> Object getSourceVariableOf(ImmutableOpcodeReference<T> source, Object initializer, ByteCodeGenerator byteCodeGenerator1) {
    Object sourceVariable = !(source instanceof Register) ? source.read().intValue() : 13;

    if (source instanceof Virtual8BitsRegister<T> virtual) {
      String name = virtual.getName();
      initializer = initializer != null ? initializer : sourceVariable;
      if (virtual.previousVersions.size() > 1) {
        sourceVariable = byteCodeGenerator1.getVariable(name, createCommonRegister(virtual, initializer, byteCodeGenerator1));
      } else {
        sourceVariable = byteCodeGenerator1.getVariable(name, initializer);
      }
    }
    return sourceVariable;
  }

  private static <T extends WordNumber> Variable createCommonRegister(Virtual8BitsRegister<T> virtual8BitsRegister, Object initializer, ByteCodeGenerator byteCodeGenerator1) {
    String name = virtual8BitsRegister.getName();
    Label branchLabel = byteCodeGenerator1.getBranchLabel();
    branchLabel.insert(() -> {
      Variable t = byteCodeGenerator1.getVariable(name, initializer);
      virtual8BitsRegister.previousVersions.forEach(p -> commonRegisters.put(p, virtual8BitsRegister));
    });
    return byteCodeGenerator1.getVariable(name, initializer);
  }

  protected void executeConditional(ByteCodeGenerator byteCodeGenerator, Runnable runnable, Condition condition) {
    Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
    if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
    else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
    else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
    else if (condition.toString().equals("C")) f.ifLt(0, runnable);
    else runnable.run();
  }


  public void visitingCp(Cp cp) {
    Object sourceVariable = getSourceVariableOf(cp.getSource(), 0, byteCodeGenerator);
    Variable a = byteCodeGenerator.registers.get(RegisterName.A.name());
    Variable targetVariable = byteCodeGenerator.registers.get(RegisterName.F.name());

    targetVariable.set(a.sub(sourceVariable));
  }

  public void visitingRet(Ret ret) {
    executeConditional(byteCodeGenerator, () -> methodMaker.return_(), ret.getCondition());
  }

  public void visitingCall(Call call) {
    int jumpLabel = call.getJumpAddress().intValue();

    String labelName = ByteCodeGenerator.createLabelName(jumpLabel);
    MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
    if (method != null) {
      Runnable runnable = () -> methodMaker.invoke(labelName);
      executeConditional(byteCodeGenerator, runnable, call.getCondition());
    }
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    conditionalInstruction.calculateRelativeJumpAddress();

    int jumpLabel = conditionalInstruction.getJumpAddress().intValue();
    Label label1 = byteCodeGenerator.getLabel(jumpLabel);
    if (label1 != null) {
      Variable f = getVariable(conditionalInstruction);
      //Variable f =  (Variable) getSourceVariableOf(register, 0, byteCodeGenerator);

      //Variable f = (Variable) byteCodeGenerator.getVariable(condition1.getRegister().getName(), getSourceUsingPreviousForRegister(true, (Virtual8BitsRegister) condition1.getRegister(), true));
//      Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
      Condition condition = conditionalInstruction.getCondition();
      if (condition.toString().contains("NZ")) f.ifNe(0, label1);
      else if (condition.toString().equals("Z")) f.ifEq(0, label1);
      else if (condition.toString().equals("NC")) f.ifGe(0, label1);
      else if (condition.toString().equals("C")) f.ifLt(0, label1);
      else label1.goto_();
    }
  }

  private Variable getVariable(ConditionalInstruction conditionalInstruction) {
    Register register;
    if (conditionalInstruction instanceof DJNZ djnz) {
      register = djnz.getB();
    } else {
      ConditionFlag condition1 = (ConditionFlag) conditionalInstruction.getCondition();
      register = condition1.getRegister();
    }

    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    register.accept(opcodeReferenceVisitor);
    Object sourceVariable = opcodeReferenceVisitor.getResult();

    Variable f = (Variable) sourceVariable;
    return f;
  }
}
