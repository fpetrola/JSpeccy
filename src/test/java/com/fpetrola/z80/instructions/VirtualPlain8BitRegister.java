package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;
import com.fpetrola.z80.registers.RegisterName;

class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final Instruction instruction;
  private final PipeRegister<T> register;
  private boolean updated;
  private NestedInstructionExecutor nestedInstructionExecutor;

  public VirtualPlain8BitRegister(Instruction instruction, PipeRegister<T> register, NestedInstructionExecutor nestedInstructionExecutor1) {
    super(RegisterName.VIRTUAL);
    this.instruction = instruction;
    this.register = register;
    nestedInstructionExecutor = nestedInstructionExecutor1;
  }

  public T read() {
    if (updated)
      return data;
    else {
      nestedInstructionExecutor.execute(instruction).ifPresent(b -> data = register.read());
      return data;
    }
  }

  public int getLength() {
    return 0;
  }

  public void write(T value) {
    updated = true;
    nestedInstructionExecutor.evicted(instruction);
    this.data = value;
  }

  @Override
  public void increment() {
    updated = true;
    super.increment();
  }

  @Override
  public void decrement() {
    updated = true;
    super.decrement();
  }

  public Instruction getInstruction() {
    return instruction;
  }
}
