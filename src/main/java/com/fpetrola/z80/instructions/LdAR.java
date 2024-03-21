package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;

public class LdAR<T extends WordNumber> extends Ld<T> {
  public static final AluOperation ldarTableAluOperation = new AluOperation() {
    public int execute(int reg_R, int reg_A, int carry) {
      reg_A = reg_R & 0x7F;
      setS((reg_A & FLAG_S) != 0);
      setZ(reg_A == 0);
      resetH();
      resetN();
      setPV(carry == 1);

      return reg_A;
    }
  };
  private final State<T> state;

  public LdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, Register<T> flag, State<T> state) {
    super(target, source, flag);
    this.state = state;
  }

  public int execute() {
    T value = source.read();
    T reg_A = target.read();
    boolean iff2 = state.isIff2();
    T ldar = ldarTableAluOperation.executeWithCarry2(reg_A, value, iff2 ? 1 : 0, flag);

    target.write(ldar);

    return cyclesCost;
  }
}
