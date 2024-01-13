package com.fpetrola.z80.opcodes.table;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.BIT;
import com.fpetrola.z80.instructions.LdOperation;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
import com.fpetrola.z80.instructions.RES;
import com.fpetrola.z80.instructions.SET;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.registers.RegisterName;

public class DDCBFDCBPrefixTableOpCodeGenerator extends TableOpCodeGenerator {

  private RegisterName ixy;

  public DDCBFDCBPrefixTableOpCodeGenerator(State state, SpyInterface opcodesSpy, RegisterName ixy, RegisterName ixyh, RegisterName ixyl, OpcodeReference a) {
    super(state, opcodesSpy, ixy, ixyh, ixyl, a);
    this.ixy = ixy;
  }

  protected OpCode getOpcode(int i) {
    switch (x) {
    case 0:
      return z != 6 ? new LdOperation(s, r[z], rot.get(y).apply(iRRn(ixy, true, 0))) : rot.get(y).apply(iRRn(ixy, true, 0));
    case 1:
      return new BIT(s, iRRn(ixy, true, 0), y, -2);
    case 2:
      return z != 6 ? new LdOperation(s, r[z], new RES(s, iRRn(ixy, true, 0), y, -2)) : new RES(s, iRRn(ixy, true, 0), y, -2);
    case 3:
      return z != 6 ? new LdOperation(s, r[z], new SET(s, iRRn(ixy, true, 0), y, -2)) : new SET(s, iRRn(ixy, true, 0), y, -2);
    }
    return null;
  }
}
