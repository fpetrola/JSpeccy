package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class InitialVirtualRegister<T extends WordNumber> implements IVirtual8BitsRegister<T> {

  private final Register<T> register;

  @Override
  public boolean hasNoPrevious() {
    return true;
  }

  public InitialVirtualRegister(Register<T> register) {
    this.register = register;
  }

  @Override
  public List<VirtualRegister<T>> getPreviousVersions() {
    return new ArrayList<>();
  }

  @Override
  public boolean usesMultipleVersions() {
    return false;
  }

  @Override
  public void reset() {
    throw new RuntimeException("not writable");
  }

  @Override
  public void saveData() {
 //   throw new RuntimeException("not writable");
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
  public VirtualComposed16BitRegister<T> getVirtualComposed16BitRegister() {
    return null;
  }

  @Override
  public IVirtual8BitsRegister getCurrentPreviousVersion() {
    return null;
  }

  @Override
  public void addPreviousVersion(IVirtual8BitsRegister previousVersion) {

  }

  public void set16BitsRegister(VirtualComposed16BitRegister<T> virtualComposed16BitRegister) {

  }
}
