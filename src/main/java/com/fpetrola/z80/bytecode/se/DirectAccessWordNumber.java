package com.fpetrola.z80.bytecode.se;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;

public class DirectAccessWordNumber extends IntegerWordNumber {
  public final int pc;

  public DirectAccessWordNumber(int i, int pc) {
    super(i);
    this.pc = pc;
  }


  protected IntegerWordNumber createInstance(int value) {
    return new DirectAccessWordNumber(value & 0xFFFF, pc);
  }
}
