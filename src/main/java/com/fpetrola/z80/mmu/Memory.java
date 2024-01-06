package com.fpetrola.z80.mmu;

public interface Memory {

  int read(int address);

  void write(int address, int value);
  boolean compare();

  void update();
}
