package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class DAA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public DAA(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, (tFlagRegister, reg_A) -> TableFlagRegisterInitTables.daaTableAluOperation.executeWithCarry2(reg_A, reg_A, tFlagRegister.read(), tFlagRegister));
  }
}
