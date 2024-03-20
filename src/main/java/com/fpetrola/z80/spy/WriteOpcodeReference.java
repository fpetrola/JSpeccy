package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class WriteOpcodeReference<T extends WordNumber> extends AbstractSpyReference<T> implements Undoable {

  public String opcodeReference;
  public T value;
//  private int lastValue;
  private boolean isIncrement;

  public WriteOpcodeReference() {
  }

  public WriteOpcodeReference(String opcodeReference, T value, boolean isIncrement, boolean indirectReference) {
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.isIncrement = isIncrement;
    this.indirectReference = indirectReference;

//    lastValue = opcodeReference.read();
  }

  public String toString() {
    return this.opcodeReference.toString() + ":= " + Helper.convertToHex(this.value) + (indirectReference ? " (I)" : "");
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
