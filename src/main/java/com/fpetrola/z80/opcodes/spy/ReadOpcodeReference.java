package com.fpetrola.z80.opcodes.spy;

import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class ReadOpcodeReference implements Undoable{

  public OpcodeReference opcodeReference;
  public int value;

  public ReadOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    this.value = value;
  }

  public String toString() {
    return value + "= " + this.opcodeReference.toString();
  }

  @Override
  public void undo() {
    // TODO Auto-generated method stub
    
  }

}
