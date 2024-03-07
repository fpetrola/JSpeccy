package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import org.cojen.maker.MethodMaker;

public interface Instruction<T> {
  int execute();

  int getLength();

  void setLength(int length);

  Instruction<T> getBaseInstruction();

  T getNextPC();

  void accept(InstructionVisitor<?> visitor);
}
