package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.registers.RegisterName;

public class ReadOpcodeReference implements Undoable, SpyReference {

  public RegisterName opcodeReference;
  public int value;

  public ReadOpcodeReference() {
  }

  public ReadOpcodeReference(RegisterName opcodeReference, int value) {
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

  public Object getReference() {
    return opcodeReference;
  }

  public void setReference(RegisterName opcodeReference) {
    this.opcodeReference = opcodeReference;
  }
}
