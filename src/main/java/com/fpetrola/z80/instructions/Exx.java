package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import static com.fpetrola.z80.registers.RegisterName.*;

public class Exx<T extends WordNumber> extends AbstractInstruction<T> {
  protected Register<T> _bc;
  protected Register<T> _de;
  protected Register<T> _hl;

  public Exx(State state) {
    super(state);
    this._bc = state.getRegister(BCx);
    this._de = state.getRegister(DEx);
    this._hl = state.getRegister(HLx);
  }

  public int execute() {
    T v1 = bc.read();
    bc.write(_bc.read());
    _bc.write(v1);

    v1 = de.read();
    de.write(_de.read());
    _de.write(v1);

    v1 = hl.read();
    hl.write(_hl.read());
    _hl.write(v1);

    return 4;
  }
}
