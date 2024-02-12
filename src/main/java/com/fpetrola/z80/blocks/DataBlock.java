package com.fpetrola.z80.blocks;

import com.fpetrola.z80.spy.ExecutionStep;

public class DataBlock extends AbstractBlock {
  public DataBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public DataBlock() {
    super();
  }

  @Override
  public Block checkExecution(ExecutionStep executionStep) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
    return this;
  }

  public Block checkExecution(int address) {
    if (canTake(address))
      return joinBlocksBetween(this, address + 1);
    else
      return this;
  }
}
