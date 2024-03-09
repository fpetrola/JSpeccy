package com.fpetrola.z80.instructions.base;

public interface JumpInstruction<T> {
  T getNextPC();
}
