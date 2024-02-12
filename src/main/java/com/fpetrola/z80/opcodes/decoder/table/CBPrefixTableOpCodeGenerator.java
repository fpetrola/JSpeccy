package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.BIT;
import com.fpetrola.z80.instructions.RES;
import com.fpetrola.z80.instructions.SET;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.spy.InstructionSpy;

import static com.fpetrola.z80.registers.RegisterName.*;

public class CBPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  public CBPrefixTableOpCodeGenerator(State state, InstructionSpy opcodesSpy, OpcodeReference a, OpcodeConditions opc1) {
    super(state, opcodesSpy, HL, H, L, a, opc1);
  }

  protected Instruction<T> getOpcode(int i) {
    return select(rot.get(y).create(r[z], 0), new BIT(s, r[z], y, 0), new RES(s, r[z], y, 0), new SET(s, r[z], y, 0)).get(x);
  }
}
