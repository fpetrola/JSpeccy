package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.function.Consumer;

public class InstructionFetcher {
  protected State state;
  protected Instruction instruction;
  protected Instruction[] opcodesTables;
  protected int opcodeInt;
  protected int pcValue;
  protected InstructionSpy spy;

  public InstructionFetcher(State aState) {
    this(aState, new OpcodeConditions(aState));
  }

  public InstructionFetcher(State aState, OpcodeConditions opcodeConditions) {
    this.state = aState;
    this.spy = aState.getSpy();
    this.spy.enable(false);
    opcodesTables = new TableBasedOpCodeDecoder(this.state, this.spy, opcodeConditions).getOpcodeLookupTable();
  }

  protected void fetchInstruction(Consumer<Instruction> instructionExecutor) {
    state.getRegisterR().increment(1);
    pcValue = state.getPc().read();
    opcodeInt = state.getMemory().read(pcValue);
    Instruction instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    wrapExecution(instructionExecutor, instruction);

    this.instruction = instruction.getBaseInstruction();
  }

  protected void wrapExecution(Consumer<Instruction> instructionExecutor, Instruction instruction) {
    instruction.setSpy(spy);
    spy.start(instruction, opcodeInt, pcValue);
    instructionExecutor.accept(instruction);
    spy.end();
  }

  public void reset() {
    spy.reset(state);
  }

  public InstructionSpy getSpy() {
    return spy;
  }
}
