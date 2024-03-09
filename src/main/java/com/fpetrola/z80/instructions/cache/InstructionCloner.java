package com.fpetrola.z80.instructions.cache;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;

public class InstructionCloner<T extends WordNumber> extends DummyInstructionVisitor<T> {
  InstructionFactory instructionFactory;
  private AbstractInstruction cloned;

  public InstructionCloner(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    instruction.accept(this);
    if (cloned == null)
      throw new RuntimeException("clone not supported for: " + instruction.getClass());
    return cloned;
  }

  public void setCloned(AbstractInstruction cloned, AbstractInstruction instruction) {
    this.cloned = cloned;
    this.cloned.setLength(instruction.getLength());
  }

  private <R extends PublicCloneable> R clone(R cloneable) {
    try {
      return (R) cloneable.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public void visitingInc16(Inc16 inc16) {
    setCloned(instructionFactory.Inc16(clone(inc16.getTarget())), inc16);
  }

  public void visitingSet(SET set) {
    setCloned(instructionFactory.SET(clone(set.getTarget()), set.getN(), set.getValueDelta()), set);
  }

  public void visitingRes(RES res) {
    setCloned(instructionFactory.RES(clone(res.getTarget()), res.getN(), res.getValueDelta()), res);
  }

  public void visitingBit(BIT bit) {
    setCloned(instructionFactory.BIT(clone(bit.getTarget()), bit.getN(), bit.getValueDelta()), bit);
  }

  public void visitingDjnz(DJNZ djnz) {
    setCloned(instructionFactory.DJNZ(djnz.getPositionOpcodeReference()), djnz);
    ((ConditionalInstruction<T>) cloned).setJumpAddress(((ConditionalInstruction<T>) djnz).getJumpAddress());
  }

  public void visitingLd(Ld ld) {
    setCloned(instructionFactory.Ld(clone(ld.getTarget()), clone(ld.getSource())), ld);
  }

  public void visitingInc(Inc inc) {
    setCloned(instructionFactory.Inc(clone(inc.getTarget())), inc);
  }

  public void visitingRla(RLA rla) {
    setCloned(instructionFactory.RLA(), rla);
  }

  public void visitingRl(RL rl) {
    setCloned(instructionFactory.RL(rl.getTarget(), rl.getValueDelta()), rl);
  }

  public void visitingRet(Ret ret) {
    setCloned(instructionFactory.Ret(ret.getCondition()), ret);
  }

  public void visitingAnd(And and) {
    setCloned(instructionFactory.And(and.getSource()), and);
  }

  public void visitingOr(Or or) {
    setCloned(instructionFactory.Or(or.getSource()), or);
  }

  public void visitingXor(Xor xor) {
    setCloned(instructionFactory.Xor(xor.getSource()), xor);
  }

  public void visitingRst(RST rst) {
    setCloned(instructionFactory.RST(rst.getP()), rst);
  }

  public void visitingIm(IM im) {
    setCloned(instructionFactory.IM(im.getMode()), im);
  }
}
