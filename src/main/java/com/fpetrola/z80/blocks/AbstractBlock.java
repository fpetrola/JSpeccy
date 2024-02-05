package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractBlock implements Block {
  @Override

  public RangeHandler getRangeHandler() {
    return rangeHandler;
  }

  protected RangeHandler rangeHandler;
  protected String callType;
  protected BlocksManager blocksManager;
  protected Set<Block> referencedBlocks = new HashSet<>();
  protected Set<BlockRelation> references = new HashSet<>();

  @Override
  public Collection<BlockRelation> getReferences() {
    return references;
  }

  @Override
  public void addBlockReferences(Collection<BlockRelation> references1) {
    references1.forEach(r -> addBlockRelation(r));
  }


  public AbstractBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    this();
    this.rangeHandler.setStartAddress(startAddress);
    this.rangeHandler.setEndAddress(endAddress);
    this.setCallType(callType);
    this.setBlocksManager(blocksManager);
  }

  public AbstractBlock() {
    rangeHandler = new RangeHandler(this.getTypeName(), rangeHandler -> blocksManager.blockChangesListener.blockChanged(AbstractBlock.this));
  }

  @Override
  public void init(int start, int end, BlocksManager blocksManager) {
    rangeHandler = new RangeHandler(this.getTypeName(), rangeHandler -> blocksManager.blockChangesListener.blockChanged(AbstractBlock.this));
    rangeHandler.setStartAddress(start);
    rangeHandler.setEndAddress(end);
    this.blocksManager = blocksManager;
  }

  @Override
  public <T extends Block> Block split(int blockAddress, String callType, Class<T> type) {
    if (blockAddress <= rangeHandler.getEndAddress() && blockAddress > rangeHandler.getStartAddress()) {
      String lastName = rangeHandler.getName();
      int lastEndAddress = rangeHandler.getEndAddress();
      rangeHandler.setEndAddress(blockAddress - 1);
      Block lastNextBlock = rangeHandler.getNextBlock();

      T block = buildBlock(blockAddress, lastEndAddress, callType, type);
      block.getRangeHandler().setNextBlock(lastNextBlock);
      rangeHandler.setNextBlock(block);
      block.getRangeHandler().setPreviousBlock(this);

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

  private List<BlockRelation> selectSourceBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getSourceAddress())).collect(Collectors.toList());
  }

  private List<BlockRelation> selectTargetBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getTargetAddress())).collect(Collectors.toList());
  }

  @Override
  public void setPreviousBlock(Block block) {
    this.rangeHandler.previousBlock = block;
  }

  @Override
  public Block join(Block block) {
    Collection<BlockRelation> references1 = new ArrayList<>(block.getReferences());
    block.removeBlockReferences(references1);
    Block nextBlock1 = block.getRangeHandler().getNextBlock();
    getBlocksManager().removeBlock(block);

    references1 = replaceBlockInReferences(references1, block, this);

    addBlockReferences(references1);
    rangeHandler.setNextBlock(nextBlock1);
    nextBlock1.setPreviousBlock(this);
    rangeHandler.setEndAddress(block.getRangeHandler().getEndAddress());
    System.out.println("Joining routine: " + this + " -> " + block);
    getBlocksManager().blockChangesListener.blockChanged(this);
    return block;
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
  public <T extends Block> T buildBlock(int startAddress, int endAddress, String callType, Class<T> type) {
    T block = createInstance(type);
    block.getRangeHandler().setStartAddress(startAddress);
    block.setCallType(callType);
    block.getRangeHandler().setEndAddress(endAddress);
    block.setBlocksManager(getBlocksManager());
    return block;
  }

  private <T extends Block> T createInstance(Class<T> type) {
    Block instance;
    try {
      Constructor<? extends Block> constructor = type.getConstructor(null);
      instance = constructor.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return (T) instance;
  }

  // public int hashCode() {
//    return Objects.hash(endAddress, startAddress);
//  }
//
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Routine other = (Routine) obj;
//    return endAddress == other.endAddress && startAddress == other.startAddress;

//  }

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
  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public boolean isInside(int address) {
    return rangeHandler.isInside(address);
  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public boolean canTake(int pcValue) {
    return false;
  }

  protected Block joinBlocksBetween(Block startBlock, int end) {
    Block endBlock = blocksManager.findBlockAt(end);

    Class<? extends AbstractBlock> newBlock = endBlock instanceof UnknownBlock ? UnknownBlock.class : CodeBlock.class;
    Block endSplit = endBlock.split(end, "", newBlock);

    while (startBlock.getRangeHandler().getEndAddress() != end - 1) {
      startBlock.join(startBlock.getRangeHandler().getNextBlock());
    }

    return startBlock;
  }

  @Override
  public Block extractAddressSpanToBlock(int start, int end, Class<? extends Block> type) {

    Block startBlock = blocksManager.findBlockAt(start);

    Block startSplit = startBlock.split(start, "", type);
    startSplit = joinBlocksBetween(startSplit, end);

    return startSplit;
  }

  @Override
  public Block transformBlockRangeToType(int pcValue, int length1, Class<? extends Block> type) {
    throw new RuntimeException("Cannot jump inside this type of block");
  }

  @Override
  public boolean contains(int address) {
    return rangeHandler.contains(address);
  }

  @Override
  public <T extends Block> T replaceType(Class<T> type) {

    Block previousBlock1 = rangeHandler.getPreviousBlock();
    Block nextBlock1 = rangeHandler.getNextBlock();

    T block = buildBlock(rangeHandler.getStartAddress(), rangeHandler.getEndAddress(), callType, type);

    block.init(rangeHandler.getStartAddress(), rangeHandler.getEndAddress(), blocksManager);
    blocksManager.addBlock(block);

    Collection<BlockRelation> references1 = getReferences();
    block.addBlockReferences(references1);

    this.removeBlockReferences(references1);

    rangeHandler.getPreviousBlock().getRangeHandler().setNextBlock(block);
    rangeHandler.getNextBlock().getRangeHandler().setPreviousBlock(block);
    block.getRangeHandler().setNextBlock(nextBlock1);
    block.getRangeHandler().setPreviousBlock(previousBlock1);

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
}
