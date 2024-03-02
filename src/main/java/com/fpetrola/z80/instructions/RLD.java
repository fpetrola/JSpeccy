package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RLD<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> a;
  private final Register<T> hl;
  private final FlagRegister<T> flag;
  private final Register<T> r;
  private final Memory<T> memory;

  RLD(Register<T> a, Register<T> hl, FlagRegister<T> flag, Register<T> r, Memory<T> memory) {
    this.a = a;
    this.hl = hl;
    this.flag = flag;
    this.r = r;
    this.memory = memory;
  }

  public int execute() {
    int reg_A = a.read().intValue();

    int temp = memory.read(hl.read()).intValue();
    int nibble1 = (reg_A & 0x00F0) >> 4;
    int nibble2 = reg_A & 0x000F;
    int nibble3 = (temp & 0x00F0) >> 4;
    int nibble4 = temp & 0x000F;
    reg_A = (nibble1 << 4) | nibble3;
    temp = (nibble4 << 4) | nibble2;

    memory.write(hl.read(), WordNumber.createValue(temp));
    flag.RLD(WordNumber.createValue(reg_A));
    
    a.write(WordNumber.createValue(reg_A));

    return 1;
  }
}
