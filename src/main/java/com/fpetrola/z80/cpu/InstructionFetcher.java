package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class InstructionFetcher<T extends WordNumber> {
  protected State<T> state;
  protected Instruction<T> instruction;
  protected Instruction<T>[] opcodesTables;
  protected int opcodeInt;
  protected T pcValue;

  public InstructionFetcher(State aState, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {
    this(aState, new OpcodeConditions(aState), fetchInstructionFactory);
  }

  public InstructionFetcher(State aState, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {
    this.state = aState;
    opcodesTables = new TableBasedOpCodeDecoder<T>(this.state, opcodeConditions, fetchInstructionFactory).getOpcodeLookupTable();
  }

  protected void fetchNextInstruction(InstructionExecutor<T> instructionExecutor) {
    state.getRegisterR().increment();
    pcValue = state.getPc().read();
    opcodeInt = state.getMemory().read(pcValue).intValue();
    Instruction<T> instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
    this.instruction = instruction.getBaseInstruction();
    instructionExecutor.execute(this.instruction,opcodeInt, pcValue);
    T nextPC = this.instruction.getNextPC();
    if (nextPC == null)
      nextPC = pcValue.plus(this.instruction.getLength());

    state.getPc().write(nextPC);
  }

  public void reset() {
  }
}
