package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.State;

public abstract class TargetSourceInstruction extends TargetInstruction {

  protected final OpcodeReference source;

  public TargetSourceInstruction(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target);
    this.source = source;
    incrementLengthBy(source.getLength());
    cyclesCost += source.cyclesCost();
  }

  public String toString() {
    return super.toString() + "," + source;
  }
  
  public OpcodeReference getSource() {
    return source;
  }
}