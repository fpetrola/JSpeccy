package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface MutableOpcodeReference<T> extends OpcodeReferenceBase {
  void write(T value);

  default void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitMutableOpcodeReference(this);
  }
}
