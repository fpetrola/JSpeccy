package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.registers.RegisterName.H;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.IX;
import static com.fpetrola.z80.registers.RegisterName.IXH;
import static com.fpetrola.z80.registers.RegisterName.IXL;
import static com.fpetrola.z80.registers.RegisterName.IY;
import static com.fpetrola.z80.registers.RegisterName.IYH;
import static com.fpetrola.z80.registers.RegisterName.IYL;
import static com.fpetrola.z80.registers.RegisterName.L;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.decoder.DefaultFetchNextOpcodeInstruction;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.models.OpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.spy.SpyInterface;
import com.fpetrola.z80.registers.RegisterName;

public class TableBasedOpCodeDecoder implements OpCodeDecoder {
  Instruction[] opcodes = new Instruction[0x100];

  public TableBasedOpCodeDecoder(State s, SpyInterface spy) {

    OpcodeTargets opcodeTargets = new OpcodeTargets(s, spy);
    OpcodeReference a = opcodeTargets.iRR(HL);
    Instruction edOpcode = new DefaultFetchNextOpcodeInstruction(s, new EDPrefixTableOpCodeGenerator(s, spy, a).getOpcodesTable(), 1, "ED", spy);
    Instruction cbOpcode = new DefaultFetchNextOpcodeInstruction(s, new CBPrefixTableOpCodeGenerator(s, spy, a).getOpcodesTable(), 1, "CB", spy);
    Instruction ddOpcode = new DefaultFetchNextOpcodeInstruction(s, fillDDFD(s, spy, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 2)), 1, "DD", spy);
    Instruction fdOpcode = new DefaultFetchNextOpcodeInstruction(s, fillDDFD(s, spy, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 2)), 1, "FD", spy);
    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(1, s, spy, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a);
    opcodes = unprefixedTableOpCodeGenerator.getOpcodesTable();
  }

  private Instruction[] fillDDFD(State s, SpyInterface spy, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a) {
    Instruction cbOpcode = new DefaultFetchNextOpcodeInstruction(s, new DDCBFDCBPrefixTableOpCodeGenerator(s, spy, registerName, highRegisterName, lowRegisterName, a).getOpcodesTable(), 2, "DDFDCB", spy);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new IndexerRegisterTableOpCodeGenerator(s, spy, cbOpcode, new Nop(s), new Nop(s), new Nop(s), registerName, highRegisterName, lowRegisterName, a, lowRegisterName, highRegisterName, registerName);
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public Instruction[] getOpcodeLookupTable() {
    return opcodes;
  }
}
