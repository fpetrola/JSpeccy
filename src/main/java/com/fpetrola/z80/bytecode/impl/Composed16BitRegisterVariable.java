package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.*;

public class Composed16BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private final String name;
  private VirtualRegister<?> register;

  public Composed16BitRegisterVariable(MethodMaker methodMaker, String name) {
    this.methodMaker = methodMaker;
    this.name = name;
  }

  public Variable getDelegate() {
    return methodMaker.invoke(name);
  }

  public String name() {
    return name;
  }

  @Override
  public void setRegister(VirtualRegister<?> register) {
    this.register = register;
  }

  public Variable set(Object value) {
    return methodMaker.invoke(name, value);
  }

  public Class<?> classType() {
    return int.class;
  }
}
