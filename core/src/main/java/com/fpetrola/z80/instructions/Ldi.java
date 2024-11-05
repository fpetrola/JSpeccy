package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BlockInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Ldi<T extends WordNumber> extends BlockInstruction<T> {
  public static final AluOperation ldiTableAluOperation = new AluOperation() {
    public int execute(int bc, int carry) {
      resetH();
      resetN();
      setPV(bc != 0);
      return data;
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
    memory.disableReadListener();
    memory.disableWriteListener();
    memory.write(de.read(), memory.read(hl.read()));

    next();
    bc.decrement();

    flagOperation();
    memory.enableReadListener();
    memory.enableWriteListener();

    return 1;
  }

  protected void flagOperation() {
    ldiTableAluOperation.executeWithCarry(bc.read(), flag);
  }

  protected void next() {
    hl.increment();
    de.increment();
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitLdi(this);
  }
}
