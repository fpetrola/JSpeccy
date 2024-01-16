package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.State;

public abstract class TargetInstruction extends AbstractInstruction {

  protected final OpcodeReference target;

  public TargetInstruction(State state, OpcodeReference target) {
    super(state);
    this.target = target;
    incrementLengthBy(target.getLength());
    cyclesCost += target.cyclesCost();
  }

  public OpcodeReference getTarget() {
    return target;
  }

  public String toString() {
    return super.toString() + " " + target.toString();
  }
}