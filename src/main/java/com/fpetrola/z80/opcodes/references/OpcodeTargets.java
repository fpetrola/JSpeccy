package com.fpetrola.z80.opcodes.references;

import static com.fpetrola.z80.registers.RegisterName.PC;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class OpcodeTargets<T> {

  private final class ConstantOpcodeReference<T> implements OpcodeReference<T> {
    private final T value;

    private ConstantOpcodeReference(T value) {
      this.value = value;
    }

    public void write(T value) {
      throw new RuntimeException("Cannot be written");
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

    public Object clone() throws CloneNotSupportedException {
      return this;
    }
  }

  private final State state;
  protected InstructionSpy spy;

  public OpcodeTargets(State state, InstructionSpy opcodesSpy) {
    this.state = state;
    this.spy = opcodesSpy;
  }

  public OpcodeReference c(T value) {
    return new ConstantOpcodeReference<T>(value);
  }

  public Register r(RegisterName name) {
    return state.getRegister(name);
  }

  public OpcodeReference iRR(RegisterName name) {
    return new IndirectMemory8BitReference(r(name), state.getMemory(), spy);
  }

  public OpcodeReference iRRn(RegisterName name, boolean rewindOnWrite, int valueDelta) {
    return new MemoryPlusRegister8BitReference(r(name), state.getMemory(), r(PC), valueDelta, spy);
  }

  public OpcodeReference iiRR(RegisterName name) {
    return new IndirectMemory16BitReference(r(name), state.getMemory(), spy);
  }

  public OpcodeReference n(int delta) {
    return new Memory8BitReference(state.getMemory(), r(PC), delta, spy);
  }

  public OpcodeReference nn(int delta) {
    return new Memory16BitReference(state.getMemory(), r(PC), delta, spy);
  }

  public OpcodeReference iinn(int delta) {
    return new IndirectMemory16BitReference(nn(delta), state.getMemory(), spy);
  }

  public OpcodeReference inn(int delta) {
    return new IndirectMemory8BitReference(nn(delta), state.getMemory(), spy);
  }

  public InstructionSpy getSpy() {
    return spy;
  }
}
