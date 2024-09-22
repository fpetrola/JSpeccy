package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import org.cojen.maker.Variable;

import java.util.function.Supplier;

public class PendingFlagUpdate {
  public final Supplier<Object> targetVariableSupplier;
  public final DefaultTargetFlagInstruction targetFlagInstruction;
  private final ByteCodeGenerator byteCodeGenerator;

  public PendingFlagUpdate(Supplier<Object> targetVariable, DefaultTargetFlagInstruction targetFlagInstruction, ByteCodeGenerator byteCodeGenerator) {
    this.targetVariableSupplier = targetVariable;
    this.targetFlagInstruction = targetFlagInstruction;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  public void update() {
    OpcodeReferenceVisitor variableAdapter = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    targetFlagInstruction.getFlag().accept(variableAdapter);
    Object targetVariable = targetVariableSupplier.get();
    if (!(targetVariable instanceof WriteArrayVariable))
      ((Variable) variableAdapter.getResult()).set(targetVariable);
  }
}
