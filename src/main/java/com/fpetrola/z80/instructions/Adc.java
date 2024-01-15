package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Adc extends TargetSourceOpcode {

  public Adc(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();

    int alu8BitAdc = flag.ALU8BitAdc(value1, value2);

    target.write(alu8BitAdc);

    return cyclesCost;
  }

  public String toString() {
    return "ADC " + target + ", " + source;
  }
}
