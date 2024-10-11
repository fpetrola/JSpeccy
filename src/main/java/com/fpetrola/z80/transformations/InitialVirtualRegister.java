package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

public class InitialVirtualRegister<T extends WordNumber> implements IVirtual8BitsRegister<T> {

  private final Register<T> register;
  private final VirtualRegisterVersionHandler versionHandler;
  private Scope scope= new Scope(0, 0);
  private VirtualComposed16BitRegister<T> virtualComposed16BitRegister;

  @Override
  public int getRegisterLine() {
    return 0;
  }

  @Override
  public boolean hasNoPrevious() {
    return true;
  }

  @Override
  public int getAddress() {
    return 0;
  }

  @Override
  public Scope getScope() {
    return scope;
  }

  @Override
  public List<VirtualRegister<T>> getDependants() {
    return null;
  }

  @Override
  public VirtualRegisterVersionHandler getVersionHandler() {
    return versionHandler;
  }

  public InitialVirtualRegister(Register<T> register, VirtualRegisterVersionHandler versionHandler) {
    this.register = register;
    this.versionHandler = versionHandler;
    register.write(WordNumber.createValue(65535));
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
  public IVirtual8BitsRegister getCurrentPreviousVersion() {
    return null;
  }

  @Override
  public void addPreviousVersion(IVirtual8BitsRegister previousVersion) {

  }

  @Override
  public void addDependant(VirtualRegister virtualRegister) {

  }

  @Override
  public void set16BitsRegister(VirtualComposed16BitRegister<T> virtualComposed16BitRegister) {
    if (this.virtualComposed16BitRegister == null)
      this.virtualComposed16BitRegister = virtualComposed16BitRegister;
  }

  @Override
  public VirtualComposed16BitRegister<T> getVirtualComposed16BitRegister() {
    return virtualComposed16BitRegister;
  }
}
