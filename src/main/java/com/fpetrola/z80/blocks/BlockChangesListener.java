package com.fpetrola.z80.blocks;

public interface BlockChangesListener {

  void removingKnownBlock(Block block, Block calledBlock);

  void addingKnownBLock(Block block, Block calledBlock, int from);

  void removingBlock(Block block);

  void addingBlock(Block block);

  void blockChanged(Block block);

  void replaceBlock(Block oldBlock, Block newBlock);
}
