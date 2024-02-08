package com.fpetrola.z80.registers;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FerFlagRegister;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class RegisterWrapper<T extends WordNumber> implements Register<T>, IFlagRegister<T> {
  private final FerFlagRegister ferFlagRegister;

  public RegisterWrapper(FerFlagRegister ferFlagRegister) {
    this.ferFlagRegister = ferFlagRegister;
  }

  @Override
  public T LDAR(T reg_A, T reg_R, boolean iff2) {
    return OOZ80.createValue(ferFlagRegister.LDAR(reg_A.intValue(), reg_R.intValue(), iff2));
  }

  @Override
  public T ALU16BitADC(T a, T b) {
    return OOZ80.createValue(ferFlagRegister.ALU16BitADC(a.intValue(), b.intValue()));
  }

  @Override
  public T ALU16BitAdd(T value, T value2) {
    return OOZ80.createValue(ferFlagRegister.ALU16BitAdd(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU16BitSBC(T HL, T DE) {
    return OOZ80.createValue(ferFlagRegister.ALU16BitSBC(HL.intValue(), DE.intValue()));
  }

  @Override
  public T ALU8BitAdc(T value, T value2) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitAdc(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU8BitAdd(T value, T value2) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitAdd(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU8BitAnd(T value, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitAnd(value.intValue(), reg_A.intValue()));
  }

  @Override
  public void CPD(T value, T reg_A, T bcValue) {
    ferFlagRegister.CPD(value.intValue(), reg_A.intValue(), bcValue.intValue());
  }

  @Override
  public T ALU8BitCp(T b, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitCp(b.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitDec(T value) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitDec(value.intValue()));
  }

  @Override
  public T ALU8BitInc(T value) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitInc(value.intValue()));
  }

  @Override
  public T ALU8BitOr(T value, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitOr(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitSbc(T value, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitSbc(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitSub(T value, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitSub(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitXor(T value, T reg_A) {
    return OOZ80.createValue(ferFlagRegister.ALU8BitXor(value.intValue(), reg_A.intValue()));
  }

  @Override
  public void CCF(T reg_A) {
    ferFlagRegister.CCF(reg_A.intValue());
  }

  @Override
  public void CPI(T valueFromHL, T reg_A, T bcValue) {
    ferFlagRegister.CPI(valueFromHL.intValue(), reg_A.intValue(), bcValue.intValue());
  }

  @Override
  public T CPL(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.CPL(reg_A.intValue()));
  }

  @Override
  public T DAA(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.DAA(reg_A.intValue()));
  }

  @Override
  public T EXAFAF(RegisterPair<T> AF1, RegisterPair<T> AF2) {
    RegisterPair<Integer> p1 = new IntegerRegisterPair(AF1);
    RegisterPair<Integer> p2 = new IntegerRegisterPair(AF2);
    T value = OOZ80.createValue(ferFlagRegister.EXAFAF(p1, p2));
    return value;
  }

  @Override
  public void inC(T temp) {
    ferFlagRegister.inC(temp.intValue());
  }

  @Override
  public void INI(T reg_B) {
    ferFlagRegister.INI(reg_B.intValue());
  }

  @Override
  public void IND(T reg_B) {
    ferFlagRegister.IND(reg_B.intValue());
  }

  @Override
  public void OUTI(T reg_B) {
    ferFlagRegister.OUTI(reg_B.intValue());
  }

  @Override
  public void OUTD(T reg_B) {
    ferFlagRegister.OUTD(reg_B.intValue());
  }

  @Override
  public void LDD(T reg_A, T hl, T bc) {
    ferFlagRegister.LDD(reg_A.intValue(), hl.intValue(), bc.intValue());
  }

  @Override
  public void LDI(T reg_A, T value, T bc) {
    ferFlagRegister.LDI(reg_A.intValue(), value.intValue(), bc.intValue());
  }

  @Override
  public T NEG(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.NEG(reg_A.intValue()));
  }

  @Override
  public T RLA(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.RLA(reg_A.intValue()));
  }

  @Override
  public T RLCA(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.RLCA(reg_A.intValue()));
  }

  @Override
  public T RRA(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.RRA(reg_A.intValue()));
  }

  @Override
  public T RRCA(T reg_A) {
    return OOZ80.createValue(ferFlagRegister.RRCA(reg_A.intValue()));
  }

  @Override
  public void SCF(T reg_A) {
    ferFlagRegister.SCF(reg_A.intValue());
  }

  @Override
  public T shiftGenericRL(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericRL(temp.intValue()));
  }

  @Override
  public T shiftGenericRLC(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericRLC(temp.intValue()));
  }

  @Override
  public T shiftGenericRR(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericRR(temp.intValue()));
  }

  @Override
  public T shiftGenericRRC(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericRRC(temp.intValue()));
  }

  @Override
  public T shiftGenericSLA(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericSLA(temp.intValue()));
  }

  @Override
  public T shiftGenericSLL(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericSLL(temp.intValue()));
  }

  @Override
  public T shiftGenericSRA(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericSRA(temp.intValue()));
  }

  @Override
  public T shiftGenericSRL(T temp) {
    return OOZ80.createValue(ferFlagRegister.shiftGenericSRL(temp.intValue()));
  }

  @Override
  public void testBit(T value, int bit) {
    ferFlagRegister.testBit(value.intValue(), bit);
  }

  @Override
  public void RLD(T reg_A) {
    ferFlagRegister.RLD(reg_A.intValue());
  }

  @Override
  public void RRD(T reg_A) {
    ferFlagRegister.RRD(reg_A.intValue());
  }

  @Override
  public int cyclesCost() {
    return 0;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  @Override
  public T read() {
    return OOZ80.createValue(ferFlagRegister.read());
  }

  @Override
  public void write(T value) {
    ferFlagRegister.write(value.intValue());
  }

  @Override
  public void increment(int by) {
    ferFlagRegister.increment(by);
  }

  @Override
  public void decrement(int by) {
    ferFlagRegister.decrement(by);
  }

  @Override
  public RegisterName getName() {
    return null;
  }

}
