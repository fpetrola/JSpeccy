package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;

public class ReadOpcodeReference {

  private OpcodeReference opcodeReference;

  public ReadOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    if (opcodeReference instanceof Register) {
      System.out.println("" + opcodeReference.toString() + " -> " + value);
    }
  }

}
