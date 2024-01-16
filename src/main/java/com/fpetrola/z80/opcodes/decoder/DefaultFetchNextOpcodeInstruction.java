package com.fpetrola.z80.opcodes.decoder;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.Instruction;
import com.fpetrola.z80.opcodes.spy.SpyInterface;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class DefaultFetchNextOpcodeInstruction extends AbstractInstruction implements FetchNextOpcodeInstruction {

  private Instruction[] table;
  private int incPc;
  private String name;
  private SpyInterface spy;
  private Register registerR;

  public DefaultFetchNextOpcodeInstruction(State state, Instruction[] table, int incPc, String name, SpyInterface spy) {
    super(state);
    this.table = table;
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null)
        table[i].incrementLengthBy(1);
    }
    this.incPc = incPc;
    this.name = name;
    this.spy = spy;
    this.registerR = state.getRegister(RegisterName.R);
  }

  public int execute() {
    registerR.increment(1);
    Instruction instruction = findNextOpcode();
    instruction.execute();
    return 4;
  }

  private Instruction findNextOpcode() {
    int opcodeInt = state.getMemory().read(pc.read() + incPc - 1 + length);
    Instruction instruction = table[opcodeInt];
    spy.flipOpcode(instruction, opcodeInt);
    return instruction;
  }

  public String toString() {
    return findNextOpcode().toString();
  }

  public int getLength() {
    return findNextOpcode().getLength();
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