package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.*;
import com.fpetrola.z80.registers.flag.FlagProxyFactory;
import com.fpetrola.z80.registers.flag.TableFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;

public class SpyRegisterBankFactory<T extends WordNumber> extends DefaultRegisterBankFactory<T> {
  private InstructionSpy spy;

  public SpyRegisterBankFactory(InstructionSpy spy) {
    this.spy = spy;
  }

  protected Register<T> createRRegister() {
    return spy.wrapRegister(super.createRRegister());
  }

  protected Register<T> createAlwaysIntegerPlain8BitRegister(RegisterName registerName) {
    return spy.wrapRegister(super.createAlwaysIntegerPlain8BitRegister(registerName));
  }

  protected Register<T> create8BitRegister(RegisterName registerName) {
    return spy.wrapRegister(super.create8BitRegister(registerName));
  }

  protected RegisterPair<T> createComposed16BitRegister(RegisterName registerName, Register<T> h, Register<T> l) {
    return (RegisterPair<T>) spy.wrapRegister(super.createComposed16BitRegister(registerName, h, l));
  }

  protected Register createAlwaysIntegerPlain16BitRegister(RegisterName registerName) {
    return spy.wrapRegister(super.createAlwaysIntegerPlain16BitRegister(registerName));
  }

  protected Register<T> createPlain16BitRegister(RegisterName registerName) {
    return spy.wrapRegister(super.createPlain16BitRegister(registerName));
  }

  protected RegisterPair createComposed16BitRegister(RegisterName registerName, RegisterName h, RegisterName l) {
    return (RegisterPair<T>) spy.wrapRegister(super.createComposed16BitRegister(registerName, h, l));
  }

  protected Register createFlagRegister() {
    return spy.wrapRegister(new FlagProxyFactory().createFlagRegisterProxy(new TableFlagRegister(F)));
  }
}
