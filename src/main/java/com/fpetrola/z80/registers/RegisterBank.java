package com.fpetrola.z80.registers;

import static com.fpetrola.z80.registers.RegisterName.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fpetrola.z80.registers.flag.FerFlagRegister;

public class RegisterBank {

  private RegisterPair af;
  private RegisterPair bc;
  private RegisterPair de;
  private RegisterPair hl;
  private RegisterPair _af;
  private RegisterPair _bc;
  private RegisterPair _de;
  private RegisterPair _hl;
  private RegisterPair ix;
  private RegisterPair iy;
  private Register pc;
  private Register sp;
  private RegisterPair ir;
  private Register memptr;

  public RegisterBank(RegisterPair af, RegisterPair bc, RegisterPair de, RegisterPair hl, RegisterPair _af, RegisterPair _bc, RegisterPair _de, RegisterPair _hl, Register pc, Register sp, RegisterPair ix, RegisterPair iy, RegisterPair ir, Register memptr) {
    this.af = af;
    this.bc = bc;
    this.de = de;
    this.hl = hl;
    this._af = _af;
    this._bc = _bc;
    this._de = _de;
    this._hl = _hl;
    this.pc = pc;
    this.sp = sp;
    this.ix = ix;
    this.iy = iy;
    this.ir = ir;
    this.memptr = memptr;
  }

  public RegisterBank() {
  }

  public static RegisterBank createSimpleBank() {
    return createBasicBank(new FerFlagRegister(F));
  }

  public static RegisterBank createNullBank() {
    return createBasicBank(new NullFlagRegister(F));
  }

  public static RegisterBank createBasicBank(Plain8BitRegister fRegister) {
    RegisterBank bank = new RegisterBank();
    bank.af = new Composed16BitRegister(AF, new Plain8BitRegister(A), fRegister);
    bank.bc = new Composed16BitRegister(BC, B, C);
    bank.de = new Composed16BitRegister(DE, D, E);
    bank.hl = new Composed16BitRegister(HL, H, L);
    bank._af = new Composed16BitRegister(AFx, Ax, Fx);
    bank._bc = new Composed16BitRegister(BCx, Bx, Cx);
    bank._de = new Composed16BitRegister(DEx, Dx, Ex);
    bank._hl = new Composed16BitRegister(HLx, Hx, Lx);
    bank.ix = new Composed16BitRegister(IX, IXH, IXL);
    bank.iy = new Composed16BitRegister(IY, IYH, IYL);
    bank.ir = new Composed16BitRegister(IR, I, R);
    bank.pc = new Plain16BitRegister(PC);
    bank.sp = new Plain16BitRegister(SP);
    bank.memptr = new Plain16BitRegister(MEMPTR);
    return bank;
  }

  public Register get(RegisterName name) {
    switch (name) {
    case A:
      return this.af.getHigh();
    case F:
      return this.af.getLow();
    case B:
      return this.bc.getHigh();
    case C:
      return this.bc.getLow();
    case D:
      return this.de.getHigh();
    case E:
      return this.de.getLow();
    case H:
      return this.hl.getHigh();
    case L:
      return this.hl.getLow();
    case IXH:
      return this.ix.getHigh();
    case IXL:
      return this.ix.getLow();
    case IYH:
      return this.iy.getHigh();
    case IYL:
      return this.iy.getLow();
    case AF:
      return this.af;
    case BC:
      return this.bc;
    case DE:
      return this.de;
    case HL:
      return this.hl;
    case PC:
      return this.pc;
    case SP:
      return this.sp;
    case IX:
      return this.ix;
    case IY:
      return this.iy;
    case I:
      return this.ir.getHigh();
    case R:
      return this.ir.getLow();
    case IR:
      return this.ir;
    case MEMPTR:
      return this.memptr;
    case Ax:
      return this._af.getHigh();
    case Fx:
      return this._af.getLow();
    case Bx:
      return this._bc.getHigh();
    case Cx:
      return this._bc.getLow();
    case Dx:
      return this._de.getHigh();
    case Ex:
      return this._de.getLow();
    case Hx:
      return this._hl.getHigh();
    case Lx:
      return this._hl.getLow();
    case AFx:
      return this._af;
    case BCx:
      return this._bc;
    case DEx:
      return this._de;
    case HLx:
      return this._hl;
    default:
      return null;
    }
  }

  @Override
  public String toString() {
    return "AF=" + String.format("%04X", af.read()) + //
        " BC=" + String.format("%04X", bc.read()) + //
        " DE=" + String.format("%04X", de.read()) + //
        " HL=" + String.format("%04X", hl.read()) + //
        " AF'=" + String.format("%04X", _af.read()) + //
        " BC'=" + String.format("%04X", _bc.read()) + //
        " DE'=" + String.format("%04X", _de.read()) + //
        " HL'=" + String.format("%04X", _hl.read()) + //
        " PC=" + String.format("%04X", pc.read()) + //
        " SP=" + String.format("%04X", sp.read()) + //
        " IX=" + String.format("%04X", ix.read()) + //
        " IY=" + String.format("%04X", iy.read()) + //
        " IR=" + String.format("%04X", ir.read()) + //
        " MEMPTR=" + String.format("%04X", memptr.read());
  }

  private List<RegisterName> getAlternateRegisters() {
    return Arrays.asList(RegisterName.AFx, RegisterName.BCx, RegisterName.DEx, RegisterName.HLx);
  }

  private List<RegisterName> getRegisters() {
    return Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL, RegisterName.IX, RegisterName.IY, RegisterName.PC, RegisterName.SP, RegisterName.IR);
  }

  public List<Register> getAll() {
    List<RegisterName> a = getRegisters();
    List<RegisterName> b = getAlternateRegisters();

    a.addAll(b);

    List<Register> collect = a.stream().map(r -> get(r)).collect(Collectors.toList());
    return collect;
  }
}
