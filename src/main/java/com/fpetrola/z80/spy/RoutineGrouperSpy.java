package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.fpetrola.z80.graph.*;
import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.MemoryImplementation;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;

public class RoutineGrouperSpy extends AbstractInstructionSpy implements InstructionSpy {
  private static final String FILE_TRACE_JSON = "game-trace.json";

  private final class ExecutionStepAddressRange extends ExecutionStepData {
    private final AddressRange addressRange;

    private ExecutionStepAddressRange(AddressRange addressRange) {
      super(memory);
      this.addressRange = addressRange;
      instructionToString = "sprite: " + addressRange.getName();
    }

    public int hashCode() {
      return System.identityHashCode(addressRange);
    }
  }

  private CustomGraph customGraph;
  private State state;
  private GraphFrame graphFrame;
  private int id;
  private int ins;
  private int id2;
  private Map<AddressRange, mxCell> vertexForRange = new HashMap<AddressRange, mxCell>();
  private BlocksManager blocksManager;
  private Map<Block, mxCell> routinesVertexs = new HashMap<Block, mxCell>();
  private mxGraph graph;
  private List<String> visitedPCs = new ArrayList<>();

  public RoutineGrouperSpy(GraphFrame graphFrame, MemoryImplementation memory) {
    super(memory);
    this.graphFrame = graphFrame;
    graph = graphFrame.graph;
    blocksManager = new BlocksManager(new BlockChangesListener() {
      public void removingRoutineCall(Block block, Block calledBlock) {
        mxCell routineVertex = routinesVertexs.get(block);
        mxCell calledRoutineVertex = routinesVertexs.get(calledBlock);

        Object[] edgesBetween = graph.getEdgesBetween(routineVertex, calledRoutineVertex);

        for (Object object : edgesBetween) {
          routineVertex.removeEdge((mxICell) object, true);
          if (calledRoutineVertex != null)
            calledRoutineVertex.removeEdge((mxICell) object, false);
          else
            System.out.println("why?");
        }

      }

      public void removingBlock(Block block) {
        mxCell routineVertex = routinesVertexs.get(block);

        Object[] edges = graph.getEdges(routineVertex);

        for (Object object : edges) {
          routineVertex.removeEdge((mxICell) object, true);
        }

        routineVertex.removeFromParent();
        routinesVertexs.remove(block);
      }

      public void addingKnownBLock(Block block, Block calledBlock, int from) {
        mxCell routineVertex = routinesVertexs.get(block);
        mxCell calledRoutineVertex = routinesVertexs.get(calledBlock);
        String callType = executionStepData.instructionToString.contains("Call") ? "CALL" : "JUMP";

        Object[] edgesBetween = graph.getEdgesBetween(routineVertex, calledRoutineVertex);
        if (edgesBetween.length == 0) {

          String style = "edgeStyle=sideToSideEdgeStyle;elbow=vertical;orthogonal=0;";
//          style="";

          graph.insertEdge(graph.getDefaultParent(), id++ + "", calledBlock.getCallType(), routineVertex, calledRoutineVertex, style);

          if (calledRoutineVertex == null)
            System.out.println("why?");
//          if (calledRoutineVertex.getEdgeCount() > 100)
//            System.out.println("sdgsdg");
        }
      }

      public void addingRoutine(Block block) {
        mxCell newRoutineVertex = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", block.getName(), 50, 50, 200, 50);
        routinesVertexs.put(block, newRoutineVertex);
      }

      public void blockChanged(Block block) {
        mxCell routineVertex = routinesVertexs.get(block);
        StringBuffer stringBuffer = new StringBuffer();
        routineVertex.setValue(block.getName());
      }
    });
  }

  public void reset() {
    super.reset();
    initGraph();
  }

