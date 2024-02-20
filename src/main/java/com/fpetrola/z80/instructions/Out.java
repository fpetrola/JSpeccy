package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Out<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Out(State state, ImmutableOpcodeReference target, ImmutableOpcodeReference source) {
    super(state, new OpcodeReference<T>() {
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
  }

  public int execute() {
    T port = target.read();
    T value = source.read();
    state.getIo().out(port, value);

    return cyclesCost;
  }
}
