package com.fpetrola.z80.opcodes.references;

public abstract class DefaultWordNumberOperation implements WordNumberOperation {
  protected TraceableWordNumber traceableWordNumber;
  protected int i;

  @Override
  public ExecutionPoint getExecutionPoint() {
    return executionPoint;
  }

  private ExecutionPoint executionPoint;

  public DefaultWordNumberOperation(TraceableWordNumber traceableWordNumber, int i) {
    this.traceableWordNumber = traceableWordNumber;
    this.i = i;
  }

  @Override
  public void setExecutionPoint(ExecutionPoint executionPoint) {
    this.executionPoint = executionPoint;
  }

  public abstract int execute();
}
