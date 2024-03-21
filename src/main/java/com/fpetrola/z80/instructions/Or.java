package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Or<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final TableAluOperation orTableAluOperation = new TableAluOperation() {
    public int execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a | value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return reg_A;
    }
  };

  public Or(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, a, value) -> orTableAluOperation.executeWithoutCarry(value, a, tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingOr(this);
  }
}
