package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;

public class JumpLabelVisitor extends DummyInstructionVisitor {
  private int jumpLabel = 0;

  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    jumpLabel = conditionalInstruction.getJumpAddress() != null ? conditionalInstruction.getJumpAddress().intValue() : -1;
  }

  public int getJumpLabel() {
    return jumpLabel;
  }
}
