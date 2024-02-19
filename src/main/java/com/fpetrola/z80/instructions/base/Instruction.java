package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.InstructionSpy;
import org.cojen.maker.MethodMaker;

public interface Instruction<T> {
  int execute();

  int getLength();

  void setLength(int length);

  Instruction<T> getBaseInstruction();

  void setSpy(InstructionSpy spy);

  T getNextPC();

  State getState();

  int getJumpLabel();

  int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator);
}
