package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Dec16<T extends WordNumber> extends DefaultTargetInstruction<T> {
  public Dec16(OpcodeReference target) {
    super(target);
  }

  public int execute() {
    target.write(target.read().minus1());
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingDec16(this);
  }
}
