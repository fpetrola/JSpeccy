package com.fpetrola.z80.graph;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.*;

public class AbstractBlock implements Block {
  protected int startAddress;
  protected int endAddress;
  protected String callType;
  protected Map<Integer, Block> knownBlocks = new HashMap<>();
  protected BlocksManager blocksManager;
  protected List<Block> referencedByBlocks = new ArrayList<>();
  protected Block nextBlock= new NullBlock();
  protected Block previousBlock = new NullBlock();

  public AbstractBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    this.startAddress = startAddress;
    this.endAddress = endAddress;
    this.setCallType(callType);
    this.setBlocksManager(blocksManager);
  }

  public AbstractBlock() {

  }

  @Override
  public void addKnowBlock(Block block, int from) {
    getBlocksManager().blockChangesListener.addingKnownBLock(this, block, from);
    getKnownBlocks().put(from, block);
    block.getReferencedByBlocks().add(this);
  }

  @Override
  public void removeKnownBLock(Block block) {
    getBlocksManager().blockChangesListener.removingKnownBlock(this, block);
    Map<Integer, Block> known = new HashMap<>(getKnownBlocks());
    known.forEach((address, knownBlock) -> {
      if (knownBlock.equals(block)) {
        getKnownBlocks().remove(address);
        block.getReferencedByBlocks().remove(this);
      }
    });
  }

  @Override
  public Block split(int blockAddress, String callType, Block newBlock) {
    if (blockAddress <= getEndAddress()) {
      String lastName = getName();
      int lastEndAddress = getEndAddress();
      setEndAddress(blockAddress - 1);

      Block block = buildBlock(newBlock, blockAddress, callType, lastEndAddress);
      getBlocksManager().addBlock(block);
      setNextBlock(block);
      block.setPreviousBlock(this);

      Set<Map.Entry<Integer, Block>> entrySet = new HashSet<>(getKnownBlocks().entrySet());
      for (Map.Entry<Integer, Block> entry : entrySet) {
        Integer callPerformedAddress = entry.getKey();
        if (callPerformedAddress >= blockAddress) {
          removeKnownBLock(entry.getValue());
          block.addKnowBlock(entry.getValue(), callPerformedAddress);
        }
      }

      getBlocksManager().blockChangesListener.blockChanged(this);

      System.out.println("Spliting block: " + lastName + " in: " + getName() + " -> " + block.getName());

      return block;
    } else
      return null;
  }

  @Override
  public void setPreviousBlock(Block block) {
    this.previousBlock = block;
  }

  @Override
  public Block join(Block block) {
    removeKnownBLock(block);
    Set<Map.Entry<Integer, Block>> entrySet = new HashSet<>(block.getKnownBlocks().entrySet());
    for (Map.Entry<Integer, Block> entry : entrySet) {
      block.removeKnownBLock(entry.getValue());
      addKnowBlock(entry.getValue(), entry.getKey());
    }

    block.getReferencedByBlocks().clear();
    getBlocksManager().removeBlock(block);
    System.out.println("Joining routine: " + this + " -> " + block);
    setEndAddress(block.getEndAddress());
    getBlocksManager().blockChangesListener.blockChanged(this);
    return block;
  }

  @Override
  public String getName() {
    return toString();
  }

  @Override
  public boolean isCallingTo(Block block) {
    return getKnownBlocks().values().stream().anyMatch(r -> r.equals(block));
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
  public List<Block> getReferencedByBlocks() {
    return referencedByBlocks;
  }

  @Override
  public String toString() {
    return String.format(getTypeName() + ": %1$04X : %2$04X", getStartAddress(), getEndAddress());
  }

  protected String getTypeName() {
    return getClass().getSimpleName();
  }

  @Override
  public Block buildBlock(Block newBlock, int blockAddress, String callType, int lastEndAddress) {
    newBlock.setStartAddress(blockAddress);
    newBlock.setCallType(callType);
    newBlock.setEndAddress(lastEndAddress);
    newBlock.setBlocksManager(getBlocksManager());
    return newBlock;
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
      if (getNextBlock() != null)
        getNextBlock().updateStartAddress(endAddress + 1);
      else
        System.out.println("sdgdsag");

      getBlocksManager().blockChangesListener.blockChanged(this);
    }
  }

  @Override
  public void setCallType(String callType) {
    this.callType = callType;
  }

  @Override
  public Map<Integer, Block> getKnownBlocks() {
    return knownBlocks;
  }

  @Override
  public void setKnownBlocks(Map<Integer, Block> knownBlocks) {
    this.knownBlocks = knownBlocks;
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
  public void setReferencedByBlocks(List<Block> referencedByBlocks) {
    this.referencedByBlocks = referencedByBlocks;
  }

  @Override
  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
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

  @Override
  public Block prepareForJump(int pcValue, int length1) {
    throw new RuntimeException("Cannot jump inside this type of block");
  }
}
