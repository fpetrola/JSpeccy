package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.DefaultFetchNextOpcodeInstruction;
import com.fpetrola.z80.spy.InstructionSpy;

public class FetchNextOpcodeInstructionFactory<T> {
  private InstructionSpy spy;
  private State state;

  public FetchNextOpcodeInstructionFactory(InstructionSpy spy, State state) {
    this.spy = spy;
    this.state = state;
  }

  public DefaultFetchNextOpcodeInstruction createFetchInstruction(Instruction<T>[] opcodesTable, String name, int incPc) {
    return new DefaultFetchNextOpcodeInstruction(this.state, opcodesTable, incPc, name, this.spy);
  }
}
