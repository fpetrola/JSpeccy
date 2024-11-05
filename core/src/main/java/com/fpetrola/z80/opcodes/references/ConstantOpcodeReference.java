package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public class ConstantOpcodeReference<T> implements ImmutableOpcodeReference<T> {
  private final T value;

  ConstantOpcodeReference(T value) {
    this.value = value;
  }

  public T read() {
    return value;
  }

  public int getLength() {
    return 0;
  }

  public String toString() {
    return value + "";
  }

  public void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitConstantOpcodeReference(this);
  }

  public Object clone() throws CloneNotSupportedException {
    return this;
  }
}
