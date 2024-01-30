package com.fpetrola.z80.cpu;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.stream.Stream;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class OOZ80 {
  protected final InstructionFetcher instructionFetcher;
  protected State state;

  public OOZ80(State aState, InstructionFetcher instructionFetcher) {
    this.state = aState;
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
    Register pc = state.getPc();
    Memory memory = state.getMemory();
    Register sp = state.getRegisterSP();
    Register memptr = state.getMemptr();

    if (state.isHalted()) {
      state.setHalted(false);
      pc.increment(1);
    }

    state.getRegisterR().increment(1);
    state.setIff1(false);
    state.setIff2(false);
    int word = pc.read();
    int spValue = sp.read();
    memory.write((--spValue) & 0xFFFF, word >>> 8);
    memory.write((--spValue) & 0xFFFF, word);
    sp.write(spValue);

    int value = 0x0038;
    if (state.modeINT() == InterruptionMode.IM2) {
      int address = (state.getRegI().read() << 8) | 0xff;
      value = memory.read(address) << 8 | memory.read(address + 1);
    }

    pc.write(value);
    memptr.write(value);
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
