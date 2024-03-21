package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class AluOperation extends AluOperationBase {
  protected BiFunction<Integer, Integer, AluResult> biFunction;
  protected TriFunction<Integer, Integer, Integer, AluResult> triFunction;

  public AluOperation() {
    super("flag");
    data = 0;
    AluResult execute = execute(0, 0, 0);
    if (execute != null) {
      triFunction = (a, b, c) -> execute(a, b, c);
      init(triFunction);
    } else {
      AluResult execute2 = execute(0, 0);
      if (execute2 != null) {
        biFunction = (a, b) -> execute(a, b);
        init(biFunction);
      }
    }
  }

  public AluResult execute(int a, int value, int carry) {
    return null;
  }

  public AluResult execute(int a, int carry) {
    return null;
  }

  protected void init(BiFunction<Integer, Integer, AluResult> biFunction) {
  }

  public void init(TriFunction<Integer, Integer, Integer, AluResult> triFunction) {
  }

  public <T extends WordNumber> T executeWithCarry(T value, T regA, Register<T> register1) {
    return executeWithCarry2(value, regA, register1.read().intValue() & 0x01, register1);
  }

  public <T extends WordNumber> T executeWithCarry2(T value, T regA, int carry, Register<T> register1) {
    AluResult result = triFunction.apply(regA.intValue(), value.intValue(), carry);
    register1.write(WordNumber.createValue(result.flag()));
    return WordNumber.createValue(result.value());
  }

  public <T extends WordNumber> T executeWithoutCarry(T value, T regA, Register<T> register) {
    AluResult result = triFunction.apply(regA.intValue(), value.intValue(), 0);
    register.write(WordNumber.createValue(result.flag()));
    return WordNumber.createValue(result.value());
  }
}
