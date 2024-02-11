package com.fpetrola.z80.spy;

import com.fpetrola.z80.blocks.references.ReferencesHandler;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

import java.util.*;
import java.util.function.Supplier;

public abstract class AbstractInstructionSpy<T extends WordNumber> implements InstructionSpy<T> {

  protected volatile boolean capturing;
  protected boolean enabled;
  protected ExecutionStep executionStep;
  protected List<ExecutionStep> executionSteps = new ArrayList<>();

  protected Map<Integer, List<ExecutionStep<T>>> memoryChanges = new HashMap<>();
  protected MemorySpy memorySpy;
  protected boolean print = false;
  private Instruction lastInstruction;
  private ExecutionPoint lastExecutionPoint;
  private LinkedList<ExecutionPoint> executionPoints = new LinkedList<>();

  @Override
  public boolean isReadAccessCapture() {
    return readAccessCapture;
  }

  private boolean readAccessCapture;

  public LinkedList<ExecutionPoint> getExecutionPoints() {
    return executionPoints;
  }

  @Override
  public long getExecutionNumber() {
    return executionNumber;
  }


  protected long executionNumber = 0;

  @Override
  public boolean[] getBitsWritten() {
    return bitsWritten;
  }

  protected boolean[] bitsWritten;
  protected Memory memory;
  protected ExecutionStep nullStep = new ExecutionStep(memory);
  private Instruction[] fetchedMemory = new Instruction[0x10000];

  @Override
  public boolean wasFetched(int address) {
    return fetchedMemory[address] != null;
  }

  @Override
  public boolean isIndirectReference() {
    return indirectReference;
  }

  private boolean indirectReference;
  protected OOZ80 z80;
  private boolean enableResquested;

  public AbstractInstructionSpy() {
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

  public void start(Instruction<T> instruction, int opcodeInt, T pcValue) {
    if (pcValue.intValue() <= 0xFFFF) {
      executionNumber++;
      Instruction<T> baseInstruction = instruction.getBaseInstruction();
      lastExecutionPoint = new ExecutionPoint(executionNumber, baseInstruction, pcValue.intValue());
      addExecutionPoint(lastExecutionPoint);

      for (int i = 0; i < baseInstruction.getLength(); i++)
        fetchedMemory[pcValue.intValue() + i] = baseInstruction;

      if (enableResquested && enableIfReturningFromRoutine(baseInstruction)) {
        enableResquested = false;
        doEnable(true);
      }

      if (enabled) {
        executionStep = new ExecutionStep(memory);
        executionStep.instruction = baseInstruction;
        executionStep.description = baseInstruction.toString();
        executionStep.opcodeInt = opcodeInt;
        executionStep.pcValue = pcValue.intValue();
      }
    }
  }

  private boolean enableIfReturningFromRoutine(Instruction instruction1) {
    return instruction1 instanceof Ret && ((Ret) instruction1).getCondition() instanceof ConditionAlwaysTrue;
  }

  private void addExecutionPoint(ExecutionPoint executionPoint) {
    executionPoints.add(executionPoint);
    if (executionPoints.size() > 20000)
      executionPoints.remove();
  }

  public void end() {
    Instruction<T> cloned = lastExecutionPoint.instruction; // new InstructionCloner<T>().clone(lastExecutionPoint.instruction);
    lastExecutionPoint.instruction = cloned;
    if (executionStep != null)
      executionStep.instruction = cloned;

    if (capturing) {
      executionStep.setIndex(executionSteps.size());

      addMemoryChanges(executionStep);
      executionSteps.add(executionStep);
    }
  }

  protected void addMemoryChanges(ExecutionStep<T> step) {
    if (!step.writeMemoryReferences.isEmpty()) {
      for (WriteMemoryReference<T> writeMemoryReference : step.writeMemoryReferences) {
        T key = writeMemoryReference.address;
        List<ExecutionStep<T>> value = memoryChanges.get(key);
        if (value == null)
          memoryChanges.put(key.intValue(), value = new ArrayList<>());

        value.add(0, step);
      }
    }
  }

  public void enable(boolean enabled) {
    enableResquested = enabled;
    if (!enabled)
      doEnable(false);
  }

  private void doEnable(boolean enabled) {
    boolean wasEnabled = this.enabled;
    this.enabled = enabled;
    capturing = enabled;
    if (wasEnabled) {
      print = false;
      process();
      executionSteps.clear();
      // executionStepData.clear();
    }
  }

  public abstract void process();

  public void addWriteReference(RegisterName opcodeReference, T value, boolean isIncrement) {
    if (capturing) {
      WriteOpcodeReference writeReference = executionStep.addWriteReference(opcodeReference, value, isIncrement, indirectReference);
      if (print)
        System.out.println(writeReference);
    }
  }

  public void addReadReference(RegisterName opcodeReference, T value) {
    if (capturing) {
      ReadOpcodeReference readReference = executionStep.addReadReference(opcodeReference, value, indirectReference);
      if (print)
        System.out.println(readReference);
    }
  }

  public void addWriteMemoryReference(T address, T value) {
    if (capturing) {
      WriteMemoryReference writeMemoryReference = executionStep.addWriteMemoryReference(address, value, indirectReference);
      if (print)
        System.out.println(writeMemoryReference);

    }
  }

  public void addReadMemoryReference(T address, T value) {
    if (capturing) {
      ReadMemoryReference<WordNumber> readMemoryReference = executionStep.addReadMemoryReference(address, value, indirectReference);
      if (print)
        System.out.println(readMemoryReference);

    }
  }

  @Override
  public boolean isStructureCapture() {
    return false;
  }

  public void flipOpcode(Instruction<T> instruction, int opcodeInt) {
    if (capturing) {
      executionStep.instruction = instruction;
      if (print)
        System.out.println(instruction + " (" + Helper.convertToHex(opcodeInt) + ")");
    }
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    this.bitsWritten = bitsWritten;
  }

  public void undo() {
    executionStep.undo();
  }

  public MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return new MemoryPlusRegister8BitReferenceSpy(memoryPlusRegister8BitReference);
  }

  public void reset(State state) {
    setState(state);
    executionSteps.clear();
    memoryChanges.clear();
    memory.reset();
    if (bitsWritten != null)
      for (int i = 0; i < bitsWritten.length; i++) {
        bitsWritten[i] = false;
      }
  }

  public void pause() {
    capturing = false;
  }

  public void doContinue() {
    capturing = enabled;
  }

  @Override
  public ReferencesHandler enableStructureCapture() {
    return null;
  }

  public void switchToDirectReference() {
    indirectReference = false;
  }

  public void switchToIndirectReference() {
    indirectReference = true;
  }

  public <T> T executeInPause(Supplier<T> object) {
    pause();
    T t = object.get();
    doContinue();
    return t;
  }

  public void setSecondZ80(OOZ80 z80) {
    this.z80 = z80;
  }


  @Override
  public ExecutionPoint getLastExecutionPoint() {
    return lastExecutionPoint;
  }

  @Override
  public void export() {

  }

  @Override
  public void enableReadAccessCapture() {
    readAccessCapture = !readAccessCapture;
  }

}