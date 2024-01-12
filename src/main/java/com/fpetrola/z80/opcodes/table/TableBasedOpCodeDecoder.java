package com.fpetrola.z80.opcodes.table;

import static com.fpetrola.z80.registers.RegisterName.IX;
import static com.fpetrola.z80.registers.RegisterName.IY;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.opcodes.OpCodeDecoder;

public class TableBasedOpCodeDecoder implements OpCodeDecoder {
  private OpCode[] unprefixedOpcodes = new OpCode[0x100];
  private OpCode[] opcodeCBLookupTable = new OpCode[0x100];
  private OpCode[] opcodeDDLookupTable = new OpCode[0x100];
  private OpCode[] opcodeEDLookupTable = new OpCode[0x100];
  private OpCode[] opcodeFDLookupTable = new OpCode[0x100];

  public TableBasedOpCodeDecoder(State state, SpyInterface spy) {

    new CBPrefixTableOpCodeGenerator(state, spy).fillOpcodeTable(opcodeCBLookupTable);
    new EDPrefixTableOpCodeGenerator(state, spy).fillOpcodeTable(opcodeEDLookupTable);
    new DDCBFDCBPrefixTableOpCodeGenerator(state, spy, IX).fillOpcodeTable(opcodeDDLookupTable);
    new DDCBFDCBPrefixTableOpCodeGenerator(state, spy, IY).fillOpcodeTable(opcodeFDLookupTable);

    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(state, spy, opcodeCBLookupTable, opcodeDDLookupTable, opcodeEDLookupTable, opcodeFDLookupTable);
    unprefixedTableOpCodeGenerator.fillOpcodeTable(unprefixedOpcodes);
  }

  public OpCode[] getOpcodeLookupTable() {
    return unprefixedOpcodes;
  }
}
