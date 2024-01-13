package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class Memory8BitReference implements OpcodeReference {

  private final Memory memory;
  private int delta;
  private OpCode opCode;
  private int fetchedAddress;

  public Memory8BitReference(Memory memory) {
    this.memory = memory;
  }

  public Memory8BitReference(Memory memory, int delta) {
    this(memory);
    this.delta = delta;
  }

  public int read() {
    return memory.read(fetchAddress() + delta);
  }

  public void write(int value) {
    memory.write(fetchAddress(), value);
  }

  private int fetchAddress() {
    Register pc = opCode.getPC();
    fetchedAddress = pc.read();
    pc.increment(1);
    return fetchedAddress;
  }

  public int cyclesCost() {
    return 3;
  }

  public String toString() {
    Register pc = opCode.getPC();
    int i = pc.read();
    String string = OOZ80.convertToHex(read()) + "";
    pc.write(i);
    return string;
  }

  public int getLength() {
    return 1;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
  }

  public Object clone() throws CloneNotSupportedException {
    return new Memory8BitReference(memory);
  }
}
