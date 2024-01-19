package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class ReadOpcodeReference implements Undoable, SpyReference{

  public OpcodeReference opcodeReference;
  public int value;

  public ReadOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    this.value = value;
  }

  public String toString() {
    return OOZ80.convertToHex(this.value) + "= " + this.opcodeReference.toString();
  }

  @Override
  public void undo() {
    // TODO Auto-generated method stub
    
  }

  public OpcodeReference getReference() {
    return opcodeReference;
  }
}
