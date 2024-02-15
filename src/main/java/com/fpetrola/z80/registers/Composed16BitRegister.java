package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Composed16BitRegister<T extends WordNumber> implements RegisterPair<T> {

  private final Register<T> high;
  private final Register<T> low;
  private RegisterName name;

  private Composed16BitRegister(RegisterName h, RegisterName l) {
    high = new Plain8BitRegister(h);
    low = new Plain8BitRegister(l);
  }

  public Composed16BitRegister(RegisterName name, RegisterName h, RegisterName l) {
    this(h, l);
    this.name = name;
  }

  public Composed16BitRegister(RegisterName name, Register h, Register l) {
    high = h;
    low = l;
    this.name = name;
  }

  public T read() {
    return (high.read().left(8)).or(low.read());
  }

  public void write(T value) {
    this.high.write(value.right(8));
    this.low.write(value.and(0xFF));
  }

  public Register getHigh() {
    return this.high;
  }

  @Override
  public Register getLow() {
    return this.low;
  }

  @Override
  public int cyclesCost() {
    return 0;
  }

  @Override
  public String toString() {
    return name == null ? high.toString() + low.toString() : name.name();
  }

  public void increment() {
    T plus = low.read().increment(1);
    low.write(plus);
    if (plus.intValue() < 0x100)
      return;
    low.write(WordNumber.createValue(0));
    T plus1 = high.read().increment(1);
    high.write(plus1);
    if (plus1.intValue() < 0x100)
      return;
    high.write(WordNumber.createValue(0));
  }

  public void decrement() {
    T lowValue = low.read();
    if (lowValue.isNotZero()) {
      T minus = lowValue.decrement();
      low.write(minus);
    } else {
      low.write(WordNumber.createValue(0xff));
      T highValue = high.read();
      if (highValue.isNotZero()) {
        T minus1 = highValue.decrement();
        high.write(minus1);
        return;
      }
      high.write(WordNumber.createValue(0xff));
    }
  }

  public int getLength() {
    return 0;
  }

  public RegisterPair<T> clone() throws CloneNotSupportedException {
    return this;
  }

  public RegisterName getName() {
    return name;
  }
}
