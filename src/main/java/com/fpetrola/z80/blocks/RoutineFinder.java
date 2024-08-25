package com.fpetrola.z80.blocks;

import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RoutineFinder implements BlockRoleVisitor {
  private Instruction instruction;
  private Instruction lastInstruction;
  private int pcValue;
  private Block currentRoutine;
  public static BlocksManager blocksManager;
  private int lastPc;

  public RoutineFinder(BlocksManager blocksManager) {
    this.blocksManager = blocksManager;
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

//        if (!(blockAt instanceof UnknownBlockType))
//          System.out.println("eh!!!");

        if (blockAt.getBlockType() instanceof RoutineBlockType) {
          System.out.println("es rutina!");
        } else {
          Block split = blockAt.split(lastAddress, UnknownBlockType.class);
          currentRoutine.growBlockTo(lastAddress);
        }
      }
    }
  }

  @Override
  public void visiting(UnknownBlockType unknownBlockType) {
    Block block = unknownBlockType.getBlock();
//    Block split1 = block.split(pcValue + instruction.getLength() - 1, UnknownBlockType.class);
    Block split = block.split(pcValue - 1, UnknownBlockType.class);
    //Block codeBlock = unknownBlockType.getAppropriatedBlockFor(pcValue, instruction.getLength(), CodeBlockType.class);
    //split.replaceType(new CodeBlockType());

    currentRoutine.accept(this);
  }

  @Override
  public void visiting(DataBlockType dataBlockType) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
  }

  public void checkExecution(Instruction instruction, int pcValue) {
    this.instruction = instruction;
    this.pcValue = pcValue;

//    if (currentRoutine != null && !currentRoutine.contains(pcValue) && (currentRoutine.getBlockType() instanceof RoutineBlockType))
//      System.out.println("mala mia");

    if (pcValue == 36821) {
      System.out.println("");
    }
//    if (pcValue == 35208) {
//      System.out.println("");
//    }

    if (lastInstruction instanceof Call /*|| lastInstruction instanceof JP*/) {
      ConditionalInstruction conditionalInstruction = (ConditionalInstruction) lastInstruction;
      WordNumber nextPC = conditionalInstruction.getNextPC();
      if (nextPC != null) {
        int nextPCValue = nextPC.intValue();
        //        if (i != executionStep.pcValue) {
//          System.out.println("error!");
//        }

        boolean create = !(lastInstruction instanceof JP) || conditionalInstruction.getCondition() instanceof ConditionAlwaysTrue;// || !currentRoutine.contains(nextPCValue);
        if (create) {
          //System.out.println(lastPc + " - CALL: " + nextPCValue + " FROM: " + currentRoutine);
          createCurrentRoutine(nextPCValue);
        }
      }
    }
    if (instruction instanceof Ret ret) {
      WordNumber nextPC = ret.getNextPC();
      if (nextPC != null) {
        int i = nextPC.intValue();

        // System.out.println(pcValue + " - RET: " + i + " FROM: " + currentRoutine);

        if (currentRoutine != null)
          ((RoutineBlockType) currentRoutine.getBlockType()).finished = true;
        Block blockAt = blocksManager.findBlockAt(i - 1);
        if (blockAt.getBlockType() instanceof RoutineBlockType || blockAt.getBlockType() instanceof CodeBlockType) {
          setCurrentRoutine(blockAt);
          lastInstruction = instruction;
          lastPc = pcValue;
          return;
        }
      }
    }

    if (currentRoutine == null) {
      createCurrentRoutine(pcValue);
    }
    Block currentBlock = blocksManager.findBlockAt(pcValue);
    currentRoutine.accept(this);

    lastInstruction = instruction;
    lastPc = pcValue;
  }

  private void createCurrentRoutine(int nextPcValue) {
    Block blockAt = this.blocksManager.findBlockAt(nextPcValue);
    if (blockAt == null || (!(blockAt.getBlockType() instanceof RoutineBlockType) || !isFinished(blockAt)))
      if (blockAt.getRangeHandler().getStartAddress() != nextPcValue && blockAt.getBlockType().canSplit()) {
        Block blockAt1 = blockAt.split(nextPcValue, UnknownBlockType.class);
        blockAt = blockAt.split(nextPcValue - 1, RoutineBlockType.class);
      } else {
        if (blockAt.getBlockType() instanceof UnknownBlockType) {
          Block blockAt1 = blockAt.split(nextPcValue, UnknownBlockType.class);
          blockAt.setType(new RoutineBlockType());
        }
      }
    Block lastCurrentRoutine = blocksManager.findBlockAt(lastPc);
    setCurrentRoutine(blockAt);

    if (lastCurrentRoutine != null)
      lastCurrentRoutine.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(lastCurrentRoutine.getRangeHandler().getStartAddress(), nextPcValue));
  }

  private boolean isFinished(Block blockAt) {
    return ((RoutineBlockType) blockAt.getBlockType()).finished;
  }

  public void setCurrentRoutine(Block currentRoutine) {
    if (currentRoutine.getBlockType() instanceof UnknownBlockType)
      System.out.println("UnknownBlockType!!");

    this.currentRoutine = currentRoutine;
  }
}
