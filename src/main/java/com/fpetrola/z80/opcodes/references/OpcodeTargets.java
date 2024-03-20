package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class OpcodeTargets<T> {

  private final class ConstantOpcodeReference<T> implements ImmutableOpcodeReference<T> {
    private final T value;

    private ConstantOpcodeReference(T value) {
      this.value = value;
    }

    public T read() {
      return value;
    }

    public int getLength() {
      return 0;
    }

    public String toString() {
      return value + "";
    }

    public Object clone() {
      return this;
    }
  }

  private final State state;

  public OpcodeTargets(State state) {
    this.state = state;
  }

  public ImmutableOpcodeReference c(int value) {
    return new ConstantOpcodeReference<T>(WordNumber.createValue(value));
  }

  public Register r(RegisterName name) {
    return state.getRegister(name);
  }

  public OpcodeReference iRR(RegisterName name) {
    return iRR(r(name));
  }

  public OpcodeReference iRR(ImmutableOpcodeReference r) {
    return new IndirectMemory8BitReference(r, state.getMemory());
  }

  public OpcodeReference iRRn(RegisterName name, boolean rewindOnWrite, int valueDelta) {
    return iRRn(valueDelta, r(name));
  }

  public OpcodeReference iRRn(int valueDelta, ImmutableOpcodeReference r) {
    return new MemoryPlusRegister8BitReference(r, state.getMemory(), r(PC), valueDelta);
  }

  public OpcodeReference iiRR(RegisterName name) {
    Register r = r(name);
    return iiRR(r);
  }

  public OpcodeReference iiRR(ImmutableOpcodeReference r) {
    return new IndirectMemory16BitReference(r, state.getMemory());
  }

  public ImmutableOpcodeReference n(int delta) {
    return new Memory8BitReference(state.getMemory(), r(PC), delta);
  }

  public ImmutableOpcodeReference nn(int delta) {
    return new Memory16BitReference(state.getMemory(), r(PC), delta);
  }

  public OpcodeReference iinn(int delta) {
    return new IndirectMemory16BitReference(nn(delta), state.getMemory());
  }

  public OpcodeReference inn(int delta) {
    return new IndirectMemory8BitReference(nn(delta), state.getMemory());
  }
}
