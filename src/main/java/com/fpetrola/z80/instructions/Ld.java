package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T> {
  protected final IFlagRegister<T> flag;

  Ld(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, IFlagRegister<T> flag) {
    super(null, target, source);
    this.flag = flag;
  }

  public int execute() {
    T value = source.read();
    T aLU8Assign = flag.ALU8Assign(value);
    target.write(aLU8Assign);
    return cyclesCost;
  }
}
