package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class JP<T extends WordNumber> extends ConditionalInstruction<T> {
  public JP(ImmutableOpcodeReference target, Condition condition, Register<T> pc) {
    super(target, condition, pc);
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingJP(this);
  }
}
