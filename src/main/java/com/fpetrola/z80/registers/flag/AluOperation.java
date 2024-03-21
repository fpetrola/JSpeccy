package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class AluOperation extends TableFlagRegisterBase {
  protected Integer8BitRegister register;
  protected BiFunction<Integer, Integer, Alu8BitResult> biFunction;
  protected TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction;

  public AluOperation(BiFunction<Integer, Integer, Alu8BitResult> biFunction, Integer8BitRegister register) {
    super("flag");
    this.biFunction = biFunction;
    this.register = register;
    init(biFunction);
  }

  public AluOperation() {
    super("flag");
    this.register = this;
    data = 0;
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

  public int executeWithCarry(int regA, Integer8BitRegister register1) {
    Alu8BitResult result = biFunction.apply(regA, register1.data & 0x01);
    register1.data = result.flag();
    return result.ans();
  }

  public int executeWithCarry(int value, int regA, Integer8BitRegister register1) {
    return executeWithCarry2(value, regA, register1.data & 0x01, register1);
  }

  public <T extends WordNumber> T executeWithCarry(T value, T regA, Register<T> register1) {
    return executeWithCarry2(value, regA, register1.read().and(0x01), register1);
  }


  public int executeWithoutCarry(int value, int regA, Integer8BitRegister register1) {
    Alu8BitResult result = triFunction.apply(regA, value, 0);
    register.data = result.flag();
    return result.ans();
  }


  public int executeWithCarry2(int value, int regA, int carry, Integer8BitRegister register1) {
    Alu8BitResult result = triFunction.apply(regA, value, carry);
    register1.data = result.flag();
    return result.ans();
  }

  public <T extends WordNumber> T executeWithCarry2(T value, T regA, T carry, Register<T> register1) {
    Alu8BitResult result = triFunction.apply(regA.intValue(), value.intValue(), carry.intValue());
    register1.write(WordNumber.createValue(result.flag()));
    return WordNumber.createValue(result.ans());
  }

  public <T extends WordNumber> T executeWithoutCarry(T value, T regA, Register<T> register) {
    Alu8BitResult result = triFunction.apply(regA.intValue(), value.intValue(), 0);
    register.write(WordNumber.createValue(result.flag()));
    return WordNumber.createValue(result.ans());
  }
}
