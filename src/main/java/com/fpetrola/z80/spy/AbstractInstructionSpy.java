package com.fpetrola.z80.spy;

import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

import java.util.*;
import java.util.function.Supplier;

public class AbstractInstructionSpy<T extends WordNumber> extends WrapperInstructionSpy<T> implements ComplexInstructionSpy<T> {

  protected boolean enabled;
  protected List<ExecutionStep> executionSteps = new ArrayList<>();

  protected Map<Integer, List<ExecutionStep<T>>> memoryChanges = new HashMap<>();
  private Instruction lastInstruction;
  private ExecutionPoint lastExecutionPoint;
  private LinkedList<ExecutionPoint> executionPoints = new LinkedList<>();
  protected int enabledExecutionNumber;

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
  protected ExecutionStep nullStep = new ExecutionStep(memory);
  private Instruction[] fetchedMemory = new Instruction[0x10000];

  @Override
  public Instruction getFetchedAt(int address) {
    return fetchedMemory[address];
  }

  @Override
  public boolean wasFetched(int address) {
    return fetchedMemory[address] != null;
  }

  @Override
  public boolean isIndirectReference() {
    return indirectReference;
  }

  protected Z80Cpu z80;
  private boolean enableResquested;

  public AbstractInstructionSpy() {
  }

  public boolean isCapturing() {
    return capturing;
  }

  public void beforeExecution(Instruction<T> instruction) {
    Register pc = state.getPc();
    T pcValue = (T) pc.read();

    if (pcValue.intValue() <= 0xFFFF) {
      executionNumber++;
      lastExecutionPoint = new ExecutionPoint(executionNumber, instruction, pcValue.intValue());
      addExecutionPoint(lastExecutionPoint);

      if (enableResquested && enableIfReturningFromRoutine(instruction)) {
        enableResquested = false;
        doEnable(true);
      }

      if (enabled) {
        enabledExecutionNumber++;
        executionStep = new ExecutionStep(memory);
        executionStep.instruction = instruction;
        executionStep.description = instruction.toString();
        executionStep.pcValue = pcValue.intValue();
      }
    }
  }

  private boolean enableIfReturningFromRoutine(Instruction instruction1) {
    return instruction1 instanceof Ret && ((Ret) instruction1).getCondition() instanceof ConditionAlwaysTrue;
  }

  protected void addExecutionPoint(ExecutionPoint executionPoint) {
    executionPoints.add(executionPoint);
    if (executionPoints.size() > 20000)
      executionPoints.remove();
  }

  public void afterExecution(Instruction<T> instruction) {
//    lastExecutionPoint.instruction = cloned;

//    if (fetchedMemory[lastExecutionPoint.pc] == null) {
//      Instruction<T> cloned = new InstructionCloner<T>().clone(lastExecutionPoint.instruction);
////    System.out.println(cloned);
//      for (int i = 0; i < cloned.getLength(); i++)
//        fetchedMemory[lastExecutionPoint.pc + i] = cloned;
//    }

    lastExecutionPoint.instruction = fetchedMemory[lastExecutionPoint.pc];

    if (executionStep != null)
      executionStep.instruction = lastExecutionPoint.instruction;

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

  public void enable() {
    this.enabled = true;
  }

  public void disable() {
    this.enabled = false;
  }

  public void process() {
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

  public void reset(State state) {
    super.reset(state);
    executionSteps.clear();
    memoryChanges.clear();
    resetBitwritten();
  }

  private void resetBitwritten() {
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
  public void enableStructureCapture() {
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

  public void setSecondZ80(Z80Cpu z80) {
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
    if (!readAccessCapture)
      resetBitwritten();

    readAccessCapture = !readAccessCapture;
  }

  public void setGameName(String gameName) {
  }
}