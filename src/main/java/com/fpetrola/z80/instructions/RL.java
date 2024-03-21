package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class RL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public RL(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> TableFlagRegisterInitTables.rlTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingRl(this);
  }
}
