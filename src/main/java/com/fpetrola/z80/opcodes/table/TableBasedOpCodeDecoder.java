package com.fpetrola.z80.opcodes.table;

import static com.fpetrola.z80.registers.RegisterName.*;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.IndirectMemory8BitReference;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
import com.fpetrola.z80.instructions.OpcodeTargets;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.opcodes.FlipOpcodeImpl;
import com.fpetrola.z80.opcodes.OpCodeDecoder;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class TableBasedOpCodeDecoder implements OpCodeDecoder {
  private OpCode[] unprefixedOpcodes = new OpCode[0x100];
  private OpCode[] opcodeCBLookupTable = new OpCode[0x100];
  private OpCode[] opcodeDDLookupTable = new OpCode[0x100];
  private OpCode[] opcodeEDLookupTable = new OpCode[0x100];
  private OpCode[] opcodeFDLookupTable = new OpCode[0x100];

  public TableBasedOpCodeDecoder(State s, SpyInterface spy) {

    OpcodeTargets opcodeTargets = new OpcodeTargets(s, spy);
    OpcodeReference a = opcodeTargets.iRR(HL);
    new CBPrefixTableOpCodeGenerator(s, spy, a).fillOpcodeTable(opcodeCBLookupTable);
    new EDPrefixTableOpCodeGenerator(s, spy, a).fillOpcodeTable(opcodeEDLookupTable);

    fillDDFD(s, spy, opcodeDDLookupTable, IX, IXH, IXL, opcodeTargets.iRRn(IX, false, 0));
    fillDDFD(s, spy, opcodeFDLookupTable, IY, IYH, IYL, opcodeTargets.iRRn(IY, false, 0));

    OpCode ddOpcode = new FlipOpcodeImpl(s, opcodeDDLookupTable, 1, "DD", spy);
    OpCode edOpcode = new FlipOpcodeImpl(s, opcodeEDLookupTable, 1, "ED", spy);
    OpCode fdOpcode = new FlipOpcodeImpl(s, opcodeFDLookupTable, 1, "FD", spy);
    OpCode cbOpcode = new FlipOpcodeImpl(s, opcodeCBLookupTable, 1, "CB", spy);

    UnprefixedTableOpCodeGenerator unprefixedTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(s, spy, cbOpcode, ddOpcode, edOpcode, fdOpcode, HL, H, L, a);
    unprefixedTableOpCodeGenerator.fillOpcodeTable(unprefixedOpcodes);
  }

  private OpCode fillDDFD(State s, SpyInterface spy, OpCode[] opcodes, RegisterName registerName, final RegisterName highRegisterName, final RegisterName lowRegisterName, OpcodeReference a) {
    OpCode[] CBTable = new OpCode[0x100];
    new DDCBFDCBPrefixTableOpCodeGenerator(s, spy, registerName, highRegisterName, lowRegisterName, a).fillOpcodeTable(CBTable);
    OpCode cbOpcode = new FlipOpcodeImpl(s, CBTable, 2, "DDFDCB", spy);
    UnprefixedTableOpCodeGenerator ddTableOpCodeGenerator = new UnprefixedTableOpCodeGenerator(s, spy, cbOpcode, new Nop(s), new Nop(s), new Nop(s), registerName, highRegisterName, lowRegisterName, a) {
      protected Ld createLd() {
        OpcodeReference target = r[y];
        OpcodeReference source = r[z];

        if (isHL(source) || isHL(target)) {
          source = replaceLowHigh(source);
          target = replaceLowHigh(target);
        }
        return new Ld(s, target, source);
      }

      private OpcodeReference replaceLowHigh(OpcodeReference source) {
        if (source instanceof Register) {
          Register register = (Register) source;
          if (register.equals(r(lowRegisterName))) {
            return r(L);
          } else if (register.equals(r(highRegisterName))) {
            return r(H);
          }
        }

        return source;
      }

      private boolean isHL(OpcodeReference source) {
        if (source instanceof MemoryPlusRegister8BitReference) {
          MemoryPlusRegister8BitReference memoryPlusRegister8BitReference = (MemoryPlusRegister8BitReference) source;
          return true;
        } else
          return false;
      }

      protected Ld createLd1() {
        OpcodeReference target = r[y];
        if (isHL(target)) {
          return new Ld(s, iRRn(registerName, false, -1), n(1));
        } else
          return new Ld(s, target, n());
      }
    };
    ddTableOpCodeGenerator.fillOpcodeTable(opcodes);
    return cbOpcode;
  }

  public OpCode[] getOpcodeLookupTable() {
    return unprefixedOpcodes;
  }
}
