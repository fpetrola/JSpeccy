package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.NullInstructionSpy;

public class SyncInstructionSpy extends NullInstructionSpy {
  private OOZ80 secondZ80;

  @Override
  public void setSecondZ80(Z80Cpu secondZ80) {
    this.secondZ80 = (OOZ80) secondZ80;
  }

  @Override
  public void beforeExecution(Instruction opcode) {
    secondZ80.execute();
  }
}
