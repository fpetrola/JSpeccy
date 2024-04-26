package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.jspeccy.FlipFLopConditionFlag;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.VirtualAssignmentInstruction;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;

public interface InstructionVisitor<T extends WordNumber> {
  void visitingTargetInstruction(TargetInstruction<T> tTargetInstruction);

  <S extends ImmutableOpcodeReference<T>> void visitingTargetSourceInstruction(TargetSourceInstruction<T, S> tsTargetSourceInstruction);

  void visitingInstruction(AbstractInstruction tAbstractInstruction);

  void visitingAdd(Add<T> tAdd);

  void visitingAdd16(Add16 tAdd16);

  void visitingAnd(And tAnd);

  boolean visitingDec(Dec dec);

  void visitingDec16(Dec16 tDec16);

  boolean visitingInc(Inc tInc);

  void visitingOr(Or tOr);

  void visitingSub(Sub tSub);

  void visitingXor(Xor tXor);

  void visitingCp(Cp tCp);

  boolean visitingRet(Ret ret);

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

  void visitingConditionAlwaysTrue(ConditionAlwaysTrue conditionAlwaysTrue);

  void visitingConditionFlag(ConditionFlag conditionFlag);

  boolean visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction);

  void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction);

  void visitingBitOperation(BitOperation tBitOperation);

  void visitingPop(Pop tPop);

  void visitingJP(JP tjp);

  void visitingVirtualAssignmentInstruction(VirtualAssignmentInstruction virtualAssignmentInstruction);

  void visitingFlag(Register<T> flag, DefaultTargetFlagInstruction targetSourceInstruction);

  void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference);

  void visitMutableOpcodeReference(MutableOpcodeReference mutableOpcodeReference);

  void visitOpcodeReference(OpcodeReference opcodeReference);

  void visitRegister(Register register);

  void visitConstantOpcodeReference(ConstantOpcodeReference<T> constantOpcodeReference);

  void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference);

  void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference);

  void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference);

  void visitEx(Ex ex);

  void visitIn(In tIn);

  void visitOut(Out tOut);

  void visitExx(Exx exx);

  default void visitNop(Nop nop) {
  }

  default void visitDI(DI tdi) {
  }

  default void visitPush(Push push) {
  }

  default void visitEI(EI ei) {
  }

  default void visitingCcf(CCF ccf) {

  }

  default void visitingScf(SCF scf) {

  }

  default void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {

  }

  default void visitRepeatingInstruction(RepeatingInstruction tRepeatingInstruction) {

  }

  default void visitLdir(Ldir tLdir) {

  }

  default void visitLddr(Lddr lddr) {

  }

  default void visitBlockInstruction(BlockInstruction blockInstruction) {

  }

  default void visitCpir(Cpir cpir) {

  }

  default void visitLdi(Ldi tLdi) {

  }

  default boolean visitingFlipFlopConditionFlag(FlipFLopConditionFlag flipFLopConditionFlag) {
    return false;
  }

  default boolean visitVirtualComposed16BitRegister(VirtualComposed16BitRegister virtualComposed16BitRegister) {
    return false;
  }
}
