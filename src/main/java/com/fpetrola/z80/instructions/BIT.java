package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Plain16BitRegister;

public class BIT extends TargetOpCode {

  private final int n;
  private int valueDelta;

  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {

//    pc.increment(valueDelta);

    final int value = target.read();

    flag.testBit(value, n);

//    if (valueDelta != 0)
//      pc.increment(1);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    Plain16BitRegister lastPC = getPC();
    Plain16BitRegister pc2 = new Plain16BitRegister("PC");
    pc2.write(lastPC.read());
    setPC(pc2);
    pc2.increment(valueDelta);
    String result = "BIT " + n + ", " + target;
    setPC(lastPC);
    return result;
  }

  public int getLength() {
    return super.getLength() + (valueDelta != 0 ? 1 : 0);
  }
}
