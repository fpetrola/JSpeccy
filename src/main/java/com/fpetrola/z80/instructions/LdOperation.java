package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class LdOperation<T extends WordNumber> extends TargetInstruction<T> {
  private Instruction<T> instruction;

  public LdOperation(OpcodeReference target, Instruction<T> instruction, FlagRegister<T> flag) {
    super(target, flag);
    this.instruction = instruction;
  }

  public int execute() {
    return 4;
  }

  public String toString() {
    return "LD " + target + "," + instruction;
  }
}
