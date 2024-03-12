package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class DAA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  DAA(OpcodeReference target, FlagRegister<T> flag) {
    super(target, FlagRegister::DAA, flag);
  }
}
