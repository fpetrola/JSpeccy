package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.List;

public class SmartComposed16BitRegisterVariable implements VariableDelegator {
  private final MethodMaker methodMaker;
  private final String name;
  private final Variable variable;
  private final ByteCodeGenerator byteCodeGenerator;
  private VirtualRegister<?> register;

  public SmartComposed16BitRegisterVariable(MethodMaker methodMaker, String name, Variable variable, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.name = name;
    this.variable = variable;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  @Override
  public void setRegister(VirtualRegister<?> register) {
    this.register = register;
  }

  public Variable getDelegate() {
    VirtualRegister<?> register1 = byteCodeGenerator.currentRegister;
    List<? extends VirtualRegister<?>> previousVersions = register1.getPreviousVersions();
    if (previousVersions.stream().anyMatch(VirtualRegister::isMixRegister)) {
      Variable high = byteCodeGenerator.variables.get(name.charAt(0) + "");
      Variable low = byteCodeGenerator.variables.get(name.charAt(1) + "");
      if (high == null || low == null)
        return variable;
      else
        return high.shl(8).or(ByteCodeGenerator.getRealVariable(low));
    }
    return variable;
  }

  public String name() {
    return name;
  }

//  public Variable set(Object value) {
//    return methodMaker.invoke(name, value);
//  }

  public Class<?> classType() {
    return int.class;
  }
}
