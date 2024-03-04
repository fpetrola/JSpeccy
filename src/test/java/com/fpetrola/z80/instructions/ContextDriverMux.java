package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class ContextDriverMux<T extends WordNumber> implements ContextDriver<T> {
  private final ContextDriver<T> firstContext;
  private final ContextDriver<T> secondContext;

  public ContextDriverMux(ContextDriver<T> firstContext, ContextDriver<T> secondContext) {
    this.firstContext = firstContext;
    this.secondContext = secondContext;
  }

  @Override
  public int add(Instruction<T> instruction) {
    int add = firstContext.add(instruction);
    secondContext.add(instruction);
    return add;
  }

  @Override
  public void step() {
    firstContext.step();
    secondContext.step();
  }

  @Override
  public Register<T> r(RegisterName registerName) {
    return firstContext.r(registerName);
  }

  @Override
  public MockedMemory<T> mem() {
    return firstContext.mem();
  }

  @Override
  public FlagRegister<T> f() {
    return firstContext.f();
  }

  @Override
  public OpcodeReference iRR(Register<T> memoryReader) {
    return firstContext.iRR(memoryReader);
  }

  @Override
  public ImmutableOpcodeReference c(int value) {
    return firstContext.c(value);
  }

  @Override
  public OpcodeReference iiRR(Register<T> memoryWriter) {
    return firstContext.iiRR(memoryWriter);
  }

  @Override
  public Instruction getInstructionAt(int i) {
    return firstContext.getInstructionAt(i);
  }

  @Override
  public <T1 extends WordNumber> ChainedRegister<T1> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T1> register) {
    return firstContext.createPair(immutableOpcodeReference, register);
  }

  @Override
  public <T1 extends WordNumber> ChainedRegister<T1> pair(Register<T1> high, Register<T1> low) {
    return firstContext.pair(high, low);
  }

  @Override
  public <T1 extends WordNumber> void addUser(ChainedComposed16BitRegister<T1> result, Register<T1> high1) {
    firstContext.addUser(result, high1);
  }

  @Override
  public <T1 extends WordNumber> VirtualPlain8BitRegister<T1> cr(InstructionAdapter ia, ChainedRegister... regs) {
    return firstContext.cr(ia, regs);
  }
}
