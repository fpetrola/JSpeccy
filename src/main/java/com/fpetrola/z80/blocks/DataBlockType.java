package com.fpetrola.z80.blocks;

public class DataBlockType extends AbstractBlockType {

  public DataBlockType(Block block) {
    this.block = block;
  }

  public Block checkExecution(int address) {
    if (block.canTake(address))
      return block.joinBlocksBetween(block, address + 1);
    else
      return block;
  }

  @Override
  public void accept(BlockRoleVisitor blockRoleVisitor) {
    blockRoleVisitor.visiting(this);
  }
}
