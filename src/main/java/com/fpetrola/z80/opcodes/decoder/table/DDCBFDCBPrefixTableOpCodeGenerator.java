package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
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
    Instruction result = null;
    switch (x) {
      case 0:
        result = z != 6 ? i.LdOperation(r[z], rot.get(y).create(iRRn(ixy, true, 2), 1)) : rot.get(y).create(iRRn(ixy, true, 2), 1);
        break;
      case 1:
        result = i.BIT(iRRn(ixy, true, 2), y);
        break;
      case 2:
        result = z != 6 ? i.LdOperation(r[z], i.RES(iRRn(ixy, true, 2), y)) : i.RES(iRRn(ixy, true, 2), y);
        break;
      case 3:
        result = z != 6 ? i.LdOperation(r[z], i.SET(iRRn(ixy, true, 2), y)) : i.SET(iRRn(ixy, true, 2), y);
    }
    ((AbstractInstruction) result).setLength(result.getLength() + 1);
    return result;
  }
}
