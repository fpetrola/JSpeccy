package com.fpetrola.z80.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlocksManager {

  List<Block> blocks = new ArrayList<>();
  Collection<Branch> branches = new ArrayList<>();
//  protected int startUserCode = 0x5B00;
  protected int startUserCode = 0x0000;
  BlockChangesListener blockChangesListener;

  public BlocksManager(BlockChangesListener blockChangesListener) {
    this.blockChangesListener = blockChangesListener;
    addBlock(new UnknownBlock(0, 0xFFFF, "WHOLE_MEMORY", this));
  }

  public Block findBlockAt(int address) {
    List<Block> foundBlocks = blocks.stream().filter(r -> r.getStartAddress() <= address && r.getEndAddress() >= address).collect(Collectors.toList());

    if (foundBlocks.size() != 1)
      System.out.println("findRoutineAt bug!");

    return foundBlocks.get(0);
  }

  public void addBlock(Block block) {
    blockChangesListener.addingRoutine(block);
    blocks.add(block);
  }

  public boolean getOrCreateBranch(int address) {
    Optional<Branch> branch = branches.stream().filter(b -> b.getAddress() == address).findFirst();

    boolean result = branch.isPresent();
    if (!result)
      branches.add(new Branch(address));

    return result;
  }

  public void removeBlock(Block block) {
    blockChangesListener.removingBlock(block);
    blocks.remove(block);
  }

  public Block addBlock(int blockAddress, int currentPC, String callType, Block block) {
    boolean branchExists = getOrCreateBranch(blockAddress);

    if (blockAddress >= startUserCode && (branchExists || callType.equals("CALL"))) {
      Block calledBlock = findBlockAt(blockAddress);
      Block currentBlock = findBlockAt(currentPC);

      boolean isNewBlock = calledBlock.getStartAddress() < blockAddress;
      if (isNewBlock) {
        calledBlock = calledBlock.split(blockAddress, callType, block);
      }
      if (!calledBlock.getReferencedByBlocks().contains(currentBlock)) {
        currentBlock.addKnowBlock(calledBlock, currentPC);
      }
      return isNewBlock ? calledBlock : null;
    }

    return null;
  }

  public List<Block> getBLocks() {
    return new ArrayList<Block>(blocks);
  }

  public void endBlock(int nextPC, int pcValue, boolean b, Block blockType) {
    Block calledBlock = findBlockAt(pcValue);
    if (calledBlock.endAddress > (pcValue + 1))
      calledBlock.split(pcValue + 1, "RET", blockType);
  }

}
