package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class ParameterizedAluInstruction<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  protected FlagRegister.AluOperation<T> aluOperation;

  public ParameterizedAluInstruction(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister.AluOperation<T> aluOperation) {
    super(target, source);
    this.aluOperation = aluOperation;
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    target.write(aluOperation.execute(value1, value2));
    return cyclesCost;
  }
}
