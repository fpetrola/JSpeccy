package com.fpetrola.z80.opcodes.table;

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
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
import com.fpetrola.z80.instructions.OpcodeTargets;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.opcodes.FlipOpcodeImpl;
import com.fpetrola.z80.opcodes.OpCodeDecoder;
import com.fpetrola.z80.registers.RegisterName;

public class TableBasedOpCodeDecoder implements OpCodeDecoder {
  OpCode[] opcodes = new OpCode[0x100];

  public TableBasedOpCodeDecoder(State s, SpyInterface spy) {

    OpcodeTargets opcodeTargets = new OpcodeTargets(s, spy);
    OpcodeReference a = opcodeTargets.iRR(HL);
    OpCode edOpcode = new FlipOpcodeImpl(s, new EDPrefixTableOpCodeGenerator(s, spy, a).getOpcodesTable(), 1, "ED", spy);
    OpCode cbOpcode = new FlipOpcodeImpl(s, new CBPrefixTableOpCodeGenerator(s, spy, a).getOpcodesTable(), 1, "CB", spy);
    OpCode ddOpcode = new FlipOpcodeImpl(s, fillDDFD(s, spy, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 0)), 1, "DD", spy);
    OpCode fdOpcode = new FlipOpcodeImpl(s, fillDDFD(s, spy, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 0)), 1, "FD", spy);
    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(s, spy, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a);
    opcodes = unprefixedTableOpCodeGenerator.getOpcodesTable();
  }

  private OpCode[] fillDDFD(State s, SpyInterface spy, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a) {
    OpCode cbOpcode = new FlipOpcodeImpl(s, new DDCBFDCBPrefixTableOpCodeGenerator(s, spy, registerName, highRegisterName, lowRegisterName, a).getOpcodesTable(), 2, "DDFDCB", spy);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new IndexerRegisterTableOpCodeGenerator(s, spy, cbOpcode, new Nop(s), new Nop(s), new Nop(s), registerName, highRegisterName, lowRegisterName, a, lowRegisterName, highRegisterName, registerName);
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public OpCode[] getOpcodeLookupTable() {
    return opcodes;
  }
}
