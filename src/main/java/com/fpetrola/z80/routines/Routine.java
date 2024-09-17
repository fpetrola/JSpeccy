package com.fpetrola.z80.routines;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.blocks.UnknownBlockType;
import com.fpetrola.z80.instructions.base.Instruction;

import java.util.*;

public class Routine {
  public List<Block> blocks;
  public boolean finished;
  private List<Instruction> instructions = new ArrayList<>();
  public Set<Routine> innerRoutines = new HashSet<>();
  private RoutineManager routineManager;
  public Map<Integer, Integer> returnPoints= new HashMap<>();

  public Routine() {
  }

  public Routine(Block block) {
    this.blocks = new ArrayList<>(Arrays.asList(block));
  }

  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  public boolean contains(int address) {
    return blocks.stream().anyMatch(b -> b.contains(address));
  }

  public void addInnerRoutine(Routine routine) {
    innerRoutines.add(routine);
  }

  public void growTo(int address, int length) {
    Block nearestBlock = findNearestBlock(address);
    nearestBlock.growBlockTo(address + length - 1);
  }

  public Block findNearestBlock(int address) {
    return blocks.stream().filter(b -> b.canTake(address)).findFirst().orElse(null);
  }

  @Override
  public String toString() {
    return blocks.toString();
  }

  public void addBlock(Block block) {
    if (blocks.contains(block))
      throw new RuntimeException("block already added");
    blocks.add(block);
  }

  public void optimize() {
    blocks.sort(Comparator.comparingInt(b -> b.getRangeHandler().getStartAddress()));
    List<Block> blocksInReverse = new ArrayList<>(blocks);
    Collections.reverse(blocksInReverse);
    blocksInReverse.forEach(block -> {
      Block previousBlock = block.getRangeHandler().getPreviousBlock();
      if (blocks.contains(previousBlock))
        if (previousBlock.isAdjacent(block) && previousBlock.getBlockType() instanceof CodeBlockType) {
          if (innerRoutines.isEmpty() || !innerRoutines.iterator().next().contains(block.getRangeHandler().getStartAddress())) {
            previousBlock.join(block);
            blocks.remove(block);
          }
        }
    });
  }

  public Routine split(int address) {
    Routine[] result = new Routine[1];
    Optional<Block> first = blocks.stream().filter(b -> b.contains(address)).findFirst();
    first.ifPresent(b -> {
      Block split = b.split(address - 1);
      addBlock(split);
      Routine routine = new Routine(split);
      addInnerRoutine(routine);
      result[0] = routine;
    });

    return result[0];
  }

  void addInstructionAt(Instruction instruction, int pcValue) {
    Block currentBlock = RoutineManager.blocksManager.findBlockAt(pcValue);
    if (currentBlock.getBlockType() instanceof UnknownBlockType) {
      currentBlock.split(pcValue + instruction.getLength() - 1);
      Block blockAt2 = currentBlock.split(pcValue - 1);
      blockAt2.setType(new CodeBlockType());
      addBlock(blockAt2);
    } else {
      Routine routineAt = routineManager.findRoutineAt(pcValue);
      if (routineAt != this && !innerRoutines.contains(routineAt)) {
        routineAt.blocks.forEach(this::addBlock);
        addInnerRoutine(routineAt);
      }
    }
  }

  public void setRoutineManager(RoutineManager routineManager) {
    this.routineManager = routineManager;
  }

  public int getStartAddress() {
    return blocks.stream().map(b -> b.getRangeHandler().getStartAddress()).min(Comparator.comparingInt(b -> b)).get();
  }

  public int getEndAddress() {
    return blocks.stream().map(b -> b.getRangeHandler().getEndAddress()).max(Comparator.comparingInt(b -> b)).get();
  }

  public void addReturnPoint(int returnAddress, int pc) {
    returnPoints.put(returnAddress, pc);
  }
}
