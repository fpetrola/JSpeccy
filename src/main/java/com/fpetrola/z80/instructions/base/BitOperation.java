package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public abstract class BitOperation<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  protected final int n;

  public BitOperation(OpcodeReference<T> target, int n, Register<T> flag) {
    super(target, flag);
    this.n = n;
  }

  public String toString() {
    return getClass().getSimpleName() + " " + n + ", " + target;
  }

  public int getN() {
    return n;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingBitOperation(this);
  }
}