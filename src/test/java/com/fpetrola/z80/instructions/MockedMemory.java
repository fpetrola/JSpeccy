package com.fpetrola.z80.instructions;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.function.Supplier;

public class MockedMemory<T extends WordNumber> implements Memory<T> {
  private T[] data = (T[]) new WordNumber[0x10000];
  private MemoryWriteListener memoryWriteListener;

  public MockedMemory() {
  }

  public void init(Supplier<T[]> supplier) {
    data = supplier.get();
  }

  @Override
  public T read(T address) {
    T datum = data[address.intValue()];
    if (datum == null)
      return WordNumber.createValue(0);
    else
      return datum.and(0xFF);
  }

  @Override
  public void write(T address, T value) {
    data[address.intValue()] = value;
    if (memoryWriteListener != null)
      memoryWriteListener.writtingMemoryAt(address.intValue(), value.intValue());
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
    this.memoryWriteListener = memoryWriteListener;
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
