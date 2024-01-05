package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Add extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Add(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = source.read();
    final int value2 = target.read();
    int ALU8BitAdd = flag.ALU8BitAdd(value1, value2);
    target.write(ALU8BitAdd);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

}
