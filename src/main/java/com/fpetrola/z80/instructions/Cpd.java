package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.AluResult;

public class Cpd<T extends WordNumber> extends Cpi<T> {
  public static final AluOperation cpdTableAluOperation = new AluOperation() {
    public AluResult execute(int reg_A, int value, int carry) {
      int result = reg_A - value;

      if ((result & 0x0080) == 0)
        resetS();
      else
        setS();
      result = result & lsb;
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

      return new AluResult(reg_A, data);
    }
  };

  public Cpd(Register<T> a, Register flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(a, flag, bc, hl, memory, io);
  }

  protected void flagOperation() {
    T value = memory.read(hl.read());
    T reg_A = a.read();
    cpdTableAluOperation.executeWithCarry2(value, reg_A, bc.read().isNotZero() ? 1 : 0, flag);
  }

  protected void next() {
    hl.decrement();
  }
}
