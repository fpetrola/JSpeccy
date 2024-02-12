package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.Variable;

public class Dec<T extends WordNumber> extends TargetInstruction<T> {

  public Dec(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final T value = target.read();

    T alu8BitDec = flag.ALU8BitDec(value);

    target.write(alu8BitDec);

    return cyclesCost;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).inc(-1);
  }
}
