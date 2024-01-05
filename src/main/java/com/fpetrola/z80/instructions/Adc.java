package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Adc extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Adc(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  public int execute() {

    pc.increment(1);

    final int value1 = source.read();
    final int value2 = target.read();

    int alu8BitAdc = flag.ALU8BitAdc(value1, value2);

    target.write(alu8BitAdc);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "ADC " + target + ", " + source;
  }
}
