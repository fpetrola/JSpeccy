package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface TargetInstruction<T extends WordNumber> extends Instruction<T> {
  OpcodeReference<T> getTarget();
  void setTarget(OpcodeReference<T> target);

  @Override
  default void accept(InstructionVisitor visitor) {
    visitor.visitingTarget(getTarget(), this);
    visitor.visitingTargetInstruction(this);
  }
}
