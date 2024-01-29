package com.fpetrola.z80.graph;

import java.util.*;

public abstract class Block {
  int startAddress;
  int endAddress;
  String callType;
  Map<Integer, Block> knownBlocks = new HashMap<>();
  BlocksManager blocksManager;
  List<Block> referencedByBlocks = new ArrayList<>();

  public Block(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    this.startAddress = startAddress;
    this.endAddress = endAddress;
    this.callType = callType;
    this.blocksManager = blocksManager;
  }

  public Block() {

  }

  public void addKnowBlock(Block block, int from) {
    blocksManager.blockChangesListener.addingKnownBLock(this, block, from);
    knownBlocks.put(from, block);
    block.referencedByBlocks.add(this);
  }

  public void removeKnownBLock(Block block) {
    blocksManager.blockChangesListener.removingRoutineCall(this, block);
    Map<Integer, Block> known = new HashMap<>(knownBlocks);
    known.forEach((address, knownBlock) -> {
      if (knownBlock.equals(block)) {
        knownBlocks.remove(address);
        block.referencedByBlocks.remove(this);
      }
    });
  }

  public Block split(int blockAddress, String callType, Block newBlock) {
    if (blockAddress <= endAddress) {
      String lastName = getName();
      int lastEndAddress = endAddress;
      endAddress = blockAddress - 1;

      Block block = buildBlock(newBlock, blockAddress, callType, lastEndAddress);
      blocksManager.addBlock(block);

      Set<Map.Entry<Integer, Block>> entrySet = new HashSet<>(knownBlocks.entrySet());
      for (Map.Entry<Integer, Block> entry : entrySet) {
        Integer callPerformedAddress = entry.getKey();
        if (callPerformedAddress >= blockAddress) {
          removeKnownBLock(entry.getValue());
          block.addKnowBlock(entry.getValue(), callPerformedAddress);
        }
      }

      blocksManager.blockChangesListener.blockChanged(this);

      System.out.println("Spliting block: " + lastName + " in: " + getName() + " -> " + block.getName());

      return block;
    } else
      return this;
  }

  public Block join(Block block) {
    removeKnownBLock(block);
    Set<Map.Entry<Integer, Block>> entrySet = new HashSet<>(block.knownBlocks.entrySet());
    for (Map.Entry<Integer, Block> entry : entrySet) {
      block.removeKnownBLock(entry.getValue());
      addKnowBlock(entry.getValue(), entry.getKey());
    }

    block.referencedByBlocks.clear();
    blocksManager.removeBlock(block);
    System.out.println("Joining routine: " + this + " -> " + block);
    endAddress = block.getEndAddress();
    blocksManager.blockChangesListener.blockChanged(this);
    return block;
  }

  public String getName() {
    return toString();
  }

  public boolean isCallingTo(Block block) {
    return knownBlocks.values().stream().anyMatch(r -> r.equals(block));
  }

  public int getStartAddress() {
    return startAddress;
  }

  public int getEndAddress() {
    return endAddress;
  }

  public String getCallType() {
    return callType;
  }

  public List<Block> getReferencedByBlocks() {
    return referencedByBlocks;
  }

  public String toString() {
    return String.format(getClass().getSimpleName() + ": %1$04X : %2$04X", startAddress, endAddress);
  }

  protected Block buildBlock(Block newBlock, int blockAddress, String callType, int lastEndAddress) {
    newBlock.setStartAddress(blockAddress);
    newBlock.setCallType(callType);
    newBlock.setEndAddress(lastEndAddress);
    newBlock.setBlocksManager(blocksManager);
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

  public void setEndAddress(int endAddress) {
    this.endAddress = endAddress;
  }

  public void setCallType(String callType) {
    this.callType = callType;
  }

  public Map<Integer, Block> getKnownBlocks() {
    return knownBlocks;
  }

  public void setKnownBlocks(Map<Integer, Block> knownBlocks) {
    this.knownBlocks = knownBlocks;
  }

  public BlocksManager getBlocksManager() {
    return blocksManager;
  }

  public void setBlocksManager(BlocksManager blocksManager) {
    this.blocksManager = blocksManager;
  }

  public void setReferencedByBlocks(List<Block> referencedByBlocks) {
    this.referencedByBlocks = referencedByBlocks;
  }
}
