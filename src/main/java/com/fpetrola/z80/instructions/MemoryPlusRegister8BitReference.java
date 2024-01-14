package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class MemoryPlusRegister8BitReference implements OpcodeReference {

  private final Memory memory;
  private final OpcodeReference target;
  private int valueDelta;
  private OpCode opCode;
  private int fetchedRelative;

  public MemoryPlusRegister8BitReference(OpcodeReference target, Memory memory, int valueDelta) {
    this.target = target;
    this.memory = memory;
    this.valueDelta = valueDelta;
  }

  public int read() {
    final int dd = fetchRelative();
    final int address = target.read() + (byte) dd;
    final int value = memory.read(address);

    return value;
  }

  public void write(int value) {
    final int dd = fetchRelative();
    final int address = target.read() + (byte) dd;
    memory.write(address, value);
  }

  private int fetchRelative() {
    Register pc = opCode.getPC();
    final int dd = memory.read(pc.read() + valueDelta);
    fetchedRelative = dd;
    return dd;
  }

  public int cyclesCost() {
    return 3 + 5 + 4 + 3;
  }

  public String toString() {
    int dd = fetchRelative();
    String string2 = (dd > 0 ? "+" : "-") + OOZ80.convertToHex(Math.abs(dd));
    String string = "(" + target.toString() + string2 + ")";
    return string;
  }

  public int getLength() {
    return 1;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
    target.setOpCode(opCode);
  }

  public Object clone() throws CloneNotSupportedException {
    return new MemoryPlusRegister8BitReference((OpcodeReference) target.clone(), memory, valueDelta);
  }
}
