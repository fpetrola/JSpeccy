package z80core;

import com.fpetrola.z80.mmu.Memory;

public class MemoryImplementation implements Memory {
  private MemIoOps memory;

  public MemoryImplementation(MemIoOps memory) {
    this.memory = memory;
  }

  @Override
  public int read(int address) {
//		if (log)
//			System.out.println("read memory: " + address);
    return memory.peek82(address);
  }

  @Override
  public void write(int address, int value) {
//		if (address >= 16384 && address <= 16384 + 6144) {
//			System.out.println("pantalla!");
//		}

    memory.poke82(address, value);
  }
}