package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.StringHelper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;

public class MemoryPlusRegister8BitReference implements OpcodeReference {

  private Memory memory;
  private OpcodeReference target;
  private int valueDelta;
  private int fetchedRelative;
  private Register pc;
  private InstructionSpy spy;

  public MemoryPlusRegister8BitReference() {
  }

  public MemoryPlusRegister8BitReference(OpcodeReference target, Memory memory, Register pc, int valueDelta, InstructionSpy spy) {
    this.target = target;
    this.memory = memory;
    this.pc = pc;
    this.valueDelta = valueDelta;
    this.spy = spy;
  }

  public int read() {
    int address = target.read() + (byte) fetchRelative();
    return memory.read(address);
  }

  public void write(int value) {
    int address = target.read() + (byte) fetchRelative();
    memory.write(address, value);
  }

  protected int fetchRelative() {
    spy.pause();
    int dd = memory.read(pc.read() + valueDelta);
    spy.doContinue();
    fetchedRelative = dd;
    return fetchedRelative;
  }

  public int cyclesCost() {
    return 3 + 5 + 4 + 3;
  }

  public String toString() {
    int dd = fetchRelative();
    String string2 = (dd > 0 ? "+" : "-") + StringHelper.convertToHex(Math.abs(dd));
    return "(" + target.toString() + string2 + ")";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedRelative = fetchedRelative;
    return new MemoryPlusRegister8BitReference((OpcodeReference) target.clone(), memory, pc, valueDelta, spy) {
      protected int fetchRelative() {
        return lastFetchedRelative;
      }
    };
  }
}
