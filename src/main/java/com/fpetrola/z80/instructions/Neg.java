package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Neg<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation negTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = 0;
      int reg_A = a;
      setHalfCarryFlagSub(0, reg_A, 0);
      setOverflowFlagSub(0, reg_A, 0);
      reg_A = 0 - reg_A;
      if ((reg_A & 0xFF00) != 0)
        setC();
      else
        resetC();
      setN();
      reg_A = reg_A & 0x00FF;
      if (reg_A == 0)
        setZ();
      else
        resetZ();
      if ((reg_A & 0x0080) != 0)
        setS();
      else
        resetS();
      setUnusedFlags(reg_A);
      return new AluResult(reg_A, data);
    }
  };

  public Neg(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, reg_A) -> negTableAluOperation.executeWithCarry(reg_A, tFlagRegister));
  }
}
