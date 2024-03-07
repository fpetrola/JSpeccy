package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;

public class DummyInstructionVisitor implements InstructionVisitor {
  @Override
  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
  }

  @Override
  public void visitingInstruction(AbstractInstruction tAbstractInstruction) {

  }

  @Override
  public void visitingAdd(Add add) {

  }

  @Override
  public void visitingAdd16(Add16 tAdd16) {

  }

  @Override
  public void visitingAnd(And tAnd) {

  }

  @Override
  public void visitingDec(Dec tDec) {

  }

  @Override
  public void visitingDec16(Dec16 tDec16) {

  }

  @Override
  public void visitingInc(Inc tInc) {

  }

  @Override
  public void visitingOr(Or tOr) {

  }

  @Override
  public void visitingSub(Sub tSub) {

  }

  @Override
  public void visitingXor(Xor tXor) {

  }

  @Override
  public void visitingCp(Cp tCp) {

  }

  @Override
  public void visitingRet(Ret ret) {

  }

  @Override
  public void visitingCall(Call tCall) {

  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
  }

  @Override
  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
  }
}
