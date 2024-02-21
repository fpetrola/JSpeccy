package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

public class SpyInstructionExecutor<T extends WordNumber> implements InstructionExecutor<T> {
  private InstructionSpy spy;

  public SpyInstructionExecutor(InstructionSpy spy) {
    this.spy = spy;
  }

  @Override
  public void execute(Instruction<T> instruction, int opcodeInt, T pcValue) {
    spy.start(instruction, opcodeInt, pcValue);
//    if (pcValue.intValue() == 38541)
//      System.out.println("BB");
//    if (pcValue.intValue() == 38538)
//      System.out.println("CC");
    instruction.execute();
    spy.end();
  }
}
