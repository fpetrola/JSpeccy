package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.PC;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.mxgraph.model.mxCell;

public class GraphExperiment {

  protected com.fpetrola.z80.GraphFrame graph;
  private List<String> labels = new ArrayList<>();
  protected Stack<String> callStack = new Stack<>();
  private Stack<String> callStack2 = null;
  private Map<String, Object> routineNode = new HashMap<>();
  private String lastRoutine = "#0";
  protected static Map<String, Map<String, Attribute>> edgeAttributes = new HashMap<>();
  protected static Map<String, Map<String, Attribute>> vertexAttributes = new HashMap<>();
  public static DefaultDirectedGraph<String, String> g2 = new DefaultDirectedGraph<>(String.class);
  private Object defaultParent;
  private String lastRoutine2;
  protected static RoutineManager routineManager = new RoutineManager();
  protected int startUserCode = 0x5B00;
  private Memory memory;

  public static void exportGraph() {
    try {
      DOTExporter<String, String> export = new DOTExporter<>();
      export.setEdgeAttributeProvider(new Function<String, Map<String, Attribute>>() {

        public Map<String, Attribute> apply(String t) {
          return edgeAttributes.get(t);
        }
      });

      export.setVertexAttributeProvider(new Function<String, Map<String, Attribute>>() {

        public Map<String, Attribute> apply(String t) {
          return vertexAttributes.get(t);
        }
      });
      export.exportGraph(g2, new FileWriter("graph.dot"));
    } catch (IOException e) {
    }
  }

  boolean isUserCode() {
    Register pc = OOZ80.state.getRegister(PC);

    return pc.read() > 16384;
  }

  private void extracted(OpCode opcode, int pcValue, OOZ80 z80) {
    Register pc = z80.state.getRegister(PC);

    // System.out.println(convertToHex(pcValue));

    boolean isUserCode = isUserCode();
//    if (isUserCode && inInterruption) {
//      // System.out.println("interruption");
//    }

    if (pcValue == 38276) {
      System.out.println("38276");
    }

    if (opcode instanceof Ld) {
      Ld ld = (Ld) opcode;
//      ld.setGraph(graph);
    }
    // System.out.println(read + ": " + opcode);

    // System.out.println(opcode);
    // pc.write(read+1);

    if (isUserCode) {
      if (opcode instanceof Call) {
        Call call = (Call) opcode;
        boolean conditionMet = call.condition.conditionMet();

        int lastPc = pc.read();
        // RegisterUtils.increment(pc, 1);
        int routineAddress = call.getTarget().read();

        if (conditionMet) {
          addRoutine(routineAddress, pcValue, true, "CALL");
        }
        pc.write(lastPc);
      }

      if (opcode instanceof JP) {
        JP jp = (JP) opcode;
        boolean conditionMet = jp.condition.conditionMet();
        jp.execute();
        int read2 = pc.read();
        int routineAddress = read2;
        if (OOZ80.convertToHex(routineAddress).equals("#AF81")) {
          System.out.println("");
        }
        if (conditionMet) {
          addRoutine(routineAddress, pcValue, false, "JP");
          pc.write(pcValue);
        }
      }

      // if (opcode instanceof JR) {
      // JR jp = (JR) opcode;
      // boolean conditionMet = jp.condition.conditionMet();
      // jp.execute();
      // int read2 = pc.read();
      // int routineAddress = read2;
      // if (conditionMet) {
      // addRoutine(routineAddress, read, false, "JR");
      // pc.write(read);
      // }
      // }

    }

    // if (isUserCode && opcode instanceof Ret) {
    // int execute = opcode.execute();
    // if (execute == 11)
    // if (callStack.isEmpty())
    // System.out.println("empty stack?");
    // else {
    // String pop = callStack.pop();
    // lastRoutine = callStack.isEmpty() ? "#0" : callStack.peek();
    // System.out.println("last routine despues de RET:" + lastRoutine);
    // }
    // }
  }

