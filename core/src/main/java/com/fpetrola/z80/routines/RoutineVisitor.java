package com.fpetrola.z80.routines;

import com.fpetrola.z80.instructions.base.Instruction;

public interface RoutineVisitor<R> {
  default void visit(Routine routine) {
  }

  default void visitParameter(String register) {
  }

  default void visitReturnValue(String register) {
  }

  default R getResult() {
    return null;
  }

  default void visitInstruction(int address, Instruction instruction) {
  }
}
