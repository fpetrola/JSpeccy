package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Dec<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public Dec(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, (tFlagRegister, value) -> TableFlagRegisterInitTables.dec8TableAluOperation.executeWithCarry(value, tFlagRegister));
  }

  public int execute() {
    final T value2 = target.read();
    T execute = unaryAluOperation.execute(flag, value2);
    target.write(execute);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingDec(this);
  }
}
