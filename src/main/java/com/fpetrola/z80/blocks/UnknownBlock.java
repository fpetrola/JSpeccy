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
    Block codeBlock = this.getAppropriatedBlockFor(executionStepData.pcValue, executionStepData.instruction.getLength(), CodeBlock.class);
    codeBlock.checkExecution(executionStepData);
    return codeBlock;
  }

  public Block getAppropriatedBlockFor(int pcValue, int length, Class<? extends Block> type) {
    Block block = rangeHandler.retrieveAppropriatedBlock(pcValue, length, this);

    if (!(type.isAssignableFrom(block.getClass())))
      block = block.replaceType(type);

    return block;
  }
}
