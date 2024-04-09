package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class CCF<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  public static final AluOperation ccfTableAluOperation = new AluOperation() {
    public int execute(int a, int carry) {
      if (getC())
        setH();
      else
        resetH();
      data = data ^ FLAG_C;
      resetN();
      return a;
    }
  };

  public CCF(Register flag, Register<T> a) {
    super(a, flag);
  }

  public int execute() {
    ccfTableAluOperation.executeWithCarry(target.read(), flag);
    return 4;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingCcf(this);
  }

}
