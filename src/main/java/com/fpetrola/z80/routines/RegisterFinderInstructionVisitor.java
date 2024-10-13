package com.fpetrola.z80.routines;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.instructions.Push;
import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.*;

@SuppressWarnings("ALL")
public abstract class RegisterFinderInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  protected boolean isTarget;
  protected boolean isSource;

  public void visitPush(Push push) {
    isSource= true;
    push.getTarget().accept(this);
  }

  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    isSource = false;
    isTarget = true;
    target.accept(this);
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    isSource = true;
    isTarget = false;
    source.accept(this);
  }

  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
    indirectMemory8BitReference.target.accept(this);
  }

  public void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {
    indirectMemory16BitReference.target.accept(this);
  }

  public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference memoryAccessOpcodeReference) {
    memoryAccessOpcodeReference.getC().accept(this);
  }

  public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    memoryPlusRegister8BitReference.getTarget().accept(this);
  }
}
