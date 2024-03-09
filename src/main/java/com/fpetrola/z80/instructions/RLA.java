package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RLA<T extends WordNumber> extends TargetInstruction<T> {
  private final FlagRegister<T> flag;

  RLA(OpcodeReference target, FlagRegister<T> flag) {
    super(target);
    this.flag = flag;
  }

  public int execute() {
    final T value = target.read();
    target.write(flag.RLA(value));
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingRla(this);
  }
}
