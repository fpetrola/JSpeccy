package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ex extends AbstractOpCode {

  private final OpcodeReference register;
  private final OpcodeReference alternate;

  public Ex(State state, OpcodeReference register, OpcodeReference alternate) {
    super(state);
    this.register = register;
    this.alternate = alternate;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int v1 = register.read();
    final int v2 = alternate.read();

    if (register == af) {
      flag.EXAFAF(af, _af);
    } else {
      register.write(v2);
      alternate.write(v1);
    }
    return 4 + register.cyclesCost() + alternate.cyclesCost();
  }

}
