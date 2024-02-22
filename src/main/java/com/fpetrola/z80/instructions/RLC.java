package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class RLC<T extends WordNumber> extends InvertedFetchInstruction<T> {

  RLC(OpcodeReference target, int valueDelta, IFlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {
    final T value = target.read();
    T shiftGenericRLC = flag.shiftGenericRLC(value);
    target.write(shiftGenericRLC);

    return cyclesCost;
  }
}
