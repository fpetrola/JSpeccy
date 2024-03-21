package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Inc<T extends WordNumber> extends ParameterizedUnaryAluInstruction<T> {
  public static final TableAluOperation inc8TableAluOperation = new TableAluOperation() {
    public int execute(int a, int carry) {
      data = carry;
      int value = a;
      setHalfCarryFlagAdd(value, 1);
      setPV(value == 0x7F);
      value++;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setUnusedFlags(value);

      return value;
    }
  };

  public Inc(OpcodeReference target, Register<T> flag) {
    super(target, flag, (tFlagRegister, value) -> inc8TableAluOperation.executeWithCarry(value, tFlagRegister));
    this.flag = flag;
  }

  @Override
  public int execute() {
    final T value2 = target.read();
    T execute = unaryAluOperation.execute(flag, value2);
    target.write(execute);
    return cyclesCost;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingInc(this);
  }
}
