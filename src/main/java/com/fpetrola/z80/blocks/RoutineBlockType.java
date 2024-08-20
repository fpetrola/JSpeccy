package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.ArrayList;
import java.util.List;

public class RoutineBlockType extends AbstractBlockType {
  public boolean finished;
  private List<Instruction> instructions = new ArrayList<>();

  public RoutineBlockType() {
  }

  public RoutineBlockType(Block block) {
    this.block = block;
  }

  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  @Override
  public String getName() {
    return "Routine";
  }

  @Override
  public void accept(BlockRoleVisitor blockRoleVisitor) {
    blockRoleVisitor.visiting(this);
  }

  @Override
  public boolean canSplit() {
    return false;
  }

  @Override
  public boolean canBeJoined() {
    return false;
  }
}
