package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class CCF<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  public static final TableAluOperation ccfTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      if (getC())
        setH();
      else
        resetH();
      data = data ^ FLAG_C;
      resetN();
      return new AluResult(a, data);
    }
  };

  public CCF(Register flag, Register<T> a) {
    super(a, flag);
  }

  public int execute() {
    ccfTableAluOperation.executeWithCarry(target.read(), flag);
    return 4;
  }
}
