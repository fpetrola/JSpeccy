package com.fpetrola.z80.blocks;

public class DataBlock extends AbstractBlock {
  public DataBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public DataBlock() {
    super();
  }
}
