package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.jspeccy.ConditionPredicate;
import com.fpetrola.z80.jspeccy.FlipFLopConditionFlag;
import com.fpetrola.z80.opcodes.references.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class InstructionTransformerBase<T extends WordNumber> extends DummyInstructionVisitor<T> {
  DefaultInstructionFactory instructionFactory;
  protected AbstractInstruction cloned;

  public InstructionTransformerBase(DefaultInstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    cloned = null;
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

  public <C extends Condition> C clone(C condition) {
    ConditionTransformer visitor = new ConditionTransformer();
    condition.accept(visitor);
    return (C) visitor.result;
  }

  @Override
  public boolean visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {
    Constructor<?>[] constructors = parameterizedUnaryAluInstruction.getClass().getConstructors();
    try {
      AbstractInstruction cloned1 = (AbstractInstruction) constructors[0].newInstance(clone(parameterizedUnaryAluInstruction.getTarget()), parameterizedUnaryAluInstruction.getFlag());
      setCloned(cloned1, parameterizedUnaryAluInstruction);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction) {
    Constructor<?>[] constructors = parameterizedBinaryAluInstruction.getClass().getConstructors();
    try {
      AbstractInstruction cloned1 = (AbstractInstruction) constructors[0].newInstance(clone(parameterizedBinaryAluInstruction.getTarget()), clone(parameterizedBinaryAluInstruction.getSource()), parameterizedBinaryAluInstruction.getFlag());
      setCloned(cloned1, parameterizedBinaryAluInstruction);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  private class ConditionTransformer extends DummyInstructionVisitor {
    public Condition result;

    public ConditionTransformer() {
    }

    public void visitingConditionFlag(ConditionFlag conditionFlag) {
      result = new ConditionFlag<>(InstructionTransformerBase.this.clone(conditionFlag.getRegister()), conditionFlag.getFlag(), conditionFlag.isNegate(), InstructionTransformerBase.clone(conditionFlag.isConditionMet));
    }

    public void visitingConditionAlwaysTrue(ConditionAlwaysTrue conditionAlwaysTrue) {
      ConditionAlwaysTrue result1 = new ConditionAlwaysTrue();
      result1.isConditionMet= InstructionTransformerBase.clone(conditionAlwaysTrue.isConditionMet);
      result = result1;
    }

    public void visitBNotZeroCondition(BNotZeroCondition bNotZeroCondition) {
      result = new BNotZeroCondition(InstructionTransformerBase.this.clone(bNotZeroCondition.getB()), InstructionTransformerBase.clone(bNotZeroCondition.isConditionMet));
    }
  }

  public static ConditionPredicate<Boolean> clone(ConditionPredicate isConditionMet) {
    if (isConditionMet instanceof FlipFLopConditionFlag.FlipFlopPredicate flipFlopPredicate) {
      return new FlipFLopConditionFlag(flipFlopPredicate.executionsListener, flipFlopPredicate.alwaysTrue).isConditionMet;
    } else
      return isConditionMet;
  }
}
