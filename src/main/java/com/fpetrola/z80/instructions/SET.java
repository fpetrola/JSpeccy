package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class SET<T extends WordNumber> extends BitOperation<T> {

  SET(OpcodeReference target, int n, int valueDelta, FlagRegister<T> flag) {
    super(target, n, valueDelta, flag);
  }

  public int execute() {
    target.write(target.read().or(1 << n));
    return cyclesCost;
  }
}
