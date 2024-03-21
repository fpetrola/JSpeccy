package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RRA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation rraTableAluOperation = new TableAluOperation() {
    public int execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x01) != 0;

      a = (a >> 1);
      if (getC())
        a = (a | 0x0080);
      if (c)
        setC();
      else
        resetC();
      resetH();
      resetN();

      return a;
    }
  };

  public RRA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> rraTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