  public static void exportRoutinesAsGraph() {

    List<Routine> routines2 = new ArrayList<>(routineManager.routines);
    routines2.stream().forEach(routine -> {
      if (routine != null) {
        if (routine.startAddress == 26953) {
          System.out.println("adgasdfg");
        }
        List<Routine> routines = routines2.stream().filter(r2 -> r2.isCallingTo(routine)).collect(Collectors.toList());
        routines.stream().filter(r -> r.endAddress + 1 == routine.startAddress).forEach(r -> r.join(routine));
      } else
        System.out.println("null!");
    });

    routineManager.routines.stream().forEach(routine -> {
      g2.addVertex(routine.getName());
      HashMap<String, Attribute> vertextAttribute = new HashMap<>();
      vertextAttribute.put("label", new DefaultAttribute<String>(routine.getName(), AttributeType.STRING));
      vertexAttributes.put(routine.getName(), vertextAttribute);
    });

    routineManager.routines.stream().forEach(routine -> {
      routine.calling.entrySet().stream().forEach(callingEntry -> {
        String lastRoutine = routine.getName();
        Routine calledRoutine = callingEntry.getValue();
        String calledRoutineName = calledRoutine.getName();

        String id = lastRoutine + ":" + calledRoutineName;
        HashMap<String, Attribute> edgeAttribute = new HashMap<>();
        edgeAttribute.put("label", new DefaultAttribute<String>(calledRoutine.callType, AttributeType.STRING));
        edgeAttributes.put(id, edgeAttribute);
        if (vertexAttributes.containsKey(lastRoutine) && vertexAttributes.containsKey(calledRoutineName))
          g2.addEdge(lastRoutine, calledRoutineName, id);
      });
    });

    exportGraph();
  }

  private void addRoutine(int routineAddress, int currentPC, boolean stacking, String callType) {
    // boolean branchExists = routineManager.getOrCreateBranch(routineAddress);
    //
    // if (routineAddress >= startUserCode && (branchExists ||
    // callType.equals("CALL"))) {
    // Routine calledRoutine = routineManager.findRoutineAt(routineAddress);
    // Routine currentRoutine = routineManager.findRoutineAt(currentPC);
    //
    // if (calledRoutine.startAddress < routineAddress) {
    // calledRoutine = calledRoutine.split(routineAddress, callType);
    // }
    // currentRoutine.addCallingRoutine(calledRoutine, currentPC);
    // }
  }

  private void extracted1(int routineAddress, boolean stacking, String callType, String hexAddress) {
    defaultParent = graph.graph.getDefaultParent();
    String peek = callStack.isEmpty() ? null : callStack.peek();
    if (stacking)
      callStack.push(hexAddress);

    if (!routineManager.routines.contains(routineAddress)) {
      addVertex(routineAddress);
    }

    mxCell currentRoutine = (mxCell) routineNode.get(lastRoutine);
    mxCell calledRoutine = (mxCell) routineNode.get(hexAddress);

    String id = lastRoutine + ":" + calledRoutine.getValue().toString();
    if (!labels.contains(id)) {
      if (lastRoutine.equals("#B09A")) {
        System.out.println("#B09A");
      }
      graph.graph.insertEdge(defaultParent, null, callType, currentRoutine, calledRoutine);
      HashMap<String, Attribute> value = new HashMap<>();
      value.put("label", new DefaultAttribute<String>(callType, AttributeType.STRING));
      edgeAttributes.put(id, value);
      g2.addEdge(lastRoutine, calledRoutine.getValue().toString(), id);
      labels.add(id);
    }

    lastRoutine = hexAddress;
  }

  public GraphExperiment(Memory memory, GraphFrame graph2) {
    super();
    this.memory = memory;
    this.graph = graph2;
//    this.memory.setGraph(graph);
    callStack.push("#0");
    addVertex(0);
    routineManager.addRoutine(new Routine(startUserCode, 0xFFFF, "START", routineManager));
  }

  protected void addVertex(int routineAddress) {
    String convertToHex = OOZ80.convertToHex(routineAddress);
    g2.addVertex(convertToHex);
    Object v1 = graph.graph.insertVertex(defaultParent, null, convertToHex, Math.random() * 100, Math.random() * 100, 100, 30);
    HashMap<String, Attribute> value = new HashMap<>();
    value.put("label", new DefaultAttribute<String>(convertToHex, AttributeType.STRING));
    vertexAttributes.put(convertToHex, value);

    routineNode.put(convertToHex, v1);
    // routines.add(convertToHex);
  }

}