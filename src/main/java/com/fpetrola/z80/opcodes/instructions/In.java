package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetSourceInstruction;
import com.fpetrola.z80.registers.Register;

public class In extends TargetSourceInstruction {

  public In(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int port = source.read();

    boolean equalsN = !(source instanceof Register);
    if (equalsN) {
      port |= a.read() << 8;

      memptr.write(port + 1);
    } else {
      port = bc.read();
    }

    int value = state.getIo().in(port);

    target.write(value);

    if (!equalsN)
      flag.inC(value);

    return cyclesCost;
  }
}
