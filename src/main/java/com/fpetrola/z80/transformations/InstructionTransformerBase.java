package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.Lddr;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class InstructionTransformerBase<T extends WordNumber> extends DummyInstructionVisitor<T> {
  InstructionFactory instructionFactory;
  protected AbstractInstruction cloned;

  public InstructionTransformerBase(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    cloned= null;
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
      opcodeReference.read();
      return (R) opcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public <R extends PublicCloneable> R clone(ImmutableOpcodeReference immutableOpcodeReference) {
    try {
      immutableOpcodeReference.read();
      return (R) immutableOpcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public Condition clone(Condition condition) {
    final Condition[] cloned2 = new Condition[1];

    DummyInstructionVisitor visitor = new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        cloned2[0] = new ConditionFlag<>(InstructionTransformerBase.this.clone(conditionFlag.getRegister()), conditionFlag.getFlag(), conditionFlag.isNegate());
      }

      public void visitingConditionAlwaysTrue(ConditionAlwaysTrue conditionAlwaysTrue) {
        cloned2[0] = new ConditionAlwaysTrue();
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

  @Override
  public void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction) {
    Constructor<?>[] constructors = parameterizedBinaryAluInstruction.getClass().getConstructors();
    try {
      cloned = (AbstractInstruction) constructors[0].newInstance(clone(parameterizedBinaryAluInstruction.getTarget()), clone(parameterizedBinaryAluInstruction.getSource()), parameterizedBinaryAluInstruction.getFlag());
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}
