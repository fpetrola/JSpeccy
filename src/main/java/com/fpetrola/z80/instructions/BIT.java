package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class BIT<T extends WordNumber> extends BitOperation<T> {
  private final IFlagRegister<T> flag;

  BIT(OpcodeReference target, int n, int valueDelta, IFlagRegister<T> flag) {
    super(target, n, valueDelta);
    this.flag = flag;
  }

  public int execute() {
    final T value = target.read();
    flag.testBit(value, n);
    return cyclesCost;
  }
}
