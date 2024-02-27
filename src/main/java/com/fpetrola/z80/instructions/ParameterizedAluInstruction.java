package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class ParameterizedAluInstruction<T extends WordNumber> extends TargetSourceInstruction<T> {
  protected IFlagRegister.AluOperation<T> aluOperation;

  public ParameterizedAluInstruction(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, IFlagRegister.AluOperation<T> aluOperation) {
    super(null, target, source);
    this.aluOperation = aluOperation;
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    target.write(aluOperation.execute(value1, value2));
    return cyclesCost;
  }
}
