package com.fpetrola.z80.instructions;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.function.Supplier;

public class MockedMemory<T extends WordNumber> implements Memory<T> {
  private T[] data;

  public void init(Supplier<T[]> supplier) {
    data= supplier.get();
  }

  @Override
  public T read(T address) {
    return data[address.intValue()];
  }

  @Override
  public void write(T address, T value) {
    data[address.intValue()] = value;
  }

  @Override
  public boolean compare() {
    return false;
  }

  @Override
  public void update() {

  }

  @Override
  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {

  }

  @Override
  public void reset() {

  }

  @Override
  public void addMemoryReadListener(MemoryReadListener memoryReadListener) {

  }

  @Override
  public void removeMemoryReadListener(MemoryReadListener memoryReadListener) {

  }
}
