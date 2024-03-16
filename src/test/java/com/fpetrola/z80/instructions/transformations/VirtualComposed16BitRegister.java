package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;

public class VirtualComposed16BitRegister<T extends WordNumber> extends Composed16BitRegister<T> implements VirtualRegister<T> {
  public VirtualComposed16BitRegister(String virtualRegisterName, VirtualRegister<T> virtualH, VirtualRegister<T> virtualL) {
    super(virtualRegisterName, virtualH, virtualL);
  }

  public void reset() {
    ((VirtualRegister)getLow()).reset();
    ((VirtualRegister)getHigh()).reset();
  }

  @Override
  public void clear() {
    ((VirtualRegister)getLow()).clear();
    ((VirtualRegister)getHigh()).clear();
  }
}
