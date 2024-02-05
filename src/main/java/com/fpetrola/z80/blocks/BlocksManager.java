package com.fpetrola.z80.blocks;

import com.fpetrola.z80.jspeccy.ReadOnlyIOImplementation;
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

  public List<Block> getBlocks() {
    return new ArrayList<Block>(blocks);
  }

  private void checkForDataReferences(ExecutionStepData executionStepData1) {
    executionStepData1.readMemoryReferences.forEach(rm -> {
      Block blockForData = findBlockAt(rm.address);
      int pcValue = executionStepData1.pcValue;
      Block currentBlock = findBlockAt(pcValue);
      if (blockForData instanceof UnknownBlock) {
        Block block = blockForData.transformBlockRangeToType(rm.address, 1, DataBlock.class);
        if (!block.getReferencedByBlocks().contains(currentBlock)) {
          currentBlock.addBlockRelation(new BlockRelation(new BlockReference(currentBlock, pcValue), new BlockReference(block, rm.address)));
        }
        ((DataBlock) block).checkExecution(rm.address);
      } else /*if (blockForData.getEndAddress() > rm.address + 1)*/ {
        currentBlock.addBlockRelation(new BlockRelation(new BlockReference(currentBlock, pcValue), new BlockReference(blockForData, rm.address)));
//            Routine routineForData2 = routineForData.split(rm.address + 1, "reading", "Data");
//            currentRoutine.addCallingRoutine(routineForData, pcValue);
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
        List<Block> blocks = getBlocks().stream().filter(r2 -> r2.isCallingTo(routine) && r2 instanceof CodeBlock).collect(Collectors.toList());
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
//    doVerify();
  }

  private void doVerify() {
    List<Block> blocks = getBlocks();

    for (int i = 0; i < blocks.size(); i++) {
      for (int j = 0; j < blocks.size(); j++) {
        if (blocks.get(i).getRangeHandler().getNextBlock() == null || blocks.get(i).getRangeHandler().getPreviousBlock() == null) {
          System.out.println("ups!");
        }
        if (blocks.get(i).getRangeHandler().getNextBlock() instanceof NullBlock && blocks.get(i).getRangeHandler().getEndAddress() != 0xFFFF) {
          System.out.println("ups!");
        }
        if (blocks.get(i).getRangeHandler().getPreviousBlock() instanceof NullBlock && blocks.get(i).getRangeHandler().getStartAddress() != 0x0) {
          System.out.println("ups!");
        }
        if (j != i)
          if (blocks.get(i).getRangeHandler().isOverlappedBy(blocks.get(j))) {
            System.out.println("ups!");
          }

      }
    }
  }

  public void replace(AbstractBlock abstractBlock, Block aBlock) {
    blocks.remove(abstractBlock);
    blockChangesListener.replaceBlock(abstractBlock, aBlock);
  }
}
