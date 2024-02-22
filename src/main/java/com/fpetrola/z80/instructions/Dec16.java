package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.Variable;

public class Dec16<T extends WordNumber> extends TargetInstruction<T> {

  Dec16(OpcodeReference target) {
    super(null, target);
  }

  public int execute() {
    target.write(target.read().minus1());
    return cyclesCost;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).sub(sourceVariable);
  }
}
