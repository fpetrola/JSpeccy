package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Xor extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Xor(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  public int execute() {
    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();

    int aLU8BitXor = flag.ALU8BitXor(value2, value1);

    target.write(aLU8BitXor);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "XOR " + source + " - " + target;
  }

}
