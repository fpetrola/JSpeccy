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

public class Cpi<T extends WordNumber> extends BlockInstruction<T> {
  public static final AluOperation cpiTableAluOperation = new TableAluOperation() {
    public int execute(int reg_A, int value, int carry) {
      //    reg_R++;
      int result = reg_A - value;
      //
      if ((result & 0x0080) == 0)
        resetS();
      else
        setS();
      result = result & 0x00FF;
      if (result == 0)
        setZ();
      else
        resetZ();
      setHalfCarryFlagSub(reg_A, value);
      setPV(carry == 1);
      setN();
      //
//    if (getH())
//      value--;
//    if ((value & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((value & 0x00008) == 0)
//      reset3();
//    else
//      set3();

      return reg_A;
    }
  };

  public Register<T> getA() {
    return a;
  }

  public void setA(Register<T> a) {
    this.a = a;
  }

  protected Register<T> a;

  public Cpi(Register<T> a, Register<T> flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
    this.a = a;
  }

  public int execute() {
    memory.disableReadListener();
    memory.disableWriteListener();
    bc.decrement();
    flagOperation();
    next();
    memory.enableReadListener();
    memory.enableWriteListener();
    return 1;
  }

  protected void flagOperation() {
    T value = memory.read(hl.read());
    T reg_A = a.read();
    cpiTableAluOperation.executeWithCarry2(value, reg_A, bc.read().isNotZero() ? 1 : 0, flag);
  }


  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitCpi(this);
  }
}
