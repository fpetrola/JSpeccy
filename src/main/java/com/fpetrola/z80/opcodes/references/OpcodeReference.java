package com.fpetrola.z80.opcodes.references;

public interface OpcodeReference extends Cloneable {
  int read();

  void write(int value);

  int cyclesCost();

  int getLength();

  Object clone() throws CloneNotSupportedException;
}
