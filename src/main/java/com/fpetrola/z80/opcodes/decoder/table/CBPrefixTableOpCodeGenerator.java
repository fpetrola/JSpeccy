package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.InstructionFactory;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

import static com.fpetrola.z80.registers.RegisterName.*;

public class CBPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  public CBPrefixTableOpCodeGenerator(State state, OpcodeReference a, OpcodeConditions opc1) {
    super(state, HL, H, L, a, opc1);
  }

  protected Instruction<T> getOpcode(int i) {
    return select(rot.get(y).create(r[z], 0), InstructionFactory.createBIT(r[z], y, 0), InstructionFactory.createRES(r[z], y, 0), InstructionFactory.createSET(r[z], y, 0)).get(x);
  }
}
