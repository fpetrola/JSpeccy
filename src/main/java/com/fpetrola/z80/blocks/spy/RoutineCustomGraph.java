package com.fpetrola.z80.blocks.spy;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlockChangesListener;
import com.fpetrola.z80.graph.CustomGraph;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoutineCustomGraph extends CustomGraph {
  private static mxGraph graph;
  private static Map<Block, mxCell> routinesVertices = new HashMap<>();

  public RoutineCustomGraph(mxGraph graph) {
    this.graph = graph;
  }

  public static class GraphBlockChangesListener implements BlockChangesListener {

    private int id;

    public void removingKnownBlock(Block block, Block calledBlock) {
      runOnSwing(() -> {
        System.out.println("graph: removing block references: " + block.getName() + " -> " + calledBlock.getName());

        mxCell routineVertex = routinesVertices.get(block);
        mxCell calledRoutineVertex = routinesVertices.get(calledBlock);

        Object[] edgesBetween = graph.getEdgesBetween(routineVertex, calledRoutineVertex);

        for (Object object : edgesBetween) {
          routineVertex.removeEdge((mxICell) object, true);
          if (calledRoutineVertex != null) {
            calledRoutineVertex.removeEdge((mxICell) object, false);
          } else {
            System.out.println("why?");
          }
        }

        graph.removeCells(edgesBetween);
      });
    }

    public void removingBlock(Block block) {
      runOnSwing(() -> {

        System.out.println("graph: removing block: " + block.getName());

        mxCell routineVertex = routinesVertices.get(block);

        Object[] edges1 = graph.getEdges(routineVertex);

        for (Object object : edges1) {
          routineVertex.removeEdge((mxICell) object, true);
        }
        graph.removeCells(edges1);
        graph.removeCells(new mxCell[]{routineVertex});
        routinesVertices.remove(block);
      });
    }

    private static void runOnSwing(Runnable runnable) {
      try {
        runnable.run();
//        SwingUtilities.invokeLater(runnable);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    public void addingKnownBLock(Block block, Block calledBlock, int from) {
      runOnSwing(() -> {

        System.out.println("graph: adding block references: " + block.getName() + " -> " + calledBlock.getName());

        mxCell routineVertex = routinesVertices.get(block);
        mxCell calledRoutineVertex = routinesVertices.get(calledBlock);
//        String callType = executionStepData.instructionToString.contains("Call") ? "CALL" : "JUMP";

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
      });
    }

    public void addingBlock(Block block) {
      runOnSwing(() -> {

        System.out.println("graph: adding block: " + block.getName());

        mxCell newRoutineVertex = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", block.getName(), 50, 50, 200, 50);
        routinesVertices.put(block, newRoutineVertex);
      });
    }

    public void blockChanged(Block block) {
      runOnSwing(() -> {

        System.out.println("graph: changed block: " + block.getName());

        mxCell routineVertex = routinesVertices.get(block);
        StringBuffer stringBuffer = new StringBuffer();
        routineVertex.setValue(block.getName());
      });

    }

    @Override
    public void replaceBlock(Block oldBlock, Block newBlock) {
      runOnSwing(() -> {

        System.out.println("graph: replace block: " + oldBlock.getName() + " by: " + newBlock.getName());

        mxCell mxCell = routinesVertices.get(oldBlock);
        routinesVertices.put(newBlock, mxCell);
//      removingBlock(oldBlock);
//      routinesVertices.remove(oldBlock);
      });

    }
  }

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

  public CustomGraph convertGraph() {

    Set<Map.Entry<Block, mxCell>> entrySet = routinesVertices.entrySet();

    for (Map.Entry<Block, mxCell> entry : entrySet) {
      mxCell vertex = entry.getValue();

      System.out.println(vertex.getValue());
      addVertex(vertex.getId(), entry.getKey().getName());
      int edgeCount = vertex.getEdgeCount();
      for (int i = 0; i < edgeCount; i++) {
        mxICell edgeAt = vertex.getEdgeAt(i);

        mxICell sourceTerminal = edgeAt.getTerminal(true);
        mxICell targetTerminal = edgeAt.getTerminal(false);
        addEdge(sourceTerminal, targetTerminal, entry.getKey().getCallType());
      }
    }

    return this;
  }
}
