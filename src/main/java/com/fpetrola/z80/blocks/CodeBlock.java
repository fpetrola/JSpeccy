package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.ExecutionStep;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.function.Predicate;

public class CodeBlock extends AbstractBlock {
  private boolean mainLoop;

  public CodeBlock() {
  }

  public CodeBlock(int startAddress, int endAddress, String callType, BlocksManager blocksManager) {
    super(startAddress, endAddress, callType, blocksManager);
  }

  @Override
  public Block checkExecution(ExecutionStep executionStep) {
    Instruction instruction = executionStep.instruction;
    int pcValue = executionStep.pcValue;
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

    if (executionStep.instruction instanceof JumpInstruction jumpInstruction) {
      WordNumber nextPC = ((WordNumber) jumpInstruction.getNextPC());
      if (nextPC != null) {
        jumpPerformed(pcValue, nextPC.intValue(), instruction);
      }
    }

    rangeHandler.joinAdjacentIfRequired(pcValue, instruction, this);
    return null;
  }

  public void jumpPerformed(int pc, int nextPC, Instruction instruction) {
    if (!contains(nextPC)) {
      Block blockAtNextPc = blocksManager.findBlockAt(nextPC);

      if (blocksManager.getExecutionNumber() > 50000 && !(instruction instanceof Ret)) {
        int mainLoopAddress = blocksManager.getGameMetadata().mainLoopAddress;
        if (mainLoopAddress > 0) {
          Block mainLoopRoutine = blocksManager.findBlockAt(mainLoopAddress);
          if (blockAtNextPc == mainLoopRoutine) {
            blocksManager.incrementCycle();
            log("cycle:(" + blocksManager.getExecutionNumber() + "): " + instruction + " _ " + Helper.convertToHex(pc) + " -> " + Helper.convertToHex(nextPC));
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
              calledBlock.split(pc, "RET", CodeBlock.class);
            }
          }
        } else {
          Block nextBlock = blockAtNextPc.getAppropriatedBlockFor(nextPC, 1, CodeBlock.class);
          referencesHandler.addBlockRelation(BlockRelation.createBlockRelation(pc, nextPC));
//        getBlocksManager().addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
        }
      }
    }
  }

  public Block getAppropriatedBlockFor(int pcValue, int length1, Class<? extends Block> type) {
    return this;
  }

  public void generateBytecode(InstructionSpy spy) {
    RandomAccessInstructionFetcher instructionFetcher = address1 -> spy.getFetchedAt(address1);
    Predicate<Integer> hasCodeChecker = address1 -> {
      boolean notCodeBlock = !(this.blocksManager.findBlockAt(address1) instanceof CodeBlock);
      boolean isNotFetched = instructionFetcher.getInstructionAt(address1) == null;
      return !(notCodeBlock || isNotFetched);
    };
    Register pc= null;//FIXME: get pc
    new ByteCodeGenerator(instructionFetcher, this.getRangeHandler().getStartAddress(), hasCodeChecker, 0xFFFF, pc).generate();
  }
}
