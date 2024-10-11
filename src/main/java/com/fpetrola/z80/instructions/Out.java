package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Out<T extends WordNumber> extends TargetSourceInstruction<T, ImmutableOpcodeReference<T>> {
  public Out(ImmutableOpcodeReference source, OutPortOpcodeReference outPortOpcodeReference, Register<T> flag) {
    super(outPortOpcodeReference, source, flag);
  }

  public int execute() {
    target.write(source.read());
    return cyclesCost;
  }

  public static class OutPortOpcodeReference<T> implements OpcodeReference<T> {
    private final IO<T> io;
    private final ImmutableOpcodeReference target;

    public OutPortOpcodeReference(IO<T> io, ImmutableOpcodeReference target) {
      this.io = io;
      this.target = target;
    }

    public void write(T value) {
      io.out((T) target.read(), value);
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

    public String toString() {
      return target.toString();
    }
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitOut(this);
  }
}
