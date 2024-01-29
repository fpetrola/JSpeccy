package com.fpetrola.z80.graph;

public class DataBlock extends Block {
  public DataBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public DataBlock() {
    super();
  }
}
