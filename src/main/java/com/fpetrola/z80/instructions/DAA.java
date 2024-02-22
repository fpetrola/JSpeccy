package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class DAA<T extends WordNumber> extends TargetInstruction<T> {
  private final IFlagRegister<T> flag;

  DAA(OpcodeReference target, IFlagRegister<T> flag) {
    super(null, target);
    this.flag = flag;
  }

  public int execute() {
    final T a = target.read();
    target.write(flag.DAA(a));
    return cyclesCost;
  }
}
