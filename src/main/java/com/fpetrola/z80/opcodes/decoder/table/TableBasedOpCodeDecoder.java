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

import com.fpetrola.z80.instructions.InstructionFactory;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.registers.RegisterName;

public class TableBasedOpCodeDecoder<T> implements OpCodeDecoder {
  Instruction[] opcodes = new Instruction[0x100];

  public TableBasedOpCodeDecoder(State s, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {

    OpcodeTargets opcodeTargets = new OpcodeTargets(s);
    OpcodeReference a = opcodeTargets.iRR(HL);
    Instruction<T> edOpcode = fetchInstructionFactory.createFetchInstruction(new EDPrefixTableOpCodeGenerator(s, a, opcodeConditions).getOpcodesTable(), "ED", 1);
    Instruction<T> cbOpcode = fetchInstructionFactory.createFetchInstruction(new CBPrefixTableOpCodeGenerator(s, a, opcodeConditions).getOpcodesTable(), "CB", 1);
    Instruction<T> ddOpcode = fetchInstructionFactory.createFetchInstruction(fillDDFD(s, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 2), opcodeConditions, fetchInstructionFactory), "DD", 1);
    Instruction<T> fdOpcode = fetchInstructionFactory.createFetchInstruction(fillDDFD(s, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 2), opcodeConditions, fetchInstructionFactory), "FD", 1);
    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(1, s, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a, opcodeConditions);
    opcodes = unprefixedTableOpCodeGenerator.getOpcodesTable();
  }

  private Instruction<T>[] fillDDFD(State s, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchNextOpcodeInstructionFactory) {
    Instruction<T> cbOpcode = fetchNextOpcodeInstructionFactory.createFetchInstruction(new DDCBFDCBPrefixTableOpCodeGenerator(s, registerName, highRegisterName, lowRegisterName, a, opcodeConditions).getOpcodesTable(), "DDFDCB", 2);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new IndexerRegisterTableOpCodeGenerator(s, cbOpcode, InstructionFactory.createNop(), InstructionFactory.createNop(), InstructionFactory.createNop(), registerName, highRegisterName, lowRegisterName, a, lowRegisterName, highRegisterName, registerName, opcodeConditions);
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public Instruction<T>[] getOpcodeLookupTable() {
    return opcodes;
  }
}
