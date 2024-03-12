package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class ParameterizedBinaryAluInstruction<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  protected FlagRegister.BinaryAluOperation<T> binaryAluOperation;

  public ParameterizedBinaryAluInstruction(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister.BinaryAluOperation<T> binaryAluOperation) {
    super(target, source);
    this.binaryAluOperation = binaryAluOperation;
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    target.write(binaryAluOperation.execute(value1, value2));
    return cyclesCost;
  }
}
