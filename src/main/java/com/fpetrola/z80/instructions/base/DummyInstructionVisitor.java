package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.VirtualAssignmentInstruction;

public class DummyInstructionVisitor<T extends WordNumber> implements InstructionVisitor<T> {
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
  public boolean visitingDec(Dec dec) {

    return false;
  }

  @Override
  public void visitingDec16(Dec16 tDec16) {

  }

  @Override
  public boolean visitingInc(Inc tInc) {

    return false;
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
  public boolean visitingRet(Ret ret) {

    return false;
  }

  @Override
  public boolean visitingCall(Call tCall) {

    return false;
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
  }

  @Override
  public void visitingInc16(Inc16 tInc16) {

  }

  @Override
  public boolean visitingSet(SET set) {

    return false;
  }

  @Override
  public boolean visitingRes(RES res) {

    return false;
  }

  @Override
  public boolean visitingBit(BIT bit) {

    return false;
  }

  @Override
  public void visitingDjnz(DJNZ<T> djnz) {

  }

  @Override
  public void visitingLd(Ld ld) {

  }

  @Override
  public void visitingRla(RLA rla) {

  }

  @Override
  public boolean visitingRl(RL rl) {

    return false;
  }

  @Override
  public void visitingRst(RST rst) {

  }

  @Override
  public void visitingIm(IM im) {

  }

  @Override
  public void visitingJR(JR jr) {

  }

  @Override
  public void visitingConditionAlwaysTrue(ConditionAlwaysTrue conditionAlwaysTrue) {

  }

  @Override
  public void visitingConditionFlag(ConditionFlag conditionFlag) {

  }

  @Override
  public boolean visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {

    return false;
  }

  @Override
  public void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction) {

  }

  @Override
  public void visitingBitOperation(BitOperation tBitOperation) {

  }

  @Override
  public void visitingPop(Pop tPop) {

  }

  @Override
  public void visitingJP(JP tjp) {

  }

  @Override
  public void visitingVirtualAssignmentInstruction(VirtualAssignmentInstruction virtualAssignmentInstruction) {
  }

  @Override
  public void visitingFlag(Register<T> flag, DefaultTargetFlagInstruction targetSourceInstruction) {

  }

  @Override
  public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {

  }

  @Override
  public void visitMutableOpcodeReference(MutableOpcodeReference mutableOpcodeReference) {

  }

  @Override
  public void visitOpcodeReference(OpcodeReference opcodeReference) {

  }

  @Override
  public boolean visitRegister(Register register) {

    return false;
  }

  @Override
  public void visitConstantOpcodeReference(ConstantOpcodeReference<T> constantOpcodeReference) {

  }

  @Override
  public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference) {

  }

  @Override
  public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference) {

  }

  @Override
  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {

  }

  @Override
  public void visitEx(Ex ex) {
  }

  @Override
  public void visitIn(In tIn) {

  }

  @Override
  public void visitOut(Out tOut) {

  }

  @Override
  public void visitExx(Exx exx) {

  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {

  }

  @Override
  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
  }

  @Override
  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
  }
}
