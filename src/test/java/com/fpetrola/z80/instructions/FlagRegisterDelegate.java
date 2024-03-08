package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class FlagRegisterDelegate<T> implements FlagRegister<T> {
  FlagRegister<T> delegate;

  public FlagRegisterDelegate(FlagRegister<T> delegate) {
    this.delegate = delegate;
  }

  @Override
  public T read() {
    return delegate.read();
  }

  @Override
  public int getLength() {
    return delegate.getLength();
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return delegate.clone();
  }

  @Override
  public void write(T value) {
    delegate.write(value);
  }

  @Override
  public void increment() {
    delegate.increment();
  }

  @Override
  public void decrement() {
    delegate.decrement();
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public boolean getZ() {
    return delegate.getZ();
  }

  @Override
  public T LDAR(T reg_A, T reg_R, boolean iff2) {
    return delegate.LDAR(reg_A, reg_R, iff2);
  }

  @Override
  public T ALU16BitADC(T a, T b) {
    return delegate.ALU16BitADC(a, b);
  }

  @Override
  public T ALU16BitAdd(T value2, T value) {
    return delegate.ALU16BitAdd(value2, value);
  }

  @Override
  public T ALU16BitSBC(T DE, T HL) {
    return delegate.ALU16BitSBC(DE, HL);
  }

  @Override
  public T ALU8BitAdc(T value, T value2) {
    return delegate.ALU8BitAdc(value, value2);
  }

  @Override
  public T ALU8BitAdd(T value, T value2) {
    return delegate.ALU8BitAdd(value, value2);
  }

  @Override
  public T ALU8BitAnd(T value, T reg_A) {
    return delegate.ALU8BitAnd(value, reg_A);
  }

  @Override
  public void CPD(T value, T reg_A, T bcValue) {
    delegate.CPD(value, reg_A, bcValue);
  }

  @Override
  public T ALU8BitCp(T b, T reg_A) {
    return delegate.ALU8BitCp(b, reg_A);
  }

  @Override
  public T ALU8BitDec(T value) {
    return delegate.ALU8BitDec(value);
  }

  @Override
  public T ALU8BitInc(T value) {
    return delegate.ALU8BitInc(value);
  }

  @Override
  public T ALU8BitOr(T value, T reg_A) {
    return delegate.ALU8BitOr(value, reg_A);
  }

  @Override
  public T ALU8BitSbc(T reg_A, T value) {
    return delegate.ALU8BitSbc(reg_A, value);
  }

  @Override
  public T ALU8BitSub(T value, T reg_A) {
    return delegate.ALU8BitSub(value, reg_A);
  }

  @Override
  public T ALU8BitXor(T value, T reg_A) {
    return delegate.ALU8BitXor(value, reg_A);
  }

  @Override
  public void CCF(T reg_A) {
    delegate.CCF(reg_A);
  }

  @Override
  public void CPI(T valueFromHL, T reg_A, T bcValue) {
    delegate.CPI(valueFromHL, reg_A, bcValue);
  }

  @Override
  public T CPL(T reg_A) {
    return delegate.CPL(reg_A);
  }

  @Override
  public T DAA(T reg_A) {
    return delegate.DAA(reg_A);
  }

  @Override
  public void inC(T temp) {
    delegate.inC(temp);
  }

  @Override
  public void INI(T reg_B) {
    delegate.INI(reg_B);
  }

  @Override
  public void IND(T reg_B) {
    delegate.IND(reg_B);
  }

  @Override
  public void OUTI(T reg_B) {
    delegate.OUTI(reg_B);
  }

  @Override
  public void OUTD(T reg_B) {
    delegate.OUTD(reg_B);
  }

  @Override
  public void LDD(T bc) {
    delegate.LDD(bc);
  }

  @Override
  public void LDI(T bc) {
    delegate.LDI(bc);
  }

  @Override
  public T NEG(T reg_A) {
    return delegate.NEG(reg_A);
  }

  @Override
  public T RLA(T reg_A) {
    return delegate.RLA(reg_A);
  }

  @Override
  public T RLCA(T reg_A) {
    return delegate.RLCA(reg_A);
  }

  @Override
  public T RRA(T reg_A) {
    return delegate.RRA(reg_A);
  }

  @Override
  public T RRCA(T reg_A) {
    return delegate.RRCA(reg_A);
  }

  @Override
  public void SCF() {
    delegate.SCF();
  }

  @Override
  public T shiftGenericRL(T temp) {
    return delegate.shiftGenericRL(temp);
  }

  @Override
  public T shiftGenericRLC(T temp) {
    return delegate.shiftGenericRLC(temp);
  }

  @Override
  public T shiftGenericRR(T temp) {
    return delegate.shiftGenericRR(temp);
  }

  @Override
  public T shiftGenericRRC(T temp) {
    return delegate.shiftGenericRRC(temp);
  }

  @Override
  public T shiftGenericSLA(T temp) {
    return delegate.shiftGenericSLA(temp);
  }

  @Override
  public T shiftGenericSLL(T temp) {
    return delegate.shiftGenericSLL(temp);
  }

  @Override
  public T shiftGenericSRA(T temp) {
    return delegate.shiftGenericSRA(temp);
  }

  @Override
  public T shiftGenericSRL(T temp) {
    return delegate.shiftGenericSRL(temp);
  }

  @Override
  public void testBit(T value, int bit) {
    delegate.testBit(value, bit);
  }

  @Override
  public void RLD(T reg_A) {
    delegate.RLD(reg_A);
  }

  @Override
  public void RRD(T reg_A) {
    delegate.RRD(reg_A);
  }

  @Override
  public T ALU8Assign(T value) {
    return delegate.ALU8Assign(value);
  }
}
