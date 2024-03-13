package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public abstract class DefaultTargetFlagInstruction<T extends WordNumber> extends DefaultTargetInstruction<T> {
  protected FlagRegister<T> flag;

  public DefaultTargetFlagInstruction(OpcodeReference<T> target, FlagRegister<T> flag) {
    super(target);
    this.flag = flag;
    incrementLengthBy(target.getLength());
  }

  public FlagRegister<T> getFlag() {
    return flag;
  }

  public void setFlag(FlagRegister<T> flag) {
    this.flag = flag;
  }
}