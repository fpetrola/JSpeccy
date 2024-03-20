package com.fpetrola.z80.registers.flag;

public class TableFlagRegister<T> extends TableFlagRegisterInitTables implements FlagRegister<Integer> {
  public TableFlagRegister(String name) {
    super(name);
  }

  public TableFlagRegister clone() throws CloneNotSupportedException {
    return this;
  }

  public void RRD(Integer regA) {
    rldTableAluOperation.executeWithCarry(regA);
  }

  @Override
  public Integer ALU8Assign(Integer value) {
    return value;
  }

  public void RLD(Integer regA) {
    rldTableAluOperation.executeWithCarry(regA);
  }

  public void LDI(Integer bc1) {
    rldTableAluOperation1.executeWithCarry(bc1);
  }

  @Override
  public void INI(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB);
  }

  @Override
  public void IND(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB);
  }

  @Override
  public void OUTI(Integer regB) {
    outiTableAluOperation.executeWithCarry(regB);
  }

  @Override
  public void OUTD(Integer regB) {
    iniTableAluOperation.executeWithCarry(regB);
  }

  public void LDD(Integer bc1) {
    rldTableAluOperation1.executeWithCarry(bc1);
  }

  public Integer DAA(Integer reg_A) {
    return daaTableAluOperation.executeWithCarry(reg_A);
  }

  public Integer shiftGenericSLL(Integer temp1) {
    return sllTableAluOperation.executeWithCarry(temp1);
  }

  public void CCF(Integer regA) {
    ccfTableAluOperation.executeWithCarry(regA);
  }

  public void SCF() {
    scfTableAluOperation.executeWithCarry(0);
  }

  public Integer RRA(Integer regA) {
    return rraTableAluOperation.executeWithCarry(regA);
  }

  public Integer RLA(Integer regA) {
    return rlaTableAluOperation.executeWithCarry(regA);
  }

  public Integer RLCA(Integer regA) {
    return rlcaTableAluOperation.executeWithCarry(regA);
  }

  public Integer shiftGenericRR(Integer temp1) {
    return rlcTableAluOperation.executeWithCarry(temp1);
  }

  public Integer shiftGenericSLA(Integer temp1) {
    return slaTableAluOperation.executeWithCarry(temp1);
  }

  public Integer shiftGenericSRL(Integer temp1) {
    return sraTableAluOperation.executeWithCarry(temp1);
  }

  public Integer shiftGenericSRA(Integer temp1) {
    return sraTableAluOperation1.executeWithCarry(temp1);
  }

  public Integer shiftGenericRRC(Integer temp1) {
    return rrcTableAluOperation.executeWithCarry(temp1);
  }

  public Integer shiftGenericRLC(Integer temp1) {
    return rlcTableAluOperation1.executeWithCarry(temp1);
  }

  public Integer shiftGenericRL(Integer temp1) {
    return rlTableAluOperation.executeWithCarry(temp1);
  }

  public Integer CPL(Integer regA) {
    return cplTableAluOperation.executeWithCarry(regA);
  }

  public Integer RRCA(Integer regA) {
    return rrcaTableAluOperation.executeWithCarry(regA);
  }

  public void inC(Integer temp1) {
    inCTableAluOperation.executeWithCarry(temp1);
  }

  public Integer ALU8BitSub(Integer value, Integer reg_A) {
    return sub8TableAluOperation.executeWithoutCarry(value, reg_A);
  }

  public Integer ALU8BitCp(Integer v, Integer reg_A) {
    return cpTableAluOperation.executeWithoutCarry(v, reg_A);
  }

  public Integer ALU8BitSbc(Integer value, Integer reg_A) {
    return sbc8TableAluOperation.executeWithCarry(value, reg_A);
  }

  public Integer ALU8BitDec(Integer value) {
    return dec8TableAluOperation.executeWithCarry(value);
  }

  public Integer ALU8BitXor(Integer value, Integer reg_A) {
    return xorTableAluOperation.executeWithoutCarry(value, reg_A);
  }

  public Integer ALU8BitAnd(Integer reg_A, final Integer value) {
    return andTableAluOperation.executeWithoutCarry(value, reg_A);
  }

  public Integer ALU8BitOr(Integer A, final Integer value) {
    return orTableAluOperation.executeWithoutCarry(value, A);
  }

  public Integer ALU8BitAdc(Integer value, Integer regA) {
    return adc8TableAluOperation.executeWithCarry(value, regA);
  }

  public Integer ALU8BitAdd(Integer value, Integer regA) {
    return adc8TableAluOperation.executeWithoutCarry(value, regA);
  }

  public final Integer ALU8BitInc(final Integer value) {
    return inc8TableAluOperation.executeWithCarry(value);
  }

  public Integer NEG(Integer reg_A) {
    return negTableAluOperation.executeWithCarry(reg_A);
  }

  public void testBit(Integer value1, int bit1) {
    testBitTableAluOperation.executeWithCarry(value1, bit1);
  }

  public Integer ALU16BitAdd(Integer value2, Integer value) {
    return add16TableAluOperation.executeWithoutCarry(value2, value);
  }

  public Integer ALU16BitADC(Integer a, Integer b) {
    return adc16TableAluOperation.executeWithCarry(a, b);
  }

  public Integer ALU16BitSBC(Integer DE, Integer HL) {
    return sbc16TableAluOperation.executeWithCarry(DE, HL);
  }

  public Integer LDAR(Integer reg_A, Integer reg_R, boolean iff2) {
    return ldarTableAluOperation.executeWithCarry2(reg_A, reg_R, iff2 ? 1 : 0);
  }

  public void CPI(Integer value, Integer reg_A, Integer bcValue) {
    cpiTableAluOperation.executeWithCarry2(value, reg_A, bcValue != 0 ? 1 : 0);
  }

  @Override
  public void CPD(Integer value, Integer reg_A, Integer bcValue) {
    cpdTableAluOperation.executeWithCarry2(value, reg_A, bcValue != 0 ? 1 : 0);
  }
}