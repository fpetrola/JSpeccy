package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Add16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Add16(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value2, value) -> TableFlagRegisterInitTables.add16TableAluOperation.executeWithCarry2(value2, value, tFlagRegister.read(), tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingAdd16(this);
  }
}
