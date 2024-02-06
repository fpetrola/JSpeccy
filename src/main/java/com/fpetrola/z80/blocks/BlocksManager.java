package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.references.BlockReference;
import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.jspeccy.ReadOnlyIOImplementation;
import com.fpetrola.z80.metadata.GameMetadata;
import com.fpetrola.z80.spy.ExecutionStepData;

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

  private boolean mutantCode;
  private long executionNumber;
  private GameMetadata gameMetadata;

  public int getCycle() {
    return cycle;
  }

  private int cycle;

  public BlocksManager(BlockChangesListener blockChangesListener) {
    this.blockChangesListener = blockChangesListener;
    addBlock(new UnknownBlock(0, 0xFFFF, "WHOLE_MEMORY", this));
  }

  public Block findBlockAt(int address) {
    List<Block> foundBlocks = blocks.stream().filter(r -> r.contains(address)).collect(Collectors.toList());

    if (foundBlocks.size() != 1 && !mutantCode)
      System.out.println("findRoutineAt bug!");

    return foundBlocks.get(0);
  }

  public void addBlock(Block block) {
    blockChangesListener.addingBlock(block);
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

  public BlockChangesListener getBlockChangesListener() {
    return blockChangesListener;
  }

  public List<Block> getBlocks() {
    return new ArrayList<Block>(blocks);
  }

  private void checkForDataReferences(ExecutionStepData executionStepData1) {
    executionStepData1.readMemoryReferences.forEach(rm -> {
      int pcValue = executionStepData1.pcValue;
      Block currentBlock = findBlockAt(pcValue);

      Block blockForData = findBlockAt(rm.address);
      if (blockForData instanceof UnknownBlock) {
        Block block = blockForData.getAppropriatedBlockFor(rm.address, 1, DataBlock.class);
        currentBlock.getReferencesHandler().addBlockRelation(new BlockRelation(new BlockReference(currentBlock, pcValue), new BlockReference(block, rm.address)));
        ((DataBlock) block).checkExecution(rm.address);
      } else /*if (blockForData.getEndAddress() > rm.address + 1)*/ {
        currentBlock.getReferencesHandler().addBlockRelation(new BlockRelation(new BlockReference(currentBlock, pcValue), new BlockReference(blockForData, rm.address)));
      }
    });
  }

  public void checkExecution(ExecutionStepData executionStepData) {
    mutantCode = (executionStepData.instruction.getState().getIo() instanceof ReadOnlyIOImplementation);

    Block currentBlock = findBlockAt(executionStepData.pcValue);

    currentBlock.checkExecution(executionStepData);

    verifyBlocks();

    checkForDataReferences(executionStepData);
  }

  public void joinRoutines() {
    List<Block> collect = getBlocks().stream().filter(r1 -> r1 instanceof DataBlock).collect(Collectors.toList());
    collect.forEach(r1 -> {
      if (r1 != null) {
        List<Block> collect1 = getBlocks().stream().filter(r2 -> r2 instanceof DataBlock && r2.isAdjacent(r1)).collect(Collectors.toList());
        collect1.forEach(r2 -> {
          r2.join(r1);
        });
      }
    });

    extracted();
  }

  private void extracted() {
    List<Block> collect = getBlocks().stream().filter(r1 -> r1 instanceof CodeBlock).collect(Collectors.toList());

    collect.stream().forEach(routine -> {
      if (routine != null) {
        List<Block> blocks = getBlocks().stream().filter(r2 -> r2.isReferencing(routine) && r2 instanceof CodeBlock).collect(Collectors.toList());
        blocks.stream().filter(r -> r.isAdjacent(routine)).forEach(r -> r.join(routine));
      }
    });

    List<Block> routines = blocks.stream().filter(b -> b instanceof CodeBlock).collect(Collectors.toList());
  }

  public void optimizeBlocks() {
    int lastRoutinesNumber = 1;
    while (getBlocks().size() != lastRoutinesNumber) {
      lastRoutinesNumber = getBlocks().size();
      joinRoutines();
    }
  }

  public void verifyBlocks() {
//    RangeHandler.doVerify(getBlocks());
  }

  public void replace(AbstractBlock abstractBlock, Block aBlock) {
    blocks.remove(abstractBlock);
    blockChangesListener.replaceBlock(abstractBlock, aBlock);
  }

  public long getExecutionNumber() {
    return executionNumber;
  }

  public void setExecutionNumber(long executionNumber) {
    this.executionNumber = executionNumber;
  }

  public Collection<Branch> getBranches() {
    return branches;
  }

  public void setGameMetadata(GameMetadata gameMetadata) {
    this.gameMetadata = gameMetadata;
  }

  public GameMetadata getGameMetadata() {
    return gameMetadata;
  }

  public void incrementCycle() {
    cycle++;
  }
}
