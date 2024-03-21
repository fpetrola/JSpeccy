package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class SRA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation sraTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      setC((a & 0x0001) != 0);
      if ((a & 0x0080) == 0) {
        a = a >> 1;
        resetS();
      } else {
        a = (a >> 1) | 0x0080;
        setS();
      }
      // standard flag updates
      if (a == 0)
        setZ();
      else
        resetZ();
      resetH();
      setPV(parity[a]);
      resetN();

      return new AluResult(a, data);
    }
  };

  public SRA(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> sraTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
