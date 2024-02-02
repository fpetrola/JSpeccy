package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Block {
  public void updateStartAddress(int startAddress);

    public void updateEndAddress(int endAddress);

  Collection<BlockReference> getReferences();

  void addReferences(Collection<BlockReference> references1);

  Block split(int blockAddress, String callType, Block newBlock);

  void removeBlockReferences(Collection<BlockReference> newBlockReferences);

  void removeBlockReference(BlockReference blockReference);

  void setPreviousBlock(Block block);

  Block join(Block block);

  String getName();

  boolean isCallingTo(Block block);

  int getStartAddress();

  int getEndAddress();

  String getCallType();

  Set<Block> getReferencedByBlocks();

  String toString();

  Block buildBlock(Block newBlock, int blockAddress, String callType, int lastEndAddress);

  void setStartAddress(int startAddress);

  void setEndAddress(int endAddress);

  void updateNextBlock(Block nextBlock);

  void setCallType(String callType);

  BlocksManager getBlocksManager();

  void setBlocksManager(BlocksManager blocksManager);

  void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData);

  Block checkExecution(ExecutionStepData executionStepData);

  boolean canTake(int pcValue);

  Block getNextBlock();

  void setNextBlock(Block nextBlock);

  Block getPreviousBlock();

  void init(int start, int end, BlocksManager blocksManager);

  Block prepareForJump(int pcValue, int length1);

  boolean isOverlappedBy(Block block);

  boolean contains(int endAddress);

  boolean containsBlock(Block block);

  Block replaceType(Block aBlock);

  void addBlockReference(Block block, Block nextBlock, int from, int to);

  void addBlockReference(BlockReference e);
}
