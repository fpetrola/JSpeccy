package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import static com.fpetrola.z80.registers.RegisterName.MEMPTR;

public class In<T extends WordNumber> extends TargetSourceInstruction<T> {
  protected Register<T> memptr;

  public In(State state, OpcodeReference target, ImmutableOpcodeReference source) {
    super(state, target, source);
    this.memptr = state.getRegister(MEMPTR);
  }

  public int execute() {
    T port = source.read();

    boolean equalsN = !(source instanceof Register);
    if (equalsN) {
      port = port.or(a.read().left(8));
      memptr.write(port.plus1());
    } else {
      port = bc.read();
    }

    T value = state.getIo().in(port);

    target.write(value);

    if (!equalsN)
      flag.inC(value);

    return cyclesCost;
  }
}
