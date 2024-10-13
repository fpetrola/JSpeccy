package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;

public interface IVirtual8BitsRegister<T extends WordNumber> extends VirtualRegister<T> {
  T readPrevious();

  VirtualComposed16BitRegister<T> getVirtualComposed16BitRegister();

  public IVirtual8BitsRegister<T> getCurrentPreviousVersion();

  public void addPreviousVersion(IVirtual8BitsRegister previousVersion);

  void set16BitsRegister(VirtualComposed16BitRegister<T> virtualComposed16BitRegister);

  void addDependant(VirtualRegister virtualRegister);
}
