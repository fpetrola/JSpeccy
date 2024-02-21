package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.instructions.InstructionFactory.*;
import static com.fpetrola.z80.registers.RegisterName.*;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class UnprefixedTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {
  private Instruction<T> cbOpcode;
  private Instruction<T> ddOpcode;
  private Instruction<T> edOpcode;
  private Instruction<T> fdOpcode;
  private int delta;

  public UnprefixedTableOpCodeGenerator(int delta, State state, Instruction<T> cbOpcode, Instruction<T> ddOpcode, Instruction<T> edOpcode, Instruction<T> fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, OpcodeConditions opc1) {
    super(state, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference, opc1);
    this.delta = delta;
    this.cbOpcode = cbOpcode;
    this.ddOpcode = ddOpcode;
    this.edOpcode = edOpcode;
    this.fdOpcode = fdOpcode;
  }

  protected Instruction<T> getOpcode(int i) {
    OpcodeReference hlRegister = r(main16BitRegister);
    switch (x) {
    case 0:
      switch (z) {
      case 0:
        switch (y) {
        case 0:
          return new Nop(s);
        case 1:
          return new Ex(s, r(AF), r(AFx));
        case 2:
          return createDJNZ(n(delta));
        case 3:
          return createJR(n(delta), opc.t());
        case 4, 5, 6, 7:
          return createJR(n(delta), cc[y - 4]);
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
        return select(new RLCA(s, r(A)), new RRCA(s, r(A)), new RLA(s, r(A)), new RRA(s, r(A)), new DAA(s, r(A)), new CPL(s, r(A)), new SCF(s), createCCF()).get(y);
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
          return select(new Ret(s, opc.t()), new Exx(s), createJP(hlRegister, opc.t()), new Ld(s, r(SP), hlRegister)).get(p);
        }
      case 2:
        return createJP(nn(delta), cc[y]);
      case 3:
        return select(createJP(nn(delta), opc.t()), cbOpcode, new Out(s, n(delta), r(A)), new In(s, r(A), n(delta)), new Ex(s, iiRR(SP), hlRegister), new Ex(s, r(DE), r(HL)), new DI(s), new EI(s)).get(y);
      case 4:
        return createCall(nn(delta), cc[y]);
      case 5:
        switch (q) {
        case 0:
          return new Push(s, rp2[p]);
        case 1:
          return select(createCall(nn(delta), opc.t()), ddOpcode, edOpcode, fdOpcode).get(p);
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
