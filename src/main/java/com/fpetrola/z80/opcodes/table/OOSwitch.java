package com.fpetrola.z80.opcodes.table;

import com.fpetrola.z80.instructions.OpCode;

public class OOSwitch {
  private OpCode[] opcodes;

  public OOSwitch(OpCode... opcodes) {
    this.opcodes = opcodes;
  }

  public OpCode getCase(int y) {
    if (y >= opcodes.length)
      return opcodes[opcodes.length - 1];
    else
      return opcodes[y];
  }

}
