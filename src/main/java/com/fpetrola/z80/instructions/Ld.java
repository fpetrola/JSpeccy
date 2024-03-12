package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  public Ld(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister<T> flag) {
    super(target, source, flag);
  }

  public int execute() {
    T value = source.read();
    T aLU8Assign = flag.ALU8Assign(value);
    target.write(aLU8Assign);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingLd(this);
  }
}
