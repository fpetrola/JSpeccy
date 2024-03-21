package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class RLC<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {

  public RLC(OpcodeReference target, FlagRegister<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> TableFlagRegisterInitTables.rlcTableAluOperation1.executeWithCarry(temp1, tFlagRegister));
  }

  public int execute() {
    final T value2 = target.read();
    T execute = unaryAluOperation.execute(flag, value2);
    target.write(execute);
    return cyclesCost;
  }
}
