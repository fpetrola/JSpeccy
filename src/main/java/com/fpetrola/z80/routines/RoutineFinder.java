package com.fpetrola.z80.routines;

import com.fpetrola.z80.blocks.*;
import com.fpetrola.z80.blocks.ranges.RangeHandler;
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
  private Routine currentRoutine;
  public static BlocksManager blocksManager;
  private int lastPc;
  public RoutineManager routineManager = new RoutineManager();

  public RoutineFinder(BlocksManager blocksManager) {
    this.blocksManager = blocksManager;
  }

  @Override
  public void visiting(CodeBlockType codeBlockType) {
    processCodeBlock(codeBlockType.getBlock());
  }

  private void processCodeBlock(Block block) {
    int length = instruction.getLength();
    BlocksManager blocksManager = block.getBlocksManager();

    int lastAddress = pcValue + length - 1;
    if (currentRoutine != null) {
      Routine routineAt = routineManager.findRoutineAt(lastAddress);

      if (routineAt != currentRoutine) {
        Block blockAt = blocksManager.findBlockAt(lastAddress);

        if (blockAt.getBlockType() instanceof CodeBlockType codeBlockType) {
          RangeHandler rangeHandler = blockAt.getRangeHandler();
          DefaultBlock routineBlock = new DefaultBlock(rangeHandler.getStartAddress(), rangeHandler.getEndAddress(), "", blocksManager);
          routineBlock.setType(codeBlockType);
          codeBlockType.setBlock(routineBlock);
          currentRoutine.addInnerRoutine(new Routine(routineBlock));
          blockAt.setType(new UnknownBlockType());
        } else {
          Block split = blockAt.split(lastAddress, UnknownBlockType.class);
          currentRoutine.growTo(pcValue, length);
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

    currentRoutine.findNearestBlock(pcValue).accept(this);
  }

  @Override
  public void visiting(DataBlockType dataBlockType) {
//    System.out.println("Mutable code?: " + executionStep.pcValue);
  }

  public void checkExecution(Instruction instruction, int pcValue) {

    try {
      this.instruction = instruction;
      this.pcValue = pcValue;

//    if (currentRoutine != null && !currentRoutine.contains(pcValue) && (currentRoutine.getBlockType() instanceof RoutineBlockType))
//      System.out.println("mala mia");

      if (pcValue == 35036) {
        System.err.print("");
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
            createCurrentRoutine(nextPCValue, instruction.getLength());
          }

          return;
        }
      }
      if (instruction instanceof Ret ret) {
        WordNumber nextPC = ret.getNextPC();
        if (nextPC != null) {
          int i = nextPC.intValue();

          // System.out.println(pcValue + " - RET: " + i + " FROM: " + currentRoutine);

          Block blockAt = blocksManager.findBlockAt(i - 1);
          if (blockAt.getBlockType() instanceof CodeBlockType) {
            setCurrentRoutine(blockAt, "Back to: ");
            return;
          }
        }
      }

      if (currentRoutine == null) {
        createCurrentRoutine(pcValue, instruction.getLength());
      } else {
        Block currentBlock = blocksManager.findBlockAt(pcValue);
        Block nearestBlock = currentRoutine.findNearestBlock(pcValue);
        if (currentBlock.getBlockType() instanceof UnknownBlockType)
          if (nearestBlock == null) {
            Block blockAt = this.blocksManager.findBlockAt(pcValue);
            Block blockAt1 = blockAt.split(pcValue + instruction.getLength() - 1, UnknownBlockType.class);
            Block blockAt2 = blockAt.split(pcValue - 1, CodeBlockType.class);
            blockAt2.setType(new CodeBlockType());
            currentRoutine.addBlock(blockAt2);
          } else
            nearestBlock.accept(this);
      }
    } finally {
      routineManager.optimizeAll();
      lastInstruction = instruction;
      lastPc = pcValue;
    }
  }

  private Routine createCurrentRoutine(int nextPcValue, int length) {
    Block blockAt = this.blocksManager.findBlockAt(nextPcValue);
    if (blockAt == null || (!(blockAt.getBlockType() instanceof CodeBlockType)))
      if (blockAt.getRangeHandler().getStartAddress() != nextPcValue && blockAt.getBlockType().canSplit()) {
        Block blockAt1 = blockAt.split(nextPcValue + length - 1, UnknownBlockType.class);
        blockAt = blockAt.split(nextPcValue - 1, CodeBlockType.class);
      } else {
        if (blockAt.getBlockType() instanceof UnknownBlockType) {
          Block blockAt1 = blockAt.split(nextPcValue, UnknownBlockType.class);
          blockAt.setType(new CodeBlockType());
        }
      }
    Block lastCurrentRoutine = blocksManager.findBlockAt(lastPc);
    Routine routine = setCurrentRoutine(blockAt, "Enter: ");

    if (lastCurrentRoutine != null)
      lastCurrentRoutine.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(lastCurrentRoutine.getRangeHandler().getStartAddress(), nextPcValue));
    return routine;
  }

  public Routine setCurrentRoutine(Block currentBlock, String message) {
    if (currentBlock.getBlockType() instanceof UnknownBlockType)
      System.out.println("UnknownBlockType!!");

    if (this.currentRoutine == currentBlock)
      System.out.print("same!");

    Routine routineAt = routineManager.findRoutineAt(currentBlock.getRangeHandler().getStartAddress());
    if (routineAt == null) {
      routineAt = new Routine(currentBlock);
      routineManager.addRoutine(routineAt);
    }
    System.out.println(this.currentRoutine + "->" + routineAt);
    this.currentRoutine = routineAt;

    return currentRoutine;
  }
}
