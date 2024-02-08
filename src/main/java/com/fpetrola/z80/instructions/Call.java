package com.fpetrola.z80.instructions;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
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
    final T position = target.read();
    if (condition.conditionMet()) {
      sp.decrement(2);
      final T address = sp.read();
      final T value = pc.read().plus(length);
      Helper.write16Bits(value, address, memory);
      setNextPC(position);
//      pc.write(position);
    }
    else
      setNextPC(null);

    spy.doContinue();

    return cyclesCost;
  }
}
