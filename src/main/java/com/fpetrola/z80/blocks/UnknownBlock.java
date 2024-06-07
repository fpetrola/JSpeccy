package com.fpetrola.z80.blocks;

public class UnknownBlock extends AbstractBlock {

  public UnknownBlock() {
  }

  public UnknownBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public String getTypeName() {
    return "Unknown";
  }

  public Block getAppropriatedBlockFor(int pcValue, int length, Class<? extends Block> type) {
    Block block = rangeHandler.retrieveAppropriatedBlock(pcValue, length, this);

    if (!(type.isAssignableFrom(block.getClass())))
      block = block.replaceType(type);

    return block;
  }

  @Override
  public void accept(BlockVisitor blockVisitor) {
    blockVisitor.visitingUnknownBlock(this);
  }
}
