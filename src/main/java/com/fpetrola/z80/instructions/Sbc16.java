package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Sbc16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  Sbc16(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister<T> flag) {
    super(target, source, flag, FlagRegister::ALU16BitSBC);
  }
}
