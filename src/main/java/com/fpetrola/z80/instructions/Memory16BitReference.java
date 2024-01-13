package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class Memory16BitReference implements OpcodeReference {

  private final Memory memory;
  private OpCode opCode;

  public Memory16BitReference(Memory memory) {
    this.memory = memory;
  }

  public int read() {
    Register pc = opCode.getPC();
    int address = pc.read();
    int lsb = memory.read(address) & 0xff;
    int value = ((memory.read(address + 1) << 8) & 0xff00 | lsb);
    pc.increment(2);
    return value;
  }

  public void write(int value) {
    Register pc = opCode.getPC();
    int address1 = pc.read();
    int lsb = memory.read(address1) & 0xff;
    int address = ((memory.read(address1 + 1) << 8) & 0xff00 | lsb);
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));
    pc.increment(2);
  }

  public int cyclesCost() {
    return 3 + 3;
  }

  public String toString() {
    Register pc = opCode.getPC();
    int i = pc.read();
    String string = OOZ80.convertToHex(read()) + "";
    pc.write(i);
    return string;
  }

  public int getLength() {
    return 2;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
  }
}
