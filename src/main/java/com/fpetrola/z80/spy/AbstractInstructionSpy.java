package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.MemoryImplementation;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public abstract class AbstractInstructionSpy implements InstructionSpy {

  public static final int STEP_PROCESSOR_CANCEL = -2;
  public static final int STEP_PROCESSOR_NOT_MATCHING = -1;
  volatile boolean capturing;
  protected boolean enabled;
  protected ExecutionStepData executionStepData;
  protected List<ExecutionStepData> executionStepDatas = new ArrayList<>();

  protected Map<Integer, List<ExecutionStepData>> memoryChanges = new HashMap<>();
  protected MemorySpy memorySpy;
  protected boolean print = false;
  protected boolean[] bitsWritten;
  protected Memory memory;
  protected ExecutionStepData nullStep = new ExecutionStepData(memory);
  private boolean indirectReference;
  protected List<AddressRange> ranges = new ArrayList<AddressRange>();
  AddressRange currentRange = new AddressRange();

  public AbstractInstructionSpy(MemoryImplementation memory) {
    this.memory = memory;
    executionStepData= new ExecutionStepData(memory);
  }

  public boolean isCapturing() {
    return capturing;
  }

  public Memory wrapMemory(Memory aMemory) {
    this.memory = aMemory;
    if (memorySpy == null)
      memorySpy = new MemorySpy(aMemory, this);
    return memorySpy;
  }

  public OpcodeReference wrapOpcodeReference(OpcodeReference opcodeReference) {
    return new OpcodeReferenceSpy(opcodeReference, this);
  }

  public Register wrapOpcodeRegister(Register register, RegisterName name) {
    if (name == RegisterName.F) {
      return register;
    } else if (register instanceof RegisterPair) {
      return new RegisterPairSpy(register, this);
    } else
      return new RegisterSpy(register, this);
  }

  public void start(Instruction opcode, int opcodeInt, int pcValue) {
    if (enabled) {
      executionStepData = new ExecutionStepData(memory);
      executionStepData.instruction = opcode.getBaseInstruction();
      executionStepData.instructionToString = opcode.getBaseInstruction().toString();
      executionStepData.opcodeInt = opcodeInt;
      executionStepData.pcValue = pcValue;
      if (print)
        executionStepData.printOpCodeHeader();
    }
  }

  public void end() {
    if (capturing) {
      // System.out.println("capturo!!");
      // System.out.println("-------------------------------------------------");
//      capturing = false;
      executionStepData.setIndex(executionStepDatas.size());

      addMemoryChanges(executionStepData);
      executionStepDatas.add(executionStepData);

      // printOpCodeHeader(executionStepData);
      // executionStepData.accessReferences.forEach(ar -> {
      // if (ar.toString().equals("mem(32768):= 1"))
      // System.out.println("sdgsdag");
      // System.out.println(ar);
      // });
    }

  }

  protected void addMemoryChanges(ExecutionStepData step) {
    if (!step.writeMemoryReferences.isEmpty()) {
      for (WriteMemoryReference writeMemoryReference : step.writeMemoryReferences) {
        int key = writeMemoryReference.address;
        List<ExecutionStepData> value = memoryChanges.get(key);
        if (value == null)
          memoryChanges.put(key, value = new ArrayList<>());

        value.add(0, step);
      }
    }
  }

  public void enable(boolean enabled) {
    boolean wasEnabled = this.enabled;
    this.enabled = enabled;
    capturing = enabled;
    if (wasEnabled) {
      print = false;
      process();
      executionStepDatas.clear();
      // executionStepData.clear();
    }
  }

  public abstract void process();

  protected ExecutionStepData walkReverse(Function<ExecutionStepData, Integer> stepProcessor, ExecutionStepData from) {
    for (int i = from.i - 1; i >= 0; i--) {
      ExecutionStepData step = executionStepDatas.get(i);
      Integer apply = stepProcessor.apply(step);
      if (apply == STEP_PROCESSOR_CANCEL)
        return nullStep;
      else if (apply != STEP_PROCESSOR_NOT_MATCHING)
        return step;
    }
    return nullStep;
  }

  protected int walkAccessReverse(ExecutionStepData step, AccessProcessor accessProcessor) {
    for (int j = step.accessReferences.size() - 1; j >= 0; j--) {
      if (accessProcessor.accessMatching(step.accessReferences.get(j)))
        return j;
    }
    return STEP_PROCESSOR_NOT_MATCHING;
  }

  public void addWriteReference(RegisterName opcodeReference, int value, boolean isIncrement) {
    if (capturing) {
      WriteOpcodeReference writeReference = executionStepData.addWriteReference(opcodeReference, value, isIncrement, indirectReference);
      if (print)
        System.out.println(writeReference);
    }
  }

  public void addReadReference(RegisterName opcodeReference, int value) {
    if (capturing) {
      ReadOpcodeReference readReference = executionStepData.addReadReference(opcodeReference, value, indirectReference);
      if (print)
        System.out.println(readReference);
    }
  }

  public void addWriteMemoryReference(int address, int value) {
    if (capturing) {
      WriteMemoryReference writeMemoryReference = executionStepData.addWriteMemoryReference(address, value, indirectReference);
      if (print)
        System.out.println(writeMemoryReference);

    }
  }

  public void addReadMemoryReference(int address, int value) {
    if (capturing) {
      ReadMemoryReference readMemoryReference = executionStepData.addReadMemoryReference(address, value, indirectReference);
      if (print)
        System.out.println(readMemoryReference);

    }
  }

  public void flipOpcode(Instruction instruction, int opcodeInt) {
    if (capturing) {
      executionStepData.instruction = instruction;
      if (print)
        System.out.println(instruction + " (" + OOZ80.convertToHex(opcodeInt) + ")");
    }
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    this.bitsWritten = bitsWritten;
  }

  public void undo() {
    executionStepData.undo();
  }

  public MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return new MemoryPlusRegister8BitReferenceSpy(memoryPlusRegister8BitReference);
  }

  public void reset() {
    executionStepDatas.clear();
    memoryChanges.clear();
  }

  public void pause() {
    capturing = false;
  }

  public void doContinue() {
    capturing = enabled;
  }

  public void switchToDirectReference() {
    indirectReference = false;
  }

  public void switchToIndirectReference() {
    indirectReference = true;
  }

  protected AddressRange getAddressRangeFor(int address, ExecutionStepData step) {
    Optional<AddressRange> first = ranges.stream().filter(r -> r.canAdd(address, step)).findFirst();
    first.ifPresentOrElse(r -> {
      currentRange = r;
      r.add(address, step);
    }, () -> ranges.add(currentRange = new AddressRange(address, step)));
  
    return currentRange;
  }
}