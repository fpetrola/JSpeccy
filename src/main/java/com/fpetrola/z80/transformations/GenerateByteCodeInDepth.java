package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class GenerateByteCodeInDepth<T extends WordNumber> extends DummyInstructionVisitor<T> {
  public void visitingLd(Ld ld) {
    Virtual8BitsRegister source = (Virtual8BitsRegister) ld.getSource();

  }
}
