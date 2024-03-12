package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public RLA(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, FlagRegister::RLA);
    this.flag = flag;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingRla(this);
  }
}
