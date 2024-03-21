package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class RLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public RLA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> AluOperationsInitializer.rlaTableAluOperation.executeWithCarry(regA, tFlagRegister));
    this.flag = flag;
  }
}
