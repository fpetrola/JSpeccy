package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.FlagInstruction;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualAssignmentInstruction;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Variable;

import java.util.List;
import java.util.function.Supplier;

public class PendingFlagUpdate {
  public final Supplier<Variable> targetVariableSupplier;
  public final FlagInstruction targetFlagInstruction;
  private final RoutineByteCodeGenerator routineByteCodeGenerator;
  public final int address;
  public Supplier<Object> sourceVariableSupplier;
  public boolean processed;

  public PendingFlagUpdate(Supplier<Variable> targetVariable, FlagInstruction targetFlagInstruction, RoutineByteCodeGenerator routineByteCodeGenerator, int address) {
    this.targetVariableSupplier = targetVariable;
    this.targetFlagInstruction = targetFlagInstruction;
    this.routineByteCodeGenerator = routineByteCodeGenerator;
    this.address = address;
  }

  public PendingFlagUpdate(Supplier<Variable> targetVariable, FlagInstruction targetFlagInstruction, RoutineByteCodeGenerator routineByteCodeGenerator, int address, Supplier<Object> sourceVariable) {
    this(targetVariable, targetFlagInstruction, routineByteCodeGenerator, address);
    this.sourceVariableSupplier = sourceVariable;
  }

  public void update(boolean force) {
    VirtualRegister virtualRegister = (VirtualRegister) targetFlagInstruction.getFlag();
    List<VirtualRegister<?>> dependants = virtualRegister.getDependants();
    if (force || dependants.stream().anyMatch(d -> d instanceof Virtual8BitsRegister<?> virtual8BitsRegister && virtual8BitsRegister.instruction instanceof VirtualAssignmentInstruction<?>)) {
      OpcodeReferenceVisitor variableAdapter = new OpcodeReferenceVisitor(true, routineByteCodeGenerator);
      targetFlagInstruction.getFlag().accept(variableAdapter);
      Object targetVariable = targetVariableSupplier.get();
      if (!(targetVariable instanceof WriteArrayVariable))
        ((Variable) variableAdapter.getResult()).set(RoutineByteCodeGenerator.getRealVariable(targetVariable));
    }
  }
}
