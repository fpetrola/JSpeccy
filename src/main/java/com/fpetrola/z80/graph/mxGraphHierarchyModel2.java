/*
 * Copyright (c) 2005-2012, JGraph Ltd
 */
package com.fpetrola.z80.graph;

import java.util.LinkedHashMap;
import java.util.List;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyEdge;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyModel;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyNode;
import com.mxgraph.layout.hierarchical.model.mxGraphHierarchyRank;

public class mxGraphHierarchyModel2 extends mxGraphHierarchyModel {

  public mxGraphHierarchyModel2(mxHierarchicalLayout layout, Object[] vertices, List<Object> roots, Object parent) {
    super(layout, vertices, roots, parent);
  }

  public void fixRanks() {
    final mxGraphHierarchyRank[] rankList = new mxGraphHierarchyRank[maxRank + 1];
    ranks = new LinkedHashMap<Integer, mxGraphHierarchyRank>(maxRank + 1);

    for (int i = 0; i < maxRank + 1; i++) {
      rankList[i] = new mxGraphHierarchyRank();
      ranks.put(new Integer(i), rankList[i]);
    }

    // Perform a DFS to obtain an initial ordering for each rank.
    // Without doing this you would end up having to process
    // crossings for a standard tree.
    mxGraphHierarchyNode[] rootsArray = null;

    if (roots != null) {
      Object[] oldRootsArray = roots.toArray();
      rootsArray = new mxGraphHierarchyNode[oldRootsArray.length];

      for (int i = 0; i < oldRootsArray.length; i++) {
        Object node = oldRootsArray[i];
        mxGraphHierarchyNode internalNode = vertexMapper.get(node);
        rootsArray[i] = internalNode;
      }
    }

    visit(new mxGraphHierarchyModel2.CellVisitor() {
      public void visit(mxGraphHierarchyNode parent, mxGraphHierarchyNode cell, mxGraphHierarchyEdge connectingEdge, int layer, int seen) {
        mxGraphHierarchyNode node = cell;

        if (seen == 0 && node.maxRank < 0 && node.minRank < 0) {
          rankList[node.temp[0]].add(cell);
          node.maxRank = node.temp[0];
          node.minRank = node.temp[0];

          // Set temp[0] to the nodes position in the rank
          node.temp[0] = rankList[node.maxRank].size() - 1;
        }

        if (parent != null && connectingEdge != null) {
          int parentToCellRankDifference = (parent).maxRank - node.maxRank;

          if (parentToCellRankDifference > 1) {
            // There are ranks in between the parent and current cell
            mxGraphHierarchyEdge edge = connectingEdge;
            edge.maxRank = (parent).maxRank;
            edge.minRank = (cell).maxRank;
            edge.temp = new int[parentToCellRankDifference - 1];
            edge.x = new double[parentToCellRankDifference - 1];
            edge.y = new double[parentToCellRankDifference - 1];

            for (int i = edge.minRank + 1; i < edge.maxRank; i++) {
              // The connecting edge must be added to the
              // appropriate ranks
              rankList[i].add(edge);
              edge.setGeneralPurposeVariable(i, rankList[i].size() - 1);
            }
          }
        }
      }
    }, rootsArray, false, null);
  }

}
