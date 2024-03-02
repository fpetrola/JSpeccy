package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class SLA<T extends WordNumber> extends InvertedFetchInstruction<T> {
  SLA(OpcodeReference<T> target, int valueDelta, FlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {

    final T value = target.read();
    T shiftGenericSLA = flag.shiftGenericSLA(value);
    target.write(shiftGenericSLA);

    return cyclesCost;
  }
}
