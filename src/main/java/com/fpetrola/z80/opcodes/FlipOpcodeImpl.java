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
        table[i].incrementLength();
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
    pc.increment(getIncPc() - 1);

    int opcodeAddress = pc.read();
    int opcodeInt = state.getMemory().read(opcodeAddress);
    pc.increment(1);
    OpCode opCode = table[opcodeInt];
    opCode.setPC(getPC());
    spy.flipOpcode(opCode, opcodeInt);
    return opCode;
  }

  public String toString() {
    int j = getPC().read();
    OpCode opCode = findNextOpcode();
    String string = opCode.toString();
    getPC().write(j);
    return string;
  }

  public int getLength() {
    int j = getPC().read();
    OpCode opCode = findNextOpcode();
    int string = opCode.getLength();
    getPC().write(j);
    return string;
  }

  public int getIncPc() {
    return incPc;
  }

  public OpCode[] getTable() {
    return table;
  }
}