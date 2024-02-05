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
  public <T extends Block> T replaceType(Class<T> type) {
    return null;
  }

  @Override
  public void addBlockRelation(BlockRelation e) {

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
  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {

  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {

    return null;
  }

  @Override
  public RangeHandler getRangeHandler() {
    return null;
  }

  @Override
  public Collection<BlockRelation> getReferences() {
    return null;
  }

  @Override
  public void addBlockReferences(Collection<BlockRelation> references1) {

  }

  @Override
  public <T extends Block> Block split(int address, String callType, Class<T> type) {
    return null;
  }

  @Override
  public void removeBlockReferences(Collection<BlockRelation> newBlockRelations) {

  }

  @Override
  public void removeBlockReference(BlockRelation blockRelation) {

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
  public String getCallType() {
    return null;
  }

  @Override
  public Set<Block> getReferencedByBlocks() {
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
}
