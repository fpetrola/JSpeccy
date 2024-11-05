package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface OpcodeReference<T> extends ImmutableOpcodeReference<T>, MutableOpcodeReference<T> {
  default void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitOpcodeReference(this);
  }
}
