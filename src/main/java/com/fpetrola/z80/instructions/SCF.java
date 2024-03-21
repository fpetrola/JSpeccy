package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class SCF<T extends WordNumber> extends AbstractInstruction<T> {
  public static final TableAluOperation scfTableAluOperation = new TableAluOperation() {
    public int execute(int a, int carry) {
      setC();
      resetH();
      resetN();
      return a;
    }
  };
  private final Register<T> flag;

  public SCF(Register<T> flag) {
    this.flag = flag;
  }

  public int execute() {
    scfTableAluOperation.executeWithCarry(WordNumber.createValue(0), flag);
    return 4;
  }
}
