package com.fpetrola.z80;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.registers.RegisterName;

import java.util.stream.Stream;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class OOZ80 {
  protected final InstructionFetcher instructionFetcher;
  protected State state;

  public OOZ80(State aState, InstructionFetcher instructionFetcher) {
    this.state= aState;
    this.instructionFetcher = instructionFetcher;
  }

  public void reset() {
    Stream.of(RegisterName.values()).forEach(r -> state.registers.get(r).write(0xFFFF));
    state.getRegister(PC).write(0);
    state.getRegister(RegisterName.IR).write(0);
    state.getRegister(RegisterName.AF).write(0xFFFFF);
    state.setIntMode(InterruptionMode.IM0);
  }

  public void execute() {
    if (state.isActiveNMI()) {
      state.setActiveNMI(false);
      return;
    }
    if (state.isIntLine()) {
      if (state.isIff1() && !state.isPendingEI()) {
        interruption();
      }
    }
    execute(1);
    if (state.isPendingEI() && instructionFetcher.opcodeInt != 0xFB) {
      state.setPendingEI(false);
      endInterruption();
    }
  }

  public void execute(int cycles) {
    state.getRegisterR().increment(1);
    instructionFetcher.pcValue = state.getPc().read();
    instructionFetcher.fetchInstruction(instruction -> instruction.execute());
    int nextPC = instructionFetcher.instruction.getNextPC();
    if (nextPC >= 0)
      state.getPc().write(nextPC);
    else {
      state.getPc().write((instructionFetcher.pcValue + instructionFetcher.instruction.getLength()) & 0xffff);
    }
  }

  public void interruption() {
    if (state.isHalted()) {
      state.setHalted(false);
      state.getPc().increment(1);
    }

    state.getRegisterR().increment(1);

    state.setIff1(false);
    state.setIff2(false);
    int word = state.getPc().read();

    int spValue = state.getRegisterSP().read();
    state.getMemory().write((--spValue) & 0xFFFF, word >>> 8);
    state.getMemory().write((--spValue) & 0xFFFF, word);
    state.getRegisterSP().write(spValue);

    if (state.modeINT() == InterruptionMode.IM2) {
      int address = (state.getRegI().read() << 8) | 0xff;
      int value = state.getMemory().read(address) << 8 | state.getMemory().read(address + 1);
      state.getPc().write(value);
    } else {
      state.getPc().write(0x0038);
    }

    state.getMemptr().write(state.getPc().read());
  }

  public void endInterruption() {
  }

  public void update() {
    state.getMemory().update();
    instructionFetcher.reset();
  }

  public int readMemoryAt(int address) {
    return state.getMemory().read(address);
  }

  public InstructionFetcher getInstructionFetcher() {
    return instructionFetcher;
  }

  public State getState() {
    return state;
  }
}
