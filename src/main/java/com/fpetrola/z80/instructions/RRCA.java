package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RRCA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final AluOperation rrcaTableAluOperation = new AluOperation() {
    public int execute(int a, int carry) {
      boolean c = (a & 0x0001) != 0;

      a = (a >> 1);
      if (c) {
        setC();
        a = (a | 0x0080);
      } else
        resetC();
      resetH();
      resetN();

      return a;
    }
  };

  public RRCA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> rrcaTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
