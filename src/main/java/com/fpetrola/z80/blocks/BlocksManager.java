package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.metadata.GameMetadata;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.ExecutionStep;

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
  private Block[] blocksAddresses = new Block[0x10000];

  public int getCycle() {
    return cycle;
  }

  private int cycle;

  public BlocksManager(BlockChangesListener blockChangesListener) {
    this.blockChangesListener = new BlockChangesListenerDelegator(blockChangesListener) {
      public void blockChanged(Block block) {
        updateBlockAddresses(block);
        super.blockChanged(block);
      }
    };

    clear();
  }

  public Block findBlockAt(int address) {
    Block block = blocksAddresses[address & 0xFFFF];
    if (block == null)
      System.out.println("dagdsg");
    return block;
  }

  public void addBlock(Block block) {
    blockChangesListener.addingBlock(block);
    blocks.add(block);

    updateBlockAddresses(block);
  }

  private void updateBlockAddresses(Block block) {
    block.getRangeHandler().forEachAddress(address -> {
//      if (blocksAddresses[address] != null)
//        throw new RuntimeException("block already present");
      blocksAddresses[address] = block;
    });
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
    block.getRangeHandler().forEachAddress(address -> blocksAddresses[address] = null);
    blocks.remove(block);
  }

  public BlockChangesListener getBlockChangesListener() {
    return blockChangesListener;
  }

  public List<Block> getBlocks() {
    return new ArrayList<Block>(blocks);
  }

  private void checkForDataReferences(ExecutionStep executionStep1) {
    ExecutionStep<WordNumber> executionStep2 = executionStep1;
    executionStep2.readMemoryReferences.forEach(rm -> {
      int pcValue = executionStep1.pcValue;
      Block currentBlock = findBlockAt(pcValue);

      int address = rm.address.intValue();
      Block blockForData = findBlockAt(address);
      if (blockForData instanceof UnknownBlockType) {
        Block block = blockForData.getAppropriatedBlockFor(address, 1, DataBlockType.class);
        currentBlock.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(pcValue, address));
        ((DataBlockType) block).checkExecution(address);
      } else /*if (blockForData.getEndAddress() > rm.address + 1)*/ {
        currentBlock.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(pcValue, address));
      }
    });
  }

  public void checkExecution(ExecutionStep executionStep) {
    mutantCode = false;//(executionStep.instruction.getState().getIo() instanceof ReadOnlyIOImplementation);

    Block currentBlock = findBlockAt(executionStep.pcValue);

    currentBlock.accept(new ExecutionChecker(executionStep.instruction, executionStep.pcValue));

    verifyBlocks();

    checkForDataReferences(executionStep);
  }

  public void joinRoutines() {
    List<Block> collect = getBlocks().stream().filter(r1 -> r1 instanceof DataBlockType).collect(Collectors.toList());
    collect.forEach(r1 -> {
      if (r1 != null) {
        List<Block> collect1 = getBlocks().stream().filter(r2 -> r2 instanceof DataBlockType && r2.isAdjacent(r1)).collect(Collectors.toList());
        collect1.forEach(r2 -> {
          r2.join(r1);
        });
      }
    });

    extracted();
  }

  private void extracted() {
    List<Block> collect = getBlocks().stream().filter(r1 -> r1 instanceof CodeBlockType).collect(Collectors.toList());

    collect.stream().forEach(routine -> {
      if (routine != null) {
        List<Block> blocks = getBlocks().stream().filter(r2 -> r2.isReferencing(routine) && r2 instanceof CodeBlockType).collect(Collectors.toList());
        blocks.stream().filter(r -> r.isAdjacent(routine)).forEach(r -> r.join(routine));
      }
    });

    List<Block> routines = blocks.stream().filter(b -> b instanceof CodeBlockType).collect(Collectors.toList());
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

  public void replace(DefaultBlock defaultBlock, Block aBlock) {
    blocks.remove(defaultBlock);
    blockChangesListener.replaceBlock(defaultBlock, aBlock);
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

  public List<Block> getBlocksBetween(int startAddress, int endAddress) {
    List<Block> result = new ArrayList<>();
    Block current = findBlockAt(startAddress);

    while (!current.contains(endAddress)) {
      result.add(current);
      current = current.getRangeHandler().getNextBlock();
    }
    result.add(current);

    return result;
  }

  public void clear() {
    blocks.clear();
    blocksAddresses = new Block[0x10000];
    Block block = new DefaultBlock(0, 0xFFFF, "WHOLE_MEMORY", this);
    block.setType(new UnknownBlockType());
    addBlock(block);
  }
}
