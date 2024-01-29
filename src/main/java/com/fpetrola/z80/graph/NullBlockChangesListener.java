package com.fpetrola.z80.graph;

public class NullBlockChangesListener implements BlockChangesListener {
  public void removingRoutineCall(Block block, Block calledBlock) {
  }

  public void removingBlock(Block block) {
  }

  public void addingRoutine(Block block) {
  }

  public void addingKnownBLock(Block block, Block calledBlock, int from) {
  }

  public void blockChanged(Block block) {
  }
}