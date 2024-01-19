package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class DefaultInstructionSpy extends AbstractInstructionSpy implements InstructionSpy {
  private Set<Integer> spritesAt = new HashSet<>();
  private State state;

  public DefaultInstructionSpy() {
    super();
  }

  public void process() {
    ExecutionStepData last = executionStepDatas.get(executionStepDatas.size() - 1);
    spritesAt.clear();

    findFirst(last);
  }

  private OpcodeReference getSource(ExecutionStepData executionStepData) {
    if (!executionStepData.readMemoryReferences.isEmpty()) {
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

//      System.out.println(screenWritingStep.writeMemoryReferences.get(0).address);
      OpcodeReference source = getSource(screenWritingStep);
      List<ExecutionStepData> originalSteps = findOriginalSourceOf(screenWritingStep, source);

      if (originalSteps != null) {
        for (ExecutionStepData originalStep : originalSteps) {
          if (!originalStep.readReferences.isEmpty()) {
            if (!originalStep.writeMemoryReferences.isEmpty())
              spritesAt.add(originalStep.writeMemoryReferences.get(0).address);
            System.out.println("sdgsdagdsag");
          }
          if (originalStep != null) {
            boolean empty = originalStep.readMemoryReferences.isEmpty();
            if (!empty) {
              for (ReadMemoryReference readMemoryReference :  originalStep.readMemoryReferences) {
                int address = readMemoryReference.address;
                spritesAt.add(address);
              }
            } else {
              System.out.println("empty");
            }
          } else
            System.out.println("null");
        }
      }

//      if (!screenWritingStep.readMemoryReferences.isEmpty()) {
//        int readAddress = screenWritingStep.readMemoryReferences.get(0).address;
//
//        boolean isSpriteAddress = isSpriteAddress(readAddress);
//
//        if (isSpriteAddress) {
//          return screenWritingStep;
//        } else {
//          ExecutionStepData oldestMemoryWriting = findOldestMemoryWriting(screenWritingStep, readAddress);
//          if (!oldestMemoryWriting.instruction.toString().startsWith("Ldir")) {
//            System.out.println("dzsadg");
//          }
//          if (isSpriteAddress(oldestMemoryWriting.readMemoryReferences.get(0).address)) {
//            return oldestMemoryWriting;
//          }
//        }
//      }
      last = getPreviousStep(screenWritingStep);
    }

    for (Integer address : spritesAt) {
      System.out.println("sprite at: " + address);

      for (int k = 0; k < 8; k++) {
        bitsWritten[address * 8 + k] = true;
      }
    }
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

  private List<ExecutionStepData> findOriginalSourceOf(ExecutionStepData foundStep, OpcodeReference source) {
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
          return findFromSources(currentStep);
        }
      } else {
        if (targetIsEqual(currentStep, source)) {
          return findFromSources(currentStep);
        }
      }

      currentStep = getPreviousStep(currentStep);
    }

    return results;
  }

  private boolean targetIsEqual(ExecutionStepData currentStep, OpcodeReference source) {
    return currentStep.writeReferences.stream().anyMatch(wr -> wr.opcodeReference.equals(source));
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
          results.addAll(findOriginalSourceOf(getPreviousStep(executionStepData), reference));
      }
    }

    if (!executionStepData.readReferences.isEmpty()) {
      if (executionStepData.readReferences.size() > 1) {
      }
      OpcodeReference reference = executionStepData.readReferences.get(0).getReference();
      if (checkSource(reference))
        results.add(executionStepData);
      else
        results.addAll(findOriginalSourceOf(getPreviousStep(executionStepData), reference));
    }

    return results;
  }

  private boolean isSpriteAddress(int address) {
    return memorySpy.getAddressModificationsCounter(address) <= 2;
  }

  private ExecutionStepData findOldestMemoryWriting(ExecutionStepData screenWritingStep, int address) {
    ExecutionStepData memoryWritingIndex = walkReverse(step -> {
      if (step.instruction instanceof JP) {
        JP jp = (JP) step.instruction;
        boolean a = jp.getCondition() instanceof ConditionAlwaysTrue;
        return a ? STEP_PROCESSOR_CANCEL : STEP_PROCESSOR_NOT_MATCHING;
      } else
        return walkAccessReverse(step, ar -> {
          return ar instanceof WriteMemoryReference && ((WriteMemoryReference) ar).address == address;
        });
    }, screenWritingStep);

    if (memoryWritingIndex != nullStep && !memoryWritingIndex.readMemoryReferences.isEmpty())
      return findOldestMemoryWriting(memoryWritingIndex, memoryWritingIndex.readMemoryReferences.get(0).address);
    else
      return screenWritingStep;
  }

  private int p2(ExecutionStepData step) {
    if (print) {
      step.printOpCodeHeader();
    }

    return walkAccessReverse(step, this::processAccess);
  }

  public boolean processAccess(Object ar) {
    if (print) {
      System.out.println(ar);
    }

//    findSpriteReading(step, ar);
//    if (isScreenWriting(step, ar))
//      return true;

    return false;
  }

  private void findSpriteReading(ExecutionStepData step, Object ar) {
    if (ar instanceof ReadMemoryReference) {
      ReadMemoryReference readMemoryReference = (ReadMemoryReference) ar;
      if (memorySpy.getAddressModificationsCounter(readMemoryReference.address) < 100 && readMemoryReference.address >= 0x5CCB) {
        if (step.instruction.toString().contains("(")) {
          int address = readMemoryReference.address;
          if (bitsWritten != null)
            for (int k = 0; k < 8; k++) {
              bitsWritten[address * 8 + k] = true;
            }
//                System.out.println("lo encontre!!");
        }
      }
    }
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
