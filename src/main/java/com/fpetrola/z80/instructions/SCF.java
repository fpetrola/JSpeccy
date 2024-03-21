package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class SCF<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> flag;

  public SCF(Register<T> flag) {
    this.flag = flag;
  }

  public int execute() {
    TableFlagRegisterInitTables.scfTableAluOperation.executeWithCarry(WordNumber.createValue(0), flag);
    return 4;
  }
}
