package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import org.cojen.maker.Variable;

public class Dec<T extends WordNumber> extends TargetInstruction<T> {
  private final IFlagRegister<T> flag;

  Dec(OpcodeReference target, IFlagRegister<T> flag) {
    super(null, target);
    this.flag = flag;
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
