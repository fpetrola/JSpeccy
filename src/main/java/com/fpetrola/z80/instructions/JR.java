package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class JR<T extends WordNumber> extends ConditionalInstruction<T> {

  public JR(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    byte by = target.read().byteValue();
    if (condition.conditionMet()) {
      T position = pc.read().plus(length + by);
      setNextPC(position);
    }
    else setNextPC(null);

    return cyclesCost;
  }
}
