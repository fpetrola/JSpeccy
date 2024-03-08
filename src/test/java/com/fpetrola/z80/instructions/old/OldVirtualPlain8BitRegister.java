package com.fpetrola.z80.instructions.old;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;
import com.fpetrola.z80.registers.RegisterName;

import java.util.ArrayList;
import java.util.List;

public class OldVirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> implements ChainedRegister<T> {
  private final Instruction instruction;
  private final PipeRegister<T> register;
  private boolean updated;

  @Override
  public List<ChainedRegister> getUsers() {
    return users;
  }

  private List<ChainedRegister> users = new ArrayList<>();

  public OldVirtualPlain8BitRegister(Instruction instruction, PipeRegister<T> register) {
    super(RegisterName.VIRTUAL);
    this.instruction = instruction;
    this.register = register;
  }

  public T read() {
    if (updated)
      return data;
    else {
//      nestedInstructionExecutor.execute(instruction).ifPresent(b -> data = register.read());
      instruction.execute();
      updated = true;
      data = register.read();
      return data;
    }
  }

  @Override
  public void evicted() {
    updated = false;
  }

  public int getLength() {
    return 0;
  }

  public void write(T value) {
    updated = true;
    this.data = value;
    ChainedRegister.super.evicted();
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

  public String toString() {
    return (updated ? "value= " + data + " - " : "") + "{" + instruction.toString() + "}";
  }
}
