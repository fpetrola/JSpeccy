package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.Block;

public class BlockRelation {
  private BlockReference sourceReference;
  private BlockReference targetReference;

  public BlockRelation(BlockReference sourceReference1, BlockReference targetReference1) {
    sourceReference = sourceReference1;
    targetReference = targetReference1;
  }

  public Block getSourceBlock() {
    return sourceReference.getBlock();
  }

  public Block getTargetBlock() {
    return targetReference.getBlock();
  }

  public int getSourceAddress() {
    return sourceReference.getAddress();
  }

  public int getTargetAddress() {
    return targetReference.getAddress();
  }

  public void setSourceBlock(Block block) {
    sourceReference.setBlock(block);
  }

  public void setTargetBlock(Block block) {
    targetReference.setBlock(block);
  }
}
