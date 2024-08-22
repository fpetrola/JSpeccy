package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.DefaultFetchNextOpcodeInstruction;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.NullInstructionSpy;

import java.io.FileWriter;

import static com.fpetrola.z80.registers.RegisterName.B;

public class DefaultInstructionFetcher<T extends WordNumber> implements InstructionFetcher {
  protected State<T> state;
  protected Instruction<T> instruction;
  protected Instruction<T>[] opcodesTables;

  protected int opcodeInt;
  protected T pcValue;
  protected final InstructionExecutor<T> instructionExecutor;
  FileWriter fileWriter;

//  {
//    try {
//      fileWriter = new FileWriter(Z80B.FILE);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }

  public DefaultInstructionFetcher(State aState, FetchNextOpcodeInstructionFactory fetchInstructionFactory, InstructionExecutor<T> instructionExecutor, InstructionFactory instructionFactory) {
    this(aState, new OpcodeConditions(aState.getFlag(), aState.getRegister(B)), fetchInstructionFactory, instructionExecutor, instructionFactory);
  }

  public DefaultInstructionFetcher(State aState, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory, InstructionExecutor<T> instructionExecutor, InstructionFactory instructionFactory) {
    this.state = aState;
    this.instructionExecutor = instructionExecutor;
    opcodesTables = new TableBasedOpCodeDecoder<T>(this.state, opcodeConditions, fetchInstructionFactory, instructionFactory).getOpcodeLookupTable();
    pcValue = state.getPc().read();
  }

  public static DefaultInstructionFetcher getInstructionFetcher(State state, NullInstructionSpy spy, InstructionFactory instructionFactory) {
    return new DefaultInstructionFetcher(state, new OpcodeConditions(state.getFlag(), state.getRegister(B)), new FetchNextOpcodeInstructionFactory(spy, state), new SpyInstructionExecutor(spy), instructionFactory);
  }

  @Override
  public void fetchNextInstruction() {
    state.getRegisterR().increment();
    pcValue = state.getPc().read();
//    if (pcValue.intValue() == 5853)
//      System.out.println("dagdag");
    opcodeInt = state.getMemory().read(pcValue).intValue();
    Instruction<T> instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    this.instruction = instruction;
    try {
      Instruction<T> executedInstruction = this.instructionExecutor.execute(this.instruction);
      String x = pcValue + ": " + instruction;

      // fileWriter.write(x + "\n");

      //System.out.println(x);
      this.instruction = getBaseInstruction(executedInstruction);

      T nextPC = null;
      if (this.instruction instanceof JumpInstruction jumpInstruction)
        nextPC = (T) jumpInstruction.getNextPC();

      if (nextPC == null)
        nextPC = pcValue.plus(getBaseInstruction(instruction).getLength());

      state.getPc().write(nextPC);
    } catch (Exception e) {
      state.setRunState(State.RunState.STATE_STOPPED_BREAK);
    }
  }

  public static <T extends WordNumber> Instruction<T> getBaseInstruction(Instruction<T> instruction) {
    while (instruction instanceof DefaultFetchNextOpcodeInstruction fetchNextOpcodeInstruction) {
      instruction = fetchNextOpcodeInstruction.findNextOpcode();
    }
    return instruction;
  }

  public static <T extends WordNumber> Instruction<T> processToBase(Instruction<T> instruction) {
    while (instruction instanceof DefaultFetchNextOpcodeInstruction fetchNextOpcodeInstruction) {
      fetchNextOpcodeInstruction.update();
      instruction = fetchNextOpcodeInstruction.findNextOpcode();
    }
    return instruction;
  }

  @Override
  public void reset() {
    instructionExecutor.reset();
  }
}
