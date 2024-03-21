package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class SLL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public SLL(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> AluOperationsInitializer.sllTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
