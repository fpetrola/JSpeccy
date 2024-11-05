package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class CachedMemoryPlusRegister8BitReference<T extends WordNumber> extends MemoryPlusRegister8BitReference<T>{
  public CachedMemoryPlusRegister8BitReference(T lastFetchedRelative, ImmutableOpcodeReference target, Memory<T> memory, Register<T> pc, int valueDelta) {
    super(target, memory, pc, valueDelta);
    fetchedRelative = lastFetchedRelative;
  }

  public byte fetchRelative() {
    return (byte) fetchedRelative.intValue();
  }
}
