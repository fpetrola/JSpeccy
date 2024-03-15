package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public abstract class ConditionalInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected final ImmutableOpcodeReference<T> positionOpcodeReference;
  protected T jumpAddress;
  protected Condition condition;
  protected Register<T> pc;

  public ConditionalInstruction(ImmutableOpcodeReference<T> positionOpcodeReference, Condition condition, Register<T> pc) {
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

  protected T calculateJumpAddress() {
    return positionOpcodeReference.read();
  }

  protected T beforeJump(T jumpAddress) {
    return jumpAddress;
  }

  protected T calculateRelativeJumpAddress() {
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

  public Condition getCondition() {
    return condition;
  }

  public String toString() {
    return getClass().getSimpleName() + " " + ((condition.toString().length() > 0) ? condition.toString() + ", " : "") + (jumpAddress != null ? jumpAddress : positionOpcodeReference);
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    condition.accept(visitor);
    visitor.visitingConditionalInstruction(this);
  }
}