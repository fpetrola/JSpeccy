package com.fpetrola.z80.blocks;

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

  public void setPreviousBlock(Block previousBlock) {
    this.previousBlock = previousBlock;
  }

  public String getName() {
    return toString();
  }

  private int getStartAddress() {
    return startAddress;
  }


  public int getEndAddress() {
    return endAddress;
  }


  public String toString() {
    return String.format(blockName + ": %1$04X : %2$04X", getStartAddress(), getEndAddress());
  }

  public void setStartAddress(int startAddress) {
    this.startAddress = startAddress;
  }

  public void updateStartAddress(int startAddress) {
    if (startAddress != this.startAddress) {
      this.startAddress = startAddress;
      rangeChangeListener.rangeChanged(this);
    }
  }

  public void setEndAddress(int endAddress) {
    this.endAddress = endAddress;
  }

  public void updateEndAddress(int endAddress) {
    if (endAddress != this.endAddress) {
      this.endAddress = endAddress;
      Block nextBlock = this.nextBlock;
      if (nextBlock != null) {
        nextBlock.getRangeHandler().updateStartAddress(endAddress + 1);
      } else System.out.println("sdgdsag");

      rangeChangeListener.rangeChanged(this);
    }
  }


  public Block getNextBlock() {
    return nextBlock;
  }


  public void setNextBlock(Block nextBlock) {
    this.nextBlock = nextBlock;
  }


  public Block getPreviousBlock() {
    return previousBlock;
  }


  public boolean isOverlappedBy(Block block) {
    Range<Integer> range1 = getOwnRange();
    Range<Integer> range2 = getRange(block);
    return range1.isOverlappedBy(range2);
  }

  private static Range<Integer> getRange(Block block) {
    return Range.between(block.getRangeHandler().getStartAddress(), block.getRangeHandler().getEndAddress());
  }

  public boolean contains(int address) {
    return address >= startAddress && address <= endAddress;
  }

  public boolean containsBlock(Block block) {
    return getOwnRange().containsRange(getRange(block));
  }

  private Range<Integer> getOwnRange() {
    return Range.between(getStartAddress(), getEndAddress());
  }

  <T extends Block> T splitRange(String callType, Class<T> type, Block aBlock, int endAddress11) {
    int lastEndAddress = getEndAddress();
    setEndAddress(endAddress11);
    Block lastNextBlock = getNextBlock();

    T block = aBlock.createBlock(endAddress11 + 1, lastEndAddress, callType, type);
    block.getRangeHandler().setNextBlock(lastNextBlock);
    setNextBlock(block);
    block.getRangeHandler().setPreviousBlock(aBlock);
    return block;
  }

  void joinRange(Block block, Block aBlock) {
    Block nextBlock1 = block.getRangeHandler().getNextBlock();
    setNextBlock(nextBlock1);
    nextBlock1.getRangeHandler().setPreviousBlock(aBlock);
    setEndAddress(block.getRangeHandler().getEndAddress());
  }

  <T extends Block> T replaceRange(Class<T> type, Block aBlock) {
    Block previousBlock1 = getPreviousBlock();
    Block nextBlock1 = getNextBlock();
    T block = aBlock.createBlock(getStartAddress(), getEndAddress(), aBlock.getCallType(), type);

    block.init(getStartAddress(), getEndAddress(), aBlock.getBlocksManager());
    getPreviousBlock().getRangeHandler().setNextBlock(block);
    getNextBlock().getRangeHandler().setPreviousBlock(block);
    block.getRangeHandler().setNextBlock(nextBlock1);
    block.getRangeHandler().setPreviousBlock(previousBlock1);
    return block;
  }

  void chainedJoin(Block startBlock, int end) {
    while (true) {
      RangeHandler rangeHandler = startBlock.getRangeHandler();
      if (!(rangeHandler.getEndAddress() != end - 1)) break;
      startBlock.join(rangeHandler.getNextBlock());
    }
  }

  public static void doVerify(List<Block> blocks) {

    for (int i = 0; i < blocks.size(); i++) {
      for (int j = 0; j < blocks.size(); j++) {
        if (blocks.get(i).getRangeHandler().getNextBlock() == null || blocks.get(i).getRangeHandler().getPreviousBlock() == null) {
          System.out.println("ups!");
        }
        if (blocks.get(i).getRangeHandler().getNextBlock() instanceof NullBlock && blocks.get(i).getRangeHandler().getEndAddress() != 0xFFFF) {
          System.out.println("ups!");
        }
        if (blocks.get(i).getRangeHandler().getPreviousBlock() instanceof NullBlock && blocks.get(i).getRangeHandler().getStartAddress() != 0x0) {
          System.out.println("ups!");
        }
        if (j != i)
          if (blocks.get(i).getRangeHandler().isOverlappedBy(blocks.get(j))) {
            System.out.println("ups!");
          }

      }
    }
  }

  boolean isAdjacent(Block block) {
    return getEndAddress() + 1 == block.getRangeHandler().getStartAddress();
  }

  protected boolean isAdjacent(int pcValue) {
    return pcValue == getEndAddress() + 1;
  }
}