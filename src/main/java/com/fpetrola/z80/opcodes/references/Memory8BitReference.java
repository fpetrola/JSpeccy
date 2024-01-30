package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.StringHelper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;

public class Memory8BitReference implements OpcodeReference {

  private final Memory memory;
  private int delta;
  private int fetchedValue;
  private Register pc;
  private InstructionSpy spy;

  public Memory8BitReference(Memory memory, Register pc, int delta, InstructionSpy spy) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
    this.spy = spy;
  }

  public int read() {
    fetchValue();
    return fetchedValue;
  }

  private void fetchValue() {
    spy.pause();
    fetchedValue = memory.read(fetchAddress() + delta);
    spy.doContinue();
  }

  public void write(int value) {
    memory.write(fetchAddress(), value);
  }

  private int fetchAddress() {
    return pc.read();
  }

  public int cyclesCost() {
    return 3;
  }

  public String toString() {
    return StringHelper.convertToHex(read()) + "";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedValue = fetchedValue;
    return new Memory8BitReference(memory, pc, delta, spy) {
      public int read() {
        return lastFetchedValue;
      }
    };
  }
}
