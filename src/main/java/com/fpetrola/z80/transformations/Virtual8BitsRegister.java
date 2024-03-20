package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

import java.util.ArrayList;
import java.util.List;

public class Virtual8BitsRegister<T extends WordNumber> extends Plain8BitRegister<T> implements VirtualRegister<T> {
  private final InstructionExecutor instructionExecutor;
  private Instruction<T> instruction;
  private VirtualFetcher<T> virtualFetcher;
  private List<VirtualRegister<T>> previousVersions = new ArrayList<>();
  protected T lastData;

  public Virtual8BitsRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, VirtualRegister<T> previousVersion, VirtualFetcher<T> virtualFetcher) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.instruction = instruction;
    this.virtualFetcher = virtualFetcher;

    addPreviousVersion(previousVersion);

    if (instruction == null)
      this.instruction = new VirtualAssignmentInstruction(this, () -> this.getCurrentPreviousVersion());
  }

  public VirtualRegister<T> getCurrentPreviousVersion() {
    return previousVersions.isEmpty() ? null : previousVersions.get(previousVersions.size() - 1);
  }

  public T read() {
    T t = virtualFetcher.readFromVirtual(() -> instructionExecutor.execute(instruction), () -> lastData != null ? null : data, () -> lastData != null ? lastData : getCurrentPreviousVersion().read());
    write(lastData != null ? null : t);
    return t;
  }

  public void write(T value) {
    lastData = null;
    super.write(value);
  }

  public void decrement() {
    read();
    super.decrement();
  }

  public void increment() {
    read();
    super.increment();
  }

  public void reset() {
    if (lastData == null)
      data = null;
  }

  public void addPreviousVersion(VirtualRegister previousVersion) {
    if (previousVersion != null) {
      previousVersions.remove(previousVersion);
      previousVersions.add(previousVersion);
    }
    if (previousVersions.size() > 1)
      previousVersion.saveData();
  }

  public void saveData() {
    lastData = data;
    data = null;
  }
}
