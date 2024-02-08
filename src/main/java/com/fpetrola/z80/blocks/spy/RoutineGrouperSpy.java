package com.fpetrola.z80.blocks.spy;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonGrammar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.graph.CustomGraph;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.jspeccy.ReadOnlyIOImplementation;
import com.fpetrola.z80.jspeccy.ReadOnlyMemoryImplementation;
import com.fpetrola.z80.metadata.GameMetadata;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.ExecutionStep;
import com.fpetrola.z80.spy.InstructionSpy;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RoutineGrouperSpy extends AbstractInstructionSpy implements InstructionSpy {
  private static final String FILE_TRACE_JSON = "game-metadata.json";
  private GameMetadata gameMetadata;

  private State state;
  private RoutineCustomGraph customGraph;
  private final GraphFrame graphFrame;

  private BlocksManager blocksManager;
  private List<String> visitedPCs = new ArrayList<>();

  private Queue<ExecutionStep> stepsQueue = new CircularFifoQueue<>(1000);
  private long executionNumber = 0;
  private String gameName;

  public RoutineGrouperSpy(GraphFrame graphFrame) {
    this.graphFrame = graphFrame;
    initGraph();
    blocksManager = new BlocksManager(new RoutineCustomGraph.GraphBlockChangesListener());
  }

  public void setGameMetadata(GameMetadata gameMetadata) {
    this.gameMetadata = gameMetadata;
    blocksManager.setGameMetadata(gameMetadata);
  }

  private void importMetadata() {
    if (gameName != null)
      try {
        ObjectMapper objectMapper = new ObjectMapper();

        Jankson jankson = Jankson.builder().build();
        setGameMetadata(jankson.fromJson(jankson.load(new File(getMetadataFileName())), GameMetadata.class));
//        gameMetadata = objectMapper.readValue(new File(getMetadataFileName()), GameMetadata.class);
      } catch (Exception e) {
        GameMetadata gameMetadata = new GameMetadata();
        gameMetadata.mainLoopAddress = -1;
        setGameMetadata(gameMetadata);
        exportMetadata(gameMetadata);
      }
  }

  private void exportMetadata(GameMetadata gameMetadata) {
    try {
      FileWriter writer = new FileWriter(getMetadataFileName());
      Jankson.builder().build().toJson(gameMetadata).toJson(writer, JsonGrammar.JSON5, 0);
      writer.close();

      //      ObjectMapper objectMapper = new ObjectMapper();
//      objectMapper.writeValue(new File(getMetadataFileName()), gameMetadata);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getMetadataFileName() {
    return gameName + ".metadata.json";
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
      executionSteps.clear();
      stepsQueue.add(executionStep);
      memoryChanges.clear();
      blocksManager.setExecutionNumber(executionNumber);
      blocksManager.checkExecution(executionStep);

//      executeMutantCode();
    }
  }

  private void executeMutantCode() {
    Memory memory1 = memorySpy.getMemory();
    try {
      if (!(executionStep.instruction.getState().getIo() instanceof ReadOnlyIOImplementation)) {
        boolean isConditional = executionStep.instruction instanceof ConditionalInstruction;
        if (isConditional) {
          z80.getState().getPc().write(executionStep.pcValue);
          memorySpy.setMemory(new ReadOnlyMemoryImplementation(memory1));
          for (int i = 0; i < 10; i++) {
            z80.execute();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      memorySpy.setMemory(memory1);
    }
  }

  public void process() {
    List<BlockRelation> relationsForCycle = blocksManager.findBlockAt(0x917F).getReferencesHandler().findRelationsForCycle(91);
    blocksManager.optimizeBlocks();
    CustomGraph a = customGraph.convertGraph();
    a.exportGraph();
  }

  public void setState(State state) {
    this.state = state;
    this.memory = state.getMemory();
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
    importMetadata();
  }

  public String getGameName() {
    return gameName;
  }
}
