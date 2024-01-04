package com.fpetrola.z80.registers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fpetrola.z80.WriteAction;
import com.fpetrola.z80.Z80Utils;

public class RegisterBank {

  private final RegisterPair af;
  private RegisterPair ir;
  private Register memptr;
  private Register states;

  public RegisterBank(RegisterPair af, RegisterPair bc, RegisterPair de, RegisterPair hl, RegisterPair _af, RegisterPair _bc, RegisterPair _de, RegisterPair _hl, Register pc, Register sp, RegisterPair ix, RegisterPair iy, RegisterPair ir, Register memptr, Register states) {
    super();
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
    this.states = states;
  }

  private final RegisterPair bc;
  private final RegisterPair de;
  private final RegisterPair hl;
  private final RegisterPair _af;
  private final RegisterPair _bc;
  private final RegisterPair _de;
  private final RegisterPair _hl;
  private final Register pc;
  private final Register sp;
  private final RegisterPair ix;
  private final RegisterPair iy;

//  public RegisterBank() {
////    this.af = new Composed16BitRegister("A", "F");
//    this.af = new Composed16BitRegister("A", "F") {
//      public int read() {
//        return Z80Utils.compose16bit(getHigh().read(), getLow().read()& 0xD7);
//      }
//    };
//    
//    this.bc = new Composed16BitRegister("B", "C");
//    this.de = new Composed16BitRegister("D", "E");
//    this.hl = new Composed16BitRegister("H", "L");
//    this._af = new Composed16BitRegister("A", "F");
//    this._bc = new Composed16BitRegister("B", "C");
//    this._de = new Composed16BitRegister("D", "E");
//    this._hl = new Composed16BitRegister("H", "L");
//    this.pc = new Plain16BitRegister("PC");
//    this.sp = new Plain16BitRegister("SP");
//    this.ix = new Composed16BitRegister("IXH", "IXL");
//    this.iy = new Composed16BitRegister("IYH", "IYL");
//    Plain8BitRegister rRegister = new Plain8BitRegister("R") {
//      public int read() {
//        return super.read() & 0x7F;
//      }
//    };
//    this.ir = new Composed16BitRegister(new Plain8BitRegister("I"), rRegister);
//    this.memptr = new Plain16BitRegister("MEMPTR");
//    this.states = new Plain16BitRegister("STATES");
//  }

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
    case STATES:
      return this.states;
    default:
      return null;
    }
  }

  public Register getAlternate(RegisterName name) {
    switch (name) {
    case AF:
      return this._af;
    case BC:
      return this._bc;
    case DE:
      return this._de;
    case HL:
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
        " MEMPTR=" + String.format("%04X", memptr.read()) + //
        " STATES=" + String.format("%04X", states.read());
  }

  public void copyTo(RegisterBank registerBank) {
    getRegisters().stream().forEach(r -> registerBank.get(r).write(get(r).readFromRealEmulator()));
    getAlternateRegisters().stream().forEach(r -> registerBank.getAlternate(r).write(getAlternate(r).readFromRealEmulator()));
  }
  
  public void copyToReal(RegisterBank registerBank) {
    getRegisters().stream().forEach(r -> registerBank.get(r).writeToRealEmulator(get(r).read()));
    getAlternateRegisters().stream().forEach(r -> registerBank.getAlternate(r).writeToRealEmulator(getAlternate(r).read()));
  }

  private List<RegisterName> getAlternateRegisters() {
    return Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
  }

  private List<RegisterName> getRegisters() {
    return Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL, RegisterName.IX, RegisterName.IY, RegisterName.PC, RegisterName.SP, RegisterName.IR, RegisterName.STATES);
  }

  public List<WriteAction> compareTo(RegisterBank lastRegisterBank) {
    List<RegisterName> a1 = getRegisters();

    List<WriteAction> collect = a1.stream().filter(r -> extracted(lastRegisterBank, r)).map(r -> new WriteAction(get(r), lastRegisterBank.get(r).read(), get(r).readFromRealEmulator())).collect(Collectors.toList());
    List<RegisterName> a = getAlternateRegisters();
    List<WriteAction> list = a.stream().filter(r -> registerEqual(r, lastRegisterBank.getAlternate(r).read(), getAlternate(r).readFromRealEmulator())).map(r -> new WriteAction(getAlternate(r), lastRegisterBank.getAlternate(r).read(), getAlternate(r).readFromRealEmulator())).collect(Collectors.toList());
//    if (collect.size() > 0) {
//      System.out.println("dagasdg");
//    }
    ArrayList<WriteAction> result = new ArrayList<>();
    result.addAll(collect);
    result.addAll(list);
    return result;
  }

  private boolean extracted(RegisterBank lastRegisterBank, RegisterName r) {
    int v1 = lastRegisterBank.get(r).read();
    int fromRealEmulator = get(r).readFromRealEmulator();
    return registerEqual(r, v1, fromRealEmulator);
  }

  private boolean registerEqual(RegisterName r, int v1, int v2) {
    if (r == RegisterName.AF) {
      v1 = v1 & 0xFFD7;
      v2 = v2 & 0xFFD7;
    }

    return v1 != v2;
  }

  public void updateFromEmulator() {
    copyTo(this);
  }
}
