package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class PipeRegister<T extends WordNumber> implements Register<T> {
  private Instruction instruction;
  private T value;
  private OpcodeReference<T> readOR;
  private boolean executing;

  public void increment() {
  }

  public void decrement() {
  }

  public RegisterName getName() {
    return RegisterName.VIRTUAL;
  }

  public T read() {
    if (!executing) {
      executing = true;
      instruction.execute();
      return value;
    } else if (value != null) {
      return value;
    } else {
      return readOR.read();
    }
  }

  public void write(T value) {
    this.value = value;
    executing = false;
  }

  public int getLength() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  public void setInstruction(Instruction instruction) {
    this.instruction = instruction;
  }

  public OpcodeReference r(OpcodeReference<T> readOR) {
    this.readOR = readOR;
    return this;
  }
}
