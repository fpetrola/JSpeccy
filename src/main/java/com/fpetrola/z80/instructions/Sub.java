package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Sub<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public Sub(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source, flag, FlagRegister::ALU8BitSub);
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingSub(this);
  }
}
