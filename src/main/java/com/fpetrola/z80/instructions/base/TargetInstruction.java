package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class TargetInstruction<T extends WordNumber> extends AbstractInstruction<T> {

  protected final OpcodeReference<T> target;

  public TargetInstruction(State state, OpcodeReference<T> target) {
    super(state);
    this.target = target;
    incrementLengthBy(target.getLength());
    cyclesCost += target.cyclesCost();
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  public String toString() {
    return spy.executeInPause(() -> super.toString() + " " + target.toString());
  }
}