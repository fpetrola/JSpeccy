package com.fpetrola.z80.opcodes.decoder;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class DefaultFetchNextOpcodeInstruction<T extends WordNumber> extends AbstractInstruction<T> implements FetchNextOpcodeInstruction<T> {

  private Instruction[] table;
  private int incPc;
  private String name;
  private InstructionSpy spy;
  private Register registerR;

  public DefaultFetchNextOpcodeInstruction(State state, Instruction[] table, int incPc, String name, InstructionSpy spy) {
    super(state);
    this.table = table;
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null)
        table[i].setLength(table[i].getLength() + 1);
    }
    this.incPc = incPc;
    this.name = name;
    this.spy = spy;
    this.registerR = state.getRegister(RegisterName.R);
  }

  public int execute() {
    spy.pause();
    registerR.increment();
    Instruction<T> instruction = findNextOpcode();
    spy.doContinue();
    instruction.execute();
    return 4;
  }

  private Instruction findNextOpcode() {
    spy.pause();
    int opcodeInt = state.getMemory().read(pc.read().plus(incPc - 1 + length)).intValue();
    Instruction instruction = table[opcodeInt];
    spy.flipOpcode(instruction, opcodeInt);
    spy.doContinue();
    return instruction;
  }

  public String toString() {
    return findNextOpcode().toString();
  }

  public int getIncPc() {
    return incPc;
  }

  public Instruction[] getTable() {
    return table;
  }

  public Instruction getBaseInstruction() {
    return findNextOpcode().getBaseInstruction();
  }
}