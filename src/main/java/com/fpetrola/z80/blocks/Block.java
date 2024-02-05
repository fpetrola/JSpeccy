package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.ranges.RangeHandler;
import com.fpetrola.z80.blocks.references.ReferencesHandler;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

public interface Block {

  RangeHandler getRangeHandler();

  <T extends Block> Block split(int address, String callType, Class<T> type);

  Block join(Block block);

  String getName();

  boolean isReferencing(Block block);

  String getCallType();

  String toString();

  String getTypeName();

  public <T extends Block> T createBlock(int startAddress, int endAddress, String callType, Class<T> type);

  void setCallType(String callType);

  BlocksManager getBlocksManager();

  void setBlocksManager(BlocksManager blocksManager);

  void jumpPerformed(int pc, int nextPC, Instruction instruction);

  Block checkExecution(ExecutionStepData executionStepData);

  Block joinBlocksBetween(Block aBlock, int end);

  boolean canTake(int pcValue);

  void init(int start, int end, BlocksManager blocksManager);

  Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type);

  <T extends Block> T replaceType(Class<T> type);

  boolean contains(int address);

  boolean isAdjacent(Block block);

  ReferencesHandler getReferencesHandler();

  boolean isReferencedBy(Block currentBlock);
}
