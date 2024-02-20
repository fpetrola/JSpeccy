package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Out<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Out(State state, ImmutableOpcodeReference target, ImmutableOpcodeReference source) {
    super(state, (OpcodeReference<T>) target, source);
  }

  public int execute() {
    T port = target.read();
    T value = source.read();
    state.getIo().out(port, value);

    return cyclesCost;
  }
}
