package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.TableFlagRegister;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class RegisterWrapper<T extends WordNumber> implements Register<T>, IFlagRegister<T> {
  private final TableFlagRegister tableFlagRegister;

  public RegisterWrapper(TableFlagRegister tableFlagRegister) {
    this.tableFlagRegister = tableFlagRegister;
  }

  @Override
  public T LDAR(T reg_A, T reg_R, boolean iff2) {
    return WordNumber.createValue(tableFlagRegister.LDAR(reg_A.intValue(), reg_R.intValue(), iff2));
  }

  @Override
  public T ALU16BitADC(T a, T b) {
    return WordNumber.createValue(tableFlagRegister.ALU16BitADC(a.intValue(), b.intValue()));
  }

  @Override
  public T ALU16BitAdd(T value, T value2) {
    return WordNumber.createValue(tableFlagRegister.ALU16BitAdd(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU16BitSBC(T HL, T DE) {
    return WordNumber.createValue(tableFlagRegister.ALU16BitSBC(HL.intValue(), DE.intValue()));
  }

  @Override
  public T ALU8BitAdc(T value, T value2) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitAdc(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU8BitAdd(T value, T value2) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitAdd(value.intValue(), value2.intValue()));
  }

  @Override
  public T ALU8BitAnd(T value, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitAnd(value.intValue(), reg_A.intValue()));
  }

  @Override
  public void CPD(T value, T reg_A, T bcValue) {
    tableFlagRegister.CPD(value.intValue(), reg_A.intValue(), bcValue.intValue());
  }

  @Override
  public T ALU8BitCp(T b, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitCp(b.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitDec(T value) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitDec(value.intValue()));
  }

  @Override
  public T ALU8BitInc(T value) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitInc(value.intValue()));
  }

  @Override
  public T ALU8BitOr(T value, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitOr(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitSbc(T value, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitSbc(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitSub(T value, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitSub(value.intValue(), reg_A.intValue()));
  }

  @Override
  public T ALU8BitXor(T value, T reg_A) {
    return WordNumber.createValue(tableFlagRegister.ALU8BitXor(value.intValue(), reg_A.intValue()));
  }

  @Override
  public void CCF(T reg_A) {
    tableFlagRegister.CCF(reg_A.intValue());
  }

  @Override
  public void CPI(T valueFromHL, T reg_A, T bcValue) {
    tableFlagRegister.CPI(valueFromHL.intValue(), reg_A.intValue(), bcValue.intValue());
  }

  @Override
  public T CPL(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.CPL(reg_A.intValue()));
  }

  @Override
  public T DAA(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.DAA(reg_A.intValue()));
  }

  @Override
  public T EXAFAF(RegisterPair<T> AF1, RegisterPair<T> AF2) {
    RegisterPair<Integer> p1 = new IntegerRegisterPair(AF1);
    RegisterPair<Integer> p2 = new IntegerRegisterPair(AF2);
    T value = WordNumber.createValue(tableFlagRegister.EXAFAF(p1, p2));
    return value;
  }

  @Override
  public void inC(T temp) {
    tableFlagRegister.inC(temp.intValue());
  }

  @Override
  public void INI(T reg_B) {
    tableFlagRegister.INI(reg_B.intValue());
  }

  @Override
  public void IND(T reg_B) {
    tableFlagRegister.IND(reg_B.intValue());
  }

  @Override
  public void OUTI(T reg_B) {
    tableFlagRegister.OUTI(reg_B.intValue());
  }

  @Override
  public void OUTD(T reg_B) {
    tableFlagRegister.OUTD(reg_B.intValue());
  }

  @Override
  public void LDD(T reg_A, T hl, T bc) {
    tableFlagRegister.LDD(reg_A.intValue(), hl.intValue(), bc.intValue());
  }

  @Override
  public void LDI(T reg_A, T value, T bc) {
    tableFlagRegister.LDI(reg_A.intValue(), value.intValue(), bc.intValue());
  }

  @Override
  public T NEG(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.NEG(reg_A.intValue()));
  }

  @Override
  public T RLA(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.RLA(reg_A.intValue()));
  }

  @Override
  public T RLCA(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.RLCA(reg_A.intValue()));
  }

  @Override
  public T RRA(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.RRA(reg_A.intValue()));
  }

  @Override
  public T RRCA(T reg_A) {
    return WordNumber.createValue(tableFlagRegister.RRCA(reg_A.intValue()));
  }

  @Override
  public void SCF(T reg_A) {
    tableFlagRegister.SCF(reg_A.intValue());
  }

  @Override
  public T shiftGenericRL(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericRL(temp.intValue()));
  }

  @Override
  public T shiftGenericRLC(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericRLC(temp.intValue()));
  }

  @Override
  public T shiftGenericRR(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericRR(temp.intValue()));
  }

  @Override
  public T shiftGenericRRC(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericRRC(temp.intValue()));
  }

  @Override
  public T shiftGenericSLA(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericSLA(temp.intValue()));
  }

  @Override
  public T shiftGenericSLL(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericSLL(temp.intValue()));
  }

  @Override
  public T shiftGenericSRA(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericSRA(temp.intValue()));
  }

  @Override
  public T shiftGenericSRL(T temp) {
    return WordNumber.createValue(tableFlagRegister.shiftGenericSRL(temp.intValue()));
  }

  @Override
  public void testBit(T value, int bit) {
    tableFlagRegister.testBit(value.intValue(), bit);
  }

  @Override
  public void RLD(T reg_A) {
    tableFlagRegister.RLD(reg_A.intValue());
  }

  @Override
  public void RRD(T reg_A) {
    tableFlagRegister.RRD(reg_A.intValue());
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
    return WordNumber.createValue(tableFlagRegister.read());
  }

  @Override
  public void write(T value) {
    tableFlagRegister.write(value.intValue());
  }

  @Override
  public void increment(int by) {
    tableFlagRegister.increment(by);
  }

  @Override
  public void decrement(int by) {
    tableFlagRegister.decrement(by);
  }

  @Override
  public RegisterName getName() {
    return null;
  }

}
