package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;

public class Selector {
  private Instruction[] opcodes;

  public Selector(Instruction... opcodes) {
    this.opcodes = opcodes;
  }

  public Instruction get(int y) {
    return opcodes[y];
  }

}
