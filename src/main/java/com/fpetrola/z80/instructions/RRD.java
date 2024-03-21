package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class RRD<T extends WordNumber> extends RLD<T> {
  public RRD(Register<T> a, Register<T> hl, Register<T> r, Register<T> flag, Memory<T> memory) {
    super(a, hl, flag, r, memory);
  }

  protected void executeAlu(T value) {
    TableFlagRegisterInitTables.rldTableAluOperation.executeWithCarry(value, flag);
  }

  protected int getTemp1(int nibble2, int nibble3, int nibble4) {
    return (nibble2 << 4) | nibble3;
  }

  protected int getRegA1(int nibble1, int nibble4, int nibble3) {
    return (nibble1 << 4) | nibble4;
  }
}
