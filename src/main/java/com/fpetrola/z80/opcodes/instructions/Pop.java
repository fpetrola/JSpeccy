package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

public class Pop extends TargetInstruction {

  public Pop(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int address = sp.read() & 0xffff;
    final int value = ((memory.read(address + 1) << 8) & 0xff00 | memory.read(address) & 0xff);
    sp.increment(2);
    target.write(value);

    return 5 + 3 + 3;
  }
}
