package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.instructions.cache.InstructionCache.CacheEntry;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.OOIntMode;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.NullInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;

import machine.Clock;

public class OOZ80 {
  public State stateFromEmulator;

  private int cyclesBalance;
  public boolean inInterruption;
  private Memory memory;

  public State state;
  public RegisterBank lastRegisterBank;
  public Instruction instruction;

  OpCodeDecoder opCodeDecoder;

  public Register pc;

  private Register memptr;

  private Register regI;

  private Register registerSP;

  private InstructionSpy spy;

  public int opcodeInt;

  public Register flag;

  private Register registerR;

  private OpCodeDecoder opCodeHandler2;

  private volatile boolean continueExecution = true;

  private volatile int till = 0xFFFFFFF;

  private volatile boolean step;

  private Clock clock;

  private Instruction[] opcodesTables;

  private InstructionCache instructionCache;

  private int pcValue;

  public OOZ80(State aState, GraphFrame graph2, InstructionSpy spy, Clock clock) {
    this.stateFromEmulator = aState;
    this.state = aState;
    this.clock = clock;
    opCodeDecoder = new TableBasedOpCodeDecoder(this.state, spy);
    opCodeHandler2 = createOpCodeHandler(aState);
    this.memory = aState.getMemory();
    pc = this.state.getRegister(PC);
    memptr = this.state.getRegister(RegisterName.MEMPTR);
    regI = this.state.getRegister(I);
    registerR = this.state.getRegister(RegisterName.R);
    registerSP = this.state.getRegister(SP);
    flag = this.state.getRegister(RegisterName.F);

    opcodesTables = opCodeDecoder.getOpcodeLookupTable();

    resetState(aState);

    this.spy = spy;
    spy.enable(true);

    instructionCache = new InstructionCache(pc, memory);
  }

  private OpCodeDecoder createOpCodeHandler(State aState) {
    State state2 = new State();
    InstructionSpy spy2 = new NullInstructionSpy();
    state2.init(RegisterBank.createNullBank(), spy2, aState.getMemory(), aState.getIo());
    OpCodeDecoder decoder1 = new TableBasedOpCodeDecoder(state2, spy2);
//    new ByExtensionOpCodeDecoder(state2, spy2).compareOpcodesGenerators(state2, spy2, decoder1);

    return decoder1;
  }

  private void resetState(State state2) {
    Stream.of(RegisterName.values()).forEach(r -> state2.registers.get(r).write(0xFFFF));
//    List<RegisterName> a = Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
//    a.stream().forEach(r -> state2.registers.getAlternate(r).write(0xFFFF));
    state2.getRegister(PC).write(0);
    state2.getRegister(RegisterName.IR).write(0);
//    state2.getRegister(RegisterName.STATES).write(64);
    state2.getRegister(RegisterName.AF).write(0xFFFFF);

    state2.setIntMode(OOIntMode.IM0);
  }

  public void reset() {

  }

  public void execute() {
    try {

      if (pc.read() == till)
        continueExecution = false;

      if (state.isActiveNMI()) {
        state.setActiveNMI(false);
        return;
      }

      if (continueExecution) {
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

        if (step) {
          continueExecution = false;
          step = false;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void execute(int cycles) {
    clock.addTstates(6);

    state.setNextPC(-1);

    cyclesBalance += cycles;
    registerR.increment(1);
    pcValue = pc.read();

    CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);

    if (cacheEntry != null && !cacheEntry.isMutable()) {
      instruction = cacheEntry.getOpcode();
      spy.start(instruction, opcodeInt, pcValue);
      instruction.setSpy(spy);
      cyclesBalance -= instruction.execute();
      spy.end();
    } else {
//      System.out.println("exec: " + pcValue);
      opcodeInt = memory.read(pcValue);
      instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];

      spy.start(instruction, opcodeInt, pcValue);
      instruction.setSpy(spy);
      cyclesBalance -= instruction.execute();
      spy.end();

      instruction = instruction.getBaseInstruction();
      if (false)
        if (cacheEntry == null || !cacheEntry.isMutable())
          instructionCache.cacheInstruction(pcValue, instruction);
    }

    int nextPC = state.getNextPC();
    if (nextPC >= 0)
      pc.write(nextPC);
    else {
      pc.write((pcValue + instruction.getLength()) & 0xffff);
    }

  }

  public void interruption() {
    clock.addTstates(7);
    if (this.state.isHalted()) {
      this.state.setHalted(false);
      pc.increment(1);
    }

//    clock.addTstates(7);

    registerR.increment(1);

    this.state.setIff1(false);
    this.state.setIff2(false);
    int word = pc.read();

    int spValue = registerSP.read();
    memory.write((--spValue) & 0xFFFF, word >>> 8);
    memory.write((--spValue) & 0xFFFF, word);
    registerSP.write(spValue);

    if (this.state.modeINT() == OOIntMode.IM2) {
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

  public InstructionSpy getSpy() {
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
    instructionCache.reset();
    spy.reset();
  }

  public int readMemoryAt(int address) {
    return memory.read(address);
  }

  public String decodeAt(int pc2) {
    Plain16BitRegister tempPC = new Plain16BitRegister(PC);
    tempPC.write(pc2);
    int i = memory.read(tempPC.read());
    Instruction opcode1 = getOpCodeHandler().getOpcodeLookupTable()[i];
//    Plain16BitRegister lastPC = opcode1.getPC();
    tempPC.increment(1);
//    opcode1.setPC(tempPC);
    int length = opcode1.getLength();
    tempPC.write(pc2 + 1);

    String result = "";

    for (int j = 0; j < length; j++) {
      int opcodePart = memory.read(pc2 + j);
      String convertToHex = OOZ80.convertToHex(opcodePart);
      result += convertToHex + " ";
    }

    String format = String.format("%-16s %s", result, opcode1.toString());
//    opcode1.setPC(lastPC);
    return format;
  }

  public int getLenghtAt(int pc2) {
    int i = memory.read(pc2);
    Instruction opcode1 = opCodeDecoder.getOpcodeLookupTable()[i];
    int length = opcode1.getLength();
    return length;
  }

  public void continueExecution() {
    continueExecution = true;
  }

  public void step() {
    step = true;
    continueExecution = true;
  }

  public void stop() {
    continueExecution = false;
    till = 0xFFFFFF;
  }

  public void till(int address) {
    this.till = address;
  }

  public OpCodeDecoder getOpCodeHandler() {
    return opCodeHandler2;
  }

  public static String convertToHex(int routineAddress) {
    return Long.toHexString(routineAddress).toUpperCase();
  }

}
