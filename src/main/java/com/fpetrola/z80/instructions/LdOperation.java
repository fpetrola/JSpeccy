package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class LdOperation extends TargetOpCode {

  private OpCode opCode;

  public LdOperation(State s, OpcodeReference target, OpCode opCode) {
    super(s, target);
    this.opCode = opCode;
  }

  public int execute() {
    return 4;
  }

  public String toString() {
    return "LD " + target + "," + opCode;
  }
}
