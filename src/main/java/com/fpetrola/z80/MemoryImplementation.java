package com.fpetrola.z80;

import com.fpetrola.z80.mmu.Memory;

import z80core.MemIoOps;

public class MemoryImplementation implements Memory {
  private MemIoOps memory;
  int[] data = new int[0x10000];

  public MemoryImplementation(MemIoOps memory2) {
    this.memory = memory2;
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      memory.poke82(i, j);
      data[i] = j;
    }
  }

  public int read(int address) {
    return data[address & 0xFFFF] & 0xff;
  }

  @Override
  public void write(int address, int value) {
//		if (address >= 16384 && address <= 16384 + 6144) {
//			System.out.println("pantalla!");
//		}
    data[address& 0xffff] = (byte) (value & 0xFF);
    memory.poke8(address & 0xffff, value);
  }

  public boolean compare() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      if (data[i] != j)
        return false;
    }
    return true;
  }
}