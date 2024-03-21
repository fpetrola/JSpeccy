package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class AluOperation extends AluOperationBase {
  protected BiFunction<Integer, Integer, Integer> biFunction;
  protected TriFunction<Integer, Integer, Integer, Integer> triFunction;

  public AluOperation() {
    super("flag");
    data = 0;
    if (execute(0, 0, 0) != -1) {
      triFunction = (a, b, c) -> execute(a, b, c);
      init(triFunction);
    } else if (execute(0, 0) != -1) {
      biFunction = (a, b) -> execute(a, b);
      init(biFunction);
    }
  }

  public int execute(int a, int value, int carry) {
    return -1;
  }

  public int execute(int a, int carry) {
    return -1;
  }

  protected void init(BiFunction<Integer, Integer, Integer> biFunction) {
  }

  public void init(TriFunction<Integer, Integer, Integer, Integer> triFunction) {
  }

  public <T extends WordNumber> T executeWithCarry(T regA, Register<T> flag) {
    Integer result = biFunction.apply(regA.intValue(), flag.read().intValue() & 0x01);
    flag.write(WordNumber.createValue(data));
    return WordNumber.createValue(result);
  }

  public <T extends WordNumber> T executeWithCarry(T value, T regA, Register<T> flag) {
    return executeWithCarry2(value, regA, flag.read().intValue() & 0x01, flag);
  }

  public <T extends WordNumber> T executeWithCarry2(T value, T regA, int carry, Register<T> flag) {
    Integer result = triFunction.apply(regA.intValue(), value.intValue(), carry);
    flag.write(WordNumber.createValue(data));
    return WordNumber.createValue(result);
  }

  public <T extends WordNumber> T executeWithoutCarry(T value, T regA, Register<T> flag) {
    Integer result = triFunction.apply(regA.intValue(), value.intValue(), 0);
    flag.write(WordNumber.createValue(data));
    return WordNumber.createValue(result);
  }
}
