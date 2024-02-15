package com.fpetrola.z80.opcodes.references;

public abstract class DefaultWordNumberOperation implements WordNumberOperation {
  protected TraceableWordNumber traceableWordNumber;
  protected int i;

  public DefaultWordNumberOperation(TraceableWordNumber traceableWordNumber, int i) {
    this.traceableWordNumber = traceableWordNumber;
    this.i = i;
  }

  public abstract int execute();
}
