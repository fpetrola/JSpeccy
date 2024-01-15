package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Adc16 extends TargetSourceOpcode {

  public Adc16(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();
    int alu8BitAdc = flag.ALU16BitADC(value1, value2);
    target.write(alu8BitAdc);

    return cyclesCost;
  }

  public String toString() {
    return "ADC16 " + target + ", " + source;
  }

}
