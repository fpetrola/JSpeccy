package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class DJNZ<T extends WordNumber> extends ConditionalInstruction<T> {

  public DJNZ(State<T> state, ImmutableOpcodeReference<T> target) {
    super(state, target, () -> state.getRegisterB().read().isNotZero());
  }

  public int execute() {
    b.decrement();
    jumpRelativeIfMatchCondition();
    return cyclesCost;
  }
}
