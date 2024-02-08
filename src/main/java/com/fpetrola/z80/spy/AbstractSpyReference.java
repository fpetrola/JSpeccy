package com.fpetrola.z80.spy;

public class AbstractSpyReference<T> implements SpyReference {

  public boolean indirectReference;

  public AbstractSpyReference() {
  }

  public boolean isIndirectReference() {
    return indirectReference;
  }
}