package com.fpetrola.z80.registers;

import com.fpetrola.z80.instructions.base.Instruction;

public class Composed16BitRegister implements RegisterPair {

  private final Plain8BitRegister high;
  private final Plain8BitRegister low;
  private String name;

  public Composed16BitRegister(String h, String l) {
    high = new Plain8BitRegister(h);
    low = new Plain8BitRegister(l);
  }

  public Composed16BitRegister(String name, String h, String l) {
    this(h, l);
    this.name = name;
  }

  public Composed16BitRegister(Plain8BitRegister h, Plain8BitRegister l) {
    high = h;
    low = l;
  }

  public int read() {
    return (high.data << 8) | low.data;
//    return Z80Utils.compose16bit(this.high.read(), this.low.read());
  }

  public void write(int value) {
    this.high.data = value >> 8;
    this.low.data = value & 0xFF;
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
    return name == null ? high.toString() + low.toString() : name;
  }

  public void increment(int by) {
    if (++low.data < 0x100)
      return;
    low.data = 0;
    if (++high.data < 0x100)
      return;
    high.data = 0;
  }

  public void decrement(int by) {
    if (--low.data >= 0)
      return;
    low.data = 0xff;
    if (--high.data >= 0)
      return;
    high.data = 0xff;
  }

  public int getLength() {
    return 0;
  }

  public RegisterPair clone() throws CloneNotSupportedException {
    return this;
  }
}
