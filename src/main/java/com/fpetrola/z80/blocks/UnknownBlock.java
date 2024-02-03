package com.fpetrola.z80.blocks;

import com.fpetrola.z80.spy.ExecutionStepData;

public class UnknownBlock extends AbstractBlock {

  public UnknownBlock() {
  }

  public UnknownBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  protected String getTypeName() {
    return "Unknown";
  }

  public Block checkExecution(ExecutionStepData executionStepData) {
    Block routineBlock = this.transformBlockRangeToType(executionStepData.pcValue, executionStepData.instruction.getLength(), new Routine());
    routineBlock.checkExecution(executionStepData);
    return routineBlock;
  }

  public Block transformBlockRangeToType(int pcValue, int length, Block targetBlock) {
    Block block = getPreviousBlock();
    if (!getPreviousBlock().canTake(pcValue))
      block = extractAddressSpanToBlock(pcValue, pcValue + length, new UnknownBlock());

    if (!(targetBlock.getClass().isAssignableFrom(block.getClass())))
      block = block.replaceType(targetBlock);

    return block;
  }
}
