package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class ExecutionChecker implements BlockRoleVisitor {
  private final Instruction instruction;
  private final int pcValue;

  public ExecutionChecker(Instruction instruction, int pcValue) {
    this.instruction = instruction;
    this.pcValue = pcValue;
  }

  private void jumpPerformed(CodeBlockType codeBlockType, int pc, int nextPC, Instruction instruction) {
    Block block = codeBlockType.getBlock();
    BlocksManager blocksManager = block.getBlocksManager();
    if (!block.contains(nextPC)) {
      Block blockAtNextPc = blocksManager.findBlockAt(nextPC);

      if (blocksManager.getExecutionNumber() > 50000 && !(instruction instanceof Ret)) {
        int mainLoopAddress = blocksManager.getGameMetadata().mainLoopAddress;
        if (mainLoopAddress > 0) {
          Block mainLoopRoutine = blocksManager.findBlockAt(mainLoopAddress);
          if (blockAtNextPc == mainLoopRoutine) {
            blocksManager.incrementCycle();
            System.out.println("cycle:(" + blocksManager.getExecutionNumber() + "): " + instruction + " _ " + Helper.convertToHex(pc) + " -> " + Helper.convertToHex(nextPC));
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
            Block calledBlock = blocksManager.findBlockAt(pc);
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
    int length = instruction.getLength();
    Block block = codeBlockType.getBlock();
    BlocksManager blocksManager = block.getBlocksManager();

    if (block.contains(pcValue)) {
      int lastAddress = pcValue + length - 1;
      if (!block.contains(lastAddress)) {
        Block endBlock = blocksManager.findBlockAt(lastAddress);
        Class<? extends BlockType> newBlock = endBlock instanceof UnknownBlockType ? UnknownBlockType.class : CodeBlockType.class;
        Block endSplit = endBlock.split(lastAddress, "", newBlock);
        block.getRangeHandler().chainedJoin(block, lastAddress + 1);
      }
    } else if (block.canTake(pcValue)) {
      block.joinBlocksBetween(block, pcValue + length);
    }

    if (instruction instanceof JumpInstruction jumpInstruction) {
      WordNumber nextPC = ((WordNumber) jumpInstruction.getNextPC());
      if (nextPC != null) {
        jumpPerformed(codeBlockType, pcValue, nextPC.intValue(), instruction);
      }
    }

    block.getRangeHandler().joinAdjacentIfRequired(pcValue, instruction, codeBlockType.getBlock());
  }

  @Override
  public void visiting(UnknownBlockType unknownBlockType) {
    Block codeBlock = unknownBlockType.getAppropriatedBlockFor(pcValue, instruction.getLength(), CodeBlockType.class);
    codeBlock.accept(this);
  }

  @Override
  public void visiting(DataBlockType dataBlockType) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
  }
}
