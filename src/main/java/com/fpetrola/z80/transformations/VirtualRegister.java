package com.fpetrola.z80.transformations;

import com.fpetrola.z80.registers.Register;

public interface VirtualRegister<T> extends Register<T> {
  void reset();

  void saveData();

  T readPrevious();
}
