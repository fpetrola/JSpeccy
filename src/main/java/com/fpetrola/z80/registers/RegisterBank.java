package com.fpetrola.z80.registers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fpetrola.z80.WriteAction;
import com.fpetrola.z80.instructions.FlagRegister;

public class RegisterBank {

  public RegisterPair af;
  public RegisterPair ir;
  private Register memptr;

  public RegisterBank(RegisterPair af, RegisterPair bc, RegisterPair de, RegisterPair hl, RegisterPair _af, RegisterPair _bc, RegisterPair _de, RegisterPair _hl, Register pc, Register sp, RegisterPair ix, RegisterPair iy, RegisterPair ir, Register memptr) {
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
  }

  private RegisterPair bc;
  private RegisterPair de;
  private RegisterPair hl;
  private RegisterPair _af;
  private RegisterPair _bc;
  private RegisterPair _de;
  private RegisterPair _hl;
  private Register pc;
  private Register sp;
  private RegisterPair ix;
  private RegisterPair iy;

  public RegisterBank() {
  }

  public static RegisterBank createSimpleBank() {
    return createBasicBank(new FlagRegister("F"));
  }

  public static RegisterBank createNullBank() {
    return createBasicBank(new NullFlagRegister("F"));
  }

  public static RegisterBank createBasicBank(Plain8BitRegister fRegister) {
    RegisterBank bank = new RegisterBank();
    bank.af = new Composed16BitRegister(new Plain8BitRegister("A"), fRegister);
    bank.bc = new Composed16BitRegister("B", "C");
    bank.de = new Composed16BitRegister("D", "E");
    bank.hl = new Composed16BitRegister("H", "L");
    bank._af = new Composed16BitRegister("A'", "F'");
    bank._bc = new Composed16BitRegister("B'", "C'");
    bank._de = new Composed16BitRegister("D'", "E'");
    bank._hl = new Composed16BitRegister("H'", "L'");
    bank.ix = new Composed16BitRegister("IXH", "IXL");
    bank.iy = new Composed16BitRegister("IYH", "IYL");
    Plain8BitRegister rRegister = new Plain8BitRegister("R") {
    };
    bank.ir = new Composed16BitRegister(new Plain8BitRegister("I"), rRegister);
    bank.pc = new Plain16BitRegister("PC");
    bank.sp = new Plain16BitRegister("SP");
    bank.memptr = new Plain16BitRegister("MEMPTR");
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
    default:
      return null;
    }
  }

  public Register getAlternate(RegisterName name) {
    switch (name) {
    case A:
      return this._af.getHigh();
    case F:
      return this._af.getLow();
    case B:
      return this._bc.getHigh();
    case C:
      return this._bc.getLow();
    case D:
      return this._de.getHigh();
    case E:
      return this._de.getLow();
    case H:
      return this._hl.getHigh();
    case L:
      return this._hl.getLow();
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
        " MEMPTR=" + String.format("%04X", memptr.read());
  }

//  private void copyTo(RegisterBank registerBank) {
//    getRegisters().stream().forEach(r -> registerBank.get(r).write(get(r).readFromRealEmulator()));
//    getAlternateRegisters().stream().forEach(r -> registerBank.getAlternate(r).write(getAlternate(r).readFromRealEmulator()));
//  }
//
//  private void copyToReal(RegisterBank registerBank) {
//    getRegisters().stream().forEach(r -> registerBank.get(r).writeToRealEmulator(get(r).read()));
//    getAlternateRegisters().stream().forEach(r -> registerBank.getAlternate(r).writeToRealEmulator(getAlternate(r).read()));
//  }

  private List<RegisterName> getAlternateRegisters() {
    return Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL);
  }

  private List<RegisterName> getRegisters() {
    return Arrays.asList(RegisterName.AF, RegisterName.BC, RegisterName.DE, RegisterName.HL, RegisterName.IX, RegisterName.IY, RegisterName.PC, RegisterName.SP, RegisterName.IR);
  }

  public List<Register> getAll() {
    List<RegisterName> a = getRegisters();
    List<RegisterName> b = getAlternateRegisters();

    List<Register> collect = a.stream().map(r -> get(r)).collect(Collectors.toList());
    List<Register> collectB = b.stream().map(r -> getAlternate(r)).collect(Collectors.toList());

    collect.addAll(collectB);
    return collect;
  }

  public List<WriteAction> compareTo(RegisterBank lastRegisterBank) {
    ArrayList<WriteAction> result = new ArrayList<>();
//    List<RegisterName> a1 = getRegisters();
//
//    List<WriteAction> collect = a1.stream().filter(r -> extracted(lastRegisterBank, r)).map(r -> new WriteAction(get(r), lastRegisterBank.get(r).read(), get(r).readFromRealEmulator())).collect(Collectors.toList());
//    List<RegisterName> a = getAlternateRegisters();
//    List<WriteAction> list = a.stream().filter(r -> registerEqual(r, lastRegisterBank.getAlternate(r).read(), getAlternate(r).readFromRealEmulator())).map(r -> new WriteAction(getAlternate(r), lastRegisterBank.getAlternate(r).read(), getAlternate(r).readFromRealEmulator())).collect(Collectors.toList());
//    result.addAll(collect);
//    result.addAll(list);
    return result;
  }

//  private boolean extracted(RegisterBank lastRegisterBank, RegisterName r) {
//    int v1 = lastRegisterBank.get(r).read();
//    int fromRealEmulator = get(r).readFromRealEmulator();
//    return registerEqual(r, v1, fromRealEmulator);
//  }

  private boolean registerEqual(RegisterName r, int v1, int v2) {
    if (r == RegisterName.AF) {
      v1 = v1 & 0xFFD7;
      v2 = v2 & 0xFFD7;
    }

    return v1 != v2;
  }
}
