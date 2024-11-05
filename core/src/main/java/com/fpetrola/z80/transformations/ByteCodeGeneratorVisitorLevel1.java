package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class ByteCodeGeneratorVisitorLevel1<T extends WordNumber> implements InstructionVisitor<T> {
  private GenerateByteCodeInDepth visitor = new GenerateByteCodeInDepth();

  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    conditionalInstruction.accept(visitor);
  }

  public void visitingLd(Ld ld) {
    if (!(ld.getTarget() instanceof Register)) {
      ld.accept(visitor);
    }
  }
}
