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
    if (contains(executionStepData.pcValue))
      rangeHandler.updateEndAddress(Math.max(rangeHandler.getEndAddress(), executionStepData.pcValue + instruction.getLength() - 1));
    else if (canTake(executionStepData.pcValue)) {
      Block startSplit = joinBlocksBetween(this, executionStepData.pcValue + instruction.getLength());
    }

    int nextPC = instruction.getNextPC();
    if (nextPC != -1) {
      jumpPerformed(executionStepData.pcValue, nextPC, instruction, executionStepData);
    }
    return null;
  }

  public void jumpPerformed(int pc, int nextPC, Instruction instruction, ExecutionStepData executionStepData) {
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
          Block nextBlock = blockAt.transformBlockRangeToType(executionStepData.instruction.getNextPC(), 1, CodeBlock.class);
          if (!nextBlock.getReferencedByBlocks().contains(this)) {
            this.addBlockRelation(new BlockRelation(new BlockReference(this, pc), new BlockReference(nextBlock, nextPC)));
          }
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
  }

  public Block transformBlockRangeToType(int pcValue, int length1, Class<? extends Block> type) {
    return this;
  }
}
