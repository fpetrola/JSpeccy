package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.graph.CustomGraph;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;

public class DefaultInstructionSpy extends AbstractInstructionSpy implements InstructionSpy {
  private Set<Integer> spritesAt = new HashSet<>();
  private State state;
  private CustomGraph customGraph;
  private int edge;

  public DefaultInstructionSpy() {
    super();
  }

  public void reset() {
    super.reset();
    spritesAt.clear();
    customGraph = new CustomGraph() {
      protected String getVertexLabel(Object object) {
        if (object instanceof ExecutionStepData) {
          ExecutionStepData currentStep = (ExecutionStepData) object;
          return OOZ80.convertToHex(currentStep.pcValue) + ": " + currentStep.instruction.toString();
        } else
          return object + "";
      }
    };
  }

  public void process() {
    ExecutionStepData last = executionStepDatas.get(executionStepDatas.size() - 1);
    spritesAt.clear();

    findFirst(last);
  }

  private OpcodeReference getSource(ExecutionStepData executionStepData) {
    if (!executionStepData.readMemoryReferences.isEmpty()) {
      if (executionStepData.readMemoryReferences.size() > 1)
        System.out.println("dsgsdagds");
      return executionStepData.readMemoryReferences.get(0).getReference();
    } else {
      if (executionStepData.readReferences.isEmpty()) {
        System.out.println("??");
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

      OpcodeReference source = getSource(screenWritingStep);
      List<ExecutionStepData> originalSteps = findOriginalSourceOf(screenWritingStep, source, "screen");

      if (originalSteps != null) {
        for (ExecutionStepData originalStep : originalSteps) {
          if (!originalStep.readReferences.isEmpty()) {
            if (!originalStep.writeMemoryReferences.isEmpty())
              spritesAt.add(originalStep.writeMemoryReferences.get(0).address);

            if (originalStep.writeMemoryReferences.size() > 1)
              System.out.println("dsgsdagds");
          }
          boolean empty = originalStep.readMemoryReferences.isEmpty();
          if (!empty) {
            for (ReadMemoryReference readMemoryReference : originalStep.readMemoryReferences) {
              int address = readMemoryReference.address;
              spritesAt.add(address);
            }
          } else {
            System.out.println("empty");
          }
        }
      }

      last = getPreviousStep(screenWritingStep);
    }

    for (Integer address : spritesAt) {
      System.out.println("sprite at: " + address);

      for (int k = 0; k < 8; k++) {
        bitsWritten[address * 8 + k] = true;
      }
    }

    customGraph.exportGraph();
    return result;
  }

  private boolean checkSource(OpcodeReference source) {
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

  private List<ExecutionStepData> findOriginalSourceOf(ExecutionStepData foundStep, OpcodeReference source, Object prev) {
//    addOrCreateVertex(foundStep);

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
          currentStep = first.get();

          List<ExecutionStepData> fromSources = findFromSources(currentStep);
          customGraph.addEdge(edge++ + "", currentStep, prev, "memory");
          return fromSources;
        }
      } else {
        if (targetIsEqual(currentStep, source)) {
          List<ExecutionStepData> fromSources = findFromSources(currentStep);
          customGraph.addEdge(edge++ + "", currentStep, prev, "register");
          return fromSources;
        }
      }

      currentStep = getPreviousStep(currentStep);
    }

    return results;
  }

  private boolean targetIsEqual(ExecutionStepData currentStep, OpcodeReference source) {
    for (WriteOpcodeReference wr : currentStep.writeReferences) {
      if (((Register) wr.opcodeReference).getName().equals(((Register) source).getName()))
        return true;
    }
    return false;
  }

  private ExecutionStepData getPreviousStep(ExecutionStepData last) {
    int index = last.i - 1;
    ExecutionStepData executionStepData = index >= 0 ? executionStepDatas.get(index) : null;
    return executionStepData;
  }

  private List<ExecutionStepData> findFromSources(ExecutionStepData executionStepData) {
    List<ExecutionStepData> results = new ArrayList<>();
    if (!executionStepData.readMemoryReferences.isEmpty()) {
      for (ReadMemoryReference readMemoryReference : executionStepData.readMemoryReferences) {

        OpcodeReference reference = readMemoryReference.getReference();
        if (checkSource(reference))
          results.add(executionStepData);
        else
          results.addAll(findOriginalSourceOf(getPreviousStep(executionStepData), reference, executionStepData));
      }
    }

    if (!executionStepData.readReferences.isEmpty()) {

      for (ReadOpcodeReference ror : executionStepData.readReferences) {

//        if (executionStepData.readReferences.size() > 1) {
//          System.out.println("hay mas de 1 reads");
//        }
        OpcodeReference reference = ror.getReference();
        if (checkSource(reference))
          results.add(executionStepData);
        else
          results.addAll(findOriginalSourceOf(getPreviousStep(executionStepData), reference, executionStepData));
      }
    }

    return results;
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
