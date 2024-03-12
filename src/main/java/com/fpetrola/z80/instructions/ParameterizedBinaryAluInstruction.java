package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class ParameterizedBinaryAluInstruction<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  protected FlagRegister.BinaryAluOperation<T> binaryAluOperation;
  protected FlagRegister<T> flag;

  public ParameterizedBinaryAluInstruction(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister<T> flag, FlagRegister.BinaryAluOperation<T> binaryAluOperation) {
    super(target, source);
    this.binaryAluOperation = binaryAluOperation;
    this.flag = flag;
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    target.write(binaryAluOperation.execute(flag, value1, value2));
    return cyclesCost;
  }

  public FlagRegister<T> getFlag() {
    return flag;
  }

  public void setFlag(FlagRegister<T> flag) {
    this.flag = flag;
  }
}
