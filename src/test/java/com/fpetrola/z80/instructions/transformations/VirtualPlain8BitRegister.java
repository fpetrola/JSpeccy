package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

public class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final boolean[] semaphore;
  private final Instruction<T> targetInstruction;
  private final ImmutableOpcodeReference<T> lastRegister;

  public VirtualPlain8BitRegister(String name, boolean[] semaphore, Instruction<T> targetInstruction, ImmutableOpcodeReference<T> lastRegister) {
    super(name);
    this.semaphore = semaphore;
    this.targetInstruction = targetInstruction;
    this.lastRegister = lastRegister;
  }

  public T read() {
    if (data != null)
      return data;

    if (!semaphore[0]) {
      semaphore[0] = true;
      targetInstruction.execute();
      semaphore[0] = false;
      return (T) data;
    } else
      return (T) lastRegister.read();
  }
}
