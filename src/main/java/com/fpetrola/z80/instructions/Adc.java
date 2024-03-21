package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Adc<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Adc(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value, regA) -> TableFlagRegisterInitTables.adc8TableAluOperation.executeWithCarry(value, regA, tFlagRegister));
  }
}
