package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.ranges.RangeHandler;
import com.fpetrola.z80.blocks.references.ReferencesHandler;
import com.fpetrola.z80.instructions.base.Instruction;

public class NullBlock implements Block {

  public NullBlock() {
  }

  public boolean canTake(int pcValue) {
    return false;
  }

  @Override
  public void init(int start, int end, BlocksManager blocksManager) {

  }

  @Override
  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type) {
    return null;
  }

  @Override
  public <T extends Block> T replaceType(Class<T> type) {
    return null;
  }

  @Override
  public boolean contains(int address) {
    return false;
  }

  @Override
  public void setCallType(String callType) {

  }


  @Override
  public BlocksManager getBlocksManager() {
    return null;
  }

  @Override
  public void setBlocksManager(BlocksManager blocksManager) {

  }

  @Override
  public Block joinBlocksBetween(Block aBlock, int end) {
    return null;
  }

  @Override
  public RangeHandler getRangeHandler() {
    return new RangeHandler(0, 0, "", rangeHandler -> {
    }); //TODO: review this for mutant code?
  }

  @Override
  public <T extends Block> Block split(int address, String callType, Class<T> type) {
    return null;
  }

  public Block join(Block block) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean isReferencing(Block block) {
    return false;
  }

  @Override
  public String getCallType() {
    return null;
  }

  @Override
  public String getTypeName() {
    return getClass().getSimpleName();
  }

  @Override
  public <T extends Block> T createBlock(int startAddress, int endAddress, String callType, Class<T> type) {
    return null;
  }

  public boolean isAdjacent(Block block) {
    return false;
  }

  @Override
  public ReferencesHandler getReferencesHandler() {
    return null;
  }

  @Override
  public boolean isReferencedBy(Block currentBlock) {
    return false;
  }
}
