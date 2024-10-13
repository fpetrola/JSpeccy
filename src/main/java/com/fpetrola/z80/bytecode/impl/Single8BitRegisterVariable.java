package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class Single8BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private Variable variable;
  private SmartComposed16BitRegisterVariable composedRegisterVariable;
  private String nibble;
  private final ByteCodeGenerator byteCodeGenerator;
  private VirtualRegister<?> register;

  public Single8BitRegisterVariable(MethodMaker methodMaker, Variable variable, SmartComposed16BitRegisterVariable composedRegister, String nibble, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.variable = variable;
    this.composedRegisterVariable = composedRegister;
    this.nibble = nibble;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  @Override
  public void setRegister(VirtualRegister<?> register) {
    String name = variable.name();
    if (!register.getName().startsWith(name)) {
      throw new RuntimeException("no!");
    }
    this.register = register;
  }

  public Variable getDelegate() {
    return variable;
    //  return methodMaker.invoke(nibble, getRealVariable(composedRegisterName));
  }

  public Variable set(Object value) {
    Variable result = variable.set(ByteCodeGenerator.getRealVariable(value));
    boolean noOptimization = !byteCodeGenerator.optimize16Convertion;

    if (noOptimization || byteCodeGenerator.currentRegister.getDependants().stream().anyMatch(VirtualRegister::isComposed2)) {
      Variable invoke;
      if (nibble.equals("l")) {
        invoke = byteCodeGenerator.mm.invoke("reg16high", composedRegisterVariable.get(), result);
      } else {
        invoke = byteCodeGenerator.mm.invoke("reg16low", composedRegisterVariable.get(), result);
      }
      composedRegisterVariable.directSet(invoke);
    }
    return result;
  }

  public void directSet(Variable value) {
    variable.set(value);
  }

  public Class<?> classType() {
    return int.class;
  }
}
