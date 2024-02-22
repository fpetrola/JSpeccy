package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class SRA<T extends WordNumber> extends InvertedFetchInstruction<T> {

  SRA(OpcodeReference target, int valueDelta, IFlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {

    final T value = target.read();
    T shiftGenericSRA = flag.shiftGenericSRA(value);
    target.write(shiftGenericSRA);

    return cyclesCost;
  }
}
