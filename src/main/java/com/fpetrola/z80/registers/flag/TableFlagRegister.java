package com.fpetrola.z80.registers.flag;

public class TableFlagRegister<T> extends TableFlagRegisterInitTables implements FlagRegister<Integer> {
  protected TableAluOperation daaTableAluOperation = new DAATableAluOperation(this);

  public TableFlagRegister(String name) {
    super(name);
  }

  public TableFlagRegister clone() throws CloneNotSupportedException {
    return this;
  }

  public void RRD(Integer reg_A) {
    // standard flag updates
    if ((reg_A & 0x80) == 0)
      resetS();
    else
      setS();
    setZ(reg_A == 0);
    resetH();
    setPV(parity[reg_A]);
    resetN();
//    setUnusedFlags(reg_A);
  }

  @Override
  public Integer ALU8Assign(Integer value) {
    return value;
  }

  public void RLD(Integer reg_A) {
    // standard flag updates
    if ((reg_A & 0x80) == 0)
      resetS();
    else
      setS();
    if (reg_A == 0)
      setZ();
    else
      resetZ();
    resetH();
    setPV(parity[reg_A]);
    resetN();
//    setUnusedFlags(reg_A);
  }

  public void LDI(Integer bc) {
    resetH();
    resetN();
    setPV(checkNotZero(bc));
  }

  @Override
  public void INI(Integer reg_B) {
    setZ(reg_B == 0);
    setN();
  }

  @Override
  public void IND(Integer reg_B) {
    setZ(reg_B == 0);
    setN();
  }


  @Override
  public void OUTI(Integer reg_B) {
    reg_B = (reg_B - 1) & lsb;
    setZ(reg_B == 0);
    setN();
  }

  @Override
  public void OUTD(Integer reg_B) {
    setZ(reg_B == 0);
    setN();
  }

  private boolean checkNotZero(int bc) {
    return bc != 0;
  }

  public void LDD(Integer bc) {
    resetH();
    resetN();
    setPV(checkNotZero(bc));
  }

  public Integer DAA(Integer reg_A) {
    return daaTableAluOperation.executeWithCarry(reg_A);
  }

  public Integer shiftGenericSLL(Integer temp) {

    // do shift operation
    temp = (temp << 1) | 0x01;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    if ((temp & 0x00FF) == 0)
      setZ();
    else
      resetZ();
    resetH();
    if ((temp & 0x0FF00) != 0)
      setC();
    else
      resetC();
    temp = temp & 0x00FF;
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
  }

  public void CCF(Integer reg_A) {

    if (getC())
      setH();
    else
      resetH();
    flipC();
    resetN();

  }

  private void flipC() {
    data = data ^ FLAG_C;
  }

  public void SCF() {
    setC();
    resetH();
    resetN();
  }

  public Integer RRA(Integer regA) {
    return rraTableAluOperation.executeWithCarry(regA);
  }

  public Integer RLA(Integer reg_A) {
    boolean carry = (reg_A & 0x0080) != 0;

    reg_A = ((reg_A << 1) & 0x00FF);
    if (getC())
      reg_A = reg_A | 0x01;
    if (carry)
      setC();
    else
      resetC();
    resetH();
    resetN();

    return reg_A;
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

  public Integer RLCA(Integer reg_A) {
    boolean carry = (reg_A & 0x0080) != 0;
    reg_A = ((reg_A << 1) & 0x00FF);
    if (carry) {
      setC();
      reg_A = (reg_A | 0x0001);
    } else
      resetC();
    resetH();
    resetN();

    return reg_A;
  }

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

  public Integer shiftGenericRR(Integer temp1) {
    return rlcTableAluOperation.executeWithCarry(temp1);
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

  public Integer shiftGenericSLA(Integer temp) {

    // do shift operation
    temp = temp << 1;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    if ((temp & 0x00FF) == 0)
      setZ();
    else
      resetZ();
    resetH();
    if ((temp & 0x0FF00) != 0)
      setC();
    else
      resetC();
    temp = temp & 0x00FF;
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
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

  public Integer shiftGenericRL(Integer temp) {

    // do shift operation
    temp = temp << 1;
    if (getC())
      temp = temp | 0x01;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    resetH();
    if ((temp & 0x0FF00) == 0)
      resetC();
    else
      setC();
    temp = temp & lsb;
    if ((temp & 0x00FF) == 0)
      setZ();
    else
      resetZ();
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
  }

  public Integer CPL(Integer reg_A) {

    reg_A = (reg_A ^ 0x00FF) & 0x00FF;
    setH();
    setN();

    return reg_A;
  }

  public Integer RRCA(Integer regA) {
    return rrcaTableAluOperation.executeWithCarry(regA);
  }

  /* IN rr,(c) */
  public void inC(Integer temp) {
    if ((temp & 0x0080) == 0)
      resetS();
    else
      setS();
    if (temp == 0)
      setZ();
    else
      resetZ();
    if (parity[temp])
      setPV();
    else
      resetPV();
    resetN();
    resetH();
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

  public void testBit(Integer value, int bit) {
    //
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
  }

}