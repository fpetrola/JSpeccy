package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Inc16<T extends WordNumber> extends DefaultTargetInstruction<T> {
  public Inc16(OpcodeReference target) {
    super(target);
  }

  public int execute() {
    T read = target.read();
    target.write(read.plus1());
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingTarget(getTarget(), this);
    visitor.visitingTargetInstruction(this);
    visitor.visitingInc16(this);
  }
}
