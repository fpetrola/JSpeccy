package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Push<T extends WordNumber> extends TargetInstruction<T> {

  public Push(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    doPush(target.read(), state.getRegisterSP(), state.getMemory());
    return 5 + cyclesCost;
  }

  public static <T extends WordNumber> void doPush(T value, Register<T> sp, Memory<T> memory) {
    sp.decrement();
    sp.decrement();
    T address = sp.read();
    Memory.write16Bits(memory, value, address);
  }
}
