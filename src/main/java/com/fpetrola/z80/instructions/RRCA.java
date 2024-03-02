package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RRCA<T extends WordNumber> extends TargetInstruction<T> {
  private final FlagRegister<T> flag;

  RRCA(OpcodeReference target, FlagRegister<T> flag) {
    super(target);
    this.flag = flag;
  }

  public int execute() {

    final T value = target.read();
    T rrca = flag.RRCA(value);
    target.write(rrca);

    return cyclesCost;
  }
}
