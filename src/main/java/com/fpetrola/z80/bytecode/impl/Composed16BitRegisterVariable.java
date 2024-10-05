package com.fpetrola.z80.bytecode.impl;

import org.cojen.maker.*;

public class Composed16BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private final String name;

  public Composed16BitRegisterVariable(MethodMaker methodMaker, String name) {
    this.methodMaker = methodMaker;
    this.name = name;
  }

  public Variable getDelegate() {
    return methodMaker.invoke(name);
  }

  public Variable set(Object value) {
    return methodMaker.invoke(name, value);
  }

  public Class<?> classType() {
    return int.class;
  }
}
