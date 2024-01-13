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
    OpCode[] CBTable = new DDCBFDCBPrefixTableOpCodeGenerator(s, spy, registerName, highRegisterName, lowRegisterName, a).getOpcodesTable();
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
          if (register.equals(r(lowRegisterName)))
            return r(L);
          else if (register.equals(r(highRegisterName)))
            return r(H);
        }

        return source;
      }

      private boolean isHL(OpcodeReference source) {
        return source instanceof MemoryPlusRegister8BitReference;
      }

      protected Ld createLd1() {
        OpcodeReference target = r[y];
        if (isHL(target))
          return new Ld(s, iRRn(registerName, false, -1), n(1));
        else
          return new Ld(s, target, n());
      }
    };
    return ddTableOpCodeGenerator.getOpcodesTable();
  }

  public OpCode[] getOpcodeLookupTable() {
    return opcodes;
  }
}
