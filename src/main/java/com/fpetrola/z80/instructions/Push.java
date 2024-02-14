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
    doPush(target.read(), state);
    return 5 + target.cyclesCost();
  }

  public static <T extends WordNumber> void doPush(T value, State<T> state) {
    Register<T> sp = state.getRegisterSP();
    Memory memory = state.getMemory();
    sp.decrement(2);
    T address = sp.read();
    Memory.write16Bits(memory, value, address);
  }
}
