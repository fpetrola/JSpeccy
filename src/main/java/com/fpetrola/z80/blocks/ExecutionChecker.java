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
  private final Instruction lastInstruction;
  private final int pcValue;
  private final Block currentRoutine;

  public ExecutionChecker(Instruction instruction, Instruction lastInstruction, int pcValue, Block currentRoutine) {
    this.instruction = instruction;
    this.lastInstruction = lastInstruction;
    this.pcValue = pcValue;
    this.currentRoutine = currentRoutine;
  }

  private void jumpPerformed(int pc, int nextPC, Instruction instruction, Block block1) {
    Block block = block1;
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
          Block nextBlock = blockAtNextPc.getAppropriatedBlockFor(nextPC, 1, new CodeBlockType());
          block.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(pc, nextPC));
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
  }

  @Override
  public void visiting(CodeBlockType codeBlockType) {
    processCodeBlock(codeBlockType.getBlock());
  }

  @Override
  public void visiting(RoutineBlockType routineBlockType) {
    processCodeBlock(routineBlockType.getBlock());
  }

  private void processCodeBlock(Block block) {
    int length = instruction.getLength();
    BlocksManager blocksManager = block.getBlocksManager();

    int lastAddress = pcValue + length - 1;
    if (currentRoutine != null) {

      Block blockAt = blocksManager.findBlockAt(lastAddress);

      if (blockAt != currentRoutine) {
        Block split = blockAt.split(lastAddress, RoutineBlockType.class);
        currentRoutine.growBlockTo(lastAddress);
      }
//      if (currentRoutine.getRangeHandler().getEndAddress() > lastAddress) {
//        currentRoutine.split(lastAddress, RoutineBlockType.class);
//      }

    }

//    if (block.contains(pcValue)) {
//      if (!block.contains(lastAddress)) {
//        Block endBlock = blocksManager.findBlockAt(lastAddress);
//        Class<? extends BlockType> newBlock = endBlock instanceof UnknownBlockType ? UnknownBlockType.class : CodeBlockType.class;
//        Block endSplit = endBlock.split(lastAddress, "", newBlock);
//        block.getRangeHandler().chainedJoin(block, lastAddress + 1);
//      }
//    } else if (block.canTake(pcValue)) {
//      block.joinBlocksBetween(block, pcValue + length);
//    }
//
//    if (instruction instanceof JumpInstruction jumpInstruction) {
//      WordNumber nextPC = ((WordNumber) jumpInstruction.getNextPC());
//      if (nextPC != null) {
//        jumpPerformed(pcValue, nextPC.intValue(), instruction, block);
//      }
//    }
//
//    block.getRangeHandler().joinAdjacentIfRequired(pcValue, instruction, block);
  }

  @Override
  public void visiting(UnknownBlockType unknownBlockType) {
    Block block = unknownBlockType.getBlock();
//    Block split1 = block.split(pcValue + instruction.getLength() - 1, UnknownBlockType.class);
    Block split = block.split(pcValue - 1, RoutineBlockType.class);
    //Block codeBlock = unknownBlockType.getAppropriatedBlockFor(pcValue, instruction.getLength(), CodeBlockType.class);
    split.replaceType(new CodeBlockType());

    split.accept(this);
  }

  @Override
  public void visiting(DataBlockType dataBlockType) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
  }
}
