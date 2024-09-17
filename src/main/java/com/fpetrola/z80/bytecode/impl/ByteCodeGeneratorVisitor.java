package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import static com.fpetrola.z80.bytecode.impl.ByteCodeGenerator.createLabelName;

@SuppressWarnings("ALL")
public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  private final MethodMaker methodMaker;
  private final ByteCodeGenerator byteCodeGenerator;

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  @Override
  public void visitingPop(Pop pop) {
    if (pop instanceof SymbolicExecutionAdapter.PopReturnAddress popReturnAddress) {
      ReturnAddressWordNumber returnAddress = popReturnAddress.getReturnAddress();
      if (returnAddress != null) {
        methodMaker.invoke("incPops");
      }
    }
  }

  @Override
  public boolean visitingBit(BIT bit) {
    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    bit.getFlag().accept(instructionVisitor2);
    Variable flag = (Variable) instructionVisitor2.getResult();
    bit.accept(new VariableHandlingInstructionVisitor((s, t) -> flag.set(t.and(1 << bit.getN())), byteCodeGenerator));
    return true;
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
  public boolean visitingRlca(RLCA rlca) {
    rlca.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(methodMaker.invoke("rlc", t.get())), byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingRrca(RRCA rrca) {
    rrca.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(methodMaker.invoke("rrc", t.get())), byteCodeGenerator));
    return true;
  }

  @Override
  public void visitIn(In in) {
    in.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(methodMaker.invoke("in", s)), byteCodeGenerator));
  }

  @Override
  public boolean visitingRlc(RLC rlc) {
    rlc.accept(new VariableHandlingInstructionVisitor((s, t) -> {
      Variable variable = t.get();
      if (variable != null)
        t.set(methodMaker.invoke("rlc", variable));
      else
        System.out.println("what rlc?");
    }, byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingRl(RL rl) {
    rl.accept(new VariableHandlingInstructionVisitor((s, t) -> {
      Variable variable = t.get();
      if (variable != null)
        t.set(methodMaker.invoke("rlc", variable));
      else
        System.out.println("what rlc?");
    }, byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingRr(RR rrc) {
    rrc.accept(new VariableHandlingInstructionVisitor((s, t) -> {
      Variable variable = t.get();
      if (variable != null)
        t.set(methodMaker.invoke("rrc", variable));
    }, byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingRrc(RRC rrc) {
    rrc.accept(new VariableHandlingInstructionVisitor((s, t) -> {
      Variable variable = t.get();
      if (variable != null)
        t.set(methodMaker.invoke("rrc", variable));
    }, byteCodeGenerator));
    return true;
  }

  @Override
  public boolean visitingSra(SRA sra) {
    sra.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.shr(1)), byteCodeGenerator));
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
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.add(1).and(0xff)), byteCodeGenerator);
    inc.accept(visitor);
    processFlag(inc, visitor);
    return false;
  }

  public void visitingXor(Xor xor) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.xor(s)), byteCodeGenerator);
    xor.accept(visitor);
    processFlag(xor, visitor);
  }

  public boolean visitingCpl(CPL cpl) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.com()), byteCodeGenerator);
    cpl.accept(visitor);
    processFlag(cpl, visitor);
    return false;
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
    add16.accept(new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t, 0xffff), byteCodeGenerator));
  }

  private void getSet(Object s, Variable t, int mask) {
    if (s != null && t != null) {
      Variable value = t == s ? t.mul(2) : t.add(s);
      t.set(value.and(mask));
    }
  }

  public void visitingInc16(Inc16 inc16) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.add(1).and(0xffff)), byteCodeGenerator);
    inc16.accept(visitor);
  }

  @Override
  public void visitingDec16(Dec16 dec16) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.add(-1).and(0xffff)), byteCodeGenerator);
    dec16.accept(visitor);
  }

  public void visitingAdd(Add add) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t, 0xff), byteCodeGenerator);
    add.accept(visitor);
    processFlag(add, visitor);
  }

  @Override
  public void visitingAdc(Adc adc) { //TODO: revisar
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t, 0xff), byteCodeGenerator);
    adc.accept(visitor);
    processFlag(adc, visitor);
  }

  public void visitingSub(Sub sub) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s).and(0xff)), byteCodeGenerator);
    sub.accept(visitor);
    processFlag(sub, visitor);
  }

  @Override
  public void visitingSbc(Sbc sbc) { //TODO: revisar
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s).and(0xff)), byteCodeGenerator);
    sbc.accept(visitor);
    processFlag(sbc, visitor);
  }

  @Override
  public void visitingSbc16(Sbc16 sbc16) {
    sbc16.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s).and(0xffff)), byteCodeGenerator));
  }


  @Override
  public void visitingAdc16(Adc16 adc16) {
    adc16.accept(new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t, 0xffff), byteCodeGenerator));
  }

  public boolean visitingDec(Dec dec) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(1).and(0xff)), byteCodeGenerator);
    dec.accept(visitor);
    processFlag(dec, visitor);
    return false;
  }

  @Override
  public void visitingNeg(Neg neg) {
    neg.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.neg().and(0xff)), byteCodeGenerator));
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
        //targetVariable.set(sourceVariable);
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

  public boolean visitingRet(Ret ret) {
    createIfs(ret, () -> methodMaker.return_());
    return true;
  }

  public boolean visitingCall(Call call) {
    int jumpLabel = call.getJumpAddress().intValue();
    if (byteCodeGenerator.getMethod(jumpLabel) != null)
      createIfs(call, () -> methodMaker.invoke(createLabelName(jumpLabel)));

    return true;
  }

  private void createIfs(Instruction instruction, Runnable runnable) {
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (instruction instanceof DJNZ<?> djnz) {
      Variable result = opcodeReferenceVisitor.process((VirtualRegister) djnz.getCondition().getB());
      Variable and = result.add(-1).and(0xFF);
      result.set(and);
      result.ifNe(0, runnable);
    } else if (instruction instanceof ConditionalInstruction conditionalInstruction && conditionalInstruction.getCondition() instanceof ConditionFlag conditionFlag) {
      Variable f = opcodeReferenceVisitor.process((VirtualRegister) conditionFlag.getRegister());
      String string = conditionalInstruction.getCondition().toString();
      if (string.equals("NZ")) f.ifNe(0, runnable);
      else if (string.equals("Z")) f.ifEq(0, runnable);
      else if (string.equals("NC")) f.ifGe(0, runnable);
      else if (string.equals("C")) f.ifLt(0, runnable);
    } else
      runnable.run();
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    conditionalInstruction.calculateJumpAddress();

    int i = conditionalInstruction.getJumpAddress().intValue();
    Label label1 = byteCodeGenerator.getLabel(i);
    if (label1 != null)
      createIfs(conditionalInstruction, () -> label1.goto_());
    else {
//      byteCodeGenerator.getMethod(i);
//      createIfs(conditionalInstruction, () -> methodMaker.invoke(ByteCodeGenerator.createLabelName(i)));

      System.out.println("no lo encuentra!: " + i);
    }
  }

  @Override
  public void visitLdir(Ldir ldir) {
    callRepeatingInstruction(ldir);
  }

  @Override
  public void visitLddr(Lddr lddr) {
    callRepeatingInstruction(lddr);
  }

  @Override
  public void visitCpir(Cpir cpir) {
    callRepeatingInstruction(cpir);
  }

  @Override
  public void visitCpdr(Cpdr cpdr) {
    callRepeatingInstruction(cpdr);
  }

  private void callRepeatingInstruction(RepeatingInstruction repeatingInstruction) {
    String methodName = repeatingInstruction.getClass().getSimpleName().toLowerCase();
    methodMaker.invoke(methodName);
  }
}
