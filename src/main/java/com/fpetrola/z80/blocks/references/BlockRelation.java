package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.Block;

import java.util.Objects;

public class BlockRelation {
  private BlockReference sourceReference;
  private BlockReference targetReference;

  private long executionNumber;

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

  public void setExecutionNumber(long executionNumber) {
    this.executionNumber = executionNumber;
  }

  public long getExecutionNumber() {
    return executionNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BlockRelation that = (BlockRelation) o;
    return Objects.equals(sourceReference, that.sourceReference) && Objects.equals(targetReference, that.targetReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceReference, targetReference);
  }
}
