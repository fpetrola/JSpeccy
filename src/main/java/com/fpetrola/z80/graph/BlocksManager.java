package com.fpetrola.z80.graph;

import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.spy.ExecutionStepData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlocksManager {

  List<Block> blocks = new ArrayList<>();
  Collection<Branch> branches = new ArrayList<>();
  //  protected int startUserCode = 0x5B00;
  protected int startUserCode = 0x0000;
  BlockChangesListener blockChangesListener;

  public BlocksManager(BlockChangesListener blockChangesListener) {
    this.blockChangesListener = blockChangesListener;
    addBlock(new UnknownBlock(0, 0xFFFF, "WHOLE_MEMORY", this));
  }

  public Block findBlockAt(int address) {
    List<Block> foundBlocks = blocks.stream().filter(r -> r.getStartAddress() <= address && r.getEndAddress() >= address).collect(Collectors.toList());

    if (foundBlocks.size() != 1)
      System.out.println("findRoutineAt bug!");

    return foundBlocks.get(0);
  }

  public void addBlock(Block block) {
    blockChangesListener.addingBlock(block);
    blocks.add(block);
  }

  public boolean getOrCreateBranch(int address) {
    Optional<Branch> branch = branches.stream().filter(b -> b.getAddress() == address).findFirst();

    boolean result = branch.isPresent();
    if (!result)
      branches.add(new Branch(address));

    return result;
  }

  public void removeBlock(Block block) {
    blockChangesListener.removingBlock(block);
    blocks.remove(block);
  }

  private Block addBlock(int blockAddress, int currentPC, String callType, Block block) {
    boolean branchExists = getOrCreateBranch(blockAddress);

    if (blockAddress >= startUserCode && (branchExists || callType.equals("CALL"))) {
      Block calledBlock = findBlockAt(blockAddress);
      Block currentBlock = findBlockAt(currentPC);

      boolean isNewBlock = calledBlock.getStartAddress() < blockAddress;
      if (isNewBlock) {
        calledBlock = calledBlock.split(blockAddress, callType, block);
      }
      if (!calledBlock.getReferencedByBlocks().contains(currentBlock)) {
        currentBlock.addKnowBlock(calledBlock, currentPC);
      }
      return isNewBlock ? calledBlock : null;
    }

    return null;
  }

  public List<Block> getBlocks() {
    return new ArrayList<Block>(blocks);
  }

  public void endBlock(int nextPC, int pcValue, boolean b, Block blockType) {
    Block calledBlock = findBlockAt(pcValue);
    if (calledBlock.endAddress > (pcValue + 1))
      calledBlock.split(pcValue + 1, "RET", blockType);
  }

  public void jumpPerformed(int pc, int nextPC, Instruction instruction) {
    boolean isConditional = instruction instanceof ConditionalInstruction;
//          isConditional |= baseInstruction instanceof DJNZ;
    isConditional &= !(instruction instanceof JR);
    isConditional |= instruction instanceof Ret;

    isConditional &= !(instruction instanceof JP) || Math.abs(nextPC - pc) < 100;
    if (isConditional) {
      String callType = instruction.toString().contains("Call") ? "CALL" : "JUMP";
      boolean isRet = instruction instanceof Ret;
      if (isRet) {
        Ret ret = (Ret) instruction;
        boolean isConditionalRet = !(ret.getCondition() instanceof ConditionAlwaysTrue);
        if (!isConditionalRet)
          endBlock(nextPC, pc, false, new Routine());
      } else {
        addBlock(nextPC, pc, instruction.getClass().getSimpleName(), new Routine());
      }
    }
  }

  private void checkForDataReferences(ExecutionStepData executionStepData1) {
    executionStepData1.readMemoryReferences.forEach(rm -> {
      Block blockForData = findBlockAt(rm.address);
      if (rm.address == 0xf41c)
        System.out.println("sdgsdg");
      int pcValue = executionStepData1.pcValue;
      Block currentBlock = findBlockAt(pcValue);
      if (!(blockForData instanceof DataBlock)) {
        blockForData = blockForData.split(rm.address, "reading", new DataBlock());
        Block blockForData2 = blockForData.split(rm.address + 1, "reading", new DataBlock());

        if (blockForData == currentBlock)
          System.out.println("sdgsdg");
        if (!blockForData.getReferencedByBlocks().contains(currentBlock)) {
          currentBlock.addKnowBlock(blockForData, pcValue);
        } else
          System.out.println("sadgdsg");
      } else if (blockForData.getEndAddress() > rm.address + 1) {
//            Routine routineForData2 = routineForData.split(rm.address + 1, "reading", "Data");
//            currentRoutine.addCallingRoutine(routineForData, pcValue);
      }
    });
  }

  public void checkExecution(ExecutionStepData executionStepData) {
    Instruction instruction = executionStepData.instruction;
    if (instruction.getNextPC() != -1) {
      jumpPerformed(executionStepData.pcValue, instruction.getNextPC(), instruction);
    }
    //        checkForDataReferences();
  }

  public void joinRoutines() {
    getBlocks().stream().forEach(routine -> {
      if (routine != null) {
        List<Block> blocks = getBlocks().stream().filter(r2 -> r2.isCallingTo(routine)).collect(Collectors.toList());
        blocks.stream().filter(r -> r.getEndAddress() + 1 == routine.getStartAddress()).forEach(r -> {
          if ((routine.getReferencedByBlocks().size() == 1 && routine.getReferencedByBlocks().get(0).equals(r)))
            r.join(routine);
        });
      }
    });

//    routineManager.getRoutines().stream().forEach(routine -> {
//      if (routine != null) {
//        List<Block> blocks = routineManager.getRoutines().stream().filter(r2 -> (r2.getType().equals("Data") && routine.getType().equals("Data"))).collect(Collectors.toList());
//        blocks.stream().filter(r -> r.getEndAddress() + 1 == routine.getStartAddress()).forEach(r -> {
//          mxCell mxCell = routinesVertexs.get(r);
//          mxCell mxCell2 = routinesVertexs.get(routine);
//          if (mxCell.getEdgeCount() != 0 && mxCell2.getEdgeCount() != 0) {
//            System.out.println("que?");
//            mxICell terminal = mxCell.getEdgeAt(0).getTerminal(true);
//            mxICell terminal2 = mxCell2.getEdgeAt(0).getTerminal(true);
//            if (terminal == terminal2)
//              r.join(routine);
//          }
//        });
//      }
//    });
  }

  public void optimizeBlocks() {
    int lastRoutinesNumber = 1;
    while (getBlocks().size() != lastRoutinesNumber) {
      lastRoutinesNumber = getBlocks().size();
      joinRoutines();
    }
  }
}
