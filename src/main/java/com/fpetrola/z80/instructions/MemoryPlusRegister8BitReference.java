package com.fpetrola.z80.instructions;

import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterUtils;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class MemoryPlusRegister8BitReference implements OpcodeReference {

  private final Register pc;
  private final Memory memory;
  private final OpcodeReference target;
  private final boolean rewindPCBeforeWrite;
  private int valueDelta;

  public MemoryPlusRegister8BitReference(Register pc, OpcodeReference target, Memory memory, boolean rewindPCBeforeWrite, int valueDelta) {
    this.pc = pc;
    this.target = target;
    this.memory = memory;
    this.rewindPCBeforeWrite = rewindPCBeforeWrite;
    this.valueDelta = valueDelta;
  }

  @Override
  public int read() {
    final int address = readAddress();
    final int value = memory.read(address);

    return value;
  }

  @Override
  public void write(int value) {
    if (rewindPCBeforeWrite) {
      pc.decrement(1);
    }
    final int address = readAddress();
    memory.write(address, value);
  }

  private int readAddress() {
    final int dd = memory.read(pc.read() + valueDelta);
    pc.increment(1);
    return target.read() + (byte) dd;
  }

  @Override
  public int cyclesCost() {
    return 3 + 5 + 4 + 3;
  }

  @Override
  public String toString() {
    return "(" + target.toString() + " + d)";
  }
}
