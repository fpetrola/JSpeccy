package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.Collection;
import java.util.Set;

public interface Block {

  RangeHandler getRangeHandler();

  Collection<BlockRelation> getReferences();

  void addBlockReferences(Collection<BlockRelation> references1);

  <T extends Block> Block split(int blockAddress, String callType, Class<T> type);

  void removeBlockReferences(Collection<BlockRelation> newBlockRelations);

  void removeBlockReference(BlockRelation blockRelation);

  void setPreviousBlock(Block block);

  Block join(Block block);

  String getName();

  boolean isCallingTo(Block block);

  String getCallType();

  Set<Block> getReferencedByBlocks();

  String toString();

  String getTypeName();

  public <T extends Block> T createBlock(int startAddress, int endAddress, String callType, Class<T> type);

  void setCallType(String callType);

  BlocksManager getBlocksManager();

  void setBlocksManager(BlocksManager blocksManager);

  boolean isInside(int address);

  void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData);

  Block checkExecution(ExecutionStepData executionStepData);

  boolean canTake(int pcValue);

  void init(int start, int end, BlocksManager blocksManager);

  //  public Block split(int blockAddress, String callType, Block newBlock) {
//    throw new RuntimeException("cannot split");
//  }
  Block extractAddressSpanToBlock(int start, int end, Class<? extends Block> type);

  Block transformBlockRangeToType(int pcValue, int length1, Class<? extends Block> type);

  <T extends Block> T replaceType(Class<T> type);

  void addBlockRelation(BlockRelation e);
}
