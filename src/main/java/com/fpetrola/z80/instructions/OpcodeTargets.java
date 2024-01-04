package com.fpetrola.z80.instructions;

import static com.fpetrola.z80.registers.RegisterName.PC;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.RegisterName;

public class OpcodeTargets {

  private final State state;
  private final Memory memory;

  public OpcodeTargets(State state, Memory memory) {
    this.state = state;
    this.memory = memory;
  }

  public OpcodeReference r(RegisterName name) {
    return state.getRegister(name);
  }

  public OpcodeReference _r(RegisterName name) {
    return state.getRegisterAlternate(name);
  }

  public OpcodeReference iRR(RegisterName name) {
    return new IndirectMemory8BitReference(state.getRegister(name), getMemory());
  }

  public OpcodeReference iRRn(RegisterName name, boolean rewindOnWrite, int valueDelta) {
    return new MemoryPlusRegister8BitReference(state.getRegister(PC), state.getRegister(name), getMemory(), rewindOnWrite, valueDelta);
  }

  public OpcodeReference iiRR(RegisterName name) {
    return new IndirectMemory16BitReference(state.getRegister(name), getMemory());
  }

  public OpcodeReference n() {
    return new Memory8BitReference(state.getRegister(PC), getMemory());
  }

  public OpcodeReference n(int delta) {
    return new Memory8BitReference(state.getRegister(PC), getMemory(), delta);
  }

  public OpcodeReference nn() {
    return new Memory16BitReference(state.getRegister(PC), getMemory());
  }

  public OpcodeReference iinn() {
    return new IndirectMemory16BitReference(nn(), getMemory());
  }

  public OpcodeReference inn() {
    return new IndirectMemory8BitReference(nn(), getMemory());
  }

  public Memory getMemory() {
    return memory;
  }

}
