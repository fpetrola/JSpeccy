package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;
import org.apache.commons.lang3.Range;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractBlock implements Block {
  protected int startAddress;
  protected int endAddress;
  protected String callType;
  protected BlocksManager blocksManager;
  protected Block nextBlock = new NullBlock();
  protected Block previousBlock = new NullBlock();
  protected Set<Block> referencedBlocks = new HashSet<>();
  protected Set<BlockReference> references = new HashSet<>();

  @Override
  public Collection<BlockReference> getReferences() {
    return references;
  }

  @Override
  public void addBlockReferences(Collection<BlockReference> references1) {
    references1.forEach(r -> addBlockReference(r));
  }


  public AbstractBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    this.startAddress = startAddress;
    this.endAddress = endAddress;
    this.setCallType(callType);
    this.setBlocksManager(blocksManager);
  }

  public AbstractBlock() {

  }

  @Override
  public <T extends Block> Block split(int blockAddress, String callType, Class<T> type) {
    if (blockAddress <= endAddress && blockAddress > startAddress) {
      String lastName = getName();
      int lastEndAddress = getEndAddress();
      setEndAddress(blockAddress - 1);
      Block lastNextBlock = getNextBlock();

      T block = buildBlock(blockAddress, lastEndAddress, callType, type);
      block.setNextBlock(lastNextBlock);
      setNextBlock(block);
      block.setPreviousBlock(this);

      List<BlockReference> newBlockReferences = selectSourceBlockReferences(block);
      newBlockReferences.addAll(selectTargetBlockReferences(block));
      removeBlockReferences(newBlockReferences);
      getBlocksManager().blockChangesListener.blockChanged(this);

      getBlocksManager().addBlock(block);
      List<BlockReference> newBlockReferences2 = replaceBlockInReferences(newBlockReferences, this, block);
      block.addBlockReferences(newBlockReferences2);

      System.out.println("Splitting block: " + lastName + " in: " + getName() + " -> " + block.getName());

      return block;
    } else
      return this;
  }


  @Override
  public void removeBlockReferences(Collection<BlockReference> newBlockReferences) {
    new ArrayList<>(newBlockReferences).forEach(r -> removeBlockReference(r));
  }

  @Override
  public void removeBlockReference(BlockReference blockReference) {
    references.remove(blockReference);
    blockReference.getTargetBlock().getReferences().remove(blockReference);

    if (blockReference.getSourceBlock() == this)
      getBlocksManager().blockChangesListener.removingKnownBlock(blockReference.getSourceBlock(), blockReference.getTargetBlock());
  }

  private List<BlockReference> selectSourceBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getSourceAddress())).collect(Collectors.toList());
  }

  private List<BlockReference> selectTargetBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getTargetAddress())).collect(Collectors.toList());
  }

  @Override
  public void setPreviousBlock(Block block) {
    this.previousBlock = block;
  }

  @Override
  public Block join(Block block) {
    Collection<BlockReference> references1 = new ArrayList<>(block.getReferences());
    block.removeBlockReferences(references1);
    Block nextBlock1 = block.getNextBlock();
    getBlocksManager().removeBlock(block);

    references1 = replaceBlockInReferences(references1, block, this);

    addBlockReferences(references1);
    setNextBlock(nextBlock1);
    nextBlock1.setPreviousBlock(this);
    setEndAddress(block.getEndAddress());
    System.out.println("Joining routine: " + this + " -> " + block);
    getBlocksManager().blockChangesListener.blockChanged(this);
    return block;
  }

  public List<BlockReference> replaceBlockInReferences(Collection<BlockReference> references1, Block block, Block replaceBlock) {
    return references1.stream().map(r -> {
      if (r.getSourceBlock() == block) r.setSourceBlock(replaceBlock);
      if (r.getTargetBlock() == block) r.setTargetBlock(replaceBlock);
      return r;
    }).collect(Collectors.toList());
  }

  @Override
  public String getName() {
    return toString();
  }

  @Override
  public boolean isCallingTo(Block block) {
    return references.stream().anyMatch(r -> r.getTargetBlock() == block);
  }

  @Override
  public int getStartAddress() {
    return startAddress;
  }

  @Override
  public int getEndAddress() {
    return endAddress;
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
    return String.format(getTypeName() + ": %1$04X : %2$04X", getStartAddress(), getEndAddress());
  }

  protected String getTypeName() {
    return getClass().getSimpleName();
  }

  @Override
  public <T extends Block> T buildBlock(int startAddress, int endAddress, String callType, Class<T> type) {
    T block = createInstance(type);
    block.setStartAddress(startAddress);
    block.setCallType(callType);
    block.setEndAddress(endAddress);
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

  public void setStartAddress(int startAddress) {
    this.startAddress = startAddress;
  }

  public void updateStartAddress(int startAddress) {
    if (startAddress != this.startAddress) {
      this.startAddress = startAddress;
      getBlocksManager().blockChangesListener.blockChanged(this);
    }
  }

  public void setEndAddress(int endAddress) {
    this.endAddress = endAddress;
  }

  public void updateEndAddress(int endAddress) {
    if (endAddress != this.endAddress) {
      this.endAddress = endAddress;
      Block nextBlock = this.nextBlock;
      if (nextBlock != null) {
        nextBlock.updateStartAddress(endAddress + 1);
      } else
        System.out.println("sdgdsag");

      blocksManager.blockChangesListener.blockChanged(this);
    }
  }

  @Override
  public void updateNextBlock(Block nextBlock) {
    setNextBlock(nextBlock);
    nextBlock.setPreviousBlock(this);
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
  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public boolean isInside(int address) {
    return address >= startAddress && address <= endAddress;
  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public boolean canTake(int pcValue) {
    return false;
  }

  @Override
  public Block getNextBlock() {
    return nextBlock;
  }

  @Override
  public void setNextBlock(Block nextBlock) {
    this.nextBlock = nextBlock;
  }

  @Override
  public Block getPreviousBlock() {
    return previousBlock;
  }

  @Override
  public void init(int start, int end, BlocksManager blocksManager) {
    startAddress = start;
    endAddress = end;
    this.blocksManager = blocksManager;
  }

  protected Block joinBlocksBetween(Block startBlock, int end) {
    Block endBlock = blocksManager.findBlockAt(end);

    Class<? extends AbstractBlock> newBlock = endBlock instanceof UnknownBlock ? UnknownBlock.class : CodeBlock.class;
    Block endSplit = endBlock.split(end, "", newBlock);

    while (startBlock.getEndAddress() != end - 1) {
      startBlock.join(startBlock.getNextBlock());
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
  public boolean isOverlappedBy(Block block) {
    Range<Integer> range1 = getRange(this);
    Range<Integer> range2 = getRange(block);
    return range1.isOverlappedBy(range2);
  }

  private static Range<Integer> getRange(Block block) {
    return Range.between(block.getStartAddress(), block.getEndAddress());
  }

  @Override
  public boolean contains(int address) {
    return address >= startAddress && address <= endAddress;
  }

  @Override
  public boolean containsBlock(Block block) {
    return getRange(this).containsRange(getRange(block));
  }

  @Override
  public <T extends Block> T replaceType(Class<T> type) {

    Block previousBlock1 = getPreviousBlock();
    Block nextBlock1 = getNextBlock();

    T block = buildBlock(startAddress, endAddress, callType, type);

    block.init(startAddress, endAddress, blocksManager);
    blocksManager.addBlock(block);

    Collection<BlockReference> references1 = getReferences();
    block.addBlockReferences(references1);

    this.removeBlockReferences(references1);

    getPreviousBlock().setNextBlock(block);
    getNextBlock().setPreviousBlock(block);
    block.setNextBlock(nextBlock1);
    block.setPreviousBlock(previousBlock1);

//    blocksManager.replace(this, block);
    blocksManager.removeBlock(this);
    return block;
  }

  @Override
  public void addBlockReference(Block sourceBlock, Block targetBlock, int sourceAddress, int targetAddress) {
    BlockReference e = new BlockReference(sourceBlock, targetBlock, sourceAddress, targetAddress);
    addBlockReference(e);
  }

  @Override
  public void addBlockReference(BlockReference e) {
    if (e.getSourceBlock() == this) {
      references.add(e);
      e.getTargetBlock().getReferences().add(e);
      getBlocksManager().blockChangesListener.addingKnownBLock(this, e.getTargetBlock(), e.getSourceAddress());
    } else e.getSourceBlock().addBlockReference(e);
  }
}
