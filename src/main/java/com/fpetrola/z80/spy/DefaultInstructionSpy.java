package com.fpetrola.z80.spy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.graph.CustomGraph;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.RegisterName;

public class DefaultInstructionSpy extends AbstractInstructionSpy implements InstructionSpy {
  private static final String FILE_TRACE_JSON = "game-trace.json";

  private final class ExecutionStepAddressRange extends ExecutionStepData {
    private final AddressRange addressRange;

    private ExecutionStepAddressRange(AddressRange addressRange) {
      this.addressRange = addressRange;
      instructionToString = "sprite: " + addressRange.getName();
    }

    public int hashCode() {
      return System.identityHashCode(addressRange);
    }
  }

  private Set<Integer> spritesAt = new HashSet<>();
  private State state;
  private CustomGraph customGraph;
  private AddressRange currentRange = new AddressRange();
  private List<AddressRange> ranges = new ArrayList<AddressRange>();

  public DefaultInstructionSpy() {
    super();
  }

  public void reset() {
    super.reset();
    spritesAt.clear();
    initGraph();
  }

  private void initGraph() {
    customGraph = new CustomGraph() {
      protected String getVertexLabel(Object object) {
        if (object instanceof ExecutionStepData) {
          ExecutionStepData currentStep = (ExecutionStepData) object;
          return OOZ80.convertToHex(currentStep.pcValue) + ": " + currentStep.instructionToString;
        } else
          return object + "";
      }

      protected String getVertexId(Object object) {
        if (object instanceof ExecutionStepAddressRange)
          return object.hashCode() + "";
        else
          return getVertexLabel(object);
      }
    };
  }

  public void process() {
    spritesAt.clear();

    execute(false);

  }

  public static void main(String[] args) {
    DefaultInstructionSpy defaultInstructionSpy = new DefaultInstructionSpy();
    defaultInstructionSpy.execute(true);
  }

