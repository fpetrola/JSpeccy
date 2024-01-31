package com.fpetrola.z80.blocks.spy;

import java.util.ArrayList;
import java.util.List;

import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.graph.*;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;

public class RoutineGrouperSpy extends AbstractInstructionSpy implements InstructionSpy {
  private static final String FILE_TRACE_JSON = "game-trace.json";

  private State state;
  private RoutineCustomGraph customGraph;
  private final GraphFrame graphFrame;

  private BlocksManager blocksManager;
  private List<String> visitedPCs = new ArrayList<>();

  public RoutineGrouperSpy(GraphFrame graphFrame) {
    this.graphFrame = graphFrame;
    initGraph();
    blocksManager = new BlocksManager(new RoutineCustomGraph.GraphBlockChangesListener());
  }

  public void reset(State state) {
    super.reset(state);
    initGraph();
  }

  private void initGraph() {
    customGraph = new RoutineCustomGraph(graphFrame.graph);
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
    CustomGraph a = customGraph.convertGraph();
    a.exportGraph();
  }

  public void setState(State state) {
    this.state = state;
    this.memory = state.getMemory();
  }
}
