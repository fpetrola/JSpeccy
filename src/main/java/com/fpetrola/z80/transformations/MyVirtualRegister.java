package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class MyVirtualRegister<T extends WordNumber> implements IVirtual8BitsRegister<T> {

  private final Register<T> register;

  public  MyVirtualRegister(Register<T> register) {
    this.register = register;
  }

  @Override
  public List<VirtualRegister<T>> getPreviousVersions() {
    return new ArrayList<>();
  }

  @Override
  public boolean usesMultipleVersions() {
    throw new RuntimeException("not writable");
  }

  @Override
  public void reset() {
    throw new RuntimeException("not writable");
  }

  @Override
  public void saveData() {
    throw new RuntimeException("not writable");
  }

  @Override
  public void increment() {
    throw new RuntimeException("not writable");
  }

  @Override
  public void decrement() {
    throw new RuntimeException("not writable");
  }

  @Override
  public String getName() {
    return register.getName();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    throw new RuntimeException("not writable");
  }

  @Override
  public void write(T value) {
    throw new RuntimeException("not writable");
  }

  @Override
  public T read() {
    return register.read();
  }

  @Override
  public int getLength() {
    throw new RuntimeException("not writable");
  }

  @Override
  public T readPrevious() {
    return register.read();
  }

  @Override
  public IVirtual8BitsRegister getCurrentPreviousVersion() {
    return null;
  }

  @Override
  public void addPreviousVersion(IVirtual8BitsRegister previousVersion) {

  }
}
