package com.fpetrola.z80.instructions.cache;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;

public class InstructionCloner<T extends WordNumber> extends DummyInstructionVisitor<T> {
  InstructionFactory instructionFactory;
  private Instruction cloned;

  public InstructionCloner(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    instruction.accept(this);
    return cloned;
  }

  public void setCloned(Instruction cloned, Instruction instruction) {
    this.cloned = cloned;
    this.cloned.setLength(instruction.getLength());
  }

  private RST cloneRst(RST rst) {
    return instructionFactory.RST(rst.getP());
  }

  private Ret cloneRet(Ret ret) {
    return instructionFactory.Ret(ret.getCondition());
  }

  private IM cloneIm(IM im) {
    return instructionFactory.IM(im.getMode());
  }

  private And cloneAnd(And and) {
    return instructionFactory.And(and.getSource());
  }

  private Xor cloneXor(Xor xor) {
    return instructionFactory.Xor(xor.getSource());
  }

  private Or cloneOr(Or or) {
    return instructionFactory.Or(or.getSource());
  }

  private <R extends PublicCloneable> R cloneRef(R inc) {
    try {
      return (R) inc.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public void visitingInc16(Inc16 inc16) {
    setCloned(cloneInc16(inc16), inc16);
  }

  private Inc16 cloneInc16(Inc16 inc16) {
    return instructionFactory.Inc16(cloneRef(inc16.getTarget()));
  }

  public void visitingSet(SET set) {
    setCloned(instructionFactory.SET(cloneRef(set.getTarget()), set.getN(), set.getValueDelta()), set);
  }

  public void visitingRes(RES res) {
    setCloned(instructionFactory.RES(cloneRef(res.getTarget()), res.getN(), res.getValueDelta()), res);
  }

  public void visitingBit(BIT bit) {
    setCloned(instructionFactory.BIT(cloneRef(bit.getTarget()), bit.getN(), bit.getValueDelta()), bit);
  }

  public void visitingDjnz(DJNZ djnz) {
    setCloned(instructionFactory.DJNZ(djnz.getPositionOpcodeReference()), djnz);
    ((ConditionalInstruction<T>) cloned).setJumpAddress(((ConditionalInstruction<T>) djnz).getJumpAddress());
  }

  public void visitingLd(Ld ld) {
    setCloned(instructionFactory.Ld(cloneRef(ld.getTarget()), cloneRef(ld.getSource())), ld);
  }

  public void visitingInc(Inc inc) {
    setCloned(instructionFactory.Inc(cloneRef(inc.getTarget())), inc);
  }

  public void visitingRla(RLA rla) {
    setCloned(instructionFactory.RLA(), rla);
  }

  public void visitingRl(RL rl) {
    setCloned(instructionFactory.RL(rl.getTarget(), rl.getValueDelta()), rl);
  }
}
