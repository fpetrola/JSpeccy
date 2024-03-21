package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Or<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Or(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, a, value) -> TableFlagRegisterInitTables.orTableAluOperation.executeWithoutCarry(value, a, tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingOr(this);
  }
}
