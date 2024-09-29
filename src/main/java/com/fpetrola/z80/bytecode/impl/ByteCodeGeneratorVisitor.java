package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.List;
import java.util.function.Supplier;

import static com.fpetrola.z80.bytecode.impl.ByteCodeGenerator.createLabelName;

@SuppressWarnings("ALL")
public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  private final MethodMaker methodMaker;
  private final ByteCodeGenerator byteCodeGenerator;
  private final int address;
  public PendingFlagUpdate pendingFlag;
  public PendingFlagUpdate previousPendingFlag;
  public boolean incPopsAdded;

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator, int address, PendingFlagUpdate previousPendingFlag) {
    this.methodMaker = methodMaker;
    this.byteCodeGenerator = byteCodeGenerator;
    this.address = address;
    this.previousPendingFlag = previousPendingFlag;
  }

  @Override
  public void visitPush(Push push) {
    VirtualRegister<?> target = (VirtualRegister<?>) push.getTarget();
    // VirtualRegister top = byteCodeGenerator.getTop(target);
    //Variable var = methodMaker.var(int.class);
    //var.name("last_" + top.getName());
    //var.set(byteCodeGenerator.getExistingVariable(target).get());
    methodMaker.invoke("push", byteCodeGenerator.getExistingVariable(target).get());
  }

  @Override
  public void visitingPop(Pop pop) {
    VirtualRegister<?> target = (VirtualRegister<?>) pop.getTarget();
//    VirtualRegister top = byteCodeGenerator.getTop(target);
//    Variable var = methodMaker.var(int.class);
//    var.name("last_" + top.getName());
//    byteCodeGenerator.getExistingVariable(target).get().set(var);
    byteCodeGenerator.getExistingVariable(target).set(methodMaker.invoke("pop"));

//    if (pop instanceof SymbolicExecutionAdapter.PopReturnAddress popReturnAddress) {
//      ReturnAddressWordNumber returnAddress = popReturnAddress.getReturnAddress();
//      if (returnAddress != null) {
//        methodMaker.invoke("incPops");
//      }
//    }
  }

  @Override
  public void visitEx(Ex ex) {
    VirtualRegister<?> source = (VirtualRegister<?>) ex.getSource();
    VirtualRegister<?> target = (VirtualRegister<?>) ex.getTarget();
    // VirtualRegister top = byteCodeGenerator.getTop(target);
    //Variable var = methodMaker.var(int.class);
    //var.name("last_" + top.getName());
    //var.set(byteCodeGenerator.getExistingVariable(target).get());
    if (byteCodeGenerator.getTop(source).getName().startsWith("AF"))
      methodMaker.invoke("exAF");
    else
      methodMaker.invoke("exHLDE");
  }

  @Override
  public boolean visitingBit(BIT bit) {
    bit.accept(new VariableHandlingInstructionVisitor((s, t) -> processFlag(bit, () -> t.and(1 << bit.getN())), byteCodeGenerator));
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
    in.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(methodMaker.invoke("in", WriteArrayVariable.getRealVariable(s))), byteCodeGenerator));
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
        t.set(methodMaker.invoke("rl", variable));
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
    processFlag(inc, () -> visitor.targetVariable);
    return false;
  }

  public void visitingXor(Xor xor) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.xor(s)), byteCodeGenerator);
    xor.accept(visitor);
    processFlag(xor, () -> visitor.targetVariable);
  }

  public boolean visitingCpl(CPL cpl) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.com()), byteCodeGenerator);
    cpl.accept(visitor);
    processFlag(cpl, () -> visitor.targetVariable);
    return false;
  }

  @Override
  public void visitingOr(Or or) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.or(s)), byteCodeGenerator);
    or.accept(visitor);
    processFlag(or, () -> visitor.targetVariable);
  }

  @Override
  public void visitingAnd(And and) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.and(s)), byteCodeGenerator);
    and.accept(visitor);
    processFlag(and, () -> visitor.targetVariable);
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
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(1).and(0xffff)), byteCodeGenerator);
    dec16.accept(visitor);
  }

  public void visitingAdd(Add add) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> getSet(s, t, 0xff), byteCodeGenerator);
    add.accept(visitor);
    processFlag(add, () -> visitor.targetVariable);
  }

  @Override
  public void visitingAdc(Adc adc) { //TODO: revisar
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.add(s).add(methodMaker.invoke("carry").and(255))), byteCodeGenerator);
    adc.accept(visitor);
    processFlag(adc, () -> visitor.targetVariable);
  }

  public void visitingSub(Sub sub) {
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s)), byteCodeGenerator);
    sub.accept(visitor);
    processFlag(sub, () -> visitor.targetVariable);
  }

  @Override
  public void visitingSbc(Sbc sbc) { //TODO: revisar
    VariableHandlingInstructionVisitor visitor = new VariableHandlingInstructionVisitor((s, t) -> t.set(t.sub(s).and(0xff)), byteCodeGenerator);
    sbc.accept(visitor);
    processFlag(sbc, () -> visitor.targetVariable);
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
    processFlag(dec, () -> visitor.targetVariable);
    return false;
  }

  @Override
  public void visitingNeg(Neg neg) {
    neg.accept(new VariableHandlingInstructionVisitor((s, t) -> t.set(t.neg().and(0xff)), byteCodeGenerator));
  }

  private void processFlag(DefaultTargetFlagInstruction targetFlagInstruction, Supplier<Object> targetVariable) {
    pendingFlag = new PendingFlagUpdate(targetVariable, targetFlagInstruction, byteCodeGenerator);
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
    cp.accept(new VariableHandlingInstructionVisitor((s, t) -> processFlag(cp, () -> t.sub(s)), byteCodeGenerator));
  }

  public boolean visitingRet(Ret ret) {
    createIfs(ret, () -> doReturn());
    return true;
  }

  private void doReturn() {
    methodMaker.return_();
  }

  public boolean visitingCall(Call call) {
    int jumpLabel = call.getJumpAddress().intValue();
    if (byteCodeGenerator.getMethod(jumpLabel) != null)
      createIfs(call, () -> {
        methodMaker.invoke(createLabelName(jumpLabel));
        Variable nextAddress = byteCodeGenerator.getField("nextAddress");
        List<Integer> i = byteCodeGenerator.routine.returnPoints.get(address).stream().toList();
        i.forEach(ga -> nextAddress.ifEq(ga, () -> {
          nextAddress.set(0);
          Label label1 = byteCodeGenerator.getLabel(ga);
          if (label1 != null)
            label1.goto_();
        }));
      });

    return true;
  }

  private void createIfs(Instruction instruction, Runnable runnable) {
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (instruction instanceof DJNZ<?> djnz) {
      Variable result = opcodeReferenceVisitor.process((VirtualRegister) djnz.getCondition().getB());
      Variable and = result.sub(1).and(0xFF);
      result.set(and);
      result.ifNe(0, runnable);
    } else if (instruction instanceof ConditionalInstruction conditionalInstruction && conditionalInstruction.getCondition() instanceof ConditionFlag conditionFlag) {
      Variable f = opcodeReferenceVisitor.process((VirtualRegister) conditionFlag.getRegister());
      String string = conditionalInstruction.getCondition().toString();
      Object source = 0;
      Variable targetVariable = f;
      if (previousPendingFlag != null) {
        DefaultTargetFlagInstruction targetFlagInstruction = previousPendingFlag.targetFlagInstruction;
        if (targetFlagInstruction instanceof Cp<?> cp) {
          ImmutableOpcodeReference<WordNumber> source1 = (ImmutableOpcodeReference<WordNumber>) cp.getSource();
          OpcodeReferenceVisitor opcodeReferenceVisitor2 = new OpcodeReferenceVisitor(false, byteCodeGenerator);
          source1.accept(opcodeReferenceVisitor2);
          source = opcodeReferenceVisitor2.getResult();
          OpcodeReferenceVisitor<WordNumber> variableAdapter = new OpcodeReferenceVisitor<>(true, byteCodeGenerator);
          targetFlagInstruction.getTarget().accept(variableAdapter);
          targetVariable = (Variable) variableAdapter.getResult();
        } else {
          targetVariable = (Variable) previousPendingFlag.targetVariableSupplier.get();
          source = 0;
        }
      }
      executeCondition(runnable, string, targetVariable, source);
    } else {
      if (previousPendingFlag != null)
        previousPendingFlag.update();
      runnable.run();
    }
  }

  private void executeCondition(Runnable runnable, String conditionString, Variable target, Object source) {
    if (conditionString.equals("NZ")) target.ifNe(source, runnable);
    else if (conditionString.equals("Z")) target.ifEq(source, runnable);
    else if (conditionString.equals("NC")) target.ifGe(source, runnable);
    else if (conditionString.equals("C")) target.ifLt(source, runnable);
    else if (conditionString.equals("NS")) target.ifGe(source, runnable);
    else if (conditionString.equals("S")) target.ifLt(source, runnable);
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
      createIfs(conditionalInstruction, () -> {
        if (byteCodeGenerator.routine.virtualPop.containsKey(address)) {
          methodMaker.invoke("incPops");
          byteCodeGenerator.getField("nextAddress").set(byteCodeGenerator.routine.virtualPop.get(address) + 1);
          incPopsAdded = true;
        }
        doReturn();
      });

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
