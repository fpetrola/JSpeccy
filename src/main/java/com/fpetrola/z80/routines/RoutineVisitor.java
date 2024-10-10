package com.fpetrola.z80.routines;

public interface RoutineVisitor<R> {
  default void visit(Routine routine) {
  }

  default void visitParameter(String register) {
  }

  default void visitReturnValue(String register) {
  }

  R getResult();
}
