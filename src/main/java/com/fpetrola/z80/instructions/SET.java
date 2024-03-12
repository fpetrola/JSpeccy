package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class SET<T extends WordNumber> extends BitOperation<T> {

  public SET(OpcodeReference target, int n, FlagRegister<T> flag) {
    super(target, n, flag);
  }

  public int execute() {
    target.write(target.read().or(1 << n));
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingSet(this);
  }
}
