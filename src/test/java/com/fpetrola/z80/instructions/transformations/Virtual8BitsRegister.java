package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

public class Virtual8BitsRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final InstructionExecutor instructionExecutor;

  public VirtualRegisterFactory.RegisterSupplier getLastValueSupplier() {
    return lastValueSupplier;
  }

  private final VirtualRegisterFactory.RegisterSupplier lastValueSupplier;
  private Instruction<T> instruction;
  private VirtualFetcher<T> virtualFetcher;

  public Virtual8BitsRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, VirtualRegisterFactory.RegisterSupplier lastValueSupplier, VirtualFetcher<T> virtualFetcher) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.instruction = instruction;
    this.lastValueSupplier = lastValueSupplier;
    this.virtualFetcher = virtualFetcher;

    if (instruction == null)
      this.instruction = new VirtualAssignmentInstruction(this, lastValueSupplier);
  }

  public T read() {
    return (T) virtualFetcher.readFromVirtual(() -> instructionExecutor.execute(instruction), () -> data, lastValueSupplier);
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
    data= null;
  }
}
