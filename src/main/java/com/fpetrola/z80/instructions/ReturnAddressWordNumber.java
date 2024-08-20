package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;

public class ReturnAddressWordNumber extends IntegerWordNumber {
  public ReturnAddressWordNumber(int i) {
    super(i);
  }


  protected IntegerWordNumber createInstance(int value) {
    return new ReturnAddressWordNumber(value & 0xFFFF);
  }
}
