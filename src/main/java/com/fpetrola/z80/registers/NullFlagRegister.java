package com.fpetrola.z80.registers;

import com.fpetrola.z80.registers.flag.IFlagRegister;

public class NullFlagRegister extends Plain8BitRegister implements IFlagRegister {

  public NullFlagRegister(String name) {
    super(name);
  }

  public int LDAR(int reg_A, int reg_R, boolean iff2) {
    return 0;
  }

  public int ALU16BitADC(int a, int b) {
    return 0;
  }

  public int ALU16BitAdd(int value, int value2) {
    return 0;
  }

  public int ALU16BitSBC(int HL, int DE) {
    return 0;
  }

  public int ALU8BitAdc(int value, int value2) {
    return 0;
  }

  public int ALU8BitAdd(int value, int value2) {
    return 0;
  }

  public int ALU8BitAnd(int value, int reg_A) {

    return 0;
  }

  public int ALU8BitCp(int b, int reg_A) {

    return 0;
  }

  public int ALU8BitDec(int value) {

    return 0;
  }

  public int ALU8BitInc(int value) {

    return 0;
  }

  public int ALU8BitOr(int value, int reg_A) {

    return 0;
  }

  public int ALU8BitSbc(int value, int reg_A) {

    return 0;
  }

  public int ALU8BitSub(int value, int reg_A) {

    return 0;
  }

  public int ALU8BitXor(int value, int reg_A) {

    return 0;
  }

  public void CCF(int reg_A) {

  }

  public void CPI(int valueFromHL, int reg_A, int bcValue) {

  }

  public int CPL(int reg_A) {

    return 0;
  }

  public int DAA(int reg_A) {

    return 0;
  }

  public int EXAFAF(RegisterPair AF1, RegisterPair AF2) {

    return 0;
  }

  public void inC(int temp) {

  }

  public void LDD(int reg_A, int hl, int bc) {

  }

  public void LDI(int reg_A, int value, int bc) {

  }

  public int NEG(int reg_A) {

    return 0;
  }

  public int RLA(int reg_A) {

    return 0;
  }

  public int RLCA(int reg_A) {

    return 0;
  }

  public int RRA(int reg_A) {

    return 0;
  }

  public int RRCA(int reg_A) {

    return 0;
  }

  public void SCF(int reg_A) {

  }

  public int shiftGenericRL(int temp) {

    return 0;
  }

  public int shiftGenericRLC(int temp) {

    return 0;
  }

  public int shiftGenericRR(int temp) {

    return 0;
  }

  public int shiftGenericRRC(int temp) {

    return 0;
  }

  public int shiftGenericSLA(int temp) {

    return 0;
  }

  public int shiftGenericSLL(int temp) {

    return 0;
  }

  public int shiftGenericSRA(int temp) {

    return 0;
  }

  public int shiftGenericSRL(int temp) {

    return 0;
  }

  public void testBit(int value, int bit) {

  }

  public void RLD(int reg_A) {

  }

  public void RRD(int reg_A) {

  }

}
