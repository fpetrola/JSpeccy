package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Exx<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> bc;
  private final Register<T> de;
  private final Register<T> hl;
  private final Register<T> _bc;
  private final Register<T> _de;
  private final Register<T> _hl;

  public Exx(Register<T> bc, Register<T> de, Register<T> hl, Register<T> _bc, Register<T> _de, Register<T> _hl) {
    this.bc = bc;
    this.de = de;
    this.hl = hl;
    this._bc = _bc;
    this._de = _de;
    this._hl = _hl;
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
