package com.fpetrola.z80.instructions;

import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterUtils;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class MemoryPlusRegister8BitReference implements OpcodeReference {

  private final Memory memory;
  private final OpcodeReference target;
  private final boolean rewindPCBeforeWrite;
  private int valueDelta;
  private OpCode opCode;

  public MemoryPlusRegister8BitReference(OpcodeReference target, Memory memory, boolean rewindPCBeforeWrite, int valueDelta) {
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
    Register pc = opCode.getPC();

    if (rewindPCBeforeWrite) {
      pc.decrement(1);
    }
    final int address = readAddress();
    memory.write(address, value);
  }

  private int readAddress() {
    Register pc = opCode.getPC();
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
    Register pc = opCode.getPC();
    int backup = pc.read();
    final int dd = memory.read(pc.read() + valueDelta);
    pc.increment(1);
    String string = "(" + target.toString() + " + " + (byte) dd + ")";
    pc.write(backup);
    return string;
  }

  public int getLength() {
    return 1;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
    target.setOpCode(opCode);
  }
}
