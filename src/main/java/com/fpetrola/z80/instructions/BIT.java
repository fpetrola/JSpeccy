package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class BIT<T extends WordNumber> extends BitOperation<T> {
  public BIT(OpcodeReference target, int n, Register<T> flag) {
    super(target, n, flag);
  }

  public int execute() {
    final T value = target.read();
    AluOperationsInitializer. testBitTableAluOperation.executeWithCarry(value, WordNumber.createValue(n), flag);
    return cyclesCost;
  }
}
