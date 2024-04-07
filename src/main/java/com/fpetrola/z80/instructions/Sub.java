package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Sub<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final TableAluOperation sub8TableAluOperation = new TableAluOperation() {
    public int execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a;
      int local_reg_A = reg_A;

      setHalfCarryFlagSub(local_reg_A, value);
      setOverflowFlagSub(local_reg_A, value);
      local_reg_A = local_reg_A - value;
      setS((local_reg_A & 0x0080) != 0);
      setC((local_reg_A & 0xff00) != 0);
      local_reg_A = local_reg_A & 0x00ff;
      setZ(local_reg_A == 0);
      setN();
      reg_A = local_reg_A;
      setUnusedFlags(reg_A);

      return reg_A;
    }
  };

  public Sub(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value, reg_A) -> sub8TableAluOperation.executeWithoutCarry(value, reg_A, tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingSub(this);
  }
}
