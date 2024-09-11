package com.fpetrola.z80.routines;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.instructions.base.Instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Routine {
  public List<Block> blocks;
  public boolean finished;
  private List<Instruction> instructions = new ArrayList<>();
  public List<Routine> innerRoutines = new ArrayList<>();

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
    blocks.add(block);
  }

  public void optimize() {
    List<Block> blocks1 = new ArrayList<>(blocks);
    blocks1.stream().forEach(b -> {
      Block nextBlock = b.getRangeHandler().getNextBlock();
      if (blocks.contains(nextBlock))
        if (b.isAdjacent(nextBlock) && nextBlock.getBlockType() instanceof CodeBlockType) {
          b.join(nextBlock);
          blocks.remove(nextBlock);
        }
    });
  }
}
