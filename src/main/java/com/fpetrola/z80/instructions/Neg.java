package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Neg<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public Neg(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, reg_A) -> TableFlagRegisterInitTables.negTableAluOperation.executeWithCarry(reg_A, tFlagRegister));
  }
}
