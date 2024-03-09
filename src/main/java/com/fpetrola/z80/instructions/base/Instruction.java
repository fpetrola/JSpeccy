package com.fpetrola.z80.instructions.base;

public interface Instruction<T> {
  int execute();

  int getLength();

  T getNextPC();

  void accept(InstructionVisitor<?> visitor);
}
