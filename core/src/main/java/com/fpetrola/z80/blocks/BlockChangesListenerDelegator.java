package com.fpetrola.z80.blocks;

public class BlockChangesListenerDelegator implements BlockChangesListener {
  private final BlockChangesListener blockChangesListener;
  private boolean delegationEnabled= true;

  public BlockChangesListenerDelegator(BlockChangesListener blockChangesListener) {

    this.blockChangesListener = blockChangesListener;
  }

  public void removingKnownBlock(Block block, Block calledBlock) {
    if (delegationEnabled)
      blockChangesListener.removingKnownBlock(block, calledBlock);
  }

  public void addingKnownBLock(Block block, Block calledBlock, int from) {
    if (delegationEnabled)
      blockChangesListener.addingKnownBLock(block, calledBlock, from);
  }

  public void removingBlock(Block block) {
    if (delegationEnabled)
      blockChangesListener.removingBlock(block);
  }

  public void addingBlock(Block block) {
    if (delegationEnabled)
      blockChangesListener.addingBlock(block);
  }

  public void blockChanged(Block block) {
    if (delegationEnabled)
      blockChangesListener.blockChanged(block);
  }

  public void replaceBlock(Block oldBlock, Block newBlock) {
    if (delegationEnabled)
      blockChangesListener.replaceBlock(oldBlock, newBlock);
  }
}
