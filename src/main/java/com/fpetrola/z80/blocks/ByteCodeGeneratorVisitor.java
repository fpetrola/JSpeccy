package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.jspeccy.FlipFLopConditionFlag;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  static Map<String, Variable> initializers = new HashMap<>();
  private final MethodMaker methodMaker;
  private final ByteCodeGenerator byteCodeGenerator;
  public static Map<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>> commonRegisters = new HashMap<>();

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  @Override
  public void visitingBit(BIT bit) {
    bit.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.and(bit.getN())), byteCodeGenerator));
  }

  @Override
  public boolean visitingSet(SET set) {
    set.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.or(1 << set.getN())), byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingRes(RES res) {
    res.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.and(~(1 << res.getN()))), byteCodeGenerator));
    return true;
  }

  @Override
  public void visitingBitOperation(BitOperation bit) {
//    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.and(bit.getN())), byteCodeGenerator);
//    bit.accept(visitor);
//    processFlag(bit, visitor);

    if (bit instanceof BIT<?>) {
      OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(true, byteCodeGenerator);
      bit.getFlag().accept(instructionVisitor2);
      Variable flag = (Variable) instructionVisitor2.getResult();
      bit.accept(new VariableHandlingInstructionVisitor((s, t) -> flag.set(t.and(1 << bit.getN())), byteCodeGenerator));
    }
//    tBitOperation.accept(new VariableHandlingInstructionVisitor((s, t) -> t.and(tBitOperation.getN()), byteCodeGenerator));
  }

  public boolean visitingInc(Inc inc) {
    inc.accept(new VariableHandlingInstructionVisitor((s, t) -> t.inc(1), byteCodeGenerator));
    return false;
  }

  public void visitingXor(Xor xor) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.xor(s)), byteCodeGenerator);
    xor.accept(visitor);
    processFlag(xor, visitor);
  }

  @Override
  public void visitingOr(Or or) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.or(s)), byteCodeGenerator);
    or.accept(visitor);
    processFlag(or, visitor);
  }

  @Override
  public void visitingAnd(And and) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.and(s)), byteCodeGenerator);
    and.accept(visitor);
    processFlag(and, visitor);
  }

  public void visitingAdd16(Add16 add16) {
    add16.accept(new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t), byteCodeGenerator));
  }

  private void getSet(Object s, Variable t) {
    if (s != null && t != null)
      t.set(t == s ? t.mul(2) : t.add(s));
  }

  public void visitingInc16(Inc16 inc16) {
    inc16.accept(new VariableHandlingInstructionVisitor((s, t) -> t.inc(1), byteCodeGenerator));
  }

  public void visitingAdd(Add add) {
    add.accept(new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t), byteCodeGenerator));
  }

  public void visitingSub(Sub sub) {
    sub.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s)), byteCodeGenerator));
  }

  public boolean visitingDec(Dec dec) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.inc(-1), byteCodeGenerator);
    dec.accept(visitor);

