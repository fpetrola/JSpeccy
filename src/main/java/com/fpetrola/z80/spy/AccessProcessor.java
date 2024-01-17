package com.fpetrola.z80.spy;

public interface AccessProcessor {
  public void processAccess(ExecutionStepData step, Object ar);
}
