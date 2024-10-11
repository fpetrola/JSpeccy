package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface ImmutableOpcodeReference<T> extends OpcodeReferenceBase {
  T read();

  int getLength();

  default void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitImmutableOpcodeReference(this);
  }
}
