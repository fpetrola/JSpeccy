package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.BIT;
import com.fpetrola.z80.instructions.LdOperation;
import com.fpetrola.z80.instructions.RES;
import com.fpetrola.z80.instructions.SET;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class DDCBFDCBPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  private RegisterName ixy;

  public DDCBFDCBPrefixTableOpCodeGenerator(State state, InstructionSpy opcodesSpy, RegisterName ixy, RegisterName ixyh, RegisterName ixyl, OpcodeReference a, OpcodeConditions opcodeConditions) {
    super(state, opcodesSpy, ixy, ixyh, ixyl, a, opcodeConditions);
    this.ixy = ixy;
  }

  protected Instruction<T> getOpcode(int i) {
    switch (x) {
    case 0:
      return z != 6 ? new LdOperation(s, r[z], rot.get(y).create(iRRn(ixy, true, 2), 1)) : rot.get(y).create(iRRn(ixy, true, 2), 1);
    case 1:
      return new BIT(s, iRRn(ixy, true, 2), y, 1);
    case 2:
      return z != 6 ? new LdOperation(s, r[z], new RES(s, iRRn(ixy, true, 2), y, 1)) : new RES(s, iRRn(ixy, true, 2), y, 1);
    case 3:
      return z != 6 ? new LdOperation(s, r[z], new SET(s, iRRn(ixy, true, 2), y, 1)) : new SET(s, iRRn(ixy, true, 2), y, 1);
    }
    return null;
  }
}
