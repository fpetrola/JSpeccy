package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

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