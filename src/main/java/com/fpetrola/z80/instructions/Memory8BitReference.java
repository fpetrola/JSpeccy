package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterUtils;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class Memory8BitReference implements OpcodeReference {

  public final Register pc;
  private final Memory memory;
  private int delta;

  public Memory8BitReference(Register pc, Memory memory) {
    this.pc = pc;
    this.memory = memory;
  }

  public Memory8BitReference(Register pc, Memory memory, int delta) {
    this(pc, memory);
    this.delta = delta;
  }

  @Override
  public int read() {
//    int value = RegisterUtils.indirect(memory, pc);
    int value = memory.read(pc.read() + delta);
    pc.increment(1);
    return value;
  }

  @Override
  public void write(int value) {
    memory.write(pc.read(), value);
    pc.increment(1);
  }

  @Override
  public int cyclesCost() {
    return 3;
  }

  @Override
  public String toString() {
    return "n";
  }
}