//    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(false, byteCodeGenerator);
//
//    dec.getTarget().accept(instructionVisitor2);
//    Variable a = (Variable) instructionVisitor2.getResult();
//
//    dec.getFlag().accept(instructionVisitor2);
//    Variable flag = (Variable) instructionVisitor2.getResult();
//
//    flag.set(a.sub(1));


    processFlag(dec, visitor);
    return false;
  }

  private void processFlag(DefaultTargetFlagInstruction targetFlagInstruction, VariableHandlingInstructionVisitor visitor) {
    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    targetFlagInstruction.getFlag().accept(instructionVisitor2);
    targetFlagInstruction.accept(new VariableHandlingInstructionVisitor((s, t) -> {
      if (!(visitor.targetVariable instanceof WriteArrayVariable))
        ((Variable) instructionVisitor2.getResult()).set(visitor.targetVariable);
    }, byteCodeGenerator));
  }

  public void visitingLd(Ld ld) {
    ld.accept(new VariableHandlingInstructionVisitor((sourceVariable, targetVariable) -> {
      Class<?> aClass = targetVariable.classType();
      if (!aClass.equals(int.class))
        targetVariable.aset(0, sourceVariable);
      else if (targetVariable instanceof WriteArrayVariable writeArrayVariable)
        writeArrayVariable.set(sourceVariable);
      else if (sourceVariable instanceof Variable variable) {
        targetVariable.set(sourceVariable);
      }
    }, byteCodeGenerator) {
      public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
        super.visitingSource(source, targetSourceInstruction);
        createInitializer = (_) -> sourceVariable;
      }
    });
  }


  public void visitingCp(Cp cp) {
    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(true, byteCodeGenerator);
//    cp.getSource().accept(instructionVisitor2);
//    Object sourceVariable = instructionVisitor2.getResult();
//
//    cp.getTarget().accept(instructionVisitor2);
//    Variable a = (Variable) instructionVisitor2.getResult();

    cp.getFlag().accept(instructionVisitor2);
    Variable flag = (Variable) instructionVisitor2.getResult();

//    flag.set(a.sub(sourceVariable));

    cp.accept(new VariableHandlingInstructionVisitor((s, t) -> flag.set(t.sub(s)), byteCodeGenerator));

//    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.sub(s), byteCodeGenerator);
//    cp.accept(visitor);
//    processFlag(cp, visitor);
  }

  public boolean visitingRet(Ret conditionalInstruction) {
    Variable f = getVariable(conditionalInstruction);
    Condition condition = conditionalInstruction.getCondition();
    String string = condition.toString().replace("FlipFlop: ", "");
    if (string.contains("NZ")) f.ifNe(0, () -> methodMaker.return_());
    else if (string.equals("Z")) {
      f.ifEq(0, () -> methodMaker.return_());
    } else if (string.equals("NC")) f.ifGe(0, () -> methodMaker.return_());
    else if (string.equals("C")) f.ifLt(0, () -> methodMaker.return_());
    else
      methodMaker.return_();

    return true;
  }

  public void visitingCall(Call call) {
    int jumpLabel = call.getJumpAddress().intValue();

    String labelName = ByteCodeGenerator.createLabelName(jumpLabel);
    MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
    if (method != null) {
      Runnable runnable = () -> methodMaker.invoke(labelName);
      Condition condition = call.getCondition();
      Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
      if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
      else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
      else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
      else if (condition.toString().equals("C")) f.ifLt(0, runnable);
      else runnable.run();
    }
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    conditionalInstruction.calculateJumpAddress();

    int jumpLabel = conditionalInstruction.getJumpAddress().intValue();
    Label label1 = byteCodeGenerator.getLabel(jumpLabel);
    if (label1 != null) {
      Variable f = getVariable(conditionalInstruction);
      if (f == null)
        label1.goto_();
      else {
        Condition condition = conditionalInstruction.getCondition();
        String string = condition.toString().replace("FlipFlop: ", "");
        if (string.contains("NZ") || conditionalInstruction instanceof DJNZ) f.ifNe(0, label1);
        else if (string.equals("Z")) {
          f.ifEq(0, label1);
        } else if (string.equals("NC")) f.ifGe(0, label1);
        else if (string.equals("C")) f.ifLt(0, label1);
        else
          label1.goto_();
      }
    }
  }

  private <T> Variable getVariable(ConditionalInstruction conditionalInstruction) {
    VirtualRegister<T> register = null;
    if (conditionalInstruction instanceof DJNZ<?> djnz) {
      register = (VirtualRegister) djnz.getCondition().getB();
    } else if (!(conditionalInstruction.getCondition() instanceof ConditionAlwaysTrue)) {
      ConditionFlag condition1 = (ConditionFlag) conditionalInstruction.getCondition();
      register = (VirtualRegister) condition1.getRegister();
    }

    if (register == null)
      return null;

    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    register.accept(opcodeReferenceVisitor);
    Object sourceVariable = opcodeReferenceVisitor.getResult();

    Variable f = (Variable) sourceVariable;

    if (conditionalInstruction instanceof DJNZ djnz)
      f.inc(-1);

    Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> fromCommonRegisters = VariableHandlingInstructionVisitor.getFromCommonRegisters(f);
    VirtualRegister<WordNumber> s = fromCommonRegisters.isEmpty() ? null : fromCommonRegisters.get().getValue();
    if (s != null) {
      if (!ByteCodeGenerator.getRegisterName(s).equals(f.name())) {
        byteCodeGenerator.getExistingVariable(s).set(f);
      }
    }
    return f;
  }
}
