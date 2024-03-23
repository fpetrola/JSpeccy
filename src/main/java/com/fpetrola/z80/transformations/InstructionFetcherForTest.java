package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.List;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.PC;

public class InstructionFetcherForTest<T extends WordNumber> implements InstructionFetcher {
  protected List<Instruction<T>> instructions = new ArrayList<>();
  private int i;
  protected Register<T> pc;
  protected final State<T> state;
  protected final InstructionExecutor instructionExecutor;

  public InstructionFetcherForTest(State<T> state, InstructionExecutor instructionExecutor) {
    pc = state.getRegister(PC);
    this.state = state;
    this.instructionExecutor = instructionExecutor;
  }

  public void fetchNextInstruction() {
    T pcValue = pc.read();
    Instruction<T> instruction = instructions.get(pcValue.intValue());
    instructionExecutor.execute(instruction);
    updatePC(instruction);
  }

  protected void updatePC(Instruction<T> instruction) {
    T nextPC = null;
    if (instruction instanceof JumpInstruction jumpInstruction)
      nextPC = (T) jumpInstruction.getNextPC();

    if (nextPC == null)
      nextPC = pc.read().plus(instruction.getLength());

    pc.write(nextPC);
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

  public Instruction getTransformedInstructionAt(int i) {
    return instructions.get(i);
  }
}
