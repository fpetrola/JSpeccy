package com.fpetrola.z80.mmu;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface Memory<T> {

  static <T extends WordNumber> T read16Bits(Memory<T> memory, T address) {
    return memory.read(address.increment()).left(8).or(memory.read(address).and(0xff));
  }

  static <T extends WordNumber> void write16Bits(Memory<T> memory, T value, T address) {
    memory.write(address, value.and(0xFF));
    memory.write(address.increment(), (value.right(8)));
  }

  T read(T address);

  void write(T address, T value);

  boolean compare();

  void update();

  void setMemoryWriteListener(MemoryWriteListener memoryWriteListener);

  Memory getMemory();

  void reset();

  void addMemoryReadListener(MemoryReadListener memoryReadListener);

  void removeMemoryReadListener(MemoryReadListener memoryReadListener);
}
