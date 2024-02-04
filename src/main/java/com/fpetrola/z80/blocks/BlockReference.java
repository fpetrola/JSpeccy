package com.fpetrola.z80.blocks;

public class BlockReference {
  private Block sourceBlock;
  private Block targetBlock;
  private int sourceAddress;
  private int targetAddress;

  public BlockReference(Block sourceBlock, Block targetBlock, int sourceAddress, int targetAddress) {
    this.sourceBlock = sourceBlock;
    this.targetBlock = targetBlock;
    this.sourceAddress = sourceAddress;
    this.targetAddress = targetAddress;
  }

  public Block getSourceBlock() {
    return sourceBlock;
  }

  public Block getTargetBlock() {
    return targetBlock;
  }

  public int getSourceAddress() {
    return sourceAddress;
  }

  public int getTargetAddress() {
    return targetAddress;
  }

  public void setSourceBlock(Block block) {
    sourceBlock = block;
  }

  public void setTargetBlock(Block block) {
    targetBlock = block;
  }
}
