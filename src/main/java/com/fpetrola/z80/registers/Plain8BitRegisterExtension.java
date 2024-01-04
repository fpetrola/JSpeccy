package com.fpetrola.z80.registers;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Plain8BitRegisterExtension extends Plain8BitRegister {
  private final Consumer<Integer> consumer;
  private final Supplier<Integer> supplier;

  public Plain8BitRegisterExtension(String name, Consumer<Integer> consumer, Supplier<Integer> supplier) {
    super(name);
    this.consumer = consumer;
    this.supplier = supplier;
  }

  public int readFromRealEmulator() {
    Integer result = supplier.get();
    write(result);
    return result;
  }

  public void writeToRealEmulator(int value) {
    this.data = value & 0xFF;
//    consumer.accept(value);
  }
  
  public void write(int value) {
    this.data = value & 0xFF;
    //    consumer.accept(value);
  }
}