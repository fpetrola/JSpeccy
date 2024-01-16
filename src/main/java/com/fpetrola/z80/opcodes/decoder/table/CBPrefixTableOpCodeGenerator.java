package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.BIT;
import com.fpetrola.z80.instructions.RES;
import com.fpetrola.z80.instructions.SET;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.spy.SpyInterface;

import static com.fpetrola.z80.registers.RegisterName.*;

public class CBPrefixTableOpCodeGenerator extends TableOpCodeGenerator {

  public CBPrefixTableOpCodeGenerator(State state, SpyInterface opcodesSpy, OpcodeReference a) {
    super(state, opcodesSpy, HL, H, L, a);
  }

  protected Instruction getOpcode(int i) {
    return select(rot.get(y).apply(r[z]), new BIT(s, r[z], y, 0), new RES(s, r[z], y, 0), new SET(s, r[z], y, 0)).get(x);
  }
}
