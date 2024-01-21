package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.registers.RegisterName;

public class ReadOpcodeReference extends AbstractSpyReference implements Undoable {

  public RegisterName opcodeReference;
  public int value;
  public ReadOpcodeReference() {
  }

  public ReadOpcodeReference(RegisterName opcodeReference, int value, boolean indirectReference) {
    super();
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.indirectReference = indirectReference;
  }

  public String toString() {
    return OOZ80.convertToHex(this.value) + "= " + this.opcodeReference.toString()+ (indirectReference ? " (I)" : "");
  }

  public void undo() {
  }
}
