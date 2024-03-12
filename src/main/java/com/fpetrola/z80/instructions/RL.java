package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public RL(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, FlagRegister::shiftGenericRL);
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingRl(this);
  }
}
