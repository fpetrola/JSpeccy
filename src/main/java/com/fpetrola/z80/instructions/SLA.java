package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class SLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public SLA(OpcodeReference<T> target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> AluOperationsInitializer.slaTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
