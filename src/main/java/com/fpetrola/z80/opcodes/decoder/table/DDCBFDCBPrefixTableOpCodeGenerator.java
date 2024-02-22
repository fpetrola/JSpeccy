package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;

public class DDCBFDCBPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  private RegisterName ixy;

  public DDCBFDCBPrefixTableOpCodeGenerator(State state, RegisterName ixy, RegisterName ixyh, RegisterName ixyl, OpcodeReference a, OpcodeConditions opcodeConditions, InstructionFactory instructionFactory) {
    super(state, ixy, ixyh, ixyl, a, opcodeConditions, instructionFactory);
    this.ixy = ixy;
  }

  protected Instruction<T> getOpcode() {
    switch (x) {
    case 0:
      return z != 6 ? i.LdOperation(r[z], rot.get(y).create(iRRn(ixy, true, 2), 1)) : rot.get(y).create(iRRn(ixy, true, 2), 1);
    case 1:
      return i.BIT(iRRn(ixy, true, 2), y, 1);
    case 2:
      return z != 6 ? i.LdOperation(r[z], i.RES(iRRn(ixy, true, 2), y, 1)) : i.RES(iRRn(ixy, true, 2), y, 1);
    case 3:
      return z != 6 ? i.LdOperation(r[z], i.SET(iRRn(ixy, true, 2), y, 1)) : i.SET(iRRn(ixy, true, 2), y, 1);
    }
    return null;
  }
}
