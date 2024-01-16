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
import com.fpetrola.z80.instructions.Dec16;
import com.fpetrola.z80.instructions.EI;
import com.fpetrola.z80.instructions.Ex;
import com.fpetrola.z80.instructions.Exx;
import com.fpetrola.z80.instructions.Halt;
import com.fpetrola.z80.instructions.In;
import com.fpetrola.z80.instructions.Inc;
import com.fpetrola.z80.instructions.Inc16;
import com.fpetrola.z80.instructions.JP;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
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
import com.fpetrola.z80.registers.RegisterName;

public class UnprefixedTableOpCodeGenerator extends TableOpCodeGenerator {
  private OpCode cbOpcode;
  private OpCode ddOpcode;
  private OpCode edOpcode;
  private OpCode fdOpcode;
  private int delta;

  public UnprefixedTableOpCodeGenerator(int delta, State state, SpyInterface opcodesSpy, OpCode cbOpcode, OpCode ddOpcode, OpCode edOpcode, OpCode fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference) {
    super(state, opcodesSpy, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference);
    this.delta = delta;
    this.cbOpcode = cbOpcode;
    this.ddOpcode = ddOpcode;
    this.edOpcode = edOpcode;
    this.fdOpcode = fdOpcode;
  }

  protected OpCode getOpcode(int i) {
    OpcodeReference hlRegister = r(main16BitRegister);
    switch (x) {
    case 0:
      switch (z) {
      case 0:
        switch (y) {
        case 0:
          return new Nop(s);
        case 1:
          return new Ex(s, r(AF), _r(AF));
        case 2:
          return new DJNZ(s, n(delta));
        case 3:
          return new JR(s, n(delta), opc.t());
        case 4, 5, 6, 7:
          return new JR(s, n(delta), cc[y - 4]);
        }
      case 1:
        return select(new Ld(s, rp[p], nn(delta)), new Add16(s, hlRegister, rp[p])).get(q);
      case 2:
        switch (q) {
        case 0:
          return select(new Ld(s, iRR(BC), r(A)), new Ld(s, iRR(DE), r(A)), new Ld(s, iinn(delta), hlRegister), new Ld(s, inn(delta), r(A))).get(p);
        case 1:
          return select(new Ld(s, r(A), iRR(BC)), new Ld(s, r(A), iRR(DE)), new Ld(s, hlRegister, iinn(delta)), new Ld(s, r(A), inn(delta))).get(p);
        }
      case 3:
        return select(new Inc16(s, rp[p]), new Dec16(s, rp[p])).get(q);
      case 4:
        return new Inc(s, r[y]);
      case 5:
        return new Dec(s, r[y]);
      case 6:
        return createLd1();
      case 7:
        return select(new RLCA(s, r(A)), new RRCA(s, r(A)), new RLA(s, r(A)), new RRA(s, r(A)), new DAA(s, r(A)), new CPL(s, r(A)), new SCF(s), new CCF(s)).get(y);
      }
      return null;
    case 1:
      if (z == 6 && y == 6)
        return new Halt(s);
      else
        return createLd();
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
          return select(new Ret(s, opc.t()), new Exx(s), new JP(s, hlRegister, opc.t()), new Ld(s, r(SP), hlRegister)).get(p);
        }
      case 2:
        return new JP(s, nn(delta), cc[y]);
      case 3:
        return select(new JP(s, nn(delta), opc.t()), cbOpcode, new Out(s, n(delta), r(A)), new In(s, r(A), n(delta)), new Ex(s, iiRR(SP), hlRegister), new Ex(s, r(DE), r(HL)), new DI(s), new EI(s)).get(y);
      case 4:
        return new Call(s, nn(delta), cc[y]);
      case 5:
        switch (q) {
        case 0:
          return new Push(s, rp2[p]);
        case 1:
          return select(new Call(s, nn(delta), opc.t()), ddOpcode, edOpcode, fdOpcode).get(p);
        }
      case 6:
        return alu.get(y).apply(n(delta));
      case 7:
        return new RST(s, y * 8);
      }
      return null;
    }
    return null;
  }

  protected Ld createLd1() {
    return new Ld(s, r[y], n(delta));
  }

  protected Ld createLd() {
    return new Ld(s, r[y], r[z]);
  }
}
