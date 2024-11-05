package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class GenerateByteCodeInDepth<T extends WordNumber> implements InstructionVisitor<T> {
  public void visitingLd(Ld ld) {
    Virtual8BitsRegister source = (Virtual8BitsRegister) ld.getSource();

  }
}
