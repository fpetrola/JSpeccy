package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fpetrola.z80.graph.*;
import com.fpetrola.z80.mmu.State;
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
  private Map<AddressRange, mxCell> vertexForRange = new HashMap<AddressRange, mxCell>();
  private BlocksManager blocksManager;
  private Map<Block, mxCell> routinesVertexs = new HashMap<Block, mxCell>();
  private mxGraph graph;
  private List<String> visitedPCs = new ArrayList<>();

  public RoutineGrouperSpy(GraphFrame graphFrame) {
    this.graphFrame = graphFrame;
    graph = graphFrame.graph;
    blocksManager = new BlocksManager(new GraphBlockChangesListener());
  }

  public void reset(State state) {
    super.reset(state);
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
      blocksManager.checkExecution(executionStepData);
    }
  }

  public void process() {
    blocksManager.optimizeBlocks();
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

  public void setState(State state) {
    this.state = state;
    this.memory= state.getMemory();
  }

  private class GraphBlockChangesListener implements BlockChangesListener {
    public void removingKnownBlock(Block block, Block calledBlock) {
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

    public void addingBlock(Block block) {
      mxCell newRoutineVertex = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", block.getName(), 50, 50, 200, 50);
      routinesVertexs.put(block, newRoutineVertex);
    }

    public void blockChanged(Block block) {
      mxCell routineVertex = routinesVertexs.get(block);
      StringBuffer stringBuffer = new StringBuffer();
      routineVertex.setValue(block.getName());
    }
  }
}
