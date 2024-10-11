package com.fpetrola.z80.minizx;

public class StateSync {
  volatile public int pc;

  public StateSync(int pc) {
    this.pc = pc;
  }
}
