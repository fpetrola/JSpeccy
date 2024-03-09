package com.fpetrola.z80.instructions.base;

public interface Instruction<T> {
  int execute();

  int getLength();

  void accept(InstructionVisitor<?> visitor);
}
