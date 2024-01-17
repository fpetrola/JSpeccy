package com.fpetrola.z80.spy;

public interface AccessProcessor {
  public boolean processAccess(ExecutionStepData step, Object ar);
}
