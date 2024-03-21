package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class RLD<T extends WordNumber> extends AbstractInstruction<T> {
  protected final Register<T> a;
  protected final Register<T> hl;
  protected final FlagRegister<T> flag;
  protected final Register<T> r;
  protected final Memory<T> memory;

  public RLD(Register<T> a, Register<T> hl, FlagRegister<T> flag, Register<T> r, Memory<T> memory) {
    this.a = a;
    this.hl = hl;
    this.flag = flag;
    this.r = r;
    this.memory = memory;
  }

  public int execute() {
    int reg_A = a.read().intValue();
    int nibble1 = (reg_A & 0x00F0) >> 4;
    int nibble2 = reg_A & 0x000F;

    int temp = memory.read(hl.read()).intValue();
    int nibble3 = (temp & 0x00F0) >> 4;
    int nibble4 = temp & 0x000F;

    memory.write(hl.read(), createValue(getTemp1(nibble2, nibble3, nibble4)));
    T value = createValue(getRegA1(nibble1, nibble4, nibble3));

    executeAlu(value);

    a.write(value);

    return 1;
  }

  protected void executeAlu(T value) {
    TableFlagRegisterInitTables.rldTableAluOperation.executeWithCarry(value, flag);
  }

  protected int getTemp1(int nibble2, int nibble3, int nibble4) {
    return (nibble4 << 4) | nibble2;
  }

  protected int getRegA1(int nibble1, int nibble4, int nibble3) {
    return (nibble1 << 4) | nibble3;
  }
}
