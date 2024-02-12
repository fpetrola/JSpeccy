package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.Variable;

public class Sub<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = target.read();
    final T value2 = source.read();

    T alu8BitSub = flag.ALU8BitSub(value2, value1);
    target.write(alu8BitSub);

    return cyclesCost;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.sub(sourceVariable));
    }
  }
}
