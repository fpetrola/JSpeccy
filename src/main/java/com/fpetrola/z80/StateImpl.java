package com.fpetrola.z80;

import com.fpetrola.z80.instructions.OpcodesSpy;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.RegisterBank;

public class StateImpl extends State {

  public StateImpl(Z80B z80, OpcodesSpy spy, Memory memory, IO io) {
    super();
    init(createBank2(z80, this), spy, memory, io);
  }

  private RegisterBank createBank2(Z80B z80, StateImpl stateImpl) {
    return RegisterBank.createSimpleBank();
  }

}
