package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class BIT<T extends WordNumber> extends BitOperation<T> {
  public BIT(OpcodeReference target, int n, FlagRegister<T> flag) {
    super(target, n, flag);
  }

  public int execute() {
    final T value = target.read();
    TableFlagRegisterInitTables. testBitTableAluOperation.executeWithCarry(value, WordNumber.createValue(n), flag);
    return cyclesCost;
  }
}
