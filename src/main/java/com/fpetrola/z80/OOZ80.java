package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.instructions.cache.InstructionCache.CacheEntry;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class OOZ80 {
  protected Memory memory;
  public State state;
  private Instruction instruction;

  OpCodeDecoder opCodeDecoder;

  public Register pc;

  private Register memptr;

  private Register regI;

  private Register registerSP;

  private InstructionSpy spy;

  public int opcodeInt;

  public Register flag;

  private Register registerR;

  private Instruction[] opcodesTables;

  private InstructionCache instructionCache;

  private int pcValue;

  public OOZ80(State aState, InstructionSpy spy) {
    this.state = aState;
    this.memory = aState.getMemory();
    opCodeDecoder = new TableBasedOpCodeDecoder(this.state, spy);
    pc = this.state.getRegister(PC);
    memptr = this.state.getRegister(RegisterName.MEMPTR);
    regI = this.state.getRegister(I);
    registerR = this.state.getRegister(RegisterName.R);
    registerSP = this.state.getRegister(SP);
    flag = this.state.getRegister(RegisterName.F);

    opcodesTables = opCodeDecoder.getOpcodeLookupTable();

    this.spy = spy;
    spy.enable(false);

    instructionCache = new InstructionCache(pc, memory);
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
    if (state.isPendingEI() && opcodeInt != 0xFB) {
      state.setPendingEI(false);
      endInterruption();
    }
  }

  public void execute(int cycles) {
    registerR.increment(1);
    pcValue = pc.read();

    fetchInstruction(instruction -> {
      spy.start(instruction, opcodeInt, pcValue);
      instruction.setSpy(spy);
      instruction.execute();
      spy.end();
    });

    int nextPC = instruction.getNextPC();
    if (nextPC >= 0)
      pc.write(nextPC);
    else {
      pc.write((pcValue + instruction.getLength()) & 0xffff);
    }
  }

  private void fetchInstruction(Consumer<Instruction> instructionExecutor) {
    CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);

    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction instruction = cacheEntry.getOpcode();
      instructionExecutor.accept(instruction);
    } else {
//      System.out.println("exec: " + pcValue);
      opcodeInt = memory.read(pcValue);
      Instruction instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
      instruction.setSpy(spy);

      instructionExecutor.accept(instruction);

      this.instruction = instruction.getBaseInstruction();
      if (false)
        if (cacheEntry == null || !cacheEntry.isMutable())
          instructionCache.cacheInstruction(pcValue, this.instruction);
    }
  }

  public void interruption() {
    if (this.state.isHalted()) {
      this.state.setHalted(false);
      pc.increment(1);
    }

    registerR.increment(1);

    this.state.setIff1(false);
    this.state.setIff2(false);
    int word = pc.read();

    int spValue = registerSP.read();
    memory.write((--spValue) & 0xFFFF, word >>> 8);
    memory.write((--spValue) & 0xFFFF, word);
    registerSP.write(spValue);

    if (this.state.modeINT() == InterruptionMode.IM2) {
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

  public InstructionSpy getSpy() {
    return spy;
  }

  public void update() {
    memory.update();
    instructionCache.reset();
    spy.reset();
  }

  public int readMemoryAt(int address) {
    return memory.read(address);
  }
}
