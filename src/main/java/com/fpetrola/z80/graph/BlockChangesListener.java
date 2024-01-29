package com.fpetrola.z80.graph;

public interface BlockChangesListener {

  void removingKnownBlock(Block block, Block calledBlock);

  void addingKnownBLock(Block block, Block calledBlock, int from);

  void removingBlock(Block block);

  void addingBlock(Block block);

  void blockChanged(Block block);

}
