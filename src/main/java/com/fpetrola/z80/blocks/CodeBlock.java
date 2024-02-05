package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.spy.ExecutionStepData;

public class CodeBlock extends AbstractBlock {
  public CodeBlock() {
  }

  public CodeBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  @Override
  public Block checkExecution(ExecutionStepData executionStepData) {
    Instruction instruction = executionStepData.instruction;
    int nextPC = executionStepData.instruction.getNextPC();
    int pcValue = executionStepData.pcValue;
    int length = instruction.getLength();

    if (contains(pcValue)) {
      int lastAddress = pcValue + length - 1;
      if (!contains(lastAddress)) {
        Block endBlock = blocksManager.findBlockAt(lastAddress);
        Class<? extends AbstractBlock> newBlock = endBlock instanceof UnknownBlock ? UnknownBlock.class : CodeBlock.class;
        Block endSplit = endBlock.split(lastAddress, "", newBlock);
        rangeHandler.chainedJoin(this, lastAddress + 1);
      }
    } else if (canTake(pcValue)) {
      joinBlocksBetween(this, pcValue + length);
    }

    if (nextPC != -1) {
      jumpPerformed(pcValue, nextPC, instruction);
    }
    return null;
  }

  public void jumpPerformed(int pc, int nextPC, Instruction instruction) {
    if (!contains(nextPC)) {
      boolean isConditional = instruction instanceof ConditionalInstruction;
//          isConditional |= baseInstruction instanceof DJNZ;
      isConditional |= instruction instanceof JR;
      isConditional |= instruction instanceof Ret;

//      isConditional &= !(instruction instanceof JP) || Math.abs(nextPC - pc) < 100;
      if (isConditional) {
        String callType = instruction.toString().contains("Call") ? "CALL" : "JUMP";
        boolean isRet = instruction instanceof Ret;
        if (isRet) {
          Ret ret = (Ret) instruction;
          if (ret.getCondition() instanceof ConditionAlwaysTrue) {
            Block calledBlock = blocksManager.findBlockAt(pc);
            if (calledBlock.contains(pc + 1))
              calledBlock.split(pc, "RET", CodeBlock.class);
          }
        } else {
          Block blockAt = blocksManager.findBlockAt(nextPC);
          Block nextBlock = blockAt.getAppropriatedBlockFor(nextPC, 1, CodeBlock.class);
          if (!nextBlock.getReferencesHandler().getReferencedByBlocks().contains(this)) {
            referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(this, pc), new BlockReference(nextBlock, nextPC)));
          }
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
  }

  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type) {
    return this;
  }
}
