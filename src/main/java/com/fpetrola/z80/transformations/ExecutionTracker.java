package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.*;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class ExecutionTracker implements BlockRoleVisitor {
  private final Instruction instruction;
  private final int pcValue;

  public ExecutionTracker(Instruction instruction, int pcValue) {
    this.instruction = instruction;
    this.pcValue = pcValue;
  }

  @Override
  public void visiting(CodeBlockType codeBlockType) {
    Block block = codeBlockType.getBlock();
    BlocksManager blocksManager = block.getBlocksManager();

    if (!codeBlockType.getBlock().isCompleted()) {
      int instructionLength = getInstructionLength();

      if (block.contains(pcValue)) {
        int lastAddress = pcValue + instructionLength - 1;
        if (!block.contains(lastAddress)) {
          block.growBlockTo(lastAddress + 1);
        }
      } else if (block.canTake(pcValue)) {
        block.growBlockTo(pcValue + instructionLength);
      }

      if (instruction instanceof ConditionalInstruction conditionalInstruction) {
        Block block1 = codeBlockType.getBlock();
        block1.setCompleted(true);
        WordNumber jumpAddress = conditionalInstruction.calculateJumpAddress();
      }

      codeBlockType.addInstruction(instruction);
    }
  }

  @Override
  public void visiting(UnknownBlockType unknownBlockType) {

    Block block = unknownBlockType.getBlock();
    if (block.canTake(pcValue)) {

    }
    Block split1 = block.split(pcValue-1, "", UnknownBlockType.class);
    int instructionLength = getInstructionLength();
    Block split = split1.split(pcValue + instructionLength - 1, "", UnknownBlockType.class);

    split1.setType(new CodeBlockType());
    split1.accept(this);
  }

  private int getInstructionLength() {
    return 1;//instruction.getLength();
  }

}
