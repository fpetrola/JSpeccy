package com.fpetrola.z80.graph;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.view.mxGraph;

import java.util.*;

final class mxHierarchicalLayoutExtension extends mxHierarchicalLayout {
  mxHierarchicalLayoutExtension(mxGraph graph) {
    super(graph);
  }

  public void run(Object parent) {
    // Separate out unconnected hierarchies
    List<Set<Object>> hierarchyVertices = new ArrayList<Set<Object>>();
    Set<Object> allVertexSet = new LinkedHashSet<Object>();

    if (this.roots == null && parent != null) {
      Set<Object> filledVertexSet = filterDescendants(parent);

      this.roots = new ArrayList<Object>();

      while (!filledVertexSet.isEmpty()) {
        List<Object> candidateRoots = findRoots(parent, filledVertexSet);

        for (Object root : candidateRoots) {
          Set<Object> vertexSet = new LinkedHashSet<Object>();
          hierarchyVertices.add(vertexSet);

          traverse(root, true, null, allVertexSet, vertexSet, hierarchyVertices, filledVertexSet);
        }

        this.roots.addAll(candidateRoots);
      }
    } else {
      // Find vertex set as directed traversal from roots

      for (int i = 0; i < roots.size(); i++) {
        Set<Object> vertexSet = new LinkedHashSet<Object>();
        hierarchyVertices.add(vertexSet);

        traverse(roots.get(i), true, null, allVertexSet, vertexSet, hierarchyVertices, null);
      }
    }

    // Iterate through the result removing parents who have children in this layout

    // Perform a layout for each separate hierarchy
    // Track initial coordinate x-positioning
    double initialX = 0;
    Iterator<Set<Object>> iter = hierarchyVertices.iterator();

    while (iter.hasNext()) {
      Set<Object> vertexSet = iter.next();

      this.model = new mxGraphHierarchyModel2(this, vertexSet.toArray(), roots, parent);

      cycleStage(parent);
      layeringStage();
      crossingStage(parent);
      initialX = placementStage(initialX, parent);
    }
  }
}