package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;

public class WriteOpcodeReference implements Undoable {

  public RegisterName opcodeReference;
  public int value;
//  private int lastValue;
  private boolean isIncrement;
  
  public WriteOpcodeReference() {
  }

  public WriteOpcodeReference(RegisterName opcodeReference, int value, boolean isIncrement) {
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.isIncrement = isIncrement;

//    lastValue = opcodeReference.read();
  }

  public String toString() {
    return this.opcodeReference.toString() + ":= " + OOZ80.convertToHex(this.value);
  }

  public void undo() {
//    if (!opcodeReference.toString().equals("PC") || isIncrement)
//      opcodeReference.write(lastValue);
  }

}
