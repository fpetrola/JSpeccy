package com.fpetrola.z80.blocks.ranges;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.NullBlock;
import com.fpetrola.z80.blocks.UnknownBlock;
import org.apache.commons.lang3.Range;

import java.util.List;

public class RangeHandler {
  protected final String blockName;
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

  private Range<Integer> getOwnRange() {
    return Range.between(startAddress, endAddress);
  }

  public <T extends Block> T splitRange(String callType, Class<T> type, Block aBlock, int address) {
    int lastEndAddress = endAddress;
    endAddress = address;
    Block lastNextBlock = nextBlock;

    T block = aBlock.createBlock(address + 1, lastEndAddress, callType, type);
    RangeHandler blockRangeHandler = block.getRangeHandler();
    nextBlock = block;

    blockRangeHandler.nextBlock = lastNextBlock;
    blockRangeHandler.previousBlock = aBlock;
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

  public static void doVerify(List<Block> blocks) {

    for (int i = 0; i < blocks.size(); i++) {
      for (int j = 0; j < blocks.size(); j++) {
        RangeHandler rangeHandler2 = blocks.get(i).getRangeHandler();
        RangeHandler rangeHandler4 = blocks.get(i).getRangeHandler();
        if (rangeHandler4.nextBlock == null || rangeHandler2.previousBlock == null) {
          System.out.println("ups!");
        }
        RangeHandler rangeHandler3 = blocks.get(i).getRangeHandler();
        if (rangeHandler3.nextBlock instanceof NullBlock) {
          RangeHandler rangeHandler = blocks.get(i).getRangeHandler();
          if (rangeHandler.endAddress != 0xFFFF) {
            System.out.println("ups!");
          }
        }
        RangeHandler rangeHandler1 = blocks.get(i).getRangeHandler();
        if (rangeHandler1.previousBlock instanceof NullBlock) {
          RangeHandler rangeHandler = blocks.get(i).getRangeHandler();
          if (rangeHandler.startAddress != 0x0) {
            System.out.println("ups!");
          }
        }
        if (j != i)
          if (blocks.get(i).getRangeHandler().isOverlappedBy(blocks.get(j))) {
            System.out.println("ups!");
          }

      }
    }
  }

  private boolean isOverlappedBy(Block block) {
    Range<Integer> range1 = getOwnRange();
    Range<Integer> range2 = getRange(block);
    return range1.isOverlappedBy(range2);
  }

  private Range<Integer> getRange(Block block) {
    RangeHandler rangeHandler = block.getRangeHandler();
    RangeHandler rangeHandler1 = block.getRangeHandler();
    return Range.between(rangeHandler.startAddress, rangeHandler1.endAddress);
  }
}