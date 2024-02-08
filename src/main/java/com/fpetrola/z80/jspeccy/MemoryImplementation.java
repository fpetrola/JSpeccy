package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.mmu.Memory;

import com.fpetrola.z80.opcodes.references.WordNumber;
import z80core.MemIoOps;

public class MemoryImplementation<T extends WordNumber> implements Memory<T> {
  private MemIoOps memory;
  int[] data = new int[0x10000];

  MemoryWriteListener memoryWriteListener;

  public MemoryImplementation(MemIoOps memory2) {
    this.memory = memory2;
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
//      memory.poke82(i, j);
      data[i] = j;
    }
  }

  public T read(T address) {
    return OOZ80.createValue(data[address.intValue() & 0xFFFF] & 0xff);
  }

  @Override
  public void write(T address, T value) {

    byte b = (byte) (value.intValue() & 0xFF);
//    byte peek84 = (byte) memory.peek84(address);
//    if (peek84 != b) {
//      System.out.println("dsgadg");
//    }

    if (memoryWriteListener != null)
      memoryWriteListener.writtingMemoryAt(address.intValue(), value.intValue());

    int a = address.intValue() & 0xffff;
    data[a] = b;
//    if (memory.peek82(a) != (value.intValue() & 0xFF) )
//      System.out.println("upa!");
    memory.poke8(address.intValue() & 0xffff, value.intValue());
  }

  public boolean compare() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      if (data[i] != j)
        return false;
    }
    return true;
  }

  @Override
  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    this.memoryWriteListener = memoryWriteListener;
  }

  @Override
  public Memory getMemory() {
    return this;
  }
}