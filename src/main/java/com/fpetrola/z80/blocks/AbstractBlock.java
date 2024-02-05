package com.fpetrola.z80.blocks;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractBlock implements Block {
  protected RangeHandler rangeHandler;
  protected String callType;
  protected BlocksManager blocksManager;
  protected Set<Block> referencedBlocks = new HashSet<>();
  protected Set<BlockRelation> references = new HashSet<>();

  public AbstractBlock() {
  }

  public AbstractBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    this();
    init(startAddress, endAddress, blocksManager);
    this.setCallType(callType);
  }

  @Override
  public void init(int start, int end, BlocksManager blocksManager) {
    rangeHandler = new RangeHandler(start, end, this.getTypeName(), rangeHandler -> blocksManager.blockChangesListener.blockChanged(AbstractBlock.this));
    this.blocksManager = blocksManager;
  }

  @Override
  public <T extends Block> Block split(int address, String callType, Class<T> type) {
    if (rangeHandler.contains(address)) {
      String lastName = rangeHandler.getName();

      T block = rangeHandler.splitRange(callType, type, this, address);

      List<BlockRelation> newBlockRelations = selectSourceBlockReferences(block);
      newBlockRelations.addAll(selectTargetBlockReferences(block));
      removeBlockReferences(newBlockRelations);
      getBlocksManager().blockChangesListener.blockChanged(this);

      getBlocksManager().addBlock(block);
      List<BlockRelation> newBlockReferences2 = replaceBlockInReferences(newBlockRelations, this, block);
      block.addBlockReferences(newBlockReferences2);

      System.out.println("Splitting block: " + lastName + " in: " + rangeHandler.getName() + " -> " + block.getName());

      return block;
    } else
      return this;
  }

  @Override
  public Block join(Block block) {
    Collection<BlockRelation> references1 = new ArrayList<>(block.getReferences());
    block.removeBlockReferences(references1);
    getBlocksManager().removeBlock(block);

    references1 = replaceBlockInReferences(references1, block, this);

    addBlockReferences(references1);
    rangeHandler.joinRange(block, this);
    System.out.println("Joining routine: " + this + " -> " + block);
    getBlocksManager().blockChangesListener.blockChanged(this);
    return block;
  }


  @Override
  public void removeBlockReferences(Collection<BlockRelation> newBlockRelations) {
    new ArrayList<>(newBlockRelations).forEach(r -> removeBlockReference(r));
  }

  @Override
  public void removeBlockReference(BlockRelation blockRelation) {
    references.remove(blockRelation);
    blockRelation.getTargetBlock().getReferences().remove(blockRelation);

    if (blockRelation.getSourceBlock() == this)
      getBlocksManager().blockChangesListener.removingKnownBlock(blockRelation.getSourceBlock(), blockRelation.getTargetBlock());
  }

  @Override
  public Collection<BlockRelation> getReferences() {
    return references;
  }

  @Override
  public RangeHandler getRangeHandler() {
    return rangeHandler;
  }

  @Override
  public void addBlockReferences(Collection<BlockRelation> references1) {
    references1.forEach(r -> addBlockRelation(r));
  }

  private List<BlockRelation> selectSourceBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getSourceAddress())).collect(Collectors.toList());
  }

  private List<BlockRelation> selectTargetBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getTargetAddress())).collect(Collectors.toList());
  }

  public List<BlockRelation> replaceBlockInReferences(Collection<BlockRelation> references1, Block block, Block replaceBlock) {
    return references1.stream().map(r -> {
      if (r.getSourceBlock() == block) r.setSourceBlock(replaceBlock);
      if (r.getTargetBlock() == block) r.setTargetBlock(replaceBlock);
      return r;
    }).collect(Collectors.toList());
  }

  @Override
  public String getName() {
    return rangeHandler.getName();
  }

  @Override
  public boolean isCallingTo(Block block) {
    return references.stream().anyMatch(r -> r.getTargetBlock() == block);
  }

  @Override
  public String getCallType() {
    return callType;
  }

  @Override
  public Set<Block> getReferencedByBlocks() {
    return references.stream().map(r -> r.getSourceBlock()).collect(Collectors.toSet());
  }

  @Override
  public String toString() {
    return rangeHandler.toString();
  }

  @Override
  public String getTypeName() {
    return getClass().getSimpleName();
  }

  @Override
  public <T extends Block> T createBlock(int startAddress, int endAddress, String callType, Class<T> type) {
    T block = Helper.createInstance(type);
    block.init(startAddress, endAddress, blocksManager);
    return block;
  }

  @Override
  public void setCallType(String callType) {
    this.callType = callType;
  }

  @Override
  public BlocksManager getBlocksManager() {
    return blocksManager;
  }

  @Override
  public void setBlocksManager(BlocksManager blocksManager) {
    this.blocksManager = blocksManager;
  }

  @Override
  public void jumpPerformed(int pc, int nextPC, Instruction instruction) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public Block joinBlocksBetween(Block aBlock, int end) {
    Block endBlock = blocksManager.findBlockAt(end);
    Block endBlockLess1 = blocksManager.findBlockAt(end - 1);

    if (endBlock == endBlockLess1) {
      Class<? extends AbstractBlock> newBlock = endBlock instanceof UnknownBlock ? UnknownBlock.class : CodeBlock.class;
      Block endSplit = endBlock.split(end - 1, "", newBlock);
    }

    rangeHandler.chainedJoin(aBlock, end);
    return aBlock;
  }

  public boolean canTake(int pcValue) {
    return rangeHandler.isAdjacent(pcValue);
  }

  @Override
  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type) {
    throw new RuntimeException("Cannot jump inside this type of block");
  }

  @Override
  public <T extends Block> T replaceType(Class<T> type) {
    T block = rangeHandler.replaceRange(type, this);

    blocksManager.addBlock(block);
    Collection<BlockRelation> references1 = getReferences();
    block.addBlockReferences(references1);
    this.removeBlockReferences(references1);

//    blocksManager.replace(this, block);
    blocksManager.removeBlock(this);
    return block;
  }

  @Override
  public void addBlockRelation(BlockRelation e) {
    if (e.getSourceBlock() == this) {
      references.add(e);
      e.getTargetBlock().getReferences().add(e);
      getBlocksManager().blockChangesListener.addingKnownBLock(this, e.getTargetBlock(), e.getSourceAddress());
    } else e.getSourceBlock().addBlockRelation(e);
  }

  @Override
  public boolean contains(int address) {
    return getRangeHandler().contains(address);
  }

  @Override
  public boolean isAdjacent(Block block) {
    return getRangeHandler().isAdjacent(block);
  }

}
