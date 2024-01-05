package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;

public class WriteOpcodeReference {

  private OpcodeReference opcodeReference;

  public WriteOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    if (opcodeReference instanceof Register) {
      System.out.println("" + opcodeReference.toString() + " <- " + value);
    }
  }

}
