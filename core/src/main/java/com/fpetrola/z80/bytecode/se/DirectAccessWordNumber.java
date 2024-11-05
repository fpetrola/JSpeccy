package com.fpetrola.z80.bytecode.se;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class DirectAccessWordNumber extends IntegerWordNumber {
  public final int pc;

  public DirectAccessWordNumber(int i, int pc) {
    super(i);
    this.pc = pc;
  }

  @Override
  public <T extends WordNumber> T left(int i) {
    return super.left(i);
  }

  public IntegerWordNumber createInstance(int value) {
    return new DirectAccessWordNumber(value & 0xFFFF, pc);
  }
}
