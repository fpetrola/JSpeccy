package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class RST<T extends WordNumber> extends AbstractInstruction<T> {
  private final int p;
  private final ImmutableOpcodeReference<T> pc;
  private final Register<T> sp;
  private final Memory<T> memory;

  RST(int p, ImmutableOpcodeReference<T> pc, Register<T> sp, Memory<T> memory) {
    this.p = p;
    this.pc = pc;
    this.sp = sp;
    this.memory = memory;
  }

  public int execute() {
    Push.doPush(pc.read().plus1(), sp, memory);
    setNextPC(WordNumber.createValue(p & 0xFFFF));
    return 5 + 3 + 3;
  }

  public String toString() {
    return "RST " + String.format("%02X", p);
  }

  public int getP() {
    return p;
  }
}
