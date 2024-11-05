package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation rlaTableAluOperation = new TableAluOperation() {
    public int execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x0080) != 0;

      a = ((a << 1) & 0x00FF);
      if (getC())
        a = a | 0x01;
      if (c)
        setC();
      else
        resetC();
      resetH();
      resetN();

      return a;
    }
  };

  public RLA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, regA) -> rlaTableAluOperation.executeWithCarry(regA, tFlagRegister));
    this.flag = flag;
  }

  public void accept(InstructionVisitor visitor) {
    if (!visitor.visitingRla(this))
      super.accept(visitor);
  }
}
