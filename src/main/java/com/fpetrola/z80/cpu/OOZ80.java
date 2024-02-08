package com.fpetrola.z80.cpu;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.stream.Stream;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class OOZ80<T extends WordNumber> {
  protected InstructionFetcher<T> instructionFetcher;
  protected State<T> state;

  public OOZ80(State aState, InstructionFetcher instructionFetcher) {
    this.state = aState;
    this.instructionFetcher = instructionFetcher;
  }

  public void reset() {
    Stream.of(RegisterName.values()).forEach(r -> state.registers.get(r).write(createValue(0xFFFF)));
    state.getRegister(PC).write(createValue(0));
    state.getRegister(RegisterName.IR).write(createValue(0));
    state.getRegister(RegisterName.AF).write(createValue(0xFFFFF));
    state.setIntMode(InterruptionMode.IM0);
  }

  public void execute() {
    if (state.isActiveNMI()) {
      state.setActiveNMI(false);
      return;
    }
    if (state.isIntLine() && state.isIff1() && !state.isPendingEI()) {
      interruption();
    }
    execute(1);

    if (state.isPendingEI() && instructionFetcher.opcodeInt != 0xFB) {
      state.setPendingEI(false);
      endInterruption();
    }
  }

  public void execute(int cycles) {
    instructionFetcher.fetchInstruction(instruction -> instruction.execute());
    T nextPC = instructionFetcher.instruction.getNextPC();
    if (nextPC == null)
      nextPC = (instructionFetcher.pcValue.plus(instructionFetcher.instruction.getLength())).and(0xffff);

    state.getPc().write(nextPC);
  }

  public void interruption() {
    Register<T> pc = state.getPc();
    Memory<T> memory = state.getMemory();
    Register<T> sp = state.getRegisterSP();
    Register<T> memptr = state.getMemptr();

    if (state.isHalted()) {
      state.setHalted(false);
      pc.increment(1);
    }

    state.getRegisterR().increment(1);
    state.setIff1(false);
    state.setIff2(false);
    T word = pc.read();
    T spValue = sp.read();
    spValue = spValue.minus(1);
    memory.write(spValue.and(0xFFFF), word.right(8));
    spValue = spValue.minus(1);
    memory.write((spValue).and(0xFFFF), word);
    sp.write(spValue);

    T value = createValue(0x0038);
    if (state.modeINT() == InterruptionMode.IM2) {
      T address = (state.getRegI().read().left(8)).or(0xff);
      value = memory.read(address).left(8).or(memory.read(address.plus(1)));
    }

    pc.write(value);
    memptr.write(value);
  }

  public static <T extends WordNumber> T createValue(int i) {
    return (T) new WordNumber(i);
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
