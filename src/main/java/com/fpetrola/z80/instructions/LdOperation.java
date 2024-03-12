package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class LdOperation<T extends WordNumber> extends AbstractInstruction<T> {
  protected Instruction<T> instruction;
  protected OpcodeReference<T> target;

  public LdOperation(OpcodeReference target, Instruction<T> instruction) {
    this.target = target;
    this.instruction = instruction;
  }

  public int execute() {
    return cyclesCost;
  }
  public String toString() {
    return "LD " + target + "," + instruction;
  }
}
