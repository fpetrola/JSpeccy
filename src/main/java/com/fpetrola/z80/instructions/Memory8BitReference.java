package com.fpetrola.z80.instructions;

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

  public Memory8BitReference(Memory memory) {
    this.memory = memory;
  }

  public Memory8BitReference(Memory memory, int delta) {
    this(memory);
    this.delta = delta;
  }

  @Override
  public int read() {
    Register pc = opCode.getPC();
    // int value = RegisterUtils.indirect(memory, pc);
    int value = memory.read(pc.read() + delta);
    pc.increment(1);
    return value;
  }

  @Override
  public void write(int value) {
    Register pc = opCode.getPC();
    memory.write(pc.read(), value);
    pc.increment(1);
  }

  @Override
  public int cyclesCost() {
    return 3;
  }

  public String toString() {
    return read() + "";
  }

  public int getLength() {
    return 1;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
  }
}
