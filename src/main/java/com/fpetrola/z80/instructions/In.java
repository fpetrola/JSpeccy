package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class In<T extends WordNumber> extends TargetSourceInstruction<T> {
  private final Register<T> a;
  private final Register<T> bc;
  private final FlagRegister<T> flag;
  private final Register<T> memptr;
  private final IO<T> io;

  In(OpcodeReference target, ImmutableOpcodeReference source, Register<T> a, Register<T> bc, FlagRegister<T> flag, Register<T> memptr, IO<T> io) {
    super(null, target, source);
    this.a = a;
    this.bc = bc;
    this.flag = flag;
    this.memptr = memptr;
    this.io = io;
  }

  public int execute() {
    T port = source.read();

    boolean equalsN = !(source instanceof Register);
    if (equalsN) {
      port = port.or(a.read().left(8));
      memptr.write(port.plus1());
    } else {
      port = bc.read();
    }

    T value = io.in(port);

    target.write(value);

    if (!equalsN) {
      flag.inC(value);
    }

    return cyclesCost;
  }
}
