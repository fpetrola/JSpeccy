package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;

public class ReadOpcodeReference<T extends WordNumber> extends AbstractSpyReference<T> implements Undoable {

  public String opcodeReference;
  public T value;
  public ReadOpcodeReference() {
  }

  public ReadOpcodeReference(String opcodeReference, T value, boolean indirectReference) {
    super();
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.indirectReference = indirectReference;
  }

  public String toString() {
    return Helper.convertToHex(this.value) + "= " + this.opcodeReference.toString()+ (indirectReference ? " (I)" : "");
  }

  public void undo() {
  }
}
