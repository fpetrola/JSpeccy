package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.IO;

public class In extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;
  private final IO io;

  public In(State state, OpcodeReference target, OpcodeReference source, IO io) {
    super(state);
    this.target = target;
    this.source = source;
    this.io = io;
  }

  @Override
  public int execute() {

    pc.increment(1);

    int port = source.read();

    boolean equalsN = source.toString().equals("n");
    if (equalsN) {
      port |= a.read() << 8;

      memptr.write(port + 1);
    } else {
      port = bc.read();
    }

    int value = io.in(port);

    target.write(value);

    if (!equalsN)
      flag.inC(value);

    return 4 + target.cyclesCost() + 4;
  }

  @Override
  public String toString() {
    return "IN " + target + "," + source;
  }

}
