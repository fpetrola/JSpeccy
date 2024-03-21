package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class CPL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public CPL(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> TableFlagRegisterInitTables.cplTableAluOperation.executeWithCarry(regA, tFlagRegister));
  }
}
