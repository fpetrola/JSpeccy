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

//  public Block split(int blockAddress, String callType, Block newBlock) {
//    throw new RuntimeException("cannot split");
//  }

  private Block extractAddressSpanToBlock(int start, int end, Block block) {
    blocksManager.verifyBlocks();
    UnknownBlock tempBlock = new UnknownBlock(getStartAddress(), getEndAddress(), "", getBlocksManager());

    Block startBlock = blocksManager.findBlockAt(start);

    Block startSplit = startBlock.split(start, "", new UnknownBlock());
    startSplit = joinBlocksBetween(startSplit, end);

//    int newBlockEnd = end - 1;
//    block.init(start, newBlockEnd, getBlocksManager());
//
//    boolean isSameRange = start == block.getStartAddress() && end == block.getEndAddress();
//    if (!isSameRange) {
//      UnknownBlock lastBlock = new UnknownBlock(newBlockEnd + 1, getEndAddress(), "", getBlocksManager());
//      if (lastBlock.getStartAddress() > lastBlock.getEndAddress())
//        System.out.println("sdgsdgh");
//      lastBlock.setPreviousBlock(block);
//      lastBlock.setNextBlock(getNextBlock());
//
//
//      if (start > 0) {
//        block.setPreviousBlock(this);
//        setNextBlock(block);
//        updateEndAddress(start - 1);
//      } else {
//        blocksManager.removeBlock(this);
//        block.setPreviousBlock(new NullBlock());
//      }
//      block.setNextBlock(lastBlock);
//      blocksManager.addBlock(block);
//      blocksManager.addBlock(lastBlock);

    blocksManager.verifyBlocks();
    return startSplit;
//    } else
//      return this;
  }

  public Block prepareForJump(int pcValue, int length) {
    Block block = checkForCorrespondingBlock(pcValue, length);
    if (!(block instanceof Routine))
      block = block.replaceType(new Routine());
    return block;
  }
}
