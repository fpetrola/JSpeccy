package com.fpetrola.z80.blocks;

public class UnknownBlockType extends AbstractBlockType {

  public UnknownBlockType() {
  }

  public UnknownBlockType(Block block) {
    this.block = block;
  }

  public String getTypeName() {
    return "Unknown";
  }

  public Block getAppropriatedBlockFor(int pcValue, int length, BlockType type) {
    Block foundBlock = block.getRangeHandler().retrieveAppropriatedBlock(pcValue, length, block);

    if (!(type.getClass().isAssignableFrom(foundBlock.getClass())))
      foundBlock = foundBlock.replaceType(type);

    return foundBlock;
  }

  @Override
  public void accept(BlockRoleVisitor blockRoleVisitor) {
    blockRoleVisitor.visiting(this);
  }

  @Override
  public String getName() {
    return "--";
  }
}
