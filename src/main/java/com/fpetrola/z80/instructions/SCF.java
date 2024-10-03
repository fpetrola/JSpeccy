package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.FlagInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;

public class SCF<T extends WordNumber> extends AbstractInstruction<T> implements FlagInstruction<T> {
  public static final AluOperation scfTableAluOperation = new AluOperation() {
    public int execute(int a, int carry) {
      setC();
      resetH();
      resetN();
      return data;
    }
  };

  public Register<T> getFlag() {
    return flag;
  }

  public void setFlag(Register<T> flag) {
    this.flag = flag;
  }

  private Register<T> flag;

  public SCF(Register<T> flag) {
    this.flag = flag;
  }

  public int execute() {
    scfTableAluOperation.executeWithCarry(WordNumber.createValue(0), flag);
    return 4;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingScf(this);
  }

}
