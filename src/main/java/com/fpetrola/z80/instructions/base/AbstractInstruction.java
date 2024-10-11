package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class AbstractInstruction<T extends WordNumber> implements Instruction<T> {
  protected int length = 1;
  protected int cyclesCost = 4;
  private T nextPC = null;

  protected AbstractInstruction() {
    cyclesCost += 1;
  }

  public String toString() {
    return getName();
  }

  protected String getName() {
    return getClass().getSimpleName();
  }

  public int getLength() {
    return length;
  }

  public void incrementLengthBy(int by) {
    length += by;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public State getState() {
    return null;
  }

  public void setNextPC(T address) {
    this.nextPC = address;
  }

  public T getNextPC() {
    return nextPC;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingInstruction(this);
  }
}
