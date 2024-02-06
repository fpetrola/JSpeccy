package com.fpetrola.z80.blocks.spy;

import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.graph.CustomGraph;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.jspeccy.ReadOnlyIOImplementation;
import com.fpetrola.z80.jspeccy.ReadOnlyMemoryImplementation;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.ExecutionStepData;
import com.fpetrola.z80.spy.InstructionSpy;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RoutineGrouperSpy extends AbstractInstructionSpy implements InstructionSpy {
  private static final String FILE_TRACE_JSON = "game-trace.json";

  private State state;
  private RoutineCustomGraph customGraph;
  private final GraphFrame graphFrame;

  private BlocksManager blocksManager;
  private List<String> visitedPCs = new ArrayList<>();

  private Queue<ExecutionStepData> stepsQueue=new CircularFifoQueue<>(50);
  private long executionNumber= 0;

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
      executionNumber++;
      executionStepDatas.clear();
      stepsQueue.add(executionStepData);
      memoryChanges.clear();
      blocksManager.setExecutionNumber(executionNumber);
      blocksManager.checkExecution(executionStepData);

//      executeMutantCode();
    }
  }

  private void executeMutantCode() {
    Memory memory1 = memorySpy.getMemory();
    try {
      if (!(executionStepData.instruction.getState().getIo() instanceof ReadOnlyIOImplementation)) {
        boolean isConditional = executionStepData.instruction instanceof ConditionalInstruction;
        if (isConditional) {
          z80.getState().getPc().write(executionStepData.pcValue);
          memorySpy.setMemory(new ReadOnlyMemoryImplementation(memory1));
          for (int i = 0; i < 10; i++) {
            z80.execute();
          }
        }
      }
    } catch (Exception e)
    {
      e.printStackTrace();
    }finally {
      memorySpy.setMemory(memory1);
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
