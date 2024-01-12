package com.fpetrola.z80.registers;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.instructions.OpCode;

public class Composed16BitRegister implements RegisterPair {

  private final Plain8BitRegister high;
  private final Plain8BitRegister low;

  public Composed16BitRegister(String h, String l) {
    high= new Plain8BitRegister(h);
    low= new Plain8BitRegister(l);
  }

  public Composed16BitRegister(Plain8BitRegister h , Plain8BitRegister l) {
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
    return high.toString() + low.toString();
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

  @Override
  public void setOpCode(OpCode opCode) {
    // TODO Auto-generated method stub
    
  }
}
