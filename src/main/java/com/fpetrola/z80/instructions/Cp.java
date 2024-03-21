package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class Cp<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final TableAluOperation cpTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      int b = value;
      int wans = a - b;
      int ans = wans & 0xff;
      data = 0x02;
      setS((ans & FLAG_S) != 0);
      set3((b & FLAG_3) != 0);
      set5((b & FLAG_5) != 0);
      setZ(ans == 0);
      setC((wans & 0x100) != 0);
      setH((((a & 0x0f) - (b & 0x0f)) & FLAG_H) != 0);
      setPV(((a ^ b) & (a ^ ans) & 0x80) != 0);

      return new AluResult(ans, data);
    }
  };

  public Cp(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, v, reg_A) -> cpTableAluOperation.executeWithoutCarry(v, reg_A, tFlagRegister));
  }

  public int execute() {
    final T value1 = target.read();
    final T value2 = source.read();
    binaryAluOperation.execute(flag, value2, value1);
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingCp(this);
  }
}
