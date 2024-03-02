package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.registers.Register;

public interface IFlagRegister<T> extends Register<T> {
  boolean getZ();

  interface AluOperation<T> {
    T execute(T value1, T value2);
  }

  T LDAR(T reg_A, T reg_R, boolean iff2);

  T ALU16BitADC(T a, T b);

  T ALU16BitAdd(T value2, T value);

  T ALU16BitSBC(T DE, T HL);

  T ALU8BitAdc(T value, T value2);

  T ALU8BitAdd(T value, T value2);

  T ALU8BitAnd(T value, T reg_A);

  void CPD(T value, T reg_A, T bcValue);

  T ALU8BitCp(T b, T reg_A);

  T ALU8BitDec(T value);

  T ALU8BitInc(T value);

  T ALU8BitOr(T value, T reg_A);

  T ALU8BitSbc(T reg_A, T value);

  T ALU8BitSub(T value, T reg_A);

  T ALU8BitXor(T value, T reg_A);

  void CCF(T reg_A);

  void CPI(T valueFromHL, T reg_A, T bcValue);

  T CPL(T reg_A);

  T DAA(T reg_A);

  void inC(T temp);

  void INI(T reg_B);

  void IND(T reg_B);

  void OUTI(T reg_B);

  void OUTD(T reg_B);

  void LDD(T bc);

  void LDI(T bc);

  T NEG(T reg_A);

  T RLA(T reg_A);

  T RLCA(T reg_A);

  T RRA(T reg_A);

  T RRCA(T reg_A);

  void SCF();

  T shiftGenericRL(T temp);

  T shiftGenericRLC(T temp);

  T shiftGenericRR(T temp);

  T shiftGenericRRC(T temp);

  T shiftGenericSLA(T temp);

  T shiftGenericSLL(T temp);

  T shiftGenericSRA(T temp);

  T shiftGenericSRL(T temp);

  void testBit(T value, int bit);

  public void RLD(T reg_A);

  public void RRD(T reg_A);

  T ALU8Assign(T value);
}
