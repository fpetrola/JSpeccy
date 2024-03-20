package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.OpCodeDecoder;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.registers.RegisterName;

import static com.fpetrola.z80.registers.RegisterName.*;

public class TableBasedOpCodeDecoder<T> implements OpCodeDecoder {
  Instruction[] opcodes = new Instruction[0x100];
  InstructionFactory instructionFactory;

  public TableBasedOpCodeDecoder(State state, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory) {
    instructionFactory = new InstructionFactory(state);
    OpcodeTargets opcodeTargets = new OpcodeTargets(state);
    OpcodeReference a = opcodeTargets.iRR(HL);
    Instruction<T> edOpcode = fetchInstructionFactory.createFetchInstruction(new EDPrefixTableOpCodeGenerator(state, a, opcodeConditions, instructionFactory).getOpcodesTable(), "ED", 1);
    Instruction<T> cbOpcode = fetchInstructionFactory.createFetchInstruction(new CBPrefixTableOpCodeGenerator(state, a, opcodeConditions, this.instructionFactory).getOpcodesTable(), "CB", 1);
    Instruction<T> ddOpcode = fetchInstructionFactory.createFetchInstruction(fillDDFD(state, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 2), opcodeConditions, fetchInstructionFactory), "DD", 1);
    Instruction<T> fdOpcode = fetchInstructionFactory.createFetchInstruction(fillDDFD(state, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 2), opcodeConditions, fetchInstructionFactory), "FD", 1);
    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(1, state, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a, opcodeConditions, this.instructionFactory);
    opcodes = unprefixedTableOpCodeGenerator.getOpcodesTable();
  }

  private Instruction<T>[] fillDDFD(State s, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchNextOpcodeInstructionFactory) {
    Instruction<T> cbOpcode = fetchNextOpcodeInstructionFactory.createFetchInstruction(new DDCBFDCBPrefixTableOpCodeGenerator(s, registerName, highRegisterName, lowRegisterName, a, opcodeConditions, instructionFactory).getOpcodesTable(), "DDFDCB", 2);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new IndexerRegisterTableOpCodeGenerator(s, cbOpcode, this.instructionFactory.Nop(), this.instructionFactory.Nop(), this.instructionFactory.Nop(), registerName, highRegisterName, lowRegisterName, a, lowRegisterName, highRegisterName, registerName, opcodeConditions, instructionFactory);
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public Instruction<T>[] getOpcodeLookupTable() {
    return opcodes;
  }
}