  private void initGraph() {
    customGraph = new CustomGraph() {
      protected String getVertexLabel(Object object) {
        if (object instanceof mxCell) {
          mxCell mxCell = (mxCell) object;
          return mxCell.getValue().toString();
        } else
          return object + "";
      }

      protected String getVertexId(Object object) {
        if (object instanceof mxCell) {
          mxCell mxCell = (mxCell) object;
          return mxCell.getId();
        } else
          return getVertexLabel(object);
      }
    };
  }

  public void end() {
    super.end();

    if (capturing) {
      executionStepDatas.clear();
      memoryChanges.clear();
      int nextPC = state.getNextPC();

      String key = executionStepData.pcValue + ":" + nextPC;
      if (!visitedPCs.contains(key)) {
//        double pX = Math.random() * 1000;
//        double pY = Math.random() * 1000;
//        String text = "PC:" + executionStepData.pcValue;
//
//        String instructionString = OOZ80.convertToHex(executionStepData.pcValue) + ": " + executionStepData.instruction.getBaseInstruction().toString() + "\n";

//        visitedPCs.add(key);
        Instruction baseInstruction = executionStepData.instruction.getBaseInstruction();

        if (nextPC >= 0) {
          boolean isConditional = baseInstruction instanceof ConditionalInstruction;
//          isConditional |= baseInstruction instanceof DJNZ;
          isConditional &= !(baseInstruction instanceof JR);
          isConditional |= baseInstruction instanceof Ret;

          isConditional &= !(baseInstruction instanceof JP) || Math.abs(nextPC - executionStepData.pcValue) < 100;
          if (isConditional) {
            String callType = executionStepData.instructionToString.contains("Call") ? "CALL" : "JUMP";
            boolean isRet = baseInstruction instanceof Ret;
            if (isRet) {
              Ret ret = (Ret) baseInstruction;
              boolean isConditionalRet = !(ret.getCondition() instanceof ConditionAlwaysTrue);
              if (!isConditionalRet)
                blocksManager.endBlock(nextPC, executionStepData.pcValue, false, new Routine());
            } else {
              blocksManager.addBlock(nextPC, executionStepData.pcValue, callType, new Routine());
            }
          }
        }

//        checkForDataReferences();
      }
    }

  }

  private void checkForDataReferences() {
    executionStepData.readMemoryReferences.forEach(rm -> {
      Block blockForData = blocksManager.findBlockAt(rm.address);
      if (rm.address == 0xf41c)
        System.out.println("sdgsdg");
      int pcValue = executionStepData.pcValue;
      Block currentBlock = blocksManager.findBlockAt(pcValue);
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

  public void process() {
    int lastRoutinesNumber = 1;
    while (blocksManager.getBLocks().size() != lastRoutinesNumber) {
      lastRoutinesNumber = blocksManager.getBLocks().size();
      joinRoutines();
    }

    CustomGraph a = convertGraph();
    a.exportGraph();
  }

  private CustomGraph convertGraph() {

    Set<Entry<Block, mxCell>> entrySet = routinesVertexs.entrySet();

    for (Entry<Block, mxCell> entry : entrySet) {
      mxCell vertex = entry.getValue();

      System.out.println(vertex.getValue());
      customGraph.addVertex(vertex.getId(), entry.getKey().getName());
      int edgeCount = vertex.getEdgeCount();
      for (int i = 0; i < edgeCount; i++) {
        mxICell edgeAt = vertex.getEdgeAt(i);

        mxICell sourceTerminal = edgeAt.getTerminal(true);
        mxICell targetTerminal = edgeAt.getTerminal(false);
        customGraph.addEdge(sourceTerminal, targetTerminal, entry.getKey().getCallType());
      }
    }

    return customGraph;
  }

  private void joinRoutines() {
    blocksManager.getBLocks().stream().forEach(routine -> {
      if (routine != null) {
        List<Block> blocks = blocksManager.getBLocks().stream().filter(r2 -> r2.isCallingTo(routine)).collect(Collectors.toList());
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

  public void setState(State state) {
    this.state = state;
  }
}
