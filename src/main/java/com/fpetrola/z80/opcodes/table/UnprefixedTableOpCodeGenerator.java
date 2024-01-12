package com.fpetrola.z80.opcodes.table;

import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.AF;
import static com.fpetrola.z80.registers.RegisterName.BC;
import static com.fpetrola.z80.registers.RegisterName.DE;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.SP;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.Add16;
import com.fpetrola.z80.instructions.CCF;
import com.fpetrola.z80.instructions.CPL;
import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.DAA;
import com.fpetrola.z80.instructions.DI;
import com.fpetrola.z80.instructions.DJNZ;
import com.fpetrola.z80.instructions.Dec;
import com.fpetrola.z80.instructions.EI;
import com.fpetrola.z80.instructions.Ex;
import com.fpetrola.z80.instructions.Exx;
import com.fpetrola.z80.instructions.Halt;
import com.fpetrola.z80.instructions.In;
import com.fpetrola.z80.instructions.Inc;
import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.Out;
import com.fpetrola.z80.instructions.Pop;
import com.fpetrola.z80.instructions.Push;
import com.fpetrola.z80.instructions.RLA;
import com.fpetrola.z80.instructions.RLCA;
import com.fpetrola.z80.instructions.RRA;
import com.fpetrola.z80.instructions.RRCA;
import com.fpetrola.z80.instructions.RST;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.SCF;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.opcodes.FlipOpcodeImpl;

public class UnprefixedTableOpCodeGenerator extends TableOpCodeGenerator {
  private OpCode[] opcodeCBLookupTable;
  private OpCode[] opcodeDDLookupTable;
  private OpCode[] opcodeEDLookupTable;
  private OpCode[] opcodeFDLookupTable;

  public UnprefixedTableOpCodeGenerator(State state, SpyInterface opcodesSpy, OpCode[] opcodeCBLookupTable, OpCode[] opcodeDDLookupTable, OpCode[] opcodeEDLookupTable, OpCode[] opcodeFDLookupTable) {
    super(state, opcodesSpy);
    this.opcodeCBLookupTable = opcodeCBLookupTable;
    this.opcodeDDLookupTable = opcodeDDLookupTable;
    this.opcodeEDLookupTable = opcodeEDLookupTable;
    this.opcodeFDLookupTable = opcodeFDLookupTable;
  }

  protected OpCode getOpcode(int i) {
    switch (x) {
    case 0:
      switch (z) {
      case 0:
        return new OOSwitch(new Nop(s), new Ex(s, r(AF), _r(AF)), new DJNZ(s, n()), new JR(s, opc.t(), n()), new JR(s, cc[y - 4], n())).getCase(y);
      case 1:
        return new OOSwitch(new Ld(s, rp[p], nn()), new Add16(s, r(HL), rp[p])).getCase(q);
      case 2:
        switch (q) {
        case 0:
          return new OOSwitch(new Ld(s, iRR(BC), r(A)), new Ld(s, iRR(DE), r(A)), new Ld(s, iinn(), r(HL)), new Ld(s, iinn(), r(A))).getCase(p);
        case 1:
          return new OOSwitch(new Ld(s, r(A), iRR(BC)), new Ld(s, r(A), iRR(DE)), new Ld(s, r(HL), iinn()), new Ld(s, r(A), iinn())).getCase(p);
        }
      case 3:
        return new OOSwitch(new Inc(s, rp[p]), new Dec(s, rp[p])).getCase(q);
      case 4:
        return new Inc(s, r[y]);
      case 5:
        return new Dec(s, r[y]);
      case 6:
        return new Ld(s, r[y], n());
      case 7:
        return new OOSwitch(new RLCA(s, r(A)), new RRCA(s, r(A)), new RLA(s, r(A)), new RRA(s, r(A)), new DAA(s, r(A)), new CPL(s, r(A)), new SCF(s), new CCF(s)).getCase(y);
      }
      return null;
    case 1:
      switch (y) {
      case 6:
        return new Halt(s);
      default:
        return new Ld(s, r[y], r[z]);
      }
    case 2:
      return alu.get(y).apply(r[z]);
    case 3:
      switch (z) {
      case 0:
        return new Ret(s, cc[y]);
      case 1:
        switch (q) {
        case 0:
          return new Pop(s, rp2[p]);
        case 1:
          return new OOSwitch(new Ret(s, opc.t()), new Exx(s), new JP(s, opc.t(), r(HL)), new Ld(s, r(SP), r(HL))).getCase(p);
        }
      case 2:
        return new JP(s, cc[y], nn());
      case 3:
        return new OOSwitch(new JP(s, opc.t(), nn()), new FlipOpcodeImpl(s, this.opcodeCBLookupTable, 1, "CB", spy), new Out(s, n(), r(A)), new In(s, r(A), n()), new Ex(s, iiRR(SP), r(HL)), new Ex(s, r(DE), r(HL)), new DI(s), new EI(s)).getCase(y);
      case 4:
        return new Call(s, cc[y], nn());
      case 5:
        switch (q) {
        case 0:
          return new Push(s, rp2[p]);
        case 1:
          return new OOSwitch(new Call(s, opc.t(), nn()), new FlipOpcodeImpl(s, this.opcodeDDLookupTable, 1, "DD", spy), new FlipOpcodeImpl(s, this.opcodeEDLookupTable, 1, "ED", spy), new FlipOpcodeImpl(s, this.opcodeFDLookupTable, 1, "FD", spy)).getCase(p);
        }
      case 6:
        return alu.get(y).apply(n());
      case 7:
        return new RST(s, y * 8);
      }
      return null;
    }
    return null;
  }
}
