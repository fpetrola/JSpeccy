package com.fpetrola.z80.blocks;

import org.apache.commons.lang3.Range;

public class RangeHandler {
  private final String blockName;
  protected int startAddress;
  protected int endAddress;
  protected Block nextBlock = new NullBlock();

  public void setPreviousBlock(Block previousBlock) {
    this.previousBlock = previousBlock;
  }

  protected Block previousBlock = new NullBlock();
  private RangeChangeListener rangeChangeListener;

  public RangeHandler(String typeName, RangeChangeListener rangeChangeListener) {
    blockName = typeName;
    this.rangeChangeListener = rangeChangeListener;
  }

  public String getName() {
    return blockName;
  }

  public int getStartAddress() {
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

  public boolean isInside(int address) {
    return address >= startAddress && address <= endAddress;
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
}