package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class LdOperation<T extends WordNumber> extends TargetInstruction<T> {

  private Instruction<T> instruction;

  public LdOperation(State s, OpcodeReference target, Instruction<T> instruction) {
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
