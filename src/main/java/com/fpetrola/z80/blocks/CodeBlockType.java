package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.ArrayList;
import java.util.List;

public class CodeBlockType extends AbstractBlockType  {
  private List<Instruction> instructions= new ArrayList<>();

  public CodeBlockType() {
  }

  public CodeBlockType(Block block) {
    this.block = block;
  }

  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends BlockType> type) {
    return block;
  }

  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  @Override
  public void accept(BlockRoleVisitor blockRoleVisitor) {
    blockRoleVisitor.visiting(this);
  }
}
