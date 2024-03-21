package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class DAA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public DAA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, reg_A) -> AluOperationsInitializer.daaTableAluOperation.executeWithCarry2(reg_A, reg_A, tFlagRegister.read(), tFlagRegister));
  }
}
