package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;

class DummyInstruction<T extends WordNumber> implements Instruction<T> {
  @Override
  public int execute() {
    return 0;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public void accept(InstructionVisitor<?> visitor) {
  }
}
