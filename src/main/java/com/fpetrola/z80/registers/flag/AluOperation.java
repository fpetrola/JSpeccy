package com.fpetrola.z80.registers.flag;

import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class AluOperation extends TableFlagRegisterBase {
  protected Integer8BitRegister register;
  protected BiFunction<Integer, Integer, Alu8BitResult> biFunction;
  protected TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction;

  public AluOperation() {
    super("flag");
  }

  public AluOperation(TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction, Integer8BitRegister register) {
    super("flag");
    this.triFunction = triFunction;
    this.register = register;
    init(triFunction);
  }

  public AluOperation(BiFunction<Integer, Integer, Alu8BitResult> biFunction, Integer8BitRegister register) {
    super("flag");
    this.biFunction = biFunction;
    this.register = register;
    init(biFunction);
  }

  public AluOperation(Integer8BitRegister register) {
    this();
    this.register= register;
    data= 0;
    Alu8BitResult execute = execute(0, 0, 0);
    if (execute != null) {
      triFunction = (a, b, c) -> execute(a, b, c);
      init(triFunction);
    } else {
      Alu8BitResult execute2 = execute(0, 0);
      if (execute2 != null) {
        biFunction = (a, b) -> execute(a, b);
        init(biFunction);
      }
    }
  }

  public Alu8BitResult execute(int a, int value, int carry) {
    return null;
  }

  public Alu8BitResult execute(int a, int carry) {
    return null;
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
    return executeWithCarry2(value, regA, register.data & 0x01);
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
