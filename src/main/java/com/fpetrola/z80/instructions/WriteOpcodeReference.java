package com.fpetrola.z80.instructions;

public class WriteOpcodeReference implements Undoable {

  public OpcodeReference opcodeReference;
  public int value;
  private int lastValue;
  private boolean isIncrement;

  public WriteOpcodeReference(OpcodeReference opcodeReference, int value, boolean isIncrement) {
    this.opcodeReference = opcodeReference;
    this.value = value;
    this.isIncrement = isIncrement;

    lastValue = opcodeReference.read();
  }

  public String toString() {
    return this.opcodeReference.toString() + ":= " + this.value;
  }

  public void undo() {
    if (!opcodeReference.toString().equals("PC") || isIncrement)
      opcodeReference.write(lastValue);
  }

}
