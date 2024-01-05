package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;

public class RegisterSpy extends Plain16BitRegister {

  protected Register register;
  protected OpcodesSpy spy;

  public RegisterSpy(Register register, OpcodesSpy spy) {
    super(register.toString());
    this.register = register;
    this.spy = spy;
  }

  public int read() {
    int value = register.read();
    spy.addReadReference(register, value);
    return value;
  }

  public void write(int value) {
    spy.addWriteReference(register, value);
    register.write(value);
  }

  public void increment(int by) {
    register.increment(by);
    spy.addWriteReference(register, register.read());
  }

  public void decrement(int by) {
    register.decrement(by);
    spy.addWriteReference(register, register.read());
  }

  public int cyclesCost() {
    return register.cyclesCost();
  }

  public int readFromRealEmulator() {
    return register.readFromRealEmulator();
  }

  public void writeToRealEmulator(int value) {
    register.writeToRealEmulator(value);
  }

  public String toString() {
    return register.toString();
  }
}
