package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public abstract class InvertedFetchInstruction<T extends WordNumber> extends TargetInstruction<T> {
  protected int valueDelta;
  protected final IFlagRegister<T> flag;

  public InvertedFetchInstruction(OpcodeReference<T> target, int valueDelta, IFlagRegister<T> flag) {
    super(target);
    this.valueDelta = valueDelta;
    this.flag = flag;
    if (valueDelta != 0)
      incrementLengthBy(1);
    else
      incrementLengthBy(0);
  }

  public int getValueDelta() {
    return valueDelta;
  }
}
