package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class BIT<T extends WordNumber> extends BitOperation<T> {
  BIT(OpcodeReference target, int n, int valueDelta, FlagRegister<T> flag) {
    super(target, n, valueDelta, flag);
  }

  public int execute() {
    final T value = target.read();
    flag.testBit(value, n);
    return cyclesCost;
  }
}
