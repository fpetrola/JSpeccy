package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Pop<T extends WordNumber> extends TargetInstruction<T> {

  public Pop(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(doPop(memory, sp));
    return 5 + 3 + 3;
  }

  public static <T extends WordNumber> T doPop(Memory<T> memory, Register<T> sp) {
    final T value = Memory.read16Bits(memory, sp.read());
    sp.increment();
    sp.increment();
    return value;
  }

}
