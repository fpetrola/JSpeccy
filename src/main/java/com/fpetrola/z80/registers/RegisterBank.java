package com.fpetrola.z80.registers;

import static com.fpetrola.z80.registers.RegisterName.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fpetrola.z80.opcodes.references.IntegerWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.TableFlagRegister;
import com.fpetrola.z80.registers.flag.FlagProxyFactory;
@SuppressWarnings("ALL")
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
  private Register<T> virtual;
  private Register<T> i;
  private Register r;

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

  public <T extends WordNumber> RegisterBank<T> initSimpleBank() {
    return initBasicBank(new FlagProxyFactory().createFlagRegisterProxy(new TableFlagRegister(F)));
  }

  public RegisterBank<T> initBasicBank(Register<T> fRegister) {
    af = createComposed16BitRegister(AF, create8BitRegister(), fRegister);
    bc = createComposed16BitRegister(BC, B, C);
    de = createComposed16BitRegister(DE, D, E);
    hl = createComposed16BitRegister(HL, H, L);
    _af = createComposed16BitRegister(AFx, Ax, Fx);
    _bc = createComposed16BitRegister(BCx, Bx, Cx);
    _de = createComposed16BitRegister(DEx, Dx, Ex);
    _hl = createComposed16BitRegister(HLx, Hx, Lx);
    ix = createComposed16BitRegister(IX, IXH, IXL);
    iy = createComposed16BitRegister(IY, IYH, IYL);
    i = createAlwaysIntegerPlain8BitRegister(I);
    r = createRRegister();
    ir = createComposed16BitRegister(IR, i, r);
    pc = createAlwaysIntegerPlain16BitRegister(PC);
    sp = createAlwaysIntegerPlain16BitRegister(SP);
    memptr = createPlain16BitRegister(MEMPTR);
    virtual = createPlain16BitRegister(VIRTUAL);
    return this;
  }

  protected Register<T> createRRegister() {
    return new RRegister<T>();
  }

  protected Register<T> createAlwaysIntegerPlain8BitRegister(RegisterName registerName) {
    return new AlwaysIntegerPlain8BitRegister<T>(registerName);
  }

  protected Register<T> create8BitRegister() {
    return new Plain8BitRegister(A);
  }

  protected RegisterPair<T> createComposed16BitRegister(RegisterName registerName, Register<T> h, Register<T> l) {
    return new Composed16BitRegister<T>(registerName, h, l);
  }

  protected Register createAlwaysIntegerPlain16BitRegister(RegisterName registerName) {
    return new AlwaysIntegerPlain16BitRegister(registerName);
  }

  protected Register<T> createPlain16BitRegister(RegisterName registerName) {
    return new Plain16BitRegister<T>(registerName);
  }

  protected RegisterPair createComposed16BitRegister(RegisterName registerName, RegisterName h, RegisterName l) {
    return new Composed16BitRegister(registerName, h, l);
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
      case VIRTUAL:
        return this.virtual;
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
    return /*"AF=" + String.format("%04X", af.read().intValue()) + //*/
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

  public static class AlwaysIntegerPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
    public AlwaysIntegerPlain8BitRegister(RegisterName registerName) {
      super(registerName);
    }

    public void write(T value) {
      this.data = (T) new IntegerWordNumber(value.intValue());
    }
  }

  public static class AlwaysIntegerPlain16BitRegister<T extends WordNumber> extends Plain16BitRegister<T> {
    public AlwaysIntegerPlain16BitRegister(RegisterName registerName) {
      super(registerName);
    }

    public void write(T value) {
      this.data = (T) new IntegerWordNumber(value.intValue());
    }
  }

  public static class RRegister<T extends WordNumber> extends AlwaysIntegerPlain8BitRegister<T> {
    private boolean regRbit7;

    public RRegister() {
      super(RegisterName.R);
    }

    public void write(T value) {
      int regR = value.intValue() & 0x7f;
      regRbit7 = (value.intValue() > 0x7f);
      super.write((T) new IntegerWordNumber(regR));
    }

    public T read() {
      int regR = super.read().intValue();
      int result = regRbit7 ? (regR & 0x7f) | 0x80 : regR & 0x7f;
      return (T) new IntegerWordNumber(result);
    }
  }
}
