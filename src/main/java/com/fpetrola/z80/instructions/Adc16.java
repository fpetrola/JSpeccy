package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Adc16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Adc16(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source, flag, FlagRegister::ALU16BitADC);
  }
}
