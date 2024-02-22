package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import org.cojen.maker.Variable;

public class Xor<T extends WordNumber> extends ParameterizedAluInstruction<T> {
  Xor(OpcodeReference target, ImmutableOpcodeReference source, IFlagRegister<T> flag) {
    super(target, source, flag::ALU8BitXor);
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.xor(sourceVariable));
    }
  }
}
