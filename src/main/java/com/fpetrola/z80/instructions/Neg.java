package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Neg<T extends WordNumber> extends TargetInstruction<T> {
  private final IFlagRegister<T> flag;

  Neg(OpcodeReference target, IFlagRegister<T> flag) {
    super(target);
    this.flag = flag;
  }

  public int execute() {
    T neg = flag.NEG(target.read());
    target.write(neg);

    return cyclesCost;
  }
}
