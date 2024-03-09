package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

import java.util.function.Supplier;

public class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final InstructionExecutor instructionExecutor;
  private final boolean[] semaphore;
  private final Instruction<T> instruction;
  private final Supplier<T> lastValueSupplier;

  public VirtualPlain8BitRegister(InstructionExecutor instructionExecutor, String name, boolean[] semaphore, Instruction<T> instruction, Supplier<T> lastValueSupplier) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.semaphore = semaphore;
    this.instruction = instruction;
    this.lastValueSupplier = lastValueSupplier;
  }

  public T read() {
    if (data != null)
      return data;

    if (!semaphore[0]) {
      semaphore[0] = true;
      instructionExecutor.execute(instruction);
      semaphore[0] = false;
      return (T) data;
    } else
      return (T) lastValueSupplier.get();
  }

  @Override
  public void decrement() {
    read();
    super.decrement();
  }
}
