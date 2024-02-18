package com.fpetrola.z80.opcodes.references;

public interface WordNumberOperation<T extends WordNumber> {
  int execute();

  int getI();

  ExecutionPoint getExecutionPoint();

  void setExecutionPoint(ExecutionPoint executionPoint);
}
