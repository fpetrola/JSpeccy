package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstructionTransformerBase<T extends WordNumber> extends DummyInstructionVisitor<T> {
  InstructionFactory instructionFactory;
  protected AbstractInstruction cloned;

  public InstructionTransformerBase(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    instruction.accept(this);
    if (cloned == null)
      throw new RuntimeException("clone not supported for: " + instruction.getClass());
    return cloned;
  }

  public void setCloned(AbstractInstruction cloned, AbstractInstruction instruction) {
    this.cloned = cloned;
    this.cloned.setLength(instruction.getLength());
  }

  public <R extends PublicCloneable> R clone(R cloneable) {
    try {
      return (R) cloneable.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public <R extends PublicCloneable> R clone(OpcodeReference opcodeReference) {
    try {
      return (R) opcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public <R extends PublicCloneable> R clone(ImmutableOpcodeReference immutableOpcodeReference) {
    try {
      return (R) immutableOpcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public Condition clone(Condition condition) {
    final ConditionFlag[] cloned2 = new ConditionFlag[1];

    DummyInstructionVisitor visitor = new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        cloned2[0] = new ConditionFlag<>(InstructionTransformerBase.this.clone(conditionFlag.getRegister()), conditionFlag.getFlag(), conditionFlag.isNegate());
      }
    };
    condition.accept(visitor);
    return cloned2[0];
  }

  @Override
  public void visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {
    Constructor<?>[] constructors = parameterizedUnaryAluInstruction.getClass().getConstructors();
    try {
      cloned = (AbstractInstruction) constructors[0].newInstance(clone(parameterizedUnaryAluInstruction.getTarget()), parameterizedUnaryAluInstruction.getFlag());
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
