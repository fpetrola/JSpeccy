package com.fpetrola.z80.registers;

import static com.fpetrola.z80.registers.RegisterName.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.TableFlagRegister;
import com.fpetrola.z80.registers.flag.FlagProxyFactory;

public class RegisterBank<T extends WordNumber> {

  private RegisterPair<T> af;
  private RegisterPair<T> bc;
  private RegisterPair<T> de;
  private RegisterPair<T> hl;
  private RegisterPair<T> _af;
  private RegisterPair<T> _bc;
  private RegisterPair<T> _de;
  private RegisterPair<T> _hl;
  private RegisterPair<T> ix;
  private RegisterPair<T> iy;
  private Register<T> pc;
  private Register<T> sp;
  private RegisterPair<T> ir;
  private Register<T> memptr;

  public RegisterBank(RegisterPair<T> af, RegisterPair<T> bc, RegisterPair<T> de, RegisterPair<T> hl, RegisterPair<T> _af, RegisterPair<T> _bc, RegisterPair<T> _de, RegisterPair<T> _hl, Register pc, Register sp, RegisterPair<T> ix, RegisterPair<T> iy, RegisterPair<T> ir, Register memptr) {
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


  public static <T extends WordNumber> RegisterBank<T> createSimpleBank() {
    return createBasicBank(FlagProxyFactory.createFlagRegisterProxy(new TableFlagRegister(F)));
  }

  public static <T extends WordNumber> RegisterBank<T> createBasicBank(Register<T> fRegister) {
    RegisterBank<T> bank = new RegisterBank<T>();
    bank.af = new Composed16BitRegister<T>(AF, new Plain8BitRegister(A), fRegister) {
      public void write(T value) {
        super.write(value);
      }
    };
    bank.bc = new Composed16BitRegister(BC, B, C);
    bank.de = new Composed16BitRegister(DE, D, E);
    bank.hl = new Composed16BitRegister(HL, H, L);
    bank._af = new Composed16BitRegister(AFx, Ax, Fx);
    bank._bc = new Composed16BitRegister(BCx, Bx, Cx);
    bank._de = new Composed16BitRegister(DEx, Dx, Ex);
    bank._hl = new Composed16BitRegister(HLx, Hx, Lx);
    bank.ix = new Composed16BitRegister(IX, IXH, IXL);
    bank.iy = new Composed16BitRegister(IY, IYH, IYL);
    Plain8BitRegister i = new Plain8BitRegister<T>(I) {
      public void write(T value) {
        if (this.data == null)
          this.data = value;
        else
          this.data.set(value.and(0xFF));
      }
    };
    Plain8BitRegister r = new Plain8BitRegister<T>(R) {
      public void write(T value) {
        if (this.data == null)
          this.data = value;
        else
          this.data.set(value.and(0xFF));
      }
    };
    bank.ir = (RegisterPair<T>) new Composed16BitRegister<IntegerWordNumber>(IR, i, r);
    bank.pc = (Register<T>) new Plain16BitRegister<IntegerWordNumber>(PC);
    bank.sp = (Register<T>) new Plain16BitRegister<IntegerWordNumber>(SP);
    bank.memptr = new Plain16BitRegister<T>(MEMPTR);
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
    return "AF=" + String.format("%04X", af.read().intValue()) + //
        " BC=" + String.format("%04X", bc.read().intValue()) + //
        " DE=" + String.format("%04X", de.read().intValue()) + //
        " HL=" + String.format("%04X", hl.read().intValue()) + //
        " AF'=" + String.format("%04X", _af.read().intValue()) + //
        " BC'=" + String.format("%04X", _bc.read().intValue()) + //
        " DE'=" + String.format("%04X", _de.read().intValue()) + //
        " HL'=" + String.format("%04X", _hl.read().intValue()) + //
        " PC=" + String.format("%04X", pc.read().intValue()) + //
        " SP=" + String.format("%04X", sp.read().intValue()) + //
        " IX=" + String.format("%04X", ix.read().intValue()) + //
        " IY=" + String.format("%04X", iy.read().intValue()) + //
        " IR=" + String.format("%04X", ir.read().intValue()) + //
        " MEMPTR=" + String.format("%04X", memptr.read().intValue());
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
