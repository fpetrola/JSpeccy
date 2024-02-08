package com.fpetrola.z80.mmu;

import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface Memory<T> {

  static <T extends WordNumber> T read16Bits(Memory<T> memory, T address) {
    return memory.read(address.plus(1)).left(8).or(memory.read(address).and(0xff)).and(0xffff);
  }

  static <T extends WordNumber> void write16Bits(Memory<T> memory, T value, T address) {
    memory.write(address, value.and(0xFF));
    memory.write(address.plus(1), (value.right(8)));
  }

  T read(T address);

  void write(T address, T value);

  boolean compare();

  void update();

  void setMemoryWriteListener(MemoryWriteListener memoryWriteListener);

  Memory getMemory();
}
