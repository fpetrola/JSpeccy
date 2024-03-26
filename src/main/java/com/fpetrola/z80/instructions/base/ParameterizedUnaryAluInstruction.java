package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class ParameterizedUnaryAluInstruction<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  public interface UnaryAluOperation<T extends WordNumber> {
    T execute(Register<T> flag, T value);
  }

  protected UnaryAluOperation<T> unaryAluOperation;

  public ParameterizedUnaryAluInstruction(OpcodeReference<T> target, Register<T> flag, UnaryAluOperation<T> unaryAluOperation) {
    super(target, flag);
    this.unaryAluOperation = unaryAluOperation;
    this.flag = flag;
  }

  public int execute() {
    final T value2 = target.read();
    T execute = unaryAluOperation.execute(flag, value2);
    target.write(execute);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingParameterizedUnaryAluInstruction(this);
  }
}
