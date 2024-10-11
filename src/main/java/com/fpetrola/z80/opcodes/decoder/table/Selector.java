package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;

public class Selector<T> {
  private Instruction<T>[] opcodes;

  public Selector(Instruction<T>... opcodes) {
    this.opcodes = opcodes;
  }

  public Instruction<T> get(int y) {
    return opcodes[y];
  }

}
