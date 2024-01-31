package com.fpetrola.z80.blocks;

import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.List;

public class UnknownBlock extends AbstractBlock {
  public UnknownBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }
  protected String getTypeName() {
    return "Unknown";
  }
  public Block checkExecution(ExecutionStepData executionStepData) {
    Block routineBlock = prepareForJump(executionStepData.pcValue, executionStepData.instruction.getLength());
    routineBlock.checkExecution(executionStepData);
    return routineBlock;
  }

  private Block checkForCorrespondingBlock(int pcValue, int length) {
    Block routineBlock = getPreviousBlock();
    if (!getPreviousBlock().canTake(pcValue)) {
      routineBlock = extractAddressSpanToBlock(pcValue, pcValue + length, new Routine());
    }
    return routineBlock;
  }

  public Block split(int blockAddress, String callType, Block newBlock) {
   throw new RuntimeException("cannot split");
  }

  private Block extractAddressSpanToBlock(int start, int end, Block block) {
    verifyBlocks();
    block.init(start, end - 1, getBlocksManager());
    block.setPreviousBlock(this);
    UnknownBlock lastBlock = new UnknownBlock(end, getEndAddress(), "", getBlocksManager());
    lastBlock.setPreviousBlock(block);
    lastBlock.setNextBlock(getNextBlock());
    setNextBlock(block);
    updateEndAddress(start - 1);
    block.setNextBlock(lastBlock);

    getBlocksManager().addBlock(block);
    getBlocksManager().addBlock(lastBlock);

    verifyBlocks();
    return block;
  }

  private void verifyBlocks() {

    List<Block> blocks = getBlocksManager().getBlocks();
    for (Block block:  blocks)
    {
      if (block.getNextBlock().getStartAddress() != block.getEndAddress()+1 && block.getEndAddress() != 0xffff)
      {
        System.out.println("ups!");
      }
    }
  }

  public Block prepareForJump(int pcValue, int length) {
    return checkForCorrespondingBlock(pcValue, length);
  }
}
