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

  public Virtual8BitsRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, VirtualRegister<T> lastRegister, VirtualFetcher<T> virtualFetcher) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.instruction = instruction;
    this.virtualFetcher = virtualFetcher;

    addLastRegister(lastRegister);

    if (instruction == null)
      this.instruction = new VirtualAssignmentInstruction(this, () -> this.getPreviousVersion());
  }

  public VirtualRegister<T> getPreviousVersion() {
    return previousVersions.isEmpty() ? null : previousVersions.get(previousVersions.size() - 1);
  }

  public T read() {
    T t = virtualFetcher.readFromVirtual(() -> instructionExecutor.execute(instruction), () -> lastData != null ? null : data, () -> lastData != null ? lastData : getPreviousVersion().read());
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

  public void addLastRegister(VirtualRegister lastRegister) {
    if (lastRegister != null) {
      previousVersions.remove(lastRegister);
      previousVersions.add(lastRegister);
    }
    if (previousVersions.size() > 1)
      lastRegister.saveData();
  }

  public void saveData() {
    lastData = data;
    data = null;
  }
}
