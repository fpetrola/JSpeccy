package com.fpetrola.z80.spy;

import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class RegisterSpy extends Plain16BitRegister {

  protected Register register;
  protected InstructionSpy spy;

  public RegisterSpy(Register register, InstructionSpy spy) {
    super(register.getName());
    this.register = register;
    this.spy = spy;
  }

  public int read() {
    int value = register.read();
    if (spy.isCapturing())
      spy.addReadReference(register.getName(), value);
    return value;
  }

  public void write(int value) {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), value, false);
    register.write(value);
  }

  public void increment(int by) {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), register.read() + 1, true);
    register.increment(by);
  }

  public void decrement(int by) {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), register.read() - 1, true);
    register.decrement(by);
  }

  public int cyclesCost() {
    return register.cyclesCost();
  }

  public String toString() {
    return register.toString();
  }

  public RegisterName getName() {
    return register.getName();
  }

  public Object clone() throws CloneNotSupportedException {
    return this;
  }
}
