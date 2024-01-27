package com.fpetrola.z80.spy;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.graph.CustomGraph;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.graph.Routine;
import com.fpetrola.z80.graph.RoutineChangesListener;
import com.fpetrola.z80.graph.RoutineManager;
import com.fpetrola.z80.instructions.DJNZ;
import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.MemoryImplementation;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
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
  private mxCell v1;
  private int ins;
  private int id2;
  private Map<AddressRange, mxCell> vertexForRange = new HashMap<AddressRange, mxCell>();
  private RoutineManager routineManager;
  private Map<Routine, mxCell> routinesVertexs = new HashMap<Routine, mxCell>();
  private mxGraph graph;
  private List<String> visitedPCs = new ArrayList<>();

  public RoutineGrouperSpy(GraphFrame graphFrame, MemoryImplementation memory) {
    super(memory);
    this.graphFrame = graphFrame;
    graph = graphFrame.graph;
    routineManager = new RoutineManager(new RoutineChangesListener() {
      public void removingRoutineCall(Routine routine, Routine calledRoutine) {
        mxCell routineVertex = routinesVertexs.get(routine);
        mxCell calledRoutineVertex = routinesVertexs.get(calledRoutine);
        routineVertex.removeEdge(calledRoutineVertex, true);
        calledRoutineVertex.removeEdge(routineVertex, false);
      }

      public void removingRoutine(Routine routine) {
        mxCell routineVertex = routinesVertexs.get(routine);

        while (routineVertex.getEdgeCount() > 0) {
          mxICell edgeAt = routineVertex.getEdgeAt(0);
          edgeAt.removeFromTerminal(true);
          edgeAt.removeFromTerminal(false);
          edgeAt.removeFromParent();
        }
        routineVertex.removeFromParent();
        routinesVertexs.remove(routine);
      }

      public void addingRoutineCall(Routine routine, Routine calledRoutine, int from) {
        mxCell routineVertex = routinesVertexs.get(routine);
        mxCell calledRoutineVertex = routinesVertexs.get(calledRoutine);
        String callType = executionStepData.instructionToString.contains("Call") ? "CALL" : "JUMP";

        Object[] edgesBetween = graph.getEdgesBetween(routineVertex, calledRoutineVertex);
        if (edgesBetween.length == 0) {

          String style = "edgeStyle=elbowEdgeStyle;elbow=vertical;orthogonal=0;";
          style="";

          graph.insertEdge(graph.getDefaultParent(), id++ + "", callType, routineVertex, calledRoutineVertex, style);
        }
      }

      public void addingRoutine(Routine routine) {
        mxCell newRoutineVertex = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", routine.getName(), 50, 50, 200, 50);
        routinesVertexs.put(routine, newRoutineVertex);
      }

      public void routineChanged(Routine routine) {
        mxCell routineVertex = routinesVertexs.get(routine);
        routineVertex.setValue(routine.getName());
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
                routineManager.endRoutine(nextPC, executionStepData.pcValue, false, callType);
            } else {
              routineManager.addRoutine(nextPC, executionStepData.pcValue, false, callType);
            }
          }
        }

//        executionStepData.readMemoryReferences.forEach(rm -> {
//          AddressRange addressRangeFor = getAddressRangeFor(rm.address, executionStepData);
//          mxCell v3 = vertexForRange.get(addressRangeFor);
//          if (v3 == null) {
//            v3 = (mxCell) graph.insertVertex(graph.getDefaultParent(), id2++ + "", addressRangeFor.getName(), 800, 50, 100, 50);
//            vertexForRange.put(addressRangeFor, v3);
//          }
//
//          Object[] edgesBetween = graph.getEdgesBetween(v1, v3);
//          if (edgesBetween.length == 0)
//            graph.insertEdge(graph.getDefaultParent(), id2++ + "", "reading", v1, v3);
//        });

//        if (v1 == null) {
//          v1 = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", new StringBuffer(instructionString), 800, 50, 100, 300);
//          ins = 1;
//        } else {
//          if (instructionString.contains("Call")) {
//            mxCell v2 = v1;
//            mxGeometry geometry = v1.getGeometry();
//            geometry.setHeight(15 * ins);
//            v1.setGeometry(geometry);
//            v1 = (mxCell) graph.insertVertex(graph.getDefaultParent(), id++ + "", new StringBuffer(instructionString), 800, 50, 150, 300);
//            graph.insertEdge(graph.getDefaultParent(), id++ + "", "call", v2, v1);
//            ins = 1;
//          } else {
//            StringBuffer value = (StringBuffer) v1.getValue();
//            value.append(instructionString);
//            v1.setValue(value);
//          }
//          ins++;
//        }
      }
    }
  }

  public void process() {
    int lastRoutinesNumber = 1;
    while (routineManager.getRoutines().size() != lastRoutinesNumber) {
      lastRoutinesNumber = routineManager.getRoutines().size();
      joinRoutines();
    }

    CustomGraph a = convertGraph();
    a.exportGraph();
  }

  private CustomGraph convertGraph() {

    Set<Entry<Routine, mxCell>> entrySet = routinesVertexs.entrySet();

    for (Entry<Routine, mxCell> entry : entrySet) {
      mxCell vertex = entry.getValue();

      System.out.println(vertex.getValue());
      customGraph.addVertex(vertex.getId(), entry.getKey().getName());
      int edgeCount = vertex.getEdgeCount();
      for (int i = 0; i < edgeCount; i++) {
        mxICell edgeAt = vertex.getEdgeAt(i);

        mxICell sourceTerminal = edgeAt.getTerminal(true);
        mxICell targetTerminal = edgeAt.getTerminal(false);
        customGraph.addEdge(sourceTerminal, targetTerminal, "call");
      }
    }

    return customGraph;
  }

  private void joinRoutines() {
    routineManager.getRoutines().stream().forEach(routine -> {
      if (routine != null) {
        List<Routine> routines = routineManager.getRoutines().stream().filter(r2 -> r2.isCallingTo(routine)).collect(Collectors.toList());
        routines.stream().filter(r -> r.getEndAddress() + 1 == routine.getStartAddress()).forEach(r -> {
          r.join(routine);
        });
      }
    });
  }

  public void setState(State state) {
    this.state = state;
  }
}
