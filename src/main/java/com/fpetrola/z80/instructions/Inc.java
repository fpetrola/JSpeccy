package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class Inc<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public Inc(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, value) -> AluOperationsInitializer.inc8TableAluOperation.executeWithCarry(value, tFlagRegister));
    this.flag = flag;
  }

  @Override
  public int execute() {
    final T value2 = target.read();
    T execute = unaryAluOperation.execute(flag, value2);
    target.write(execute);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingInc(this);
  }
}
