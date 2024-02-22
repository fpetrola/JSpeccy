package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class LdAR<T extends WordNumber> extends Ld<T> {
  private final State<T> state;

  LdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, IFlagRegister<T> flag, State<T> state) {
    super(target, source, flag);
    this.state = state;
  }

  public int execute() {
    T value = source.read();
    T ldar = flag.LDAR(target.read(), value, state.isIff2());
   
    target.write(ldar);

    return cyclesCost;
  }
}
