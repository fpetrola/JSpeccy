package com.fpetrola.z80.spy;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class RegisterSpy<T extends WordNumber> extends Plain16BitRegister<T> {

  protected Register<T> register;
  protected InstructionSpy<T> spy;
  protected List<RegisterWriteListener<T>> registerWriteListeners= new ArrayList<>();
  protected List<RegisterReadListener<T>> registerReadListeners= new ArrayList<>();

  public RegisterSpy(Register<T> register, InstructionSpy<T> spy) {
    super(register.getName());
    this.register = register;
    this.spy = spy;
  }

  public T read() {
    T value = register.read();
    registerReadListeners.forEach(l->l.readingRegister(value));

    return value;
  }

  public void write(T value) {
    registerWriteListeners.forEach(l -> l.writingRegister(value, false));
    register.write(value);
  }

  public void increment() {
    registerWriteListeners.forEach(l -> l.writingRegister(register.read().plus1(), true));
    register.increment();
  }

  public void decrement() {
    registerWriteListeners.forEach(l -> l.writingRegister(register.read().minus1(), true));
    register.decrement();
  }

  public String toString() {
    return register.toString();
  }

  public String getName() {
    return register.getName();
  }

  public Object clone() throws CloneNotSupportedException {
    return this;
  }

  public void addRegisterWriteListener(RegisterWriteListener memoryWriteListener) {
    this.registerWriteListeners.add(memoryWriteListener);
  }

  public void removeRegisterWriteListener(RegisterWriteListener memoryWriteListener) {
    this.registerWriteListeners.remove(memoryWriteListener);
  }

  public void addRegisterReadListener(RegisterReadListener memoryReadListener) {
    this.registerReadListeners.add(memoryReadListener);
  }

  public void removeRegisterReadListener(RegisterReadListener memoryReadListener) {
    this.registerReadListeners.remove(memoryReadListener);
  }
}
