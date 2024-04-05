package com.fpetrola.z80.transformations;

import com.fpetrola.z80.registers.Register;

import java.util.List;

public interface VirtualRegister<T> extends Register<T> {
  List<VirtualRegister<T>> getPreviousVersions();

  boolean usesMultipleVersions();

  void reset();

  void saveData();
}
