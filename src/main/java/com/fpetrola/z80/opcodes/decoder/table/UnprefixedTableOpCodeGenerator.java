package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.registers.RegisterName.*;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;

public class UnprefixedTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {
  private Instruction<T> cbOpcode;
  private Instruction<T> ddOpcode;
  private Instruction<T> edOpcode;
  private Instruction<T> fdOpcode;
  private int delta;

  public UnprefixedTableOpCodeGenerator(int delta, State state, Instruction<T> cbOpcode, Instruction<T> ddOpcode, Instruction<T> edOpcode, Instruction<T> fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, OpcodeConditions opc1, InstructionFactory instructionFactory) {
    super(state, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference, opc1, instructionFactory);
    this.delta = delta;
    this.cbOpcode = cbOpcode;
    this.ddOpcode = ddOpcode;
    this.edOpcode = edOpcode;
    this.fdOpcode = fdOpcode;
  }

  protected Instruction getOpcode() {
    OpcodeReference hlRegister = r(main16BitRegister);
    switch (x) {
    case 0:
      switch (z) {
      case 0:
        switch (y) {
        case 0:
          return i.Nop();
        case 1:
          return i.Ex(r(AF), r(AFx));
        case 2:
          return i.DJNZ(n());
        case 3:
          return i.JR(n(), opc.t());
        case 4, 5, 6, 7:
          return i.JR(n(), cc[y - 4]);
        }
      case 1:
        return select(i.Ld(rp[p], nn()), i.Add16(hlRegister, rp[p])).get(q);
      case 2:
        switch (q) {
        case 0:
          return select(i.Ld(iRR(BC), r(A)), i.Ld(iRR(DE), r(A)), i.Ld(iinn(), hlRegister), i.Ld(inn(), r(A))).get(p);
        case 1:
          return select(i.Ld(r(A), iRR(BC)), i.Ld(r(A), iRR(DE)), i.Ld(hlRegister, iinn()), i.Ld(r(A), inn())).get(p);
        }
      case 3:
        return select(i.Inc16(rp[p]), i.Dec16(rp[p])).get(q);
      case 4:
        return i.Inc(r[y]);
      case 5:
        return i.Dec(r[y]);
      case 6:
        return createLd1();
      case 7:
        return select(i.RLCA(r(A)), i.RRCA(r(A)), i.RLA(r(A)), i.RRA(r(A)), i.DAA(r(A)), i.CPL(r(A)), i.SCF(), i.CCF()).get(y);
      }
      return null;
    case 1:
      if (z == 6 && y == 6)
        return i.Halt();
      else
        return createLd();
    case 2:
      return alu.get(y).apply(r[z]);
    case 3:
      switch (z) {
      case 0:
        return i.Ret(cc[y]);
      case 1:
        switch (q) {
        case 0:
          return i.Pop(rp2[p]);
        case 1:
          return select(i.Ret(opc.t()), i.Exx(), i.JP(hlRegister, opc.t()), i.Ld(r(SP), hlRegister)).get(p);
        }
      case 2:
        return i.JP(nn(), cc[y]);
      case 3:
        return select(i.JP(nn(), opc.t()), cbOpcode, i.Out(n(), r(A)), i.In(r(A), n()), i.Ex(iiRR(SP), hlRegister), i.Ex(r(DE), r(HL)), i.DI(), i.EI()).get(y);
      case 4:
        return i.Call(nn(), cc[y]);
      case 5:
        switch (q) {
        case 0:
          return i.Push(rp2[p]);
        case 1:
          return select(i.Call(nn(), opc.t()), ddOpcode, edOpcode, fdOpcode).get(p);
        }
      case 6:
        return alu.get(y).apply(n());
      case 7:
        return i.RST(y * 8);
      }
      return null;
    }
    return null;
  }

  private OpcodeReference<WordNumber> inn() {
    return inn(delta);
  }

  private ImmutableOpcodeReference nn() {
    return nn(delta);
  }

  private OpcodeReference<WordNumber> iinn() {
    return iinn(delta);
  }

  private ImmutableOpcodeReference n() {
    return n(delta);
  }

  protected Ld createLd1() {
    return i.Ld(r[y], n());
  }

  protected Ld createLd() {
    return i.Ld(r[y], r[z]);
  }
}
