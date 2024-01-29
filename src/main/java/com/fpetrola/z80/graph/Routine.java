package com.fpetrola.z80.graph;

public class Routine extends Block {
  public Routine() {

  }

  public Routine(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

}
