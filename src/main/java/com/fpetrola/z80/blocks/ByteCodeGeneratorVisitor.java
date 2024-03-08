package com.fpetrola.z80.blocks;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  private final MethodMaker methodMaker;
  private final int label;
  private final ByteCodeGenerator byteCodeGenerator;

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.label = label;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  public static String createLabelName(int label) {
    return "$" + Helper.convertToHex(label);
  }

  @Override
  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    hereLabel();
    Object sourceVariable = 0;
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, targetInstruction.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).set(sourceVariable);
  }

  @Override
  public void visitingInstruction(AbstractInstruction instruction) {
    hereLabel();
  }

  @Override
  public void visitingAdd(Add add) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, add.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, add.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(sourceVariable);
  }

  @Override
  public void visitingAdd16(Add16 add) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, add.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, add.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(sourceVariable);
  }

  @Override
  public void visitingAnd(And and) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, and.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, and.getTarget(), true);
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.and(sourceVariable));
    }
  }

  @Override
  public void visitingDec(Dec dec) {
    hereLabel();
    Object sourceVariable = 0;
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, dec.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(-1);
  }

  @Override
  public void visitingDec16(Dec16 dec) {
    hereLabel();
    Object sourceVariable = 0;
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, dec.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).sub(sourceVariable);
  }

  @Override
  public void visitingInc(Inc inc) {
    hereLabel();
    Object sourceVariable = 0;
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, inc.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(1);
  }

  @Override
  public void visitingOr(Or or) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, or.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, or.getTarget(), true);
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.or(sourceVariable));
    }
  }

  @Override
  public void visitingSub(Sub sub) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, sub.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, sub.getTarget(), true);
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.sub(sourceVariable));
    }
  }

  @Override
  public void visitingXor(Xor xor) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, xor.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, xor.getTarget(), true);
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.xor(sourceVariable));
    }
  }

  @Override
  public void visitingCp(Cp cp) {
    hereLabel();

    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, cp.getSource(), false);
    Variable a = byteCodeGenerator.registers.get(RegisterName.A.name());
    Variable targetVariable = byteCodeGenerator.registers.get(RegisterName.F.name());

    targetVariable.set(a.sub(sourceVariable));
  }

  @Override
  public void visitingRet(Ret ret) {
    hereLabel();
    Runnable runnable = () -> methodMaker.return_();
    executeConditional(byteCodeGenerator, runnable, ret.getCondition());
  }

  @Override
  public void visitingCall(Call call) {
    hereLabel();

    int jumpLabel = call.getJumpAddress().intValue();

    String labelName = createLabelName(jumpLabel);
    MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
    if (method != null) {
      Runnable runnable = () -> methodMaker.invoke(labelName);
      executeConditional(byteCodeGenerator, runnable, call.getCondition());
    }
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    hereLabel();

    int jumpLabel = conditionalInstruction.getJumpAddress().intValue();
    Label label1 = byteCodeGenerator.getLabel(jumpLabel);
    if (label1 != null) {
      Field a = byteCodeGenerator.registers.get(RegisterName.F.name());
      Condition condition = conditionalInstruction.getCondition();
      if (condition.toString().equals("NZ")) a.ifNe(0, label1);
      else if (condition.toString().equals("Z")) a.ifEq(0, label1);
      else if (condition.toString().equals("NC")) a.ifGe(0, label1);
      else if (condition.toString().equals("C")) a.ifLt(0, label1);
      else label1.goto_();
    }
  }

  @Override
  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    hereLabel();
    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, targetSourceInstruction.getSource(), false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, targetSourceInstruction.getTarget(), true);
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).set(sourceVariable);
  }

  protected void hereLabel() {
    if (label != -1)
      byteCodeGenerator.hereLabel(label);
  }

  protected <T extends WordNumber> Object getSourceVariableOf(ByteCodeGenerator byteCodeGenerator, ImmutableOpcodeReference<T> source2, boolean isTarget) {
    Object sourceVariable2 = source2.read().intValue();

    if (source2 instanceof Register) {
      sourceVariable2 = byteCodeGenerator.getField(((Register<T>) source2));
    } else if (source2 instanceof MemoryPlusRegister8BitReference<T>) {
      MemoryPlusRegister8BitReference<T> source1 = (MemoryPlusRegister8BitReference<T>) source2;
      Field field = byteCodeGenerator.getField((Register) source1.getTarget());
      if (isTarget)
        sourceVariable2 = new WriteArrayVariable(byteCodeGenerator, field, source1);
      else
        sourceVariable2 = byteCodeGenerator.memory.aget(field.add(source1.fetchRelative()));
    }
    return sourceVariable2;
  }

  protected void executeConditional(ByteCodeGenerator byteCodeGenerator, Runnable runnable, Condition condition) {
    Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
    if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
    else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
    else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
    else if (condition.toString().equals("C")) f.ifLt(0, runnable);
    else runnable.run();
  }
}
