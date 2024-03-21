package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class LdAR<T extends WordNumber> extends Ld<T> {
  private final State<T> state;

  public LdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, FlagRegister<T> flag, State<T> state) {
    super(target, source, flag);
    this.state = state;
  }

  public int execute() {
    T value = source.read();
    T reg_A = target.read();
    boolean iff2 = state.isIff2();
    T ldar = TableFlagRegisterInitTables.ldarTableAluOperation.executeWithCarry2(reg_A, value, WordNumber.createValue(iff2 ? 1 : 0), flag);

    target.write(ldar);

    return cyclesCost;
  }
}
