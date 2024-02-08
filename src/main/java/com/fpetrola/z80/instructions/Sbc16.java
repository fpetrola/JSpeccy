package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Sbc16<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Sbc16(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    T alu8BitAdc = flag.ALU16BitSBC(value2, value1);
    target.write(alu8BitAdc);

    return cyclesCost;
  }
}
