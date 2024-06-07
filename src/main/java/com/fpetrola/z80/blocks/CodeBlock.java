package com.fpetrola.z80.blocks;

public class CodeBlock extends AbstractBlock {
  public CodeBlock() {
  }

  public CodeBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type) {
    return this;
  }

  @Override
  public void accept(BlockVisitor blockVisitor) {
    blockVisitor.visitingCodeBlock(this);
  }
}
