package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BlockInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Ldi<T extends WordNumber> extends BlockInstruction<T> {
  public static final TableAluOperation ldiTableAluOperation = new TableAluOperation() {
    public int execute(int bc, int carry) {
      data = 0;
      resetH();
      resetN();
      setPV(bc != 0);
      return bc;
    }
  };

  public Register<T> getDe() {
    return de;
  }

  public void setDe(Register<T> de) {
    this.de = de;
  }

  protected Register<T> de;

  public Ldi(Register<T> de, RegisterPair<T> bc, Register<T> hl, Register<T> flag, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
    this.de = de;
  }

  public int execute() {
    memory.write(de.read(), memory.read(hl.read()));

    next();
    bc.decrement();

    flagOperation();

    return 1;
  }

  protected void flagOperation() {
    ldiTableAluOperation.executeWithCarry(bc.read(), flag);
  }

  protected void next() {
    hl.increment();
    de.increment();
  }
}
