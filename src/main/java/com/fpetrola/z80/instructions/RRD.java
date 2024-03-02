package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RRD<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> a;
  private final Register<T> hl;
  private final Register<T> r;
  private final FlagRegister<T> flag;
  private final Memory<T> memory;

  RRD(Register<T> a, Register<T> hl, Register<T> r, FlagRegister<T> flag, Memory<T> memory) {
    this.a = a;
    this.hl = hl;
    this.r = r;
    this.flag = flag;
    this.memory = memory;
  }

  public int execute() {
    int reg_A = a.read().intValue();

    int temp = memory.read(hl.read()).intValue();
    int nibble1 = (reg_A & 0x00F0) >> 4;
    int nibble2 = reg_A & 0x000F;
    int nibble3 = (temp & 0x00F0) >> 4;
    int nibble4 = temp & 0x000F;
    reg_A = (nibble1 << 4) | nibble4;
    temp = (nibble2 << 4) | nibble3;

    memory.write(hl.read(), WordNumber.createValue(temp));
    flag.RRD(WordNumber.createValue(reg_A));

    a.write(WordNumber.createValue(reg_A));

    return 1;
  }
}
