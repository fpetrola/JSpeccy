package com.fpetrola.z80.instructions;

import static com.fpetrola.z80.registers.RegisterName.PC;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.RegisterName;

public class OpcodeTargets {

  private final class ConstantOpcodeReference implements OpcodeReference {
    private final int value;

    private ConstantOpcodeReference(int value) {
      this.value = value;
    }

    public void write(int value) {
      throw new RuntimeException("Cannot be written");
    }

    public int read() {
      return value;
    }

    public int cyclesCost() {
      return 0;
    }

    public int getLength() {
      return 0;
    }

    @Override
    public void setOpCode(OpCode opCode) {
      // TODO Auto-generated method stub

    }
  }

  private final State state;
  protected OpcodesSpy spy;

  public OpcodeTargets(State state, OpcodesSpy opcodesSpy) {
    this.state = state;
    this.spy = opcodesSpy;
  }

  public OpcodeReference c(int value) {
    return new ConstantOpcodeReference(value);
  }

  public OpcodeReference r(RegisterName name) {
    return state.getRegister(name);
  }

  public OpcodeReference _r(RegisterName name) {
    return state.getRegisterAlternate(name);
  }

  public OpcodeReference iRR(RegisterName name) {
    return spy.wrapOpcodeReference(new IndirectMemory8BitReference(r(name), state.getMemory()));
  }

  public OpcodeReference iRRn(RegisterName name, boolean rewindOnWrite, int valueDelta) {
    return spy.wrapOpcodeReference(new MemoryPlusRegister8BitReference(r(name), state.getMemory(), rewindOnWrite, valueDelta));
  }

  public OpcodeReference iiRR(RegisterName name) {
    return spy.wrapOpcodeReference(new IndirectMemory16BitReference(r(name), state.getMemory()));
  }

  public OpcodeReference n() {
    return spy.wrapOpcodeReference(new Memory8BitReference(state.getMemory()));
  }

  public OpcodeReference n(int delta) {
    return spy.wrapOpcodeReference(new Memory8BitReference(state.getMemory(), delta));
  }

  public OpcodeReference nn() {
    return spy.wrapOpcodeReference(new Memory16BitReference(state.getMemory()));
  }

  public OpcodeReference iinn() {
    return spy.wrapOpcodeReference(new IndirectMemory16BitReference(nn(), state.getMemory()));
  }

  public OpcodeReference inn() {
    return spy.wrapOpcodeReference(new IndirectMemory8BitReference(nn(), state.getMemory()));
  }

  public OpcodesSpy getSpy() {
    return spy;
  }
}
