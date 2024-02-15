package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.Push;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.opcodes.references.IntegerWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

import java.util.stream.Stream;

import static com.fpetrola.z80.registers.RegisterName.*;

public class OOZ80<T extends WordNumber> {
  protected InstructionFetcher<T> instructionFetcher;
  protected State<T> state;

  public OOZ80(State aState, InstructionFetcher instructionFetcher) {
    this.state = aState;
    this.instructionFetcher = instructionFetcher;
  }

  public void reset() {
    Stream.of(RegisterName.values()).forEach(r -> state.registers.get(r).write(WordNumber.createValue(0xFFFF)));
    state.getRegister(RegisterName.IR).write(WordNumber.createValue(0));
    state.getRegister(RegisterName.AF).write(WordNumber.createValue(0xFFFFF));
    state.setIntMode(InterruptionMode.IM0);
  }

  public void execute() {
    if (state.isActiveNMI()) {
      state.setActiveNMI(false);
      return;
    }
    if (state.isIntLine() && state.isIff1() && !state.isPendingEI())
      interruption();

    try {
      execute(1);
    } catch (Exception e)
    {
      e.printStackTrace();
      System.out.println("Invalid instruction");
    }
    if (state.isPendingEI() && instructionFetcher.opcodeInt != 0xFB) {
      state.setPendingEI(false);
      endInterruption();
    }
  }

  public void execute(int cycles) {
    instructionFetcher.fetchInstruction(instruction -> instruction.execute());
    T nextPC = instructionFetcher.instruction.getNextPC();
    if (nextPC == null)
      nextPC = (instructionFetcher.pcValue.plus(instructionFetcher.instruction.getLength()));

    state.getPc().write(nextPC);
  }

  public void interruption() {
    Register<T> pc = state.getPc();

    if (state.isHalted()) {
      state.setHalted(false);
      pc.increment();
    }

    state.getRegisterR().increment();
    state.setIff1(false);
    state.setIff2(false);

    Push.doPush(pc.read(), state);
    T value = state.modeINT() == InterruptionMode.IM2 ? Memory.read16Bits(state.getMemory(), (state.getRegI().read().left(8)).or(0xff)) : WordNumber.createValue(0x0038);
    pc.write(value);
    state.getMemptr().write(value);
  }

  public void endInterruption() {
  }

  public void update() {
    state.getMemory().update();
    instructionFetcher.reset();
  }

  public InstructionFetcher getInstructionFetcher() {
    return instructionFetcher;
  }

  public State<T> getState() {
    return state;
  }
}
