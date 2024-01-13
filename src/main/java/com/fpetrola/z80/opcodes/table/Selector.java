package com.fpetrola.z80.opcodes.table;

import com.fpetrola.z80.instructions.OpCode;

public class Selector {
  private OpCode[] opcodes;

  public Selector(OpCode... opcodes) {
    this.opcodes = opcodes;
  }

  public OpCode get(int y) {
    return opcodes[y];
  }

}
