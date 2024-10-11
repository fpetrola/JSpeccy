package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class RegisterPairSpy<T extends WordNumber> extends RegisterSpy<T> implements RegisterPair<T> {

  public RegisterPairSpy(Register register, InstructionSpy spy) {
    super(register, spy);
  }

  public Register getHigh() {
    return getPair().getHigh();
  }

  private RegisterPair<T> getPair() {
    return (RegisterPair<T>) register;
  }

  public Register getLow() {
    return getPair().getLow();
  }

  public Object clone() throws CloneNotSupportedException {
    return this;
  }

  public String getName() {
    return getPair().getName();
  }
}
