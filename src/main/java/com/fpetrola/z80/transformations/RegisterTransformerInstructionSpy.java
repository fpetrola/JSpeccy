package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.WrapperInstructionSpy;

import java.util.ArrayList;
import java.util.List;

public class RegisterTransformerInstructionSpy<T extends WordNumber> extends WrapperInstructionSpy<T> {

  private BlocksManager blocksManager = new BlocksManager(new NullBlockChangesListener());

  public List<Instruction<T>> getExecutedInstructions() {
    return executedInstructions;
  }

  private List<Instruction<T>> executedInstructions = new ArrayList<>();

  public RegisterTransformerInstructionSpy() {
  }

  public void beforeExecution(Instruction<T> instruction) {
    executedInstructions.add(instruction);
    super.beforeExecution(instruction);
  }

  @Override
  public void afterExecution(Instruction<T> instruction) {
    int instructionLength = instruction.getLength();
    if (instructionLength > 0) {
      instructionLength= 1;
      Register pc = state.getPc();
      T pcValue = (T) pc.read();

      int pcIntValue = pcValue.intValue();

      System.out.println(pcIntValue + " - " + instruction);

      Block foundBlock = blocksManager.findBlockAt(pcIntValue);

      if (foundBlock instanceof CodeBlockType codeBlockType) {
        Block split = codeBlockType.getBlock().split(pcValue.intValue(), "jump target", CodeBlockType.class);
      } else {


        if (pcIntValue >= 0) {
          Block previousBlock = blocksManager.findBlockAt(pcIntValue - 1);
          if (previousBlock instanceof Block codeBlock)
            if (!codeBlock.isCompleted() && codeBlock.canTake(pcIntValue)) {
              int start = pcIntValue;
              int end = pcIntValue + instructionLength - 1;
              codeBlock.growBlockTo(end);

//              Block endBlock = blocksManager.findBlockAt(end);
//              Block endSplit = endBlock.split(end, "", UnknownBlockType.class);
//              Block startBlock = blocksManager.findBlockAt(start);
//              Block startSplit = startBlock.split(start, "", UnknownBlockType.class);
//              Block endBlock2 = blocksManager.findBlockAt(end);
//
//              startSplit = codeBlock.joinBlocksBetween(codeBlock, end);
              foundBlock = codeBlock;
            }
        }
        foundBlock.accept(new ExecutionTracker(instruction, pcIntValue));
      }
    }
    super.afterExecution(instruction);
  }
}
