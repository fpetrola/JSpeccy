package com.fpetrola.z80.blocks.ranges;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlock;
import com.fpetrola.z80.blocks.NullBlock;
import com.fpetrola.z80.blocks.UnknownBlock;
import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.base.Instruction;

import java.util.function.Consumer;

public class RangeHandler {
  protected String blockName;
  protected int startAddress;
  protected int endAddress;
  protected Block nextBlock = new NullBlock();
  protected Block previousBlock = new NullBlock();
  protected RangeChangeListener rangeChangeListener;

  public RangeHandler(int start, int end, String typeName, RangeChangeListener rangeChangeListener) {
    this.startAddress = start;
    this.endAddress = end;
    blockName = typeName;
    this.rangeChangeListener = rangeChangeListener;
  }

  public String getName() {
    return toString();
  }

  public String toString() {
    return String.format(blockName + ": %1$04X : %2$04X", startAddress, endAddress);
  }

  public boolean contains(int address) {
    return address >= startAddress && address <= endAddress;
  }

  public <T extends Block> T splitRange(T block, String callType, Class<T> type, Block aBlock, int address) {
    int lastEndAddress = endAddress;
    endAddress = address;
    Block lastNextBlock = nextBlock;

    RangeHandler blockRangeHandler = block.getRangeHandler();
    nextBlock = block;

    blockRangeHandler.nextBlock = lastNextBlock;
    blockRangeHandler.previousBlock = aBlock;
    return block;
  }

  public <T extends Block> T createBlockForSplit(String callType, Class<T> type, Block aBlock, int address) {
    T block = aBlock.createBlock(address + 1, endAddress, callType, type);
    return block;
  }

  public void joinRange(Block block, Block otherBlock) {
    Block lastNextBlock = otherBlock.getRangeHandler().nextBlock;
    this.nextBlock = lastNextBlock;
    lastNextBlock.getRangeHandler().previousBlock = block;
    this.endAddress = otherBlock.getRangeHandler().endAddress;
  }

  public <T extends Block> T replaceRange(Class<T> type, Block aBlock) {
    Block lastPreviousBlock = previousBlock;
    Block lastNextBlock = nextBlock;

    T block = aBlock.createBlock(startAddress, endAddress, aBlock.getCallType(), type);
    RangeHandler newBlockRangeHandler = block.getRangeHandler();

    previousBlock.getRangeHandler().nextBlock = block;
    nextBlock.getRangeHandler().previousBlock = block;
    newBlockRangeHandler.nextBlock = lastNextBlock;
    newBlockRangeHandler.previousBlock = lastPreviousBlock;
    return block;
  }

  public void chainedJoin(Block startBlock, int end) {
    while (true) {
      RangeHandler rangeHandler = startBlock.getRangeHandler();
      if (!(rangeHandler.endAddress != end - 1)) break;
      startBlock.join(rangeHandler.nextBlock);
    }
  }

  public boolean isAdjacent(Block block) {
    return endAddress + 1 == block.getRangeHandler().startAddress;
  }

  public boolean isAdjacent(int pcValue) {
    return pcValue == endAddress + 1;
  }

  public Block retrieveAppropriatedBlock(int pcValue, int length, Block fromBlock) {
    Block previousBlock = this.previousBlock;
    Block block = previousBlock;
    if (!previousBlock.canTake(pcValue)) {
      Block startBlock = fromBlock.getBlocksManager().findBlockAt(pcValue);
      Block startSplit = startBlock.split(pcValue - 1, "", (Class<? extends Block>) UnknownBlock.class);
      startSplit = fromBlock.joinBlocksBetween(startSplit, pcValue + length);
      block = startSplit;
    }
    return block;
  }

  public void joinAdjacentIfRequired(int pcValue, Instruction instruction, CodeBlock codeBlock) {
    if (nextBlock instanceof CodeBlock) {
      boolean isRetBlock = nextBlock.getRangeHandler().endAddress - nextBlock.getRangeHandler().startAddress == 0;
      boolean isFromSameRoutine = instruction instanceof Call && pcValue + instruction.getLength() - 1 == endAddress;
      if (isRetBlock || isFromSameRoutine) {
        codeBlock.join(nextBlock);
      }
    }
  }

  public void forEachAddress(Consumer<Integer> consumer) {
    for (int i = startAddress; i <= endAddress; i++)
      consumer.accept(i);
  }
}