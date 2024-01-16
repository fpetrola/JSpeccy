package com.fpetrola.z80.opcodes;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.AbstractOpCode;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class FlipOpcodeImpl extends AbstractOpCode implements FlipOpcode {

  private OpCode[] table;
  private int incPc;
  private String name;
  private SpyInterface spy;
  private Register registerR;

  public FlipOpcodeImpl(State state, OpCode[] table, int incPc, String name, SpyInterface spy) {
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
    OpCode opCode = findNextOpcode();
    opCode.execute();
    return 4;
  }

  private OpCode findNextOpcode() {
    int opcodeInt = state.getMemory().read(pc.read() + incPc - 1 + length);
    OpCode opCode = table[opcodeInt];
    spy.flipOpcode(opCode, opcodeInt);
    return opCode;
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

  public OpCode[] getTable() {
    return table;
  }
  
  public OpCode getInstruction() {
    return findNextOpcode().getInstruction();
  }
}