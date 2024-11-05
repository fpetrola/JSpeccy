package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Composed16BitRegister<T extends WordNumber, R extends Register<T>> implements RegisterPair<T> {
  protected final R high;
  protected final R low;
  private String name;

  private Composed16BitRegister(String h, String l) {
    high = (R) new Plain8BitRegister(h);
    low = (R) new Plain8BitRegister(l);
  }

  public Composed16BitRegister(String name, String h, String l) {
    this(h, l);
    this.name = name;
  }

  public Composed16BitRegister(String name, Register h, Register l) {
    high = (R) h;
    low = (R) l;
    this.name = name;
  }

  public Composed16BitRegister(RegisterName name, Register h, Register l) {
    this(name.name(), h, l);
  }

  public T read() {
    return (high.read().left(8)).or(low.read());
  }

  public void write(T value) {
    this.high.write(value.right(8));
    this.low.write(value.and(0xFF));
  }

  public R getHigh() {
    return this.high;
  }

  @Override
  public R getLow() {
    return this.low;
  }

  @Override
  public String toString() {
    return name == null ? high.toString() + low.toString() : name;
  }

  public void increment() {
    T plus = low.read().plus(1);
    low.write(plus);
    if (plus.intValue() < 0x100)
      return;
    low.write(WordNumber.createValue(0));
    T plus1 = high.read().plus(1);
    high.write(plus1);
    if (plus1.intValue() < 0x100)
      return;
    high.write(WordNumber.createValue(0));
  }

  public void decrement() {
    T lowValue = low.read();
    if (lowValue.isNotZero()) {
      T minus = lowValue.minus1();
      low.write(minus);
    } else {
      low.write(WordNumber.createValue(0xff));
      T highValue = high.read();
      if (highValue.isNotZero()) {
        T minus1 = highValue.minus1();
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

  public String getName() {
    return name;
  }
}
