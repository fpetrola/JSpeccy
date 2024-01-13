package com.fpetrola.z80.instructions;

public interface OpcodeReference extends Cloneable{
  int read();
  void write(int value);
  int cyclesCost();
  int getLength();
  void setOpCode(OpCode opCode);
  Object clone() throws CloneNotSupportedException;
}
