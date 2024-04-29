package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public abstract class ConditionalInstruction<T extends WordNumber, C extends Condition> extends AbstractInstruction<T> {
  public void setPositionOpcodeReference(ImmutableOpcodeReference<T> positionOpcodeReference) {
    this.positionOpcodeReference = positionOpcodeReference;
  }

  protected ImmutableOpcodeReference<T> positionOpcodeReference;
  protected T jumpAddress;
  protected C condition;
  protected Register<T> pc;

  public ConditionalInstruction(ImmutableOpcodeReference<T> positionOpcodeReference, C condition, Register<T> pc) {
    this.positionOpcodeReference = positionOpcodeReference;
    this.condition = condition;
    this.pc = pc;
    incrementLengthBy(positionOpcodeReference.getLength());
  }

  public int execute() {
    return jumpIfConditionMatches();
  }

  protected int jumpIfConditionMatches() {
    T jumpAddress = calculateJumpAddress();
    if (condition.conditionMet()) {
      jumpAddress = beforeJump(jumpAddress);
      setJumpAddress(jumpAddress);
      setNextPC(jumpAddress);
    } else
      setNextPC(null);

    return cyclesCost;
  }

  public T calculateJumpAddress() {
    return jumpAddress= positionOpcodeReference.read();
  }

  protected T beforeJump(T jumpAddress) {
    return jumpAddress;
  }

  public T calculateRelativeJumpAddress() {
    return jumpAddress = pc.read().plus(length + (byte) positionOpcodeReference.read().intValue());
  }

  public T getJumpAddress() {
    return jumpAddress;
  }

  public void setJumpAddress(T jumpAddress) {
    this.jumpAddress = jumpAddress;
  }

  public ImmutableOpcodeReference<T> getPositionOpcodeReference() {
    return positionOpcodeReference;
  }

  public C getCondition() {
    return condition;
  }

  public String toString() {
  //  return getClass().getSimpleName() + " " + ((condition.toString().length() > 0) ? condition.toString() + ", " : "") + (jumpAddress != null ? jumpAddress : positionOpcodeReference);
    return getClass().getSimpleName() + " " + ((condition.toString().length() > 0) ? condition.toString() + ", " : "") + (jumpAddress != null ? positionOpcodeReference : positionOpcodeReference);
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    condition.accept(visitor);
    visitor.visitingConditionalInstruction(this);
  }
}