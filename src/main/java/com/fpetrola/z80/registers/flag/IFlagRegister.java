package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.registers.RegisterPair;

public interface IFlagRegister {
  
  int LDAR(int reg_A, int reg_R, boolean iff2);

  int ALU16BitADC(int a, int b);

  int ALU16BitAdd(int value, int value2);

  int ALU16BitSBC(int HL, int DE);

  int ALU8BitAdc(int value, int value2);

  int ALU8BitAdd(int value, int value2);

  int ALU8BitAnd(int value, int reg_A);

  void CPD(int value, int reg_A, int bcValue);

  int ALU8BitCp(int b, int reg_A);

  int ALU8BitDec(int value);

  int ALU8BitInc(int value);

  int ALU8BitOr(int value, int reg_A);

  int ALU8BitSbc(int value, int reg_A);

  int ALU8BitSub(int value, int reg_A);

  int ALU8BitXor(int value, int reg_A);

  void CCF(int reg_A);

  void CPI(int valueFromHL, int reg_A, int bcValue);

  int CPL(int reg_A);

  int DAA(int reg_A);

  int EXAFAF(RegisterPair AF1, RegisterPair AF2);

  void inC(int temp);

  void INI(int reg_B);

  void IND(int reg_B);

  void OUTI(int reg_B);

  void OUTD(int reg_B);

  void LDD(int reg_A, int hl, int bc);

  void LDI(int reg_A, int value, int bc);

  int NEG(int reg_A);

  int RLA(int reg_A);

  int RLCA(int reg_A);

  int RRA(int reg_A);

  int RRCA(int reg_A);

  void SCF(int reg_A);

  int shiftGenericRL(int temp);

  int shiftGenericRLC(int temp);

  int shiftGenericRR(int temp);

  int shiftGenericRRC(int temp);

  int shiftGenericSLA(int temp);

  int shiftGenericSLL(int temp);

  int shiftGenericSRA(int temp);

  int shiftGenericSRL(int temp);

  void testBit(int value, int bit);
  
  public void RLD(int reg_A);
  public void RRD(int reg_A);


}