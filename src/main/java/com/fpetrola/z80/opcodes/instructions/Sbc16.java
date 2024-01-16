package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetSourceInstruction;

public class Sbc16 extends TargetSourceInstruction {

  public Sbc16(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();
    int alu8BitAdc = flag.ALU16BitSBC(value2, value1);
    target.write(alu8BitAdc);

    return cyclesCost;
  }
}
