package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Out<T extends WordNumber> extends TargetSourceInstruction<T> {
  private final IO<T> io;

  Out(ImmutableOpcodeReference target, ImmutableOpcodeReference source, IO<T> io) {
    super(new OpcodeReference<T>() {
      public void write(T value) {
        System.out.println("sdgsdhsdh");
      }

      public T read() {
        return (T) target.read();
      }

      public int getLength() {
        return target.getLength();
      }

      public Object clone() throws CloneNotSupportedException {
        return target.clone();
      }
    }, source);
    this.io = io;
  }

  public int execute() {
    T port = target.read();
    T value = source.read();
    io.out(port, value);

    return cyclesCost;
  }
}
