package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

public class Virtual8BitsRegister<T extends WordNumber> extends Plain8BitRegister<T> implements VirtualRegister<T> {
  private final InstructionExecutor instructionExecutor;
  private VirtualRegister<T> lastRegister;
  private Instruction<T> instruction;
  private VirtualFetcher<T> virtualFetcher;
  private boolean cleared = false;

  public Virtual8BitsRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, VirtualRegister<T> lastRegister, VirtualFetcher<T> virtualFetcher) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.instruction = instruction;
    this.lastRegister = lastRegister;
    this.virtualFetcher = virtualFetcher;

    if (instruction == null)
      this.instruction = new VirtualAssignmentInstruction(this, () -> this.lastRegister);
  }

  public VirtualRegister<T> getLastRegister() {
    return lastRegister;
  }

  public void setLastRegister(VirtualRegister<T> lastRegister) {
    this.lastRegister = lastRegister;
  }

  public T read() {
    T t = virtualFetcher.readFromVirtual(() -> instructionExecutor.execute(instruction), () -> cleared ? null : data, () -> cleared ? data : lastRegister.read());
    write(t);
    return t;
  }

  public void write(T value) {
    cleared = false;
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
    cleared = true;
  }
}
