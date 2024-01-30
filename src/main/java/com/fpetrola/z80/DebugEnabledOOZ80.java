package com.fpetrola.z80;

import com.fpetrola.z80.helpers.StringHelper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.NullInstructionSpy;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class DebugEnabledOOZ80 extends OOZ80{
  protected OpCodeDecoder opCodeHandler2;
  protected volatile boolean continueExecution = true;
  protected volatile int till = 0xFFFFFFF;
  protected volatile boolean step;

  public DebugEnabledOOZ80(State aState, InstructionSpy spy) {
    super(aState, new InstructionFetcher(aState, spy));
    opCodeHandler2 = createOpCodeHandler(aState);
  }

  protected OpCodeDecoder createOpCodeHandler(State aState) {
    State state2 = new State();
    InstructionSpy spy2 = new NullInstructionSpy();
    state2.init(RegisterBank.createNullBank(), spy2, aState.getMemory(), aState.getIo());
    OpCodeDecoder decoder1 = new TableBasedOpCodeDecoder(state2, spy2);
//    new ByExtensionOpCodeDecoder(state2, spy2).compareOpcodesGenerators(state2, spy2, decoder1);

    return decoder1;
  }

  public void execute() {
    try {

      if (state.getPc().read() == till)
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

        if (state.isPendingEI() && instructionFetcher.opcodeInt != 0xFB) {
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

  public String decodeAt(int pc2) {
    Plain16BitRegister tempPC = new Plain16BitRegister(PC);
    tempPC.write(pc2);
    int i = state.getMemory().read(tempPC.read());
    Instruction opcode1 = getOpCodeHandler().getOpcodeLookupTable()[i];
//    Plain16BitRegister lastPC = opcode1.getPC();
    tempPC.increment(1);
//    opcode1.setPC(tempPC);
    int length = opcode1.getLength();
    tempPC.write(pc2 + 1);

    String result = "";

    for (int j = 0; j < length; j++) {
      int opcodePart = state.getMemory().read(pc2 + j);
      String convertToHex = StringHelper.convertToHex(opcodePart);
      result += convertToHex + " ";
    }

    String format = String.format("%-16s %s", result, opcode1.toString());
//    opcode1.setPC(lastPC);
    return format;
  }

  public int getLenghtAt(int pc2) {
    int i = state.getMemory().read(pc2);
    Instruction opcode1 = createOpCodeHandler(state).getOpcodeLookupTable()[i];
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
}
