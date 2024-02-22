package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class RES<T extends WordNumber> extends BitOperation<T> {

  RES(OpcodeReference target, int n, int valueDelta, IFlagRegister<T> flag) {
    super(target, n, valueDelta, flag);
  }

  public int execute() {
    target.write(target.read().and(~(1 << n)));
    return cyclesCost;
  }
}
