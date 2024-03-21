package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluResult;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RLCA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation rlcaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x0080) != 0;
      a = ((a << 1) & 0x00FF);
      if (c) {
        setC();
        a = (a | 0x0001);
      } else
        resetC();
      resetH();
      resetN();

      return new AluResult(a, data);
    }
  };

  public RLCA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> rlcaTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
