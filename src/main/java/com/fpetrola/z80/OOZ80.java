package com.fpetrola.z80;

import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import com.fpetrola.z80.State.OOIntMode;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.ByExtensionOpCodeDecoder;
import com.fpetrola.z80.opcodes.FlipOpcode;
import com.fpetrola.z80.opcodes.OpCodeDecoder;
import com.fpetrola.z80.opcodes.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;

import machine.Clock;

public class OOZ80 {

  public State stateFromEmulator;

  private int cyclesBalance;
  public boolean inInterruption;
  private Memory memory;

  public static State state;
  public static RegisterBank lastRegisterBank;
  public static OpCode opcode;

  OpCodeDecoder opCodeDecoder;

  public Register pc;

  private Register memptr;

  private Register regI;

  private Register registerSP;

  private SpyInterface spy;

  public int opcodeInt;

  public Register flag;

  private Register registerR;

  private OpCodeDecoder opCodeHandler2;

  private volatile boolean continueExecution = true;

  private volatile int till = 0xFFFFFFF;

  private volatile boolean step;

  private int counter;

  private Clock clock;

  private OpCode[] opcodesTables;

  public OOZ80(State aState, GraphFrame graph2, SpyInterface spy, Clock clock) {
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
    spy.enable(false);
  }

  private OpCodeDecoder createOpCodeHandler(State aState) {
    State state2 = new State();
    SpyInterface spy2 = new NullSpy();
    state2.init(RegisterBank.createNullBank(), spy2, aState.getMemory(), aState.getIo());
    OpCodeDecoder decoder1 = new TableBasedOpCodeDecoder(state2, spy2);
    OpCodeDecoder decoder2 = new ByExtensionOpCodeDecoder(state2, spy2);

    OpCode[] opcodeLookupTable = decoder1.getOpcodeLookupTable();
    OpCode[] opcodeLookupTable2 = decoder2.getOpcodeLookupTable();
    int comparison = compareOpcodes(opcodeLookupTable, opcodeLookupTable2);

    return decoder1;
  }

  private int compareOpcodes(OpCode[] opcodeLookupTable, OpCode[] opcodeLookupTable2) {
    int compare = Arrays.compare(opcodeLookupTable, opcodeLookupTable2, new Comparator<OpCode>() {
      public int compare(OpCode o1, OpCode o2) {
        if (o1 != null && o2 != null) {
          Plain16BitRegister pc2 = new Plain16BitRegister("PC");
          pc2.write(0);
          Plain16BitRegister pc3 = new Plain16BitRegister("PC");
          pc3.write(0);
          o1.setPC(pc2);
          o2.setPC(pc3);

          if (o1 instanceof FlipOpcode) {
            FlipOpcode flipOpcode = (FlipOpcode) o1;
            FlipOpcode flipOpcode2 = (FlipOpcode) o2;
            return compareOpcodes(flipOpcode.getTable(), flipOpcode2.getTable());
          } else {
            int compareTo = o1.toString().compareTo(o2.toString());
            if (compareTo != 0)
              System.out.println("dgadg");
            return compareTo;
          }
        } else
          return 0;
      }
    });
    return compare;
  }

  private void resetState(State state2) {
    Stream.of(RegisterName.values()).forEach(r -> state2.registers.get(r).write(0xFFFF));
    List<RegisterName> a = Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
    a.stream().forEach(r -> state2.registers.getAlternate(r).write(0xFFFF));
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
//        counter++;
//        if ((counter % 1000) == 0)
//          Thread.sleep(1);

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
//    lastRegisterBank = new RegisterBank();
//    stateFromEmulator.registers.copyTo(lastRegisterBank);
    state.setNextPC(-1);

    cyclesBalance += cycles;
    registerR.increment(1);
    int pcValue = pc.read();
    opcodeInt = memory.read(pcValue);
    pc.increment(1);
    opcode = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    spy.start(opcode, opcodeInt, pcValue);
    cyclesBalance -= opcode.execute();

    int nextPC = state.getNextPC();
    if (nextPC >= 0)
      pc.write(nextPC);
    else {
//      int pcValue2 = pc.read();
//      pc.write(pcValue + 1);
//      int length = opcode.getLength();
//      int value = (pcValue + length) & 0xffff;
//      if (pcValue2 != value) {
//        System.out.println("ohhh!!!");
//        value= pcValue2;
//      }
//      pc.write(value);
    }
    
//    pc.write(value);
    spy.end();
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

  public SpyInterface getSpy() {
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

  public int readMemoryAt(int address) {
    return memory.read(address);
  }

  public String decodeAt(int pc2) {
    Plain16BitRegister tempPC = new Plain16BitRegister("PC");
    tempPC.write(pc2);
    int i = memory.read(tempPC.read());
    OpCode opcode1 = getOpCodeHandler().getOpcodeLookupTable()[i];
    Plain16BitRegister lastPC = opcode1.getPC();
    tempPC.increment(1);
    opcode1.setPC(tempPC);
    int length = opcode1.getLength();
    tempPC.write(pc2 + 1);

    String result = "";

    for (int j = 0; j < length; j++) {
      int opcodePart = memory.read(pc2 + j);
      String convertToHex = OOZ80.convertToHex(opcodePart);
      result += convertToHex + " ";
    }

    String format = String.format("%-16s %s", result, opcode1.toString());
    opcode1.setPC(lastPC);
    return format;
  }

  public int getLenghtAt(int pc2) {
    int i = memory.read(pc2);
    OpCode opcode1 = opCodeDecoder.getOpcodeLookupTable()[i];
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
