package com.fpetrola.z80.registers.flag;

public class TableFlagRegister<T> extends TableFlagRegisterInitTables implements FlagRegister<Integer> {
  public TableFlagRegister(String name) {
    super(name);
  }

  public TableFlagRegister clone() throws CloneNotSupportedException {
    return this;
  }

  public void RRD(Integer regA) {
    rldTableAluOperation.executeWithCarry(regA, this);
  }

  @Override
  public Integer ALU8Assign(Integer value) {
    return value;
  }

  public void RLD(Integer regA) {
    rldTableAluOperation.executeWithCarry(regA, this);
  }

  public void LDI(Integer bc1) {
    ldiTableAluOperation.executeWithCarry(bc1, this);
  }

  @Override
  public void INI(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB, this);
  }

  @Override
  public void IND(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB, this);
  }

  @Override
  public void OUTI(Integer regB) {
    outiTableAluOperation.executeWithCarry(regB, this);
  }

  @Override
  public void OUTD(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB, this);
  }

  public void LDD(Integer bc1) {
    ldiTableAluOperation.executeWithCarry(bc1, this);
  }

  public Integer DAA(Integer reg_A) {
    return daaTableAluOperation.executeWithCarry2(reg_A, reg_A, data, this);
  }

  public Integer shiftGenericSLL(Integer temp1) {
    return sllTableAluOperation.executeWithCarry(temp1, this);
  }

  public void CCF(Integer regA) {
    ccfTableAluOperation.executeWithCarry(regA, this);
  }

  public void SCF() {
    scfTableAluOperation.executeWithCarry(0, this);
  }

  public Integer RRA(Integer regA) {
    return rraTableAluOperation.executeWithCarry(regA, this);
  }

  public Integer RLA(Integer regA) {
    return rlaTableAluOperation.executeWithCarry(regA, this);
  }

  public Integer RLCA(Integer regA) {
    return rlcaTableAluOperation.executeWithCarry(regA, this);
  }

  public Integer shiftGenericRR(Integer temp1) {
    return rlcTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericSLA(Integer temp1) {
    return slaTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericSRL(Integer temp1) {
    return sraTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericSRA(Integer temp1) {
    return sraTableAluOperation1.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericRRC(Integer temp1) {
    return rrcTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericRLC(Integer temp1) {
    return rlcTableAluOperation1.executeWithCarry(temp1, this);
  }

  public Integer shiftGenericRL(Integer temp1) {
    return rlTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer CPL(Integer regA) {
    return cplTableAluOperation.executeWithCarry(regA, this);
  }

  public Integer RRCA(Integer regA) {
    return rrcaTableAluOperation.executeWithCarry(regA, this);
  }

  public void inC(Integer temp1) {
    inCTableAluOperation.executeWithCarry(temp1, this);
  }

  public Integer ALU8BitSub(Integer value, Integer reg_A) {
    return sub8TableAluOperation.executeWithoutCarry(value, reg_A,this);
  }

  public Integer ALU8BitCp(Integer v, Integer reg_A) {
    return cpTableAluOperation.executeWithoutCarry(v, reg_A, this);
  }

  public Integer ALU8BitSbc(Integer value, Integer reg_A) {
    return sbc8TableAluOperation.executeWithCarry(value, reg_A, this);
  }

  public Integer ALU8BitDec(Integer value) {
    return dec8TableAluOperation.executeWithCarry(value, this);
  }

  public Integer ALU8BitXor(Integer value, Integer reg_A) {
    return xorTableAluOperation.executeWithoutCarry(value, reg_A, this);
  }

  public Integer ALU8BitAnd(Integer reg_A, final Integer value) {
    return andTableAluOperation.executeWithoutCarry(value, reg_A, this);
  }

  public Integer ALU8BitOr(Integer A, final Integer value) {
    return orTableAluOperation.executeWithoutCarry(value, A, this);
  }

  public Integer ALU8BitAdc(Integer value, Integer regA) {
    return adc8TableAluOperation.executeWithCarry(value, regA,this);
  }

  public Integer ALU8BitAdd(Integer value, Integer regA) {
    return adc8TableAluOperation.executeWithoutCarry(value, regA, this);
  }

  public final Integer ALU8BitInc(final Integer value) {
    return inc8TableAluOperation.executeWithCarry(value, this);
  }

  public Integer NEG(Integer reg_A) {
    return negTableAluOperation.executeWithCarry(reg_A, this);
  }

  public void testBit(Integer value1, int bit1) {
    testBitTableAluOperation.executeWithCarry(value1, bit1, this);
  }

  public Integer ALU16BitAdd(Integer value2, Integer value) {
    return add16TableAluOperation.executeWithCarry2(value2, value, data, this);
  }

  public Integer ALU16BitADC(Integer a, Integer b) {
    return adc16TableAluOperation.executeWithCarry(a, b, this);
  }

  public Integer ALU16BitSBC(Integer DE, Integer HL) {
    return sbc16TableAluOperation.executeWithCarry(DE, HL, this);
  }

  public Integer LDAR(Integer reg_A, Integer reg_R, boolean iff2) {
    return ldarTableAluOperation.executeWithCarry2(reg_A, reg_R, iff2 ? 1 : 0, this);
  }

  public void CPI(Integer value, Integer reg_A, Integer bcValue) {
    cpiTableAluOperation.executeWithCarry2(value, reg_A, bcValue != 0 ? 1 : 0, this);
  }

  @Override
  public void CPD(Integer value, Integer reg_A, Integer bcValue) {
    cpdTableAluOperation.executeWithCarry2(value, reg_A, bcValue != 0 ? 1 : 0, this);
  }
}