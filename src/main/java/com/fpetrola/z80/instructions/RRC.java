package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RRC<T extends WordNumber> extends InvertedFetchInstruction<T> {

  RRC(OpcodeReference target, int valueDelta, FlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {
    final T value = target.read();
    T shiftGenericRRC = flag.shiftGenericRRC(value);
    target.write(shiftGenericRRC);

    return cyclesCost;
  }
}
