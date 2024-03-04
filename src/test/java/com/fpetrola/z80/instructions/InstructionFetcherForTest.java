package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.PC;

public class InstructionFetcherForTest<T extends WordNumber> implements InstructionFetcher {
  List<Instruction<T>> instructions = new ArrayList<>();
  private int i;
  private Register<T> pc;
  private final SpyInstructionExecutor instructionExecutor;

  public InstructionFetcherForTest(State<T> state, SpyInstructionExecutor instructionExecutor) {
    pc = state.getRegister(PC);
    this.instructionExecutor = instructionExecutor;
  }

  public void fetchNextInstruction() {
    T pcValue = pc.read();
    Instruction<T> instruction = instructions.get(pcValue.intValue());
    instructionExecutor.execute(instruction);
    if (instruction.getNextPC() == null)
      pc.write(pc.read().plus1());
    else
      pc.write(instruction.getNextPC());
  }

  public void reset() {
    pc.write(createValue(0));
  }

  public int add(Instruction<T> instruction) {
    instructions.add(instruction);
    return instructions.size();
  }

  public Instruction<T> getInstructionAt(int i) {
    return instructions.get(i);
  }
}
