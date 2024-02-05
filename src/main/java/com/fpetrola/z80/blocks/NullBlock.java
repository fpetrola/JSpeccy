package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.Collection;
import java.util.Set;

public class NullBlock implements Block {

  public NullBlock() {
  }

  public boolean canTake(int pcValue) {
    return false;
  }

  @Override
  public Block getNextBlock() {
    return null;
  }

  @Override
  public void setNextBlock(Block nextBlock) {

  }

  @Override
  public Block getPreviousBlock() {
    return null;
  }

  @Override
  public void init(int start, int end, BlocksManager blocksManager) {

  }

  @Override
  public Block extractAddressSpanToBlock(int start, int end, Class<? extends Block> type) {
    return null;
  }

  @Override
  public Block transformBlockRangeToType(int pcValue, int length1, Class<? extends Block> type) {
    return null;
  }

  @Override
  public boolean isOverlappedBy(Block block) {
    return false;
  }

  @Override
  public boolean contains(int endAddress) {
    return false;
  }

  @Override
  public boolean containsBlock(Block block) {
    return false;
  }

  @Override
  public <T extends Block> T replaceType(Class<T> type) {
    return null;
  }

  @Override
  public void addBlockRelation(Block block, Block nextBlock, int from, int to) {

  }

  @Override
  public void addBlockRelation(BlockRelation e) {

  }

  public void setStartAddress(int startAddress) {
    throw new RuntimeException("Not implemented");
  }

  public void setEndAddress(int endAddress) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void updateNextBlock(Block nextBlock) {

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
  public boolean isInside(int address) {
    return false;
  }

  @Override
  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {

  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {

    return null;
  }

  @Override
  public void updateStartAddress(int startAddress) {

  }

  @Override
  public void updateEndAddress(int endAddress) {

  }

  @Override
  public Collection<BlockRelation> getReferences() {
    return null;
  }

  @Override
  public void addBlockReferences(Collection<BlockRelation> references1) {

  }

  @Override
  public <T extends Block> Block split(int blockAddress, String callType, Class<T> type) {
    return null;
  }

  @Override
  public void removeBlockReferences(Collection<BlockRelation> newBlockRelations) {

  }

  @Override
  public void removeBlockReference(BlockRelation blockRelation) {

  }

  @Override
  public void setPreviousBlock(Block block) {

  }

  public Block join(Block block) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean isCallingTo(Block block) {
    return false;
  }

  @Override
  public int getStartAddress() {
    return 0;
  }

  @Override
  public int getEndAddress() {
    return 0;
  }

  @Override
  public String getCallType() {
    return null;
  }

  @Override
  public Set<Block> getReferencedByBlocks() {
    return null;
  }

  @Override
  public <T extends Block> T buildBlock(int startAddress, int endAddress, String callType, Class<T> type) {
    return null;
  }

}
