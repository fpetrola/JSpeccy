package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class ParameterizedBinaryAluInstruction<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  public interface BinaryAluOperation<T extends WordNumber> {
    T execute(Register<T> flag, T value1, T value2);
  }

  protected BinaryAluOperation<T> binaryAluOperation;

  public ParameterizedBinaryAluInstruction(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, Register<T> flag, BinaryAluOperation<T> binaryAluOperation) {
    super(target, source, flag);
    this.binaryAluOperation = binaryAluOperation;
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    target.write(binaryAluOperation.execute(flag, value1, value2));
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingTarget(getTarget(), this);
    visitor.visitingSource(getSource(), this);
    visitor.visitingFlag(getFlag(), this);
    visitor.visitingParameterizedBinaryAluInstruction(this);
  }
}
