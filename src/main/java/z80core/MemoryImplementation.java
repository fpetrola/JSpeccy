package z80core;

import com.fpetrola.z80.mmu.Memory;

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

  public MemoryImplementation() {
  }

  public int read(int address) {
//		if (log)
//			System.out.println("read memory: " + address);
    return data[address] & 0xff;
//    return memory.peek8(address);
  }

  @Override
  public void write(int address, int value) {
//		if (address >= 16384 && address <= 16384 + 6144) {
//			System.out.println("pantalla!");
//		}
    data[address] = (byte) (value & 0xFF);
    memory.poke8(address, value);
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