package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Adc<T extends WordNumber> extends TargetSourceInstruction<T> {

  Adc(State state, OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();

    T alu8BitAdc = flag.ALU8BitAdc(value1, value2);

    target.write(alu8BitAdc);

    return cyclesCost;
  }
}
