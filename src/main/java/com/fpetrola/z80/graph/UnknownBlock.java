package com.fpetrola.z80.graph;

public class UnknownBlock extends Block {
  public UnknownBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }
}
