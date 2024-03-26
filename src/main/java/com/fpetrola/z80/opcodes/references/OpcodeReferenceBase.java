package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface OpcodeReferenceBase extends PublicCloneable {
  void accept(InstructionVisitor instructionVisitor);
}
