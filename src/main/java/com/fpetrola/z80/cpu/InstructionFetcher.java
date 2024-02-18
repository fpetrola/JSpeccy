package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.function.Consumer;

public class InstructionFetcher<T extends WordNumber> {
  protected State<T> state;
  protected Instruction<T> instruction;
  protected Instruction<T>[] opcodesTables;
  protected int opcodeInt;
  protected T pcValue;
  protected InstructionSpy spy;

  public InstructionFetcher(State aState) {
    this(aState, new OpcodeConditions(aState));
  }

  public InstructionFetcher(State aState, OpcodeConditions opcodeConditions) {
    this.state = aState;
    this.spy = aState.getSpy();
    this.spy.enable(false);
    opcodesTables = new TableBasedOpCodeDecoder<T>(this.state, this.spy, opcodeConditions).getOpcodeLookupTable();
  }

  protected void fetchInstruction(Consumer<Instruction<T>> instructionExecutor) {
    state.getRegisterR().increment();
    pcValue = state.getPc().read();
//    if (pcValue.intValue() == 0x945F)
//      System.out.println("addag");
    opcodeInt = state.getMemory().read(pcValue).intValue();
    Instruction<T> instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    wrapExecution(instructionExecutor, instruction);

    this.instruction = instruction.getBaseInstruction();
  }

  protected void wrapExecution(Consumer<Instruction<T>> instructionExecutor, Instruction<T> instruction) {
    instruction.setSpy(spy);
    spy.start(instruction, opcodeInt, pcValue);
    if (pcValue.intValue() == 38541)
      System.out.println("BB");
    if (pcValue.intValue() == 38538)
      System.out.println("CC");
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
