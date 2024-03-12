package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RLCA<T extends WordNumber>  extends ParameterizedUnaryAluInstruction<T> {
  RLCA(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, FlagRegister::RLCA);
  }
}
