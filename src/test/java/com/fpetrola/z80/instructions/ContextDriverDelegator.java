package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class ContextDriverDelegator<T extends WordNumber> implements ContextDriver<T> {
  protected ContextDriver<T> currentContext;


  public int add(Instruction<T> instruction) {
    return currentContext.add(instruction);
  }


  public void step() {
    currentContext.step();
  }


  public Register<T> r(RegisterName registerName) {
    return currentContext.r(registerName);
  }


  public MockedMemory<T> mem() {
    return currentContext.mem();
  }


  public FlagRegister<T> f() {
    return currentContext.f();
  }


  public OpcodeReference iRR(Register<T> memoryReader) {
    return currentContext.iRR(memoryReader);
  }


  public ImmutableOpcodeReference c(int value) {
    return currentContext.c(value);
  }


  public OpcodeReference iiRR(Register<T> memoryWriter) {
    return currentContext.iiRR(memoryWriter);
  }


  public Instruction getInstructionAt(int i) {
    return currentContext.getInstructionAt(i);
  }


  public <T1 extends WordNumber> ChainedRegister<T1> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T1> register) {
    return currentContext.createPair(immutableOpcodeReference, register);
  }

  public <T1 extends WordNumber> ChainedRegister<T1> pair(Register<T1> high, Register<T1> low) {
    return currentContext.pair(high, low);
  }

  public <T1 extends WordNumber> void addUser(ChainedComposed16BitRegister<T1> result, Register<T1> high1) {
    currentContext.addUser(result, high1);
  }

  public <T1 extends WordNumber> VirtualPlain8BitRegister<T1> cr(InstructionAdapter ia, ChainedRegister... regs) {
    return currentContext.cr(ia, regs);
  }
}
