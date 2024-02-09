package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.Integer8BitRegister;

public class IntegerRegisterPair<T extends WordNumber> implements RegisterPair<Integer> {
  private final RegisterPair<T> registerPair;
  private Integer8BitRegister low;
  private Integer8BitRegister high;

  public IntegerRegisterPair(RegisterPair<T> registerPair) {
    this.registerPair = registerPair;
    low = new MyInteger8BitRegister(registerPair.getLow());
    high = new MyInteger8BitRegister(registerPair.getHigh());
  }

  @Override
  public Register<Integer> getHigh() {
    return high;
  }

  @Override
  public Register<Integer> getLow() {
    return low;
  }

  @Override
  public Integer read() {
    return null;
  }

  @Override
  public void write(Integer value) {
  }

  @Override
  public void increment(int by) {

  }

  @Override
  public void decrement(int by) {

  }

  @Override
  public RegisterName getName() {
    return null;
  }

  @Override
  public int cyclesCost() {
    return 0;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  private class MyInteger8BitRegister extends Integer8BitRegister {
    private final Register<T> low1;

    public MyInteger8BitRegister(Register<T> low1) {
      super(low1.getName());
      this.low1 = low1;
    }

    public Integer read() {
      return low1.read().intValue();
    }

    public void write(Integer value) {
      low1.write(WordNumber.createValue(value));
    }
  }
}
