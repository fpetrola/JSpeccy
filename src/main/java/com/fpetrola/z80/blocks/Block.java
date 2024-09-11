package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.ranges.RangeHandler;
import com.fpetrola.z80.blocks.references.ReferencesHandler;

import java.util.List;

public interface Block {

  default RangeHandler getRangeHandler() {
    return new RangeHandler(0, 0, "", rangeHandler -> {
    }); //TODO: review this for mutant code?
  }

  default Block split(int address, String callType, Class<? extends BlockType> type) {
    return null;
  }

  default Block split(int address, Class<? extends BlockType> type) {
    return split(address, "split", type);
  }

  default Block join(Block block) {
    throw new RuntimeException("Not implemented");
  }

  default String getName() {
    return null;
  }

  default boolean isReferencing(Block block) {
    return false;
  }

  default String getCallType() {
    return null;
  }

  String toString();

  default String getTypeName() {
    return getClass().getSimpleName();
  }

  default Block createBlock(int startAddress, int endAddress, String callType, Class<? extends BlockType> type) {
    return null;
  }

  default void setCallType(String callType) {

  }

  default BlocksManager getBlocksManager() {
    return null;
  }

  default void setBlocksManager(BlocksManager blocksManager) {

  }

  default Block joinBlocksBetween(Block aBlock, int end) {
    return null;
  }

  default boolean canTake(int pcValue) {
    return false;
  }

  default void init(int start, int end, BlocksManager blocksManager) {

  }

  default Block getAppropriatedBlockFor(int pcValue, int length1, BlockType type) {
    return null;
  }

  default Block replaceType(BlockType type) {
    return null;
  }

  default boolean contains(int address) {
    return false;
  }

  default boolean isAdjacent(Block block) {
    return false;
  }

  default ReferencesHandler getReferencesHandler() {
    return null;
  }

  default boolean isReferencedBy(Block currentBlock) {
    return false;
  }

  default void accept(BlockRoleVisitor blockRoleVisitor) {
    getBlockType().accept(blockRoleVisitor);
  }

  default void check1() {
//    if (getRangeHandler().getStartAddress() == 35211)
//      System.out.println("dagadg");
  }

  default Block growBlockTo(int endAddress) {
    check1();

    if (endAddress > getRangeHandler().getEndAddress()) {
      List<Block> blocksBetween = getBlocksManager().getBlocksBetween(getRangeHandler().getStartAddress(), endAddress);

      blocksBetween.remove(0);
      for (Block block : blocksBetween) {
        this.join(block);
      }

      Block split = this.split(endAddress, "", UnknownBlockType.class);
      return split;
    } else
      return this;
  }


  default BlockType getBlockType() {
    return null;
  }

  default void setType(BlockType blockType) {

  }

  default boolean isCompleted() {
    return false;
  }

  default void setCompleted(boolean b) {

  }

}
