package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class LdOperation extends TargetInstruction {

  private Instruction instruction;

  public LdOperation(State s, OpcodeReference target, Instruction instruction) {
    super(s, target);
    this.instruction = instruction;
  }

  public int execute() {
    return 4;
  }

  public String toString() {
    return "LD " + target + "," + instruction;
  }
}
