package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.flag.FlagRegister;

public interface FlagRegisterDelegate<T> extends FlagRegister<T> {
  FlagRegister<T> getDelegate();

  default Object clone() throws CloneNotSupportedException{
    return getDelegate().clone();
  }

  default T read(){
    return getDelegate().read();
  }

  default String getName() {
    return getDelegate().getName();
  }

  default int getLength(){
    return getDelegate().getLength();
  }

  default void increment() {
    getDelegate().increment();
  }

  default void decrement() {
    getDelegate().decrement();
  }

  default void write(T value) {
    getDelegate().write(value);
  }

  default boolean getZ() {
    return getDelegate().getZ();
  }

  default T LDAR(T reg_A, T reg_R, boolean iff2) {
    return getDelegate().LDAR(reg_A, reg_R, iff2);
  }

  default T ALU16BitADC(T a, T b) {
    return getDelegate().ALU16BitADC(a, b);
  }

  default T ALU16BitAdd(T value2, T value) {
    return getDelegate().ALU16BitAdd(value2, value);
  }

  default T ALU16BitSBC(T DE, T HL) {
    return getDelegate().ALU16BitSBC(DE, HL);
  }

  default T ALU8BitAdc(T value, T value2) {
    return getDelegate().ALU8BitAdc(value, value2);
  }

  default T ALU8BitAdd(T value, T value2) {
    return getDelegate().ALU8BitAdd(value, value2);
  }

  default T ALU8BitAnd(T value, T reg_A) {
    return getDelegate().ALU8BitAnd(value, reg_A);
  }

  default void CPD(T value, T reg_A, T bcValue) {
    getDelegate().CPD(value, reg_A, bcValue);
  }

  default T ALU8BitCp(T b, T reg_A) {
    return getDelegate().ALU8BitCp(b, reg_A);
  }

  default T ALU8BitDec(T value) {
    return getDelegate().ALU8BitDec(value);
  }

  default T ALU8BitInc(T value) {
    return getDelegate().ALU8BitInc(value);
  }

  default T ALU8BitOr(T value, T reg_A) {
    return getDelegate().ALU8BitOr(value, reg_A);
  }

  default T ALU8BitSbc(T reg_A, T value) {
    return getDelegate().ALU8BitSbc(reg_A, value);
  }

  default T ALU8BitSub(T value, T reg_A) {
    return getDelegate().ALU8BitSub(value, reg_A);
  }

  default T ALU8BitXor(T value, T reg_A) {
    return getDelegate().ALU8BitXor(value, reg_A);
  }

  default void CCF(T reg_A) {
    getDelegate().CCF(reg_A);
  }

  default void CPI(T valueFromHL, T reg_A, T bcValue) {
    getDelegate().CPI(valueFromHL, reg_A, bcValue);
  }

  default T CPL(T reg_A) {
    return getDelegate().CPL(reg_A);
  }

  default T DAA(T reg_A) {
    return getDelegate().DAA(reg_A);
  }

  default void inC(T temp) {
    getDelegate().inC(temp);
  }

  default void INI(T reg_B) {
    getDelegate().INI(reg_B);
  }

  default void IND(T reg_B) {
    getDelegate().IND(reg_B);
  }

  default void OUTI(T reg_B) {
    getDelegate().OUTI(reg_B);
  }

  default void OUTD(T reg_B) {
    getDelegate().OUTD(reg_B);
  }

  default void LDD(T bc) {
    getDelegate().LDD(bc);
  }

  default void LDI(T bc) {
    getDelegate().LDI(bc);
  }

  default T NEG(T reg_A) {
    return getDelegate().NEG(reg_A);
  }

  default T RLA(T reg_A) {
    return getDelegate().RLA(reg_A);
  }

  default T RLCA(T reg_A) {
    return getDelegate().RLCA(reg_A);
  }

  default T RRA(T reg_A) {
    return getDelegate().RRA(reg_A);
  }

  default T RRCA(T reg_A) {
    return getDelegate().RRCA(reg_A);
  }

  default void SCF() {
    getDelegate().SCF();
  }

  default T shiftGenericRL(T temp) {
    return getDelegate().shiftGenericRL(temp);
  }

  default T shiftGenericRLC(T temp) {
    return getDelegate().shiftGenericRLC(temp);
  }

  default T shiftGenericRR(T temp) {
    return getDelegate().shiftGenericRR(temp);
  }

  default T shiftGenericRRC(T temp) {
    return getDelegate().shiftGenericRRC(temp);
  }

  default T shiftGenericSLA(T temp) {
    return getDelegate().shiftGenericSLA(temp);
  }

  default T shiftGenericSLL(T temp) {
    return getDelegate().shiftGenericSLL(temp);
  }

  default T shiftGenericSRA(T temp) {
    return getDelegate().shiftGenericSRA(temp);
  }

  default T shiftGenericSRL(T temp) {
    return getDelegate().shiftGenericSRL(temp);
  }

  default void testBit(T value, int bit) {
    getDelegate().testBit(value, bit);
  }

  default void RLD(T reg_A) {
    getDelegate().RLD(reg_A);
  }

  default void RRD(T reg_A) {
    getDelegate().RRD(reg_A);
  }

  default T ALU8Assign(T value) {
    return getDelegate().ALU8Assign(value);
  }
}
