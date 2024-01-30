package com.fpetrola.z80.graph;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.List;
import java.util.Map;

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
  public Block prepareForJump(int pcValue, int length1) {
    return null;
  }

  public void setStartAddress(int startAddress) {
    throw new RuntimeException("Not implemented");
  }

  public void setEndAddress(int endAddress) {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public void setCallType(String callType) {

  }

  @Override
  public Map<Integer, Block> getKnownBlocks() {
    return null;
  }

  @Override
  public void setKnownBlocks(Map<Integer, Block> knownBlocks) {

  }

  @Override
  public BlocksManager getBlocksManager() {
    return null;
  }

  @Override
  public void setBlocksManager(BlocksManager blocksManager) {

  }

  @Override
  public void setReferencedByBlocks(List<Block> referencedByBlocks) {

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
  public void addKnowBlock(Block block, int from) {

  }

  @Override
  public void removeKnownBLock(Block block) {

  }

  public Block split(int blockAddress, String callType, Block newBlock) {
    throw new RuntimeException("Not implemented");
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
  public List<Block> getReferencedByBlocks() {
    return null;
  }

  @Override
  public Block buildBlock(Block newBlock, int blockAddress, String callType, int lastEndAddress) {
    return null;
  }
}
