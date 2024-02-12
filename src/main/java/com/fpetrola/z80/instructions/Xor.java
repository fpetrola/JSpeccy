package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.Variable;

public class Xor<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Xor(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = target.read();
    final T value2 = source.read();

    T alu8BitXor = flag.ALU8BitXor(value2, value1);
    target.write(alu8BitXor);

    return cyclesCost;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable) {
      Variable variable = (Variable) targetVariable;
      variable.set(variable.xor(sourceVariable));
    }
  }
}
