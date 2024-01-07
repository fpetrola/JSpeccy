package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fpetrola.z80.State.IntMode2;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodesSpy;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;

public class OOZ80 {

  public State stateFromEmulator;

  private int cyclesBalance;
  public boolean inInterruption;
  private Memory memory;

  public static State state;
  public static RegisterBank lastRegisterBank;
  public static OpCode opcode;

  OpCodeHandler opCodeHandler;

  public Register pc;

  private Register memptr;

  private Register regI;

  private Register registerSP;

  private OpcodesSpy spy;

  public int opcodeInt;

  public Register flag;

  private Register regR;

  public OOZ80(Memory memory, IO io, State aState, GraphFrame graph2, OpcodesSpy spy) {
    this.stateFromEmulator = aState;
    this.state = aState;
    opCodeHandler = new OpCodeHandler(this.state, spy);
    this.memory = opCodeHandler.memory();
    pc = this.state.getRegister(PC);
    memptr = this.state.getRegister(RegisterName.MEMPTR);
    regI = this.state.getRegister(I);
    regR = this.state.getRegister(RegisterName.R);
    registerSP = this.state.getRegister(SP);
    flag = this.state.getRegister(RegisterName.F);

    resetState(aState);

    opCodeHandler.fillOpcodeLookupTable();
    this.spy = spy;
    spy.enable(false);
  }

  private void resetState(State state2) {
    Stream.of(RegisterName.values()).forEach(r -> state2.registers.get(r).write(0xFFFF));
    List<RegisterName> a = Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
    a.stream().forEach(r -> state2.registers.getAlternate(r).write(0xFFFF));
    state2.getRegister(PC).write(0);
    state2.getRegister(RegisterName.IR).write(0);
    state2.getRegister(RegisterName.STATES).write(64);
    state2.getRegister(RegisterName.AF).write(0xFFFFF);

    state2.setIntMode(IntMode2.IM0);
  }

  public void reset() {

  }

  public void execute(int cycles) {
//    lastRegisterBank = new RegisterBank();
//    stateFromEmulator.registers.copyTo(lastRegisterBank);
    cyclesBalance += cycles;
    regR.increment(1);
    int pcValue = pc.read();
    opcodeInt = memory.read(pcValue);
    opcode = opCodeHandler.opcodeLookupTable[this.state.isHalted() ? 0x76 : opcodeInt];
    spy.start(opcode, opcodeInt, pcValue);
    cyclesBalance -= opcode.execute();
    spy.end();
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
    memory.write(--spValue & 0xFFFF, word >>> 8);
    memory.write(--spValue & 0xFFFF, word);
    registerSP.write(spValue);

    if (this.state.modeINT() == IntMode2.IM2) {
      int address = (regI.read() << 8) | 0xff;
      int value = memory.read(address) << 8 | memory.read(address + 1);
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

  public OpcodesSpy getSpy() {
    return spy;
  }

  public void compare() {
//    List<WriteAction> compareTo = state.registers.compareTo(state.registers);
//    if (compareTo.size() > 0)
//      System.out.println("dagadgdgsa");
    memory.compare();
  }

  public void update() {
    memory.update();

  }

}
