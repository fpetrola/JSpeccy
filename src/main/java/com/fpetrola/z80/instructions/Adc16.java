package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Flags;

public class Adc16 extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Adc16(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = source.read();
    final int value2 = target.read();
    int alu8BitAdc = flag.ALU16BitADC(value1, value2);
    target.write(alu8BitAdc);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

}
