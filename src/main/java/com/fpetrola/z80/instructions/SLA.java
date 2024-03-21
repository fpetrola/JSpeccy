package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class SLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public SLA(OpcodeReference<T> target, FlagRegister<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> TableFlagRegisterInitTables.slaTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
