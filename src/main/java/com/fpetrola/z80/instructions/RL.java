package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class RL<T extends WordNumber> extends InvertedFetchInstruction<T> {
  public RL(OpcodeReference target, int valueDelta, FlagRegister<T> flag) {
    super(target, valueDelta, flag);
  }

  public int execute() {
    final T value = target.read();
    target.write(flag.shiftGenericRL(value));
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingRl(this);
  }
}
