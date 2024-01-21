package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;

public class WriteOpcodeReference extends AbstractSpyReference implements Undoable {

  public RegisterName opcodeReference;
  public int value;
//  private int lastValue;
  private boolean isIncrement;

  public WriteOpcodeReference() {
  }

  public WriteOpcodeReference(RegisterName opcodeReference, int value, boolean isIncrement, boolean indirectReference) {
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.isIncrement = isIncrement;
    this.indirectReference = indirectReference;

//    lastValue = opcodeReference.read();
  }

  public String toString() {
    return this.opcodeReference.toString() + ":= " + OOZ80.convertToHex(this.value) + (indirectReference ? " (I)" : "");
  }

  public boolean sameReference(SpyReference obj) {
    if (obj instanceof ReadOpcodeReference) {
      ReadOpcodeReference readOpcodeReference = (ReadOpcodeReference) obj;
      return readOpcodeReference.opcodeReference.equals(opcodeReference);
    } else
      return false;
  }

  public void undo() {
//    if (!opcodeReference.toString().equals("PC") || isIncrement)
//      opcodeReference.write(lastValue);
  }
}
