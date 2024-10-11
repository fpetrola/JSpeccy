package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;

public class ReturnAddressWordNumber extends IntegerWordNumber {
  public final int pc;

  public ReturnAddressWordNumber(int i, int pc) {
    super(i);
    this.pc = pc;
  }


  protected IntegerWordNumber createInstance(int value) {
    return new ReturnAddressWordNumber(value & 0xFFFF, pc);
  }
}
