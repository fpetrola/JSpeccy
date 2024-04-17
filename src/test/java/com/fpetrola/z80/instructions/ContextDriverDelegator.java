package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.old.RegisterTransformerInstructionSpy;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

import java.util.function.Supplier;

public class ContextDriverDelegator<T extends WordNumber> implements Z80ContextDriver<T> {
  protected Z80ContextDriver<T> currentContext;

  public ContextDriverDelegator(Z80ContextDriver<T> currentContext) {
    this.currentContext = currentContext;
  }

  public int add(Instruction<T> instruction) {
    return currentContext.add(instruction);
  }


  public void step() {
    currentContext.step();
  }


  public Register<T> r(RegisterName registerName) {
    return currentContext.r(registerName);
  }

  public RegisterPair<T> rp(RegisterName registerName) {
    return currentContext.rp(registerName);
  }

  public MockedMemory<T> mem() {
    return currentContext.mem();
  }

  @Override
  public MockedMemory<T> initMem(Supplier<T[]> supplier) {
    return currentContext.initMem(supplier);
  }


  public Register<T> f() {
    return currentContext.f();
  }

  @Override
  public Register<T> pc() {
    return currentContext.pc();
  }


  public OpcodeReference iRR(Register<T> memoryReader) {
    return currentContext.iRR(memoryReader);
  }

  @Override
  public OpcodeReference iRRn(Register<T> register, int plus) {
    return currentContext.iRRn(register, plus);
  }

  public ImmutableOpcodeReference<T> c(int value) {
    return currentContext.c(value);
  }

  public Condition nz() {
    return currentContext.nz();
  }

  public Condition z() {
    return currentContext.z();
  }

  public Condition t() {
    return currentContext.t();
  }


  public OpcodeReference iiRR(Register<T> memoryWriter) {
    return currentContext.iiRR(memoryWriter);
  }

  @Override
  public OpcodeReference nn(ImmutableOpcodeReference<T> r) {
    return currentContext.nn(r);
  }


  public Instruction getInstructionAt(int i) {
    return currentContext.getInstructionAt(i);
  }

  @Override
  public Instruction getTransformedInstructionAt(int i) {
    return currentContext.getTransformedInstructionAt(i);
  }

  @Override
  public RegisterTransformerInstructionSpy getRegisterTransformerInstructionSpy() {
    return currentContext.getRegisterTransformerInstructionSpy();
  }
}
