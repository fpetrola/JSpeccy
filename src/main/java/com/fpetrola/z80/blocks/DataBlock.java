package com.fpetrola.z80.blocks;

import com.fpetrola.z80.spy.ExecutionStepData;

public class DataBlock extends AbstractBlock {
  public DataBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public DataBlock() {
    super();
  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {
    System.out.println("Mutable code?: " + executionStepData.pcValue);
    return this;
  }

  public Block checkExecution(int address) {
    if (rangeHandler.isInside(address))
      rangeHandler.updateEndAddress(Math.max(rangeHandler.getEndAddress(), address));
    else if (canTake(address)) {
      Block startSplit = joinBlocksBetween(this, address + 1);
      return startSplit;
    }

    return this;
  }

  public boolean canTake(int pcValue) {
    return pcValue == rangeHandler.getEndAddress() + 1;
  }
}
