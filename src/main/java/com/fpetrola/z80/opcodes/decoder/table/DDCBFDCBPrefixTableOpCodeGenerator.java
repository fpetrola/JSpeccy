package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.BIT;
import com.fpetrola.z80.instructions.LdOperation;
import com.fpetrola.z80.instructions.RES;
import com.fpetrola.z80.instructions.SET;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class DDCBFDCBPrefixTableOpCodeGenerator extends TableOpCodeGenerator {

  private RegisterName ixy;

  public DDCBFDCBPrefixTableOpCodeGenerator(State state, InstructionSpy opcodesSpy, RegisterName ixy, RegisterName ixyh, RegisterName ixyl, OpcodeReference a) {
    super(state, opcodesSpy, ixy, ixyh, ixyl, a);
    this.ixy = ixy;
  }

  protected Instruction getOpcode(int i) {
    switch (x) {
    case 0:
      return z != 6 ? new LdOperation(s, r[z], rot.get(y).apply(iRRn(ixy, true, 2))) : rot.get(y).apply(iRRn(ixy, true, 2));
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
