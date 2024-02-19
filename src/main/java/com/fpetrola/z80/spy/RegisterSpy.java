package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class RegisterSpy<T extends WordNumber> extends Plain16BitRegister<T> {

  protected Register<T> register;
  protected InstructionSpy<T> spy;

  public RegisterSpy(Register<T> register, InstructionSpy<T> spy) {
    super(register.getName());
    this.register = register;
    this.spy = spy;
  }

  public T read() {
    T value = register.read();
    if (spy.isCapturing())
      spy.addReadReference(register.getName(), value);
    return value;
  }

  public void write(T value) {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), value, false);
    register.write(value);
  }

  public void increment() {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), register.read().plus1(), true);
    register.increment();
  }

  public void decrement() {
    if (spy.isCapturing())
      spy.addWriteReference(register.getName(), register.read().minus1(), true);
    register.decrement();
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
