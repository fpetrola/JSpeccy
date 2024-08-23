package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class RR<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation rrTableAluOperation = new TableAluOperation() {
    public int execute(int a, int carry) {
      data = carry;

      boolean tempC;
      // do shift operation
      tempC = getC();
      setC((a & 0x0001) != 0);
      a = a >> 1;
      if (tempC)
        a = a | 0x80;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if (a == 0)
        setZ();
      else
        resetZ();
      resetH();
      setPV(parity[a]);
      resetN();
      // put value back

      return a;
    }
  };

  public RR(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> rrTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }

  public void accept(InstructionVisitor visitor) {
    if (!visitor.visitingRr(this))
      super.accept(visitor);
  }
}
