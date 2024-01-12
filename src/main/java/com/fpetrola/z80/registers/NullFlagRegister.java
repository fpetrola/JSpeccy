package com.fpetrola.z80.registers;

import com.fpetrola.z80.instructions.IFlagRegister;
import com.fpetrola.z80.instructions.OpcodeReference;

public class NullFlagRegister extends Plain8BitRegister implements IFlagRegister {

  public NullFlagRegister(String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  @Override
  public int LDAR(int reg_A, int reg_R, boolean iff2) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU16BitADC(int a, int b) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU16BitAdd(int value, int value2) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU16BitSBC(int HL, int DE) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitAdc(int value, int value2) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitAdd(int value, int value2) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitAnd(int value, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitCp(int b, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitDec(int value) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitInc(int value) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitOr(int value, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitSbc(int value, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitSub(int value, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int ALU8BitXor(int value, int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void CCF(int reg_A) {
    // TODO Auto-generated method stub

  }

  @Override
  public void CPI(int valueFromHL, int reg_A, int bcValue) {
    // TODO Auto-generated method stub

  }

  @Override
  public int CPL(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int DAA(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int EXAFAF(RegisterPair AF1, RegisterPair AF2) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void inC(int temp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void LDD(int reg_A, int hl, int bc) {
    // TODO Auto-generated method stub

  }

  @Override
  public void LDI(int reg_A, int value, int bc) {
    // TODO Auto-generated method stub

  }

  @Override
  public int NEG(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int RLA(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int RLCA(OpcodeReference target) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int RRA(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int RRCA(int reg_A) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void SCF(int reg_A) {
    // TODO Auto-generated method stub

  }

  @Override
  public int shiftGenericRL(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericRLC(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericRR(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericRRC(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericSLA(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericSLL(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericSRA(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int shiftGenericSRL(int temp) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void testBit(int value, int bit) {
    // TODO Auto-generated method stub

  }

  @Override
  public void RLD(int reg_A) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void RRD(int reg_A) {
    // TODO Auto-generated method stub
    
  }

}
