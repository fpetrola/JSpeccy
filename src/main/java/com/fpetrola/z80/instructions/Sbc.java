package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Sbc<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Sbc(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value, reg_A) -> TableFlagRegisterInitTables.sbc8TableAluOperation.executeWithCarry(value, reg_A, tFlagRegister));
  }
}
