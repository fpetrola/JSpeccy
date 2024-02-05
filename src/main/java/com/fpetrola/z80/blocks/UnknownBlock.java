package com.fpetrola.z80.blocks;

import com.fpetrola.z80.spy.ExecutionStepData;

public class UnknownBlock extends AbstractBlock {

  public UnknownBlock() {
  }

  public UnknownBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  public String getTypeName() {
    return "Unknown";
  }

  public Block checkExecution(ExecutionStepData executionStepData) {
    Block codeBlock = this.transformBlockRangeToType(executionStepData.pcValue, executionStepData.instruction.getLength(), CodeBlock.class);
    codeBlock.checkExecution(executionStepData);
    return codeBlock;
  }

  public Block transformBlockRangeToType(int pcValue, int length, Class<? extends Block> type) {
    Block previousBlock = rangeHandler.getPreviousBlock();
    Block block = previousBlock;
    if (!previousBlock.canTake(pcValue))
      block = extractAddressSpanToBlock(pcValue, pcValue + length, UnknownBlock.class);

    if (!(type.isAssignableFrom(block.getClass())))
      block = block.replaceType(type);

    return block;
  }
}
