package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class DefaultInstructionFetcher<T extends WordNumber> implements InstructionFetcher<T> {
  protected State<T> state;
  protected Instruction<T> instruction;
  protected Instruction<T>[] opcodesTables;

  protected int opcodeInt;
  protected T pcValue;

  public DefaultInstructionFetcher(State aState, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {
    this(aState, new OpcodeConditions(aState.getFlag()), fetchInstructionFactory);
  }

  public DefaultInstructionFetcher(State aState, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {
    this.state = aState;
    opcodesTables = new TableBasedOpCodeDecoder<T>(this.state, opcodeConditions, fetchInstructionFactory).getOpcodeLookupTable();
  }

  @Override
  public void fetchNextInstruction(InstructionExecutor<T> instructionExecutor) {
    state.getRegisterR().increment();
    pcValue = state.getPc().read();
    opcodeInt = state.getMemory().read(pcValue).intValue();
    Instruction<T> instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    this.instruction = instruction;
    instructionExecutor.execute(this.instruction,opcodeInt, pcValue);
    this.instruction = instruction.getBaseInstruction();
    T nextPC = this.instruction.getNextPC();
    if (nextPC == null)
      nextPC = pcValue.plus(this.instruction.getLength());

    state.getPc().write(nextPC);
  }

  @Override
  public void reset() {
  }
}
