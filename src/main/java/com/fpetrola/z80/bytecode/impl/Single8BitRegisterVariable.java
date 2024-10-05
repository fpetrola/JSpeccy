package com.fpetrola.z80.bytecode.impl;

import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class Single8BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private final String name;
  private Variable composedRegisterName;
  private String nibble;

  public Single8BitRegisterVariable(MethodMaker methodMaker, String name, Variable composedRegister, String nibble) {
    this.methodMaker = methodMaker;
    this.name = name;
    this.composedRegisterName = composedRegister;
    this.nibble = nibble;
  }

  public Variable getDelegate() {
    return methodMaker.invoke(nibble, composedRegisterName);
  }

  public Variable set(Object value) {
    value = ByteCodeGenerator.getRealVariable(value);
    if (nibble.equals("l"))
      composedRegisterName.set(methodMaker.invoke("h", composedRegisterName).or(value));
    else {
      if (value instanceof Variable variable)
        value = variable.shl(8);
      else if (value instanceof Integer integer)
        value = integer << 8;
      composedRegisterName.set(methodMaker.invoke("l", composedRegisterName).or(value));
    }
    return composedRegisterName;
  }

  public Class<?> classType() {
    return int.class;
  }
}
