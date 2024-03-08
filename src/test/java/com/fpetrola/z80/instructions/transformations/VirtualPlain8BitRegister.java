package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

public class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final boolean[] executing;
  private final Instruction<T> targetInstruction;
  private final ImmutableOpcodeReference<T> lastRegister;

  public VirtualPlain8BitRegister(String name, boolean[] executing, Instruction<T> targetInstruction, ImmutableOpcodeReference<T> lastRegister) {
    super(name);
    this.executing = executing;
    this.targetInstruction = targetInstruction;
    this.lastRegister = lastRegister;
  }

  public T read() {
    if (data != null)
      return data;

    if (!executing[0]) {
      executing[0] = true;
      targetInstruction.execute();
      executing[0] = false;
      return (T) data;
    } else
      return (T) lastRegister.read();
  }
}
