package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Sub extends TargetSourceInstruction {

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitSub = flag.ALU8BitSub(value2, value1);
    target.write(alu8BitSub);

    return cyclesCost;
  }
}
