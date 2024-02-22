package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Adc16<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  Adc16(OpcodeReference target, ImmutableOpcodeReference source, IFlagRegister<T> flag) {
    super(target, source, flag::ALU16BitADC);
  }
}
