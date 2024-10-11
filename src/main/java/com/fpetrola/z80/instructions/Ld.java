package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  public Ld(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, Register<T> flag) {
    super(target, source, flag);
  }

  public int execute() {
    T value = source.read();
    T aLU8Assign = value;
    target.write(aLU8Assign);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingLd(this);
  }
}
