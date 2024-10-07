package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class Single8BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private Variable variable;
  private Variable composedRegisterName;
  private String nibble;
  private VirtualRegister<?> register;

  public Single8BitRegisterVariable(MethodMaker methodMaker, Variable variable, Variable composedRegister, String nibble) {
    this.methodMaker = methodMaker;
    this.variable = variable;
    this.composedRegisterName = composedRegister;
    this.nibble = nibble;
  }

  @Override
  public void setRegister(VirtualRegister<?> register) {
    this.register = register;
  }

  public Variable getDelegate() {
    return variable;
    //  return methodMaker.invoke(nibble, getRealVariable(composedRegisterName));
  }

  public Variable set(Object value) {
    return variable.set(ByteCodeGenerator.getRealVariable(value));
//    value = getRealVariable(value);
//    if (nibble.equals("l"))
//      composedRegisterName.set(methodMaker.invoke("h", getRealVariable(composedRegisterName)).or(value));
//    else {
//      if (value instanceof Variable variable)
//        value = variable.shl(8);
//      else if (value instanceof Integer integer)
//        value = integer << 8;
//      composedRegisterName.set(methodMaker.invoke("l", getRealVariable(composedRegisterName)).or(value));
//    }
//    return composedRegisterName;
  }

  public Class<?> classType() {
    return int.class;
  }
}
