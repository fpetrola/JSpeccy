package com.fpetrola.z80.instructions;

import static com.fpetrola.z80.registers.RegisterName.PC;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.RegisterName;

public class OpcodeTargets {

  private final State state;
  private final Memory memory;
  protected OpcodesSpy spy;

  public OpcodeTargets(State state, Memory memory, OpcodesSpy opcodesSpy) {
    this.state = state;
    this.memory = memory;
    this.spy = opcodesSpy;
  }

  public OpcodeReference r(RegisterName name) {
    return state.getRegister(name);
  }

  public OpcodeReference _r(RegisterName name) {
    return state.getRegisterAlternate(name);
  }

  public OpcodeReference iRR(RegisterName name) {
    return spy.wrapOpcodeReference(new IndirectMemory8BitReference(r(name), memory()));
  }

  public OpcodeReference iRRn(RegisterName name, boolean rewindOnWrite, int valueDelta) {
    return spy.wrapOpcodeReference(new MemoryPlusRegister8BitReference(state.getRegister(PC), r(name), memory(), rewindOnWrite, valueDelta));
  }

  public OpcodeReference iiRR(RegisterName name) {
    return spy.wrapOpcodeReference(new IndirectMemory16BitReference(r(name), memory()));
  }

  public OpcodeReference n() {
    return spy.wrapOpcodeReference(new Memory8BitReference(state.getRegister(PC), memory()));
  }

  public OpcodeReference n(int delta) {
    return spy.wrapOpcodeReference(new Memory8BitReference(state.getRegister(PC), memory(), delta));
  }

  public OpcodeReference nn() {
    return spy.wrapOpcodeReference(new Memory16BitReference(state.getRegister(PC), memory()));
  }

  public OpcodeReference iinn() {
    return spy.wrapOpcodeReference(new IndirectMemory16BitReference(nn(), memory()));
  }

  public OpcodeReference inn() {
    return spy.wrapOpcodeReference(new IndirectMemory8BitReference(nn(), memory()));
  }

  public Memory memory() {
    return spy.wrapMemory(memory);
  }

  public OpcodesSpy getSpy() {
    return spy;
  }
}
