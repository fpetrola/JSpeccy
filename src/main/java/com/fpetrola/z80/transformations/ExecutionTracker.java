package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.*;
import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;

public class ExecutionTracker implements BlockRoleVisitor {
  private final Instruction instruction;
  private final int pcValue;

  public ExecutionTracker(Instruction instruction, int pcValue) {
    this.instruction = instruction;
    this.pcValue = pcValue;
  }

  private void jumpPerformed(CodeBlockType codeBlockType, int pc, int nextPC, Instruction instruction) {
    Block block = codeBlockType.getBlock();
    BlocksManager blocksManager = block.getBlocksManager();
    if (!block.contains(nextPC)) {
      Block blockAtNextPc = block.getBlocksManager().findBlockAt(nextPC);

      if (block.getBlocksManager().getExecutionNumber() > 50000 && !(instruction instanceof Ret)) {
        int mainLoopAddress = block.getBlocksManager().getGameMetadata().mainLoopAddress;
        if (mainLoopAddress > 0) {
          Block mainLoopRoutine = block.getBlocksManager().findBlockAt(mainLoopAddress);
          if (blockAtNextPc == mainLoopRoutine) {
            block.getBlocksManager().incrementCycle();
            System.out.println("cycle:(" + block.getBlocksManager().getExecutionNumber() + "): " + instruction + " _ " + Helper.convertToHex(pc) + " -> " + Helper.convertToHex(nextPC));
          }
        }
      }

      boolean isConditional = instruction instanceof ConditionalInstruction;
//          isConditional |= baseInstruction instanceof DJNZ;
      isConditional |= instruction instanceof JR;
      isConditional |= instruction instanceof Ret;
//      isConditional &= !(instruction instanceof JP) || Math.abs(nextPC - pc) < 100;
      isConditional &= !(instruction instanceof RepeatingInstruction);
      if (isConditional) {
        String callType = instruction.toString().contains("Call") ? "CALL" : "JUMP";
        boolean isRet = instruction instanceof Ret;
        if (isRet) {
          Ret ret = (Ret) instruction;
          if (ret.getCondition() instanceof ConditionAlwaysTrue) {
            Block calledBlock = block.getBlocksManager().findBlockAt(pc);
            if (calledBlock.contains(pc + 1)) {
              calledBlock.split(pc, "RET", CodeBlockType.class);
            }
          }
        } else {
          Block nextBlock = blockAtNextPc.getAppropriatedBlockFor(nextPC, 1, CodeBlockType.class);
          block.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(pc, nextPC));
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
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

      if (instruction instanceof JumpInstruction jumpInstruction) {
        codeBlockType.getBlock().setCompleted(true);
//      WordNumber nextPC = ((WordNumber) jumpInstruction.getNextPC());
//      if (nextPC != null) {
//        int address = nextPC.intValue() - 1;
//        Block blockAt = codeBlock.getBlocksManager().findBlockAt(address);
//        if (blockAt instanceof UnknownBlock) {
//          Block split = blockAt.split(address, "jump", CodeBlock.class);
//        }
//        jumpPerformed(codeBlock, pcValue, nextPC.intValue(), instruction);
//      }
      }

      //codeBlock.getRangeHandler().joinAdjacentIfRequired(pcValue, instruction, codeBlock);

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
    //   Block codeBlock = unknownBlockType.getAppropriatedBlockFor(pcValue, instruction.getLength(), CodeBlockType.class);
    split1.accept(this);
  }

  private int getInstructionLength() {
    return 1;//instruction.getLength();
  }

}