  private void execute(boolean replay) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      if (!replay)
        exportData(objectMapper);
      else
        importData(objectMapper);
    } catch (Exception e) {
      e.printStackTrace();
    }

    ExecutionStepData last = executionStepDatas.get(executionStepDatas.size() - 1);
    findFirst(last);

    updateSpriteBrowser();

    exportGraph();
  }

  private void exportGraph() {
    while (mergeRanges() > 0)
      ;

    customGraph.exportGraph();
  }

  private int mergeRanges() {
    for (int i = 0; i < ranges.size(); i++) {
      AddressRange addressRange = ranges.get(i);
      for (int j = 0; j < ranges.size(); j++) {
        AddressRange b = ranges.get(j);
        if (b != addressRange) {
          boolean merged = addressRange.mergeIfRequired(b);
          ExecutionStepData targetVertex = new ExecutionStepAddressRange(addressRange);
          ExecutionStepData sourceVertex = new ExecutionStepAddressRange(b);

          if (merged) {
            customGraph.mergeVertexWith(targetVertex, sourceVertex);
            ranges.remove(b);
            return 1;
          }
        }
      }
    }

    return 0;
  }

  private void updateSpriteBrowser() {
    for (Integer address : spritesAt) {
      System.out.println("sprite at: " + address);
      for (int k = 0; k < 8; k++)
        bitsWritten[address * 8 + k] = true;
    }
  }

  private void importData(ObjectMapper objectMapper) throws IOException, StreamReadException, DatabindException {
    ResultContainer resultContainer2 = objectMapper.readValue(new File(FILE_TRACE_JSON), ResultContainer.class);

    executionStepDatas = resultContainer2.executionStepDatas;
    memorySpy = resultContainer2.memorySpy;

    for (ExecutionStepData step : executionStepDatas) {
      step.accessReferences = new ArrayList<>();
      step.accessReferences.addAll(step.writeMemoryReferences);
      step.accessReferences.addAll(step.writeReferences);
      step.accessReferences.addAll(step.readMemoryReferences);
      step.accessReferences.addAll(step.readReferences);
      addMemoryChanges(step);
    }
    initGraph();
    bitsWritten = new boolean[0x10000 * 8];
  }

  private void exportData(ObjectMapper objectMapper) throws IOException, StreamWriteException, DatabindException {
    ResultContainer resultContainer = new ResultContainer();
    resultContainer.executionStepDatas = executionStepDatas;
    resultContainer.memorySpy = memorySpy;
    objectMapper.writeValue(new File(FILE_TRACE_JSON), resultContainer);
  }

  private Object getSource(ExecutionStepData executionStepData) {
    if (!executionStepData.readMemoryReferences.isEmpty()) {
      if (executionStepData.readMemoryReferences.size() > 1)
        System.out.println("dsgsdagds");
      return executionStepData.readMemoryReferences.get(0).getReference();
    } else {
      if (executionStepData.readReferences.isEmpty()) {
        return null;
      } else
        return executionStepData.readReferences.get(0).getReference();
    }
  }

  private ExecutionStepData findFirst(ExecutionStepData last) {
    ExecutionStepData result = last;

    while (last != null) {
      ExecutionStepData screenWritingStep = walkReverse(step -> walkAccessReverse(step, this::isScreenWriting), last);
      if (screenWritingStep == nullStep)
        break;

      ExecutionStepData screenStep = addScreenEdge(screenWritingStep);

      List<ExecutionStepData> originalSteps = findOriginalSourceOf(screenWritingStep, getSource(screenWritingStep), screenStep);

      for (ExecutionStepData originalStep : originalSteps) {
        for (WriteMemoryReference writeMemoryReference : originalStep.writeMemoryReferences) {
          addRangeEdge(originalStep, "s0", writeMemoryReference.address);
        }
        for (ReadMemoryReference readMemoryReference : originalStep.readMemoryReferences) {
          addRangeEdge(originalStep, "s1", readMemoryReference.address);
        }
      }

      last = getPreviousStep(screenWritingStep);
    }

    return result;
  }

  private ExecutionStepData addScreenEdge(ExecutionStepData screenWritingStep) {
    ExecutionStepData screenStep = new ExecutionStepData();
    screenStep.instructionToString = "screen";
    screenStep.i = screenWritingStep.i;
    customGraph.addEdge(screenWritingStep, screenStep, "write");
    return screenStep;
  }

  private void addRangeEdge(ExecutionStepData originalStep, String label, int address) {
    AddressRange addressRange = getAddressRangeFor(address, originalStep);
    ExecutionStepData targetVertex = new ExecutionStepAddressRange(addressRange);
    customGraph.addEdge(targetVertex, originalStep, label);
    spritesAt.add(address);
  }

  private AddressRange getAddressRangeFor(int address, ExecutionStepData step) {
    Optional<AddressRange> first = ranges.stream().filter(r -> r.canAdd(address, step)).findFirst();
    first.ifPresentOrElse(r -> {
      currentRange = r;
      r.add(address, step);
    }, () -> ranges.add(currentRange = new AddressRange(address, step)));

    return currentRange;
  }

  private boolean checkSource(Object source) {
    if (source == null)
      return true;
    else if (source instanceof ReadMemoryOpcodeReference) {
      ReadMemoryOpcodeReference readMemoryOpcodeReference = (ReadMemoryOpcodeReference) source;
      if (readMemoryOpcodeReference.address < 0x4000)
        return true;
      else if (isSpriteAddress(readMemoryOpcodeReference.address))
        return true;
    }
    return false;
  }

  private List<ExecutionStepData> findOriginalSourceOf(ExecutionStepData foundStep, Object source, ExecutionStepData prev) {
    List<ExecutionStepData> results = new ArrayList<>();

    if (checkSource(source))
      return Arrays.asList(foundStep);

    ExecutionStepData currentStep = foundStep;

    while (currentStep != null) {
      if (source instanceof ReadMemoryOpcodeReference) {
        ReadMemoryOpcodeReference readMemoryOpcodeReference = (ReadMemoryOpcodeReference) source;

//        if (readMemoryOpcodeReference.address == 27581) {
//          System.out.println("AAAA");
//        }

        List<ExecutionStepData> list = memoryChanges.get(readMemoryOpcodeReference.address);

        int currentIndex = currentStep.i;
        Optional<ExecutionStepData> first = list.stream().filter(step -> step.i < currentIndex).findFirst();

        if (first.isPresent()) {
//          for (int i = prev.i - 1; i > currentStep.i; i--) {
//            customGraph.addEdge(edge++ + "", executionStepDatas.get(i), executionStepDatas.get(i + 1), "m2");
//          }

          currentStep = first.get();

          List<ExecutionStepData> fromSources = findFromSources(currentStep, prev, "memory");

          return fromSources;
        }
      } else {
        if (targetIsEqual(currentStep, (RegisterName) source)) {
          List<ExecutionStepData> fromSources = findFromSources(currentStep, prev, "register");
//          for (int i = prev.i - 1; i > currentStep.i; i--) {
//            customGraph.addEdge(edge++ + "", executionStepDatas.get(i), executionStepDatas.get(i + 1), "r2");
//          }
          return fromSources;
        }
      }

      currentStep = getPreviousStep(currentStep);
    }

    return results;
  }

  private boolean targetIsEqual(ExecutionStepData currentStep, RegisterName source) {
    for (WriteOpcodeReference wr : currentStep.writeReferences) {
      if (wr.opcodeReference.equals(source))
        return true;
    }
    return false;
  }

  private ExecutionStepData getPreviousStep(ExecutionStepData last) {
    int index = last.i - 1;
    ExecutionStepData executionStepData = index >= 0 ? executionStepDatas.get(index) : null;
    return executionStepData;
  }

  private List<ExecutionStepData> findFromSources(ExecutionStepData executionStepData, ExecutionStepData prev, String label) {
    List<ExecutionStepData> results = new ArrayList<>();
    addSources(executionStepData, results, executionStepData.readMemoryReferences);
    addSources(executionStepData, results, executionStepData.readReferences);
    customGraph.addEdge(executionStepData, prev, label);

    return results;
  }

  private void addSources(ExecutionStepData executionStepData, List<ExecutionStepData> results, List<? extends SpyReference> readMemoryReferences) {
    if (!readMemoryReferences.isEmpty()) {
      for (SpyReference readMemoryReference : readMemoryReferences) {
        Object reference = readMemoryReference.getReference();
        if (checkSource(reference))
          results.add(executionStepData);
        else
          results.addAll(findOriginalSourceOf(getPreviousStep(executionStepData), reference, executionStepData));
      }
    }
  }

  private boolean isSpriteAddress(int address) {
    return memorySpy.getAddressModificationsCounter(address) <= 2;
  }

  private boolean isScreenWriting(Object accessReference) {
    if (accessReference instanceof WriteMemoryReference) {
      WriteMemoryReference wr = (WriteMemoryReference) accessReference;
      if (wr.address >= 0x4000 && wr.address <= (0x5000))
        return true;
    }
    return false;
  }

  public void setState(State state) {
    this.state = state;
  }
}
