package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.cojen.maker.Variable;

public class Xor<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  Xor(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source, flag::ALU8BitXor);
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingXor(this);
  }
}
