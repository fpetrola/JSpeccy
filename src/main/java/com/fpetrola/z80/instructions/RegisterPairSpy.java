package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class RegisterPairSpy extends RegisterSpy implements RegisterPair {

  public RegisterPairSpy(Register register, OpcodesSpy spy) {
    super(register, spy);
  }

  public Register getHigh() {
    return getPair().getHigh();
  }

  private RegisterPair getPair() {
    return (RegisterPair) register;
  }

  public Register getLow() {
    return getPair().getLow();
  }
}
