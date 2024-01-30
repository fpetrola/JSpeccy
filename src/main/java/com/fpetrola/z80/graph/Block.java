package com.fpetrola.z80.graph;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.List;
import java.util.Map;

public interface Block {
  public void updateStartAddress(int startAddress);

    public void updateEndAddress(int endAddress);

    void addKnowBlock(Block block, int from);

  void removeKnownBLock(Block block);

  Block split(int blockAddress, String callType, Block newBlock);

  void setPreviousBlock(Block block);

  Block join(Block block);

  String getName();

  boolean isCallingTo(Block block);

  int getStartAddress();

  int getEndAddress();

  String getCallType();

  List<Block> getReferencedByBlocks();

  String toString();

  Block buildBlock(Block newBlock, int blockAddress, String callType, int lastEndAddress);

  void setStartAddress(int startAddress);

  void setEndAddress(int endAddress);

  void setCallType(String callType);

  Map<Integer, Block> getKnownBlocks();

  void setKnownBlocks(Map<Integer, Block> knownBlocks);

  BlocksManager getBlocksManager();

  void setBlocksManager(BlocksManager blocksManager);

  void setReferencedByBlocks(List<Block> referencedByBlocks);

  void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData);

  Block checkExecution(ExecutionStepData executionStepData);

  boolean canTake(int pcValue);

  Block getNextBlock();

  void setNextBlock(Block nextBlock);

  Block getPreviousBlock();

  void init(int start, int end, BlocksManager blocksManager);

  Block prepareForJump(int pcValue, int length1);
}
