package com.fpetrola.z80.blocks;

public abstract class AbstractBlockType implements BlockType {
  protected Block block;

  @Override
  public Block getBlock() {
    return block;
  }

  @Override
  public void setBlock(Block block) {
    this.block = block;
  }
}
