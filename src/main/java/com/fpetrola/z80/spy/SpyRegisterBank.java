package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public class SpyRegisterBank<T extends WordNumber> extends RegisterBank<T> {
  private final InstructionSpy spy;

  public SpyRegisterBank(InstructionSpy spy) {
    this.spy = spy;
  }

  protected Register<T> createRRegister() {
    return spy.wrapOpcodeRegister(super.createRRegister());
  }

  protected Register<T> createAlwaysIntegerPlain8BitRegister(RegisterName registerName) {
    return spy.wrapOpcodeRegister(super.createAlwaysIntegerPlain8BitRegister(registerName));
  }

  protected Register<T> create8BitRegister() {
    return spy.wrapOpcodeRegister(super.create8BitRegister());
  }

  protected RegisterPair<T> createComposed16BitRegister(RegisterName registerName, Register<T> h, Register<T> l) {
    return (RegisterPair<T>) spy.wrapOpcodeRegister(super.createComposed16BitRegister(registerName, h, l));
  }

  protected Register createAlwaysIntegerPlain16BitRegister(RegisterName registerName) {
    return spy.wrapOpcodeRegister(super.createAlwaysIntegerPlain16BitRegister(registerName));
  }

  protected Register<T> createPlain16BitRegister(RegisterName registerName) {
    return spy.wrapOpcodeRegister(super.createPlain16BitRegister(registerName));
  }

  protected RegisterPair createComposed16BitRegister(RegisterName registerName, RegisterName h, RegisterName l) {
    return (RegisterPair<T>) spy.wrapOpcodeRegister(super.createComposed16BitRegister(registerName, h, l));
  }
}
