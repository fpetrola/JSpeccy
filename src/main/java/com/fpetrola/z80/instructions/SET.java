package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class SET<T extends WordNumber> extends BitOperation<T> {

  public SET(OpcodeReference target, int n, Register<T> flag) {
    super(target, n, flag);
  }

  public int execute() {
    target.write(target.read().or(1 << n));
    return cyclesCost;
  }
}
