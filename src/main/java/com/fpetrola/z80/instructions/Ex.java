package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;

public class Ex extends TargetSourceInstruction {

  public Ex(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int v1 = target.read();
    final int v2 = source.read();

    if ((target instanceof Register) && ((Register) target).getName().equals(af.getName())) { // FIXME: FIXIT with wrappers equals
      flag.EXAFAF(af, _af);
    } else {
      target.write(v2);
      source.write(v1);
    }
    return cyclesCost;
  }
}
