package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class SRL<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {

  public static final TableAluOperation srlTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      setC((a & 0x0001) != 0);
      a = a >> 1;
      // standard flag updates
      setS((a & 0x0080) != 0);
      setZ(a == 0);
      resetH();
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public SRL(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, temp1) -> srlTableAluOperation.executeWithCarry(temp1, tFlagRegister));
  }
}
