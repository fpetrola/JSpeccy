package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;

public class Memory8BitReference<T extends WordNumber> implements OpcodeReference<T> {

  private final Memory<T> memory;
  private int delta;
  private T fetchedValue;
  private Register<T> pc;
  private InstructionSpy spy;

  public Memory8BitReference(Memory memory, Register pc, int delta, InstructionSpy spy) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
    this.spy = spy;
  }

  public T read() {
    fetchValue();
    return fetchedValue;
  }

  private void fetchValue() {
    spy.pause();
    fetchedValue = memory.read(fetchAddress().increment(delta));
    spy.doContinue();
  }

  public void write(T value) {
    memory.write(fetchAddress(), value);
  }

  private T fetchAddress() {
    return pc.read();
  }

  public int cyclesCost() {
    return 3;
  }

  public String toString() {
    T read = read();
    return read == null ? "" : Helper.convertToHex(read.intValue()) + "";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    T lastFetchedValue = fetchedValue;
    return new Memory8BitReference(memory, pc, delta, spy) {
      public T read() {
        return lastFetchedValue;
      }
    };
  }
}
