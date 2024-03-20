package com.fpetrola.z80.registers.flag;

public class TableFlagRegister<T> extends TableFlagRegisterInitTables implements FlagRegister<Integer> {
  protected AluOperation daaTableAluOperation = new DAATableAluOperation(this);
  private AluOperation testBitTableAluOperation = new AluOperation((bit, value, carry) -> {
    resetS();

    switch (bit) {
      case 0: {
        value = value & setBit0;
        break;
      }
      case 1: {
        value = value & setBit1;
        break;
      }
      case 2: {
        value = value & setBit2;
        break;
      }
      case 3: {
        value = value & setBit3;
        break;
      }
      case 4: {
        value = value & setBit4;
        break;
      }
      case 5: {
        value = value & setBit5;
        break;
      }
      case 6: {
        value = value & setBit6;
        break;
      }
      case 7: {
        value = value & setBit7;
        setS(checkNotZero(value));
        break;
      }
    }
    setZ(0 == value);
    setPV(0 == value);
    resetN();
    setH();

    return new Alu8BitResult(value, data);
  }, this);

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

  /* 16 bit ADD */

  public Integer ALU16BitAdd(Integer value2, Integer value) {

    int operand = value;
    int result = value2 + value; // ADD HL,rr
    resetN(); // N = 0;
    //
    int temp = (value2 & 0x0FFF) + (operand & 0x0FFF);
    if ((temp & 0xF000) != 0)
      setH();
    else
      resetH();
    if (result > lsw) // overflow ?
    {
      setC();

      return (result & lsw);
    } else {
      resetC();

      return result;
    }
  }
  /* IN rr,(c) */
  /* 16 bit ADC */

  public Integer ALU16BitADC(Integer a, Integer b) {

    int c = getC() ? 1 : 0;
    int lans = a + b + c;
    int ans = lans & 0xffff;
    setS((ans & (FLAG_S << 8)) != 0);
    setZ(ans == 0);
    setC(lans > 0xFFFF);
    // setPV( ((a ^ b) & (a ^ ans) & 0x8000)!=0 );
    setOverflowFlagAdd16(a, b, c);
    if ((((a & 0x0fff) + (b & 0x0fff) + c) & 0x1000) != 0)
      setH();
    else
      resetH();
    resetN();

    return ans;
  }

  public Integer LDAR(Integer reg_A, Integer reg_R, boolean iff2) {

    reg_A = reg_R & 0x7F;
    setS((reg_A & FLAG_S) != 0);
    setZ(reg_A == 0);
    resetH();
    resetN();
    setPV(iff2);
    return reg_A;
  }

  public void CPI(Integer value, Integer reg_A, Integer bcValue) {
//    reg_R++;
    int result = reg_A - value;
    //
    if ((result & 0x0080) == 0)
      resetS();
    else
      setS();
    result = result & 0x00FF;
    if (result == 0)
      setZ();
    else
      resetZ();
    setHalfCarryFlagSub(reg_A, value);
    setPV(bcValue != 0);
    setN();
    //
//    if (getH())
//      result--;
//    if ((result & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((result & 0x00008) == 0)
//      reset3();
//    else
//      set3();
  }

  @Override
  public void CPD(Integer value, Integer reg_A, Integer bcValue) {
    int result = reg_A - value;

    if ((result & 0x0080) == 0)
      resetS();
    else
      setS();
    result = result & lsb;
    if (result == 0)
      setZ();
    else
      resetZ();
    setHalfCarryFlagSub(reg_A, value);
    setPV(bcValue != 0);
    setN();
    //
//    if (getH())
//      result--;
//    if ((result & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((result & 0x00008) == 0)
//      reset3();
//    else
//      set3();
  }

  public Integer ALU16BitSBC(Integer DE, Integer HL) {
    int a = HL;
    int b = DE;
    int c = getC() ? 1 : 0;
    int lans = (a - b) - c;
    int ans = lans & 0xffff;
    setS((ans & (FLAG_S << 8)) != 0);
    setZ(ans == 0);
    setC(lans < 0);
    // setPV( ((a ^ b) & (a ^ ans) & 0x8000)!=0 );
    setOverflowFlagSub16(a, b, c);
    if ((((a & 0x0fff) - (b & 0x0fff) - c) & 0x1000) != 0)
      setH();
    else
      resetH();
    setN();

    return ans;
  }
}