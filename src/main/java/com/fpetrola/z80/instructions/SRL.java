package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class SRL<T extends WordNumber> extends InvertedFetchInstruction<T> {

  SRL(OpcodeReference target, int valueDelta, IFlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {

    final T value = target.read();
    T shiftGenericSRL = flag.shiftGenericSRL(value);
    target.write(shiftGenericSRL);

    return cyclesCost;
  }
}
