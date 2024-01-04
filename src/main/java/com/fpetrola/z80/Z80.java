package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fpetrola.z80.OpCodeHandler.FlipOpcode;
import com.fpetrola.z80.State.IntMode2;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;

public class Z80 {

  public State stateFromEmulator;

  private int cyclesBalance;
  public boolean inInterruption;
  private Memory memory;

  public static State state;
  public static RegisterBank lastRegisterBank;
  public static OpCode opcode;

  OpCodeHandler opCodeHandler;

  private Register pc;

  private Register memptr;

  private Register regI;

  private Register registerSP;

  public Z80(Memory memory, IO io, State aState, GraphFrame graph2) {
    this.memory = memory;
    this.stateFromEmulator = aState;
    this.state = aState;
    opCodeHandler = new OpCodeHandler(memory, io, this.state);
    pc = this.state.getRegister(PC);
    memptr = this.state.getRegister(RegisterName.MEMPTR);
    regI = this.state.getRegister(I);
    registerSP = this.state.getRegister(SP);

    resetState(aState);

    opCodeHandler.fillOpcodeLookupTable();
  }

  private void resetState(State state2) {
    Stream.of(RegisterName.values()).forEach(r -> state2.registers.get(r).write(0xFFFF));
    List<RegisterName> a = Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
    a.stream().forEach(r -> state2.registers.getAlternate(r).write(0xFFFF));
    state2.getRegister(PC).write(0);
    state2.getRegister(RegisterName.IR).write(0);
    state2.getRegister(RegisterName.STATES).write(64);
    state2.getRegister(RegisterName.AF).write(65495);

    state2.setIntMode(IntMode2.IM0);
  }

  public void reset() {

  }

  public void execute(int cycles) {
//    lastRegisterBank = new RegisterBank();
//    stateFromEmulator.registers.copyTo(lastRegisterBank);
    cyclesBalance += cycles;
    opcode = opCodeHandler.opcodeLookupTable[this.state.isHalted() ? 0x76 : memory.read(pc.read(), false)];
    cyclesBalance -= opcode.execute();
  }

  public void interruption() {
    if (this.state.isHalted()) {
      this.state.setHalt(false);
      pc.increment(1);
    }

//    clock.addTstates(7);

    OpCodeHandler.registerR.increment(1);

    this.state.setIff1(false);
    this.state.setIff2(false);
    int word = pc.read();

    int spValue = registerSP.read();
    memory.write(--spValue, word >>> 8);
    memory.write(--spValue, word);
    registerSP.write(spValue);
    
    if (this.state.modeINT() == IntMode2.IM2) {
      int address = (regI.read() << 8) | 0xff;
      int value = memory.read(address, false) << 8 | memory.read(address + 1, false);
      pc.write(value);
    } else {
      pc.write(0x0038);
    }

    memptr.write(pc.read());
  }

  public void endInterruption() {
  }

  public int getCyclesBalance() {
    return cyclesBalance;
  }

}
