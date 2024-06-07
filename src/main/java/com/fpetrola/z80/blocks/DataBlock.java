package com.fpetrola.z80.blocks;

public class DataBlock extends AbstractBlock {
  public DataBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public DataBlock() {
    super();
  }

  public Block checkExecution(int address) {
    if (canTake(address))
      return joinBlocksBetween(this, address + 1);
    else
      return this;
  }

  @Override
  public void accept(BlockVisitor blockVisitor) {
    blockVisitor.visitingDataBlock(this);
  }
}
