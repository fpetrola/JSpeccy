package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualAssignmentInstruction;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Variable;

import java.util.List;
import java.util.function.Supplier;

public class PendingFlagUpdate {
  public final Supplier<Variable> targetVariableSupplier;
  public final DefaultTargetFlagInstruction targetFlagInstruction;
  private final ByteCodeGenerator byteCodeGenerator;
  public final int address;
  public Supplier<Object> sourceVariableSupplier;
  public boolean processed;

  public PendingFlagUpdate(Supplier<Variable> targetVariable, DefaultTargetFlagInstruction targetFlagInstruction, ByteCodeGenerator byteCodeGenerator, int address) {
    this.targetVariableSupplier = targetVariable;
    this.targetFlagInstruction = targetFlagInstruction;
    this.byteCodeGenerator = byteCodeGenerator;
    this.address = address;
  }

  public PendingFlagUpdate(Supplier<Variable> targetVariable, DefaultTargetFlagInstruction targetFlagInstruction, ByteCodeGenerator byteCodeGenerator, int address, Supplier<Object> sourceVariable) {
    this(targetVariable, targetFlagInstruction, byteCodeGenerator, address);
    this.sourceVariableSupplier = sourceVariable;
  }

  public void update(boolean force) {
    VirtualRegister virtualRegister = (VirtualRegister) targetFlagInstruction.getFlag();
    List<VirtualRegister<?>> dependants = virtualRegister.getDependants();
    if (force || dependants.stream().anyMatch(d -> d instanceof Virtual8BitsRegister<?> virtual8BitsRegister && virtual8BitsRegister.instruction instanceof VirtualAssignmentInstruction<?>)) {
      OpcodeReferenceVisitor variableAdapter = new OpcodeReferenceVisitor(true, byteCodeGenerator);
      targetFlagInstruction.getFlag().accept(variableAdapter);
      Object targetVariable = targetVariableSupplier.get();
      if (!(targetVariable instanceof WriteArrayVariable))
        ((Variable) variableAdapter.getResult()).set(ByteCodeGenerator.getRealVariable(targetVariable));
    }
  }
}
