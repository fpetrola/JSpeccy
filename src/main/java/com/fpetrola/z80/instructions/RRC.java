package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class RRC<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public RRC(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> AluOperationsInitializer.rrcTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
