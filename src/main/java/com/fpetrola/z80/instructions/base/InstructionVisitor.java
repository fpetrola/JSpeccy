package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface InstructionVisitor<T extends WordNumber> {
  void visitingTargetInstruction(TargetInstruction<T> tTargetInstruction);

  <S extends ImmutableOpcodeReference<T>> void visitingTargetSourceInstruction(TargetSourceInstruction<T, S> tsTargetSourceInstruction);

  void visitingInstruction(AbstractInstruction tAbstractInstruction);

  void visitingAdd(Add<T> tAdd);

  void visitingAdd16(Add16 tAdd16);

  void visitingAnd(And tAnd);

  void visitingDec(Dec tDec);

  void visitingDec16(Dec16 tDec16);

  void visitingInc(Inc tInc);

  void visitingOr(Or tOr);

  void visitingSub(Sub tSub);

  void visitingXor(Xor tXor);

  void visitingCp(Cp tCp);

  void visitingRet(Ret ret);

  void visitingCall(Call tCall);

  void visitingConditionalInstruction(ConditionalInstruction tConditionalInstruction);

  <S extends ImmutableOpcodeReference<T>> void visitingSource(S source, TargetSourceInstruction targetSourceInstruction);

  <T extends WordNumber> void visitingTarget(OpcodeReference<T> target, TargetInstruction targetInstruction);

  void visitingInc16(Inc16 tInc16);

  void visitingSet(SET set);

  void visitingRes(RES res);

  void visitingBit(BIT bit);

  void visitingDjnz(DJNZ djnz);

  void visitingLd(Ld ld);

  void visitingRla(RLA rla);

  void visitingRl(RL rl);

  void visitingRst(RST rst);

  void visitingIm(IM im);

  void visitingJR(JR jr);
}
