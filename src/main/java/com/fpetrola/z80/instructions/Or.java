package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.cojen.maker.Variable;

public class Or<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  Or(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source, flag::ALU8BitOr);
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingOr(this);
  }
}
