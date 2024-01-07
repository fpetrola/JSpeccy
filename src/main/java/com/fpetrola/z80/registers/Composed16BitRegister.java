package com.fpetrola.z80.registers;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.instructions.OpCode;

public class Composed16BitRegister implements RegisterPair {

  private final Plain8BitRegister high;
  private final Plain8BitRegister low;

  public Composed16BitRegister(String h, String l, Consumer<Integer> consumer, Supplier<Integer> supplier, Consumer<Integer> consumer2, Supplier<Integer> supplier2) {
    this.high = new Plain8BitRegisterExtension(h, consumer, supplier);
    this.low = new Plain8BitRegisterExtension(l, consumer2, supplier2);
  }

  public Composed16BitRegister(String h, String l, Consumer<Integer> consumer, Supplier<Integer> supplier) {
    this.high = new Plain8BitRegisterExtension(h, createHighConsumer(consumer, supplier), createHighSupplier(consumer, supplier));
    this.low = new Plain8BitRegisterExtension(h, createLowConsumer(consumer, supplier), createLowSupplier(consumer, supplier));
  }

  public Composed16BitRegister(String h, Consumer<Integer> consumer, Supplier<Integer> supplier, Plain8BitRegister lowRegister) {
    this.high = new Plain8BitRegisterExtension(h, consumer, supplier);
    this.low = lowRegister;
  }

  public Composed16BitRegister(String h, String l) {
    high= new Plain8BitRegister(h);
    low= new Plain8BitRegister(l);
  }

  public Composed16BitRegister(Plain8BitRegister h , Plain8BitRegister l) {
    high = h;
    low = l;
  }

  private Supplier<Integer> createLowSupplier(Consumer<Integer> consumer, Supplier<Integer> supplier) {
    return () -> (supplier.get() & 0xFF);
  }

  private Consumer<Integer> createLowConsumer(Consumer<Integer> consumer, Supplier<Integer> supplier) {
    return (c) -> {
      consumer.accept(((supplier.get() & 0xFF00) | ((c & 0xFF))));
    };
  }

  private Supplier<Integer> createHighSupplier(Consumer<Integer> consumer, Supplier<Integer> supplier) {
    return () -> (supplier.get() >>> 8);
  }

  private Consumer<Integer> createHighConsumer(Consumer<Integer> consumer, Supplier<Integer> supplier) {
    return (c) -> {
      consumer.accept(((supplier.get() & 0xFF) | ((c & 0xFF) << 8)));
    };
  }

  @Override
  public int read() {
    return (high.data << 8) | low.data;
//    return Z80Utils.compose16bit(this.high.read(), this.low.read());
  }

  @Override
  public int readFromRealEmulator() {
    int result = Z80Utils.compose16bit(this.high.readFromRealEmulator(), this.low.readFromRealEmulator());
    write(result);
    return result;
  }

  @Override
  public void write(int value) {
    this.high.data = value >> 8;
    this.low.data = value & 0xFF;
  }

  @Override
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

  @Override
  public void writeToRealEmulator(int value) {
    this.high.data = value >> 8;
    this.low.data = value & 0xFF;

//    this.high.writeToRealEmulator(value >> 8);
//    this.low.writeToRealEmulator(value & 0xFF);
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
