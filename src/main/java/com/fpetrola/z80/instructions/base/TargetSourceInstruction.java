package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class TargetSourceInstruction<T extends WordNumber> extends TargetInstruction<T> {

  protected final OpcodeReference<T> source;

  public TargetSourceInstruction(State state, OpcodeReference<T> target, OpcodeReference<T> source) {
    super(state, target);
    this.source = source;
    incrementLengthBy(source.getLength());
    cyclesCost += source.cyclesCost();
  }

  public String toString() {
    return spy.executeInPause(() ->  super.toString() + "," + source);
  }
  
  public OpcodeReference getSource() {
    return source;
  }
}