package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;

public class VirtualComposed16BitRegister<T extends WordNumber> extends Composed16BitRegister<T> implements VirtualRegister<T> {
  private final VirtualRegister<T> virtualH;
  private final VirtualRegister<T> virtualL;

  public VirtualComposed16BitRegister(String virtualRegisterName, VirtualRegister<T> virtualH, VirtualRegister<T> virtualL) {
    super(virtualRegisterName, virtualH, virtualL);
    this.virtualH = virtualH;
    this.virtualL = virtualL;
  }

  public void reset() {
    virtualL.reset();
    virtualH.reset();
  }

  public void saveData() {
    virtualL.saveData();
    virtualH.saveData();
  }

  public T readPrevious() {
    return (virtualH.readPrevious().left(8)).or(virtualL.readPrevious());
  }
}
