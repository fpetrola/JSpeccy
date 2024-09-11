package com.fpetrola.z80.minizx.emulation;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.DefaultInstructionFactory;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.NullInstructionSpy;

public class Helper {
  public static <T extends WordNumber> OOZ80<T> createOOZ80(IO io) {
    var state = new State(io, new MockedMemory());
    return new OOZ80(state, DefaultInstructionFetcher.getInstructionFetcher(state, new NullInstructionSpy(), new DefaultInstructionFactory<T>(state)));
  }
}
