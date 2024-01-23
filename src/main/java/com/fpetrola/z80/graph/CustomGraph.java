package com.fpetrola.z80.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.DefaultInstructionSpy;
import com.fpetrola.z80.spy.ExecutionStepData;
import com.fpetrola.z80.spy.ReadOpcodeReference;

public class CustomGraph {

  protected Map<String, Map<String, Attribute>> edgeAttributes = new HashMap<>();
  protected Map<String, Map<String, Attribute>> vertexAttributes = new HashMap<>();
  public DefaultDirectedGraph<String, String> g2 = new DefaultDirectedGraph<>(String.class);
  private Map<String, String> vertexes = new HashMap<>();
  public int edges;
  DefaultInstructionSpy spy;

  public CustomGraph(DefaultInstructionSpy defaultInstructionSpy) {
    spy = defaultInstructionSpy;
  }

  public void exportGraph() {
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

  public void addVertex(String vertextId, String label) {
    g2.addVertex(vertextId);
    Map<String, Attribute> attributes = new HashMap<>();
    attributes.put("label", new DefaultAttribute<String>(label, AttributeType.STRING));
    vertexAttributes.put(vertextId, attributes);
  }

  protected String getVertexLabel(Object currentStep) {
    return currentStep.toString();
  }

  protected String getVertexId(Object currentStep) {
    return currentStep.toString();
  }

  private String addOrCreateVertex(Object currentStep, boolean alreadyCreated) {
    String id = getVertexId(currentStep);
    String a = vertexes.get(id);
    String label = getVertexLabel(currentStep);

    if (a == null) {
      addVertex(id, label);
      vertexes.put(id, label);
    } else {
      vertexAttributes.get(id).put("label", DefaultAttribute.createAttribute(label));
    }
    
    if (!alreadyCreated)
      addIxAccess(currentStep);
    return id;
  }

  private void addIxAccess(Object currentStep) {
    ExecutionStepData step = (ExecutionStepData) currentStep;
    List<ReadOpcodeReference> readReferences = step.readReferences;
    for (ReadOpcodeReference readOpcodeReference : readReferences) {
      if (readOpcodeReference.opcodeReference == RegisterName.IX) {
        int address = step.readMemoryReferences.isEmpty() ? readOpcodeReference.value : step.readMemoryReferences.get(1).address;
        spy.addRangeEdge(step, "ix", address, true);
//        System.out.println(OOZ80.convertToHex(address));
      }
      if (readOpcodeReference.opcodeReference == RegisterName.IY) {
        int address = step.readMemoryReferences.isEmpty() ? readOpcodeReference.value : step.readMemoryReferences.get(1).address;
        spy.addRangeEdge(step, "iy", address, true);
      }
    }
  }

  public void addEdge(Object sourceVertex, Object targetVertex, String label, boolean alreadyCreated) {
    String edgeName = edges++ + "";
    String sourceVertexId = addOrCreateVertex(sourceVertex, alreadyCreated);
    String targetVertexId = addOrCreateVertex(targetVertex, alreadyCreated);

    Map<String, Attribute> attributes = new HashMap<>();
    attributes.put("label", new DefaultAttribute<String>(label, AttributeType.STRING));
    edgeAttributes.put(edgeName, attributes);
    g2.addEdge(sourceVertexId, targetVertexId, edgeName);
  }

  public void mergeVertexWith(ExecutionStepData targetVertex, ExecutionStepData sourceVertex) {
    String targetVertexId = addOrCreateVertex(targetVertex, true);
    String sourceVertexId = addOrCreateVertex(sourceVertex, true);

    Set<String> sourceEdges = new HashSet<String>(g2.edgesOf(sourceVertexId));
    for (String edge : sourceEdges) {
      String edgeSource = g2.getEdgeSource(edge);
      String edgeTarget = g2.getEdgeTarget(edge);
      Map<String, Attribute> map = edgeAttributes.get(edge);
      Attribute attribute = map.get("label");
      String edgeName = edges++ + "";
      edgeAttributes.put(edgeName, map);
      g2.addEdge(targetVertexId, edgeTarget, edgeName);

      g2.removeEdge(edge);
    }

    g2.removeVertex(sourceVertexId);
  }

}