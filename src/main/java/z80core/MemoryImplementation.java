package z80core;

import com.fpetrola.z80.GraphFrame;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.RegisterBank;

import snapshots.MemoryState;

public class MemoryImplementation implements Memory {
  private MemIoOps memory;
  private GraphFrame graph;

  public MemoryImplementation(MemIoOps memory) {
    this.memory = memory;
  }

  @Override
  public int read(int address, boolean log) {
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

  @Override
  public void setGraph(GraphFrame graph) {
    this.graph = graph;
    // TODO Auto-generated method stub

  }

  @Override
  public void write2(int address, int value) {
    memory.poke82(address, value);

  }

  @Override
  public Object getState() {
    return memory.getState();
  }

  @Override
  public void setSate(Object memoryState) {
    memory.setState(memoryState);
  }

  @Override
  public void stopEmulation() {
    memory.stopEmulation();
  }

  @Override
  public void startEmulation() {
    memory.startEmulation();

  }

  @Override
  public void compareMemoryStates(Object memoryState) {
    memory.compareMemoryStates(memoryState);
  }

  @Override
  public void setCustomState() {
    memory.setCustomState();
  }
}