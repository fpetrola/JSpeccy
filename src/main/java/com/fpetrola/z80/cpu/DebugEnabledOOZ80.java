package com.fpetrola.z80.cpu;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.NullInstructionSpy;

import static com.fpetrola.z80.registers.RegisterName.PC;

public class DebugEnabledOOZ80<T extends WordNumber> extends OOZ80<T> {
  protected OpCodeDecoder opCodeHandler2;
  protected volatile boolean continueExecution = true;
  protected volatile int till = 0xFFFFFFF;
  protected volatile boolean step;

  public DebugEnabledOOZ80(State aState, InstructionSpy spy) {
    super(aState, new InstructionFetcher(aState));
    opCodeHandler2 = createOpCodeHandler(aState);
  }

  protected OpCodeDecoder createOpCodeHandler(State aState) {
    NullInstructionSpy spy = new NullInstructionSpy();
    State state2 = new State(spy, aState.getMemory(), aState.getIo());
    OpCodeDecoder decoder1 = new TableBasedOpCodeDecoder<T>(state2, spy, new OpcodeConditions(state2));
//    new ByExtensionOpCodeDecoder(state2, spy2).compareOpcodesGenerators(state2, spy2, decoder1);

    return decoder1;
  }

  public void execute() {
    try {

      if (state.getPc().read().intValue() == till)
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
    Plain16BitRegister<T> tempPC = new Plain16BitRegister<T>(PC);
    T value = createValue(pc2);
    tempPC.write(value);
    int i = state.getMemory().read(tempPC.read()).intValue();
    Instruction<T> opcode1 = getOpCodeHandler().getOpcodeLookupTable()[i];
//    Plain16BitRegister lastPC = opcode1.getPC();
    tempPC.increment(1);
//    opcode1.setPC(tempPC);
    int length = opcode1.getLength();
    tempPC.write(value.plus(1));

    String result = "";

    for (int j = 0; j < length; j++) {
      int opcodePart = state.getMemory().read(value.plus(j)).intValue();
      String convertToHex = Helper.convertToHex(opcodePart);
      result += convertToHex + " ";
    }

    String format = String.format("%-16s %s", result, opcode1.toString());
//    opcode1.setPC(lastPC);
    return format;
  }

  public int getLenghtAt(int pc2) {
    int i = state.getMemory().read(createValue(pc2)).intValue();
    Instruction<T> opcode1 = createOpCodeHandler(state).getOpcodeLookupTable()[i];
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
