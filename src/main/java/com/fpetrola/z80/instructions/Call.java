package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Call<T extends WordNumber> extends ConditionalInstruction<T> {

  public Call(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    spy.pause();

    T position = target.read();
    if (condition.conditionMet())
      Push.doPush(pc.read().plus(length), sp, memory);
    else
      position = null;

    setNextPC(position);

    spy.doContinue();

    return cyclesCost;
  }
}
