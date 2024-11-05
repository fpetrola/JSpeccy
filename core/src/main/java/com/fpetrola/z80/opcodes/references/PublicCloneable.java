package com.fpetrola.z80.opcodes.references;

public interface PublicCloneable extends Cloneable {
  Object clone() throws CloneNotSupportedException;
}
