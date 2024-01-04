package com.fpetrola.z80.mmu;

import com.fpetrola.z80.GraphFrame;

public interface Memory {

  int read(int address, boolean log);

  void write(int address, int value);
  void write2(int address, int value);

  void setGraph(GraphFrame graph);

  Object getState();

  void setSate(Object memoryState);

  void stopEmulation();

  void startEmulation();

  void compareMemoryStates(Object memoryState);

  void setCustomState();
}
