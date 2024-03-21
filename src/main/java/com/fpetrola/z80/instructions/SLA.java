package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class SLA<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation slaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      a = a << 1;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if ((a & 0x00FF) == 0)
        setZ();
      else
        resetZ();
      resetH();
      if ((a & 0x0FF00) != 0)
        setC();
      else
        resetC();
      a = a & 0x00FF;
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public SLA(OpcodeReference<T> target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> slaTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
