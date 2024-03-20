package com.fpetrola.z80.registers.flag;

import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class AluOperation {
  protected Integer8BitRegister register;
  protected BiFunction<Integer, Integer, Alu8BitResult> biFunction;
  protected TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction;

  public AluOperation(TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction, Integer8BitRegister register) {
    this.triFunction = triFunction;
    this.register = register;
    init(triFunction);
  }

  public AluOperation(BiFunction<Integer, Integer, Alu8BitResult> biFunction, Integer8BitRegister register) {
    this.biFunction = biFunction;
    this.register = register;
    init(biFunction);
  }

  protected void init(BiFunction<Integer, Integer, Alu8BitResult> biFunction) {
  }

  public void init(TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction) {
  }

  public int executeWithCarry(int regA) {
    Alu8BitResult result = biFunction.apply(regA, register.data & 0x01);
    register.data = result.flag();
    return result.ans();
  }


  public int executeWithCarry(int value, int regA) {
    Alu8BitResult result = triFunction.apply(regA, value, register.data & 0x01);
    register.data = result.flag();
    return result.ans();
  }

  public int executeWithoutCarry(int value, int regA) {
    Alu8BitResult result = triFunction.apply(regA, value, 0);
    register.data = result.flag();
    return result.ans();
  }

  public int executeWithCarry2(int value, int regA, int carry) {
    Alu8BitResult result = triFunction.apply(regA, value, carry);
    register.data = result.flag();
    return result.ans();
  }
}
