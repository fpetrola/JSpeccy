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

import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.DefaultFetchNextOpcodeInstruction;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class TableBasedOpCodeDecoder<T> implements OpCodeDecoder {
  Instruction[] opcodes = new Instruction[0x100];

  public TableBasedOpCodeDecoder(State s, InstructionSpy spy, OpcodeConditions opcodeConditions) {

    OpcodeTargets opcodeTargets = new OpcodeTargets(s);
    OpcodeReference a = opcodeTargets.iRR(HL);
    Instruction<T> edOpcode = new DefaultFetchNextOpcodeInstruction(s, new EDPrefixTableOpCodeGenerator(s, spy, a, opcodeConditions).getOpcodesTable(), 1, "ED", spy);
    Instruction<T> cbOpcode = new DefaultFetchNextOpcodeInstruction(s, new CBPrefixTableOpCodeGenerator(s, spy, a, opcodeConditions).getOpcodesTable(), 1, "CB", spy);
    Instruction<T> ddOpcode = new DefaultFetchNextOpcodeInstruction(s, fillDDFD(s, spy, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 2), opcodeConditions), 1, "DD", spy);
    Instruction<T> fdOpcode = new DefaultFetchNextOpcodeInstruction(s, fillDDFD(s, spy, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 2), opcodeConditions), 1, "FD", spy);
    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(1, s, spy, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a, opcodeConditions);
    opcodes = unprefixedTableOpCodeGenerator.getOpcodesTable();
  }

  private Instruction<T>[] fillDDFD(State s, InstructionSpy spy, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a, OpcodeConditions opcodeConditions) {
    Instruction<T> cbOpcode = new DefaultFetchNextOpcodeInstruction(s, new DDCBFDCBPrefixTableOpCodeGenerator(s, spy, registerName, highRegisterName, lowRegisterName, a, opcodeConditions).getOpcodesTable(), 2, "DDFDCB", spy);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new IndexerRegisterTableOpCodeGenerator(s, spy, cbOpcode, new Nop(s), new Nop(s), new Nop(s), registerName, highRegisterName, lowRegisterName, a, lowRegisterName, highRegisterName, registerName, opcodeConditions);
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public Instruction<T>[] getOpcodeLookupTable() {
    return opcodes;
  }
}
