package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RLCA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final AluOperation rlcaTableAluOperation = new AluOperation() {
    public int execute(int a, int carry) {
      boolean c = (a & 0x0080) != 0;
      a = ((a << 1) & 0x00FF);
      if (c) {
        setC();
        a = (a | 0x0001);
      } else
        resetC();
      resetH();
      resetN();

      return a;
    }
  };

  public RLCA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> rlcaTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
