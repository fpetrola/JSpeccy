package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class CPL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final AluOperation cplTableAluOperation = new AluOperation() {
    public int execute(int a, int carry) {
      a = (a ^ 0x00FF) & 0x00FF;
      setH();
      setN();
      return a;
    }
  };

  public CPL(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> cplTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
