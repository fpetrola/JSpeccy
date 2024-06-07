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

public class ExecutionChecker implements BlockVisitor {
  private final Instruction instruction;
  private final int pcValue;

  public ExecutionChecker(Instruction instruction, int pcValue) {
    this.instruction = instruction;
    this.pcValue = pcValue;
  }

  public static void jumpPerformed(CodeBlock codeBlock, int pc, int nextPC, Instruction instruction) {
    if (!codeBlock.contains(nextPC)) {
      Block blockAtNextPc = codeBlock.blocksManager.findBlockAt(nextPC);

      if (codeBlock.blocksManager.getExecutionNumber() > 50000 && !(instruction instanceof Ret)) {
        int mainLoopAddress = codeBlock.blocksManager.getGameMetadata().mainLoopAddress;
        if (mainLoopAddress > 0) {
          Block mainLoopRoutine = codeBlock.blocksManager.findBlockAt(mainLoopAddress);
          if (blockAtNextPc == mainLoopRoutine) {
            codeBlock.blocksManager.incrementCycle();
            codeBlock.log("cycle:(" + codeBlock.blocksManager.getExecutionNumber() + "): " + instruction + " _ " + Helper.convertToHex(pc) + " -> " + Helper.convertToHex(nextPC));
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
            Block calledBlock = codeBlock.blocksManager.findBlockAt(pc);
            if (calledBlock.contains(pc + 1)) {
              calledBlock.split(pc, "RET", CodeBlock.class);
            }
          }
        } else {
          Block nextBlock = blockAtNextPc.getAppropriatedBlockFor(nextPC, 1, CodeBlock.class);
          codeBlock.referencesHandler.addBlockRelation(BlockRelation.createBlockRelation(pc, nextPC));
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
  }

  @Override
  public void visitingBlock(Block block) {
    throw new RuntimeException("Cannot execute instruction inside this type of block");
  }

  @Override
  public void visitingCodeBlock(CodeBlock codeBlock) {
    int length = instruction.getLength();

    if (codeBlock.contains(pcValue)) {
      int lastAddress = pcValue + length - 1;
      if (!codeBlock.contains(lastAddress)) {
        Block endBlock = codeBlock.blocksManager.findBlockAt(lastAddress);
        Class<? extends AbstractBlock> newBlock = endBlock instanceof UnknownBlock ? UnknownBlock.class : CodeBlock.class;
        Block endSplit = endBlock.split(lastAddress, "", newBlock);
        codeBlock.rangeHandler.chainedJoin(codeBlock, lastAddress + 1);
      }
    } else if (codeBlock.canTake(pcValue)) {
      codeBlock.joinBlocksBetween(codeBlock, pcValue + length);
    }

    if (instruction instanceof JumpInstruction jumpInstruction) {
      WordNumber nextPC = ((WordNumber) jumpInstruction.getNextPC());
      if (nextPC != null) {
        jumpPerformed(codeBlock, pcValue, nextPC.intValue(), instruction);
      }
    }

    codeBlock.rangeHandler.joinAdjacentIfRequired(pcValue, instruction, codeBlock);
  }

  @Override
  public void visitingUnknownBlock(UnknownBlock unknownBlock) {
    Block codeBlock = unknownBlock.getAppropriatedBlockFor(pcValue, instruction.getLength(), CodeBlock.class);
    codeBlock.accept(this);
  }

  @Override
  public void visitingDataBlock(DataBlock dataBlock) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
  }
}
