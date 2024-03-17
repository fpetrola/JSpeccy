package com.fpetrola.z80.registers.flag;

public class TableFlagRegister<T> extends Base8080 implements FlagRegister<Integer> {
  private final TableAluOperation sbc8TableAluOperation;
  private TableAluOperation adc8TableAluOperation;

  public TableFlagRegister(String name) {
    super(name);
    adc8TableAluOperation = new TableAluOperation((a, value, carry) -> {
      int flag = 0;
      int ans = a + value + carry;
      if ((ans & 0x80) != 0)
        flag |= FLAG_S;
      if ((ans & 0x100) != 0)
        flag |= FLAG_C;
      if ((ans & 0xff) == 0)
        flag |= FLAG_Z;
      if (((a ^ ans ^ value) & 0x10) != 0)
        flag |= FLAG_H;
      if (((a ^ value ^ 0x80) & (value ^ ans) & 0x80) != 0)
        flag |= FLAG_PV;
      if ((ans & 0x08) != 0)
        flag |= FLAG_3;
      if ((ans & 0x20) != 0)
        flag |= FLAG_5;

      return new Alu8BitResult(ans, flag);
    }, this);

    sbc8TableAluOperation = new TableAluOperation((a, value, carry) -> {
      int flag = FLAG_N;
      int ans = a - value - carry;
      if ((ans & 0x80) != 0)
        flag |= FLAG_S;
      if ((ans & 0x100) != 0)
        flag |= FLAG_C;
      if ((ans & 0xff) == 0)
        flag |= FLAG_Z;
      if (((a ^ ans ^ value) & 0x10) != 0)
        flag |= FLAG_H;
      if (((value ^ a) & (a ^ ans) & 0x80) != 0)
        flag |= FLAG_PV;
      if ((ans & 0x08) != 0)
        flag |= FLAG_3;
      if ((ans & 0x20) != 0)
        flag |= FLAG_5;

      return new Alu8BitResult(ans, flag);
    }, this);
  }

  private final static int byteSize = 8;

  // for setting
  private final static int flag_S = 0x0080;
  private final static int flag_Z = 0x0040;
  private final static int flag_H = 0x0010;
  private final static int flag_PV = 0x0004;
  private final static int flag_N = 0x0002;
  private final static int flag_C = 0x0001;

  // for resetting
  private final static int flag_S_N = 0x007F;
  private final static int flag_Z_N = 0x00BF;
  private final static int flag_5_N = 0x00DF;
  private final static int flag_H_N = 0x00EF;
  private final static int flag_3_N = 0x00F7;
  private final static int flag_PV_N = 0x00FB;
  private final static int flag_N_N = 0x00FD;
  private final static int flag_C_N = 0x00FE;
  /* LSB, MSB masking values */
  private final static int lsb = 0x00FF;
  private final static int msb = 0xFF00;
  private final static int lsw = 0x0000FFFF;

  private final static int setBit0 = 0x0001; // or
  // mask
  // value
  private final static int setBit1 = 0x0002;
  private final static int setBit2 = 0x0004;
  private final static int setBit3 = 0x0008;
  private final static int setBit4 = 0x0010;
  private final static int setBit5 = 0x0020;
  private final static int setBit6 = 0x0040;
  private final static int setBit7 = 0x0080;
  private final static int resetBit0 = setBit0 ^ 0x00FF; // and
  // mask
  // value
  private final static int resetBit1 = setBit1 ^ 0x00FF;
  private final static int resetBit2 = setBit2 ^ 0x00FF;
  private final static int resetBit3 = setBit3 ^ 0x00FF;
  private final static int resetBit4 = setBit4 ^ 0x00FF;
  private final static int resetBit5 = setBit5 ^ 0x00FF;
  private final static int resetBit6 = setBit6 ^ 0x00FF;
  private final static int resetBit7 = setBit7 ^ 0x00FF;

  private static final boolean[] parity = new boolean[256];

  static {
    parity[0] = true; // even parity seed value
    int position = 1; // table position
    for (int bit = 0; bit < byteSize; bit++) {
      for (int fill = 0; fill < position; fill++) {
        parity[position + fill] = !parity[fill];
      }
      position = position * 2;
    }
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

    Integer ans = reg_A;
    int incr = 0;
    boolean carry = getC();
    if ((getH()) || ((ans & 0x0f) > 0x09)) {
      incr |= 0x06;
    }
    if (carry || (ans > 0x9f) || ((ans > 0x8f) && ((ans & 0x0f) > 0x09))) {
      incr |= 0x60;
    }
    if (ans > 0x99) {
      carry = true;
    }
    if (getN()) {
      ALU8BitSub(incr, reg_A); // sub_a(incr);
    } else {
      ALU8BitAdd(incr, reg_A); // add_a(incr);
    }
    ans = reg_A;
    if (carry)
      setC();
    else
      resetC(); // setC( carry );
    setPV(parity[ans]); // setPV( parity[ ans ] );

    return ans;
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
    data = data ^ flag_C;
  }

  public void SCF() {
    setC();
    resetH();
    resetN();
  }

  public Integer RRA(Integer reg_A) {
    boolean carry = (reg_A & 0x01) != 0;

    reg_A = (reg_A >> 1);
    if (getC())
      reg_A = (reg_A | 0x0080);
    if (carry)
      setC();
    else
      resetC();
    resetH();
    resetN();

    return reg_A;
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

  /* 8 bit SBC */
  public Integer ALU8BitSbc(Integer value, Integer reg_A) {

    int local_reg_A = reg_A;
    int carry;

    if (getC())
      carry = 1;
    else
      carry = 0;
    setHalfCarryFlagSub(local_reg_A, value, carry);
    setOverflowFlagSub(local_reg_A, value, carry);
    local_reg_A = local_reg_A - value - carry;
    setS((local_reg_A & 0x0080) != 0);
    setC((local_reg_A & 0xff00) != 0);
    local_reg_A = local_reg_A & 0x00ff;
    setZ(local_reg_A == 0);
    setN();
    reg_A = local_reg_A;

    return reg_A;
  }

  /* 16 bit ADC */
  public Integer ALU16BitADC(Integer a, Integer b) {

    int c = getC() ? 1 : 0;
    int lans = a + b + c;
    int ans = lans & 0xffff;
    setS((ans & (flag_S << 8)) != 0);
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

  public Integer shiftGenericRR(Integer temp) {

    boolean tempC;
    // do shift operation
    tempC = getC();
    setC((temp & 0x0001) != 0);
    temp = temp >> 1;
    if (tempC)
      temp = temp | 0x80;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    if (temp == 0)
      setZ();
    else
      resetZ();
    resetH();
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
  }

  public Integer LDAR(Integer reg_A, Integer reg_R, boolean iff2) {

    reg_A = reg_R & 0x7F;
    setS((reg_A & flag_S) != 0);
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

  public Integer shiftGenericSRL(Integer temp) {

    // do shift operation
    setC((temp & 0x0001) != 0);
    temp = temp >> 1;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    setZ(temp == 0);
    resetH();
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
  }

  public Integer shiftGenericSRA(Integer temp) {

    // do shift operation
    setC((temp & 0x0001) != 0);
    if ((temp & 0x0080) == 0) {
      temp = temp >> 1;
      resetS();
    } else {
      temp = (temp >> 1) | 0x0080;
      setS();
    }
    // standard flag updates
    if (temp == 0)
      setZ();
    else
      resetZ();
    resetH();
    setPV(parity[temp]);
    resetN();

    return temp;
  }

  /* 8 bit SUB */
  public Integer ALU8BitSub(Integer value, Integer reg_A) {

    int local_reg_A = reg_A;

    setHalfCarryFlagSub(local_reg_A, value);
    setOverflowFlagSub(local_reg_A, value);
    local_reg_A = local_reg_A - value;
    setS((local_reg_A & 0x0080) != 0);
    setC((local_reg_A & 0xff00) != 0);
    local_reg_A = local_reg_A & 0x00ff;
    setZ(local_reg_A == 0);
    setN();
    reg_A = local_reg_A;

    return reg_A;
  }

  public Integer NEG(Integer reg_A) {

    setHalfCarryFlagSub(0, reg_A, 0);
    // if ((value & 0x0f) == 0x00) setH(); else resetH();
    setOverflowFlagSub(0, reg_A, 0);
    // if (value == 0x80) setPV(); else resetPV();
    reg_A = 0 - reg_A;
    if ((reg_A & 0xFF00) != 0)
      setC();
    else
      resetC();
    setN();
    reg_A = reg_A & 0x00FF;
    if (reg_A == 0)
      setZ();
    else
      resetZ();
    if ((reg_A & 0x0080) != 0)
      setS();
    else
      resetS();

    return reg_A;
  }

  public Integer shiftGenericRRC(Integer temp) {

    // do shift operation
    setC((temp & 0x0001) != 0);
    temp = temp >> 1;
    if (getC())
      temp = temp | 0x80;
    // standard flag updates
    setS((temp & 0x0080) != 0);
    if (temp == 0)
      setZ();
    else
      resetZ();
    resetH();
    setPV(parity[temp]);
    resetN();
    // put value back

    return temp;
  }

  public Integer shiftGenericRLC(Integer temp) {

    temp = temp << 1;
    if ((temp & 0x0FF00) != 0) {
      setC();
      temp = temp | 0x01;
    } else
      resetC();
    // standard flag updates
    if ((temp & flag_S) == 0)
      resetS();
    else
      setS();
    if ((temp & 0x00FF) == 0)
      setZ();
    else
      resetZ();
    resetH();
    resetN();
    // put value back
    temp = temp & 0x00FF;
    setPV(parity[temp]);

    return temp;
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

  public Integer ALU8BitCp(Integer v, Integer reg_A) {
    return sbc8TableAluOperation.executeWithoutCarry(v, reg_A);
  }

  /* 8 bit CP */
  public Integer ALU8BitCp2(Integer b, Integer reg_A) {

    int a = reg_A;
    int wans = a - b;
    int ans = wans & 0xff;
    data = 0x02;
    setS((ans & flag_S) != 0);
    // if ( true ) setN();
    setZ(ans == 0);
    setC((wans & 0x100) != 0);
    setH((((a & 0x0f) - (b & 0x0f)) & flag_H) != 0);
    setPV(((a ^ b) & (a ^ ans) & 0x80) != 0);

    return wans;
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

  public void EXAFAF(int reg_A, int reg_A_ALT, int reg_F, int reg_F_ALT) {
    int temp;

    temp = reg_A;
    reg_A = reg_A_ALT;
    reg_A_ALT = temp;
    temp = reg_F;
    reg_F = reg_F_ALT;
    reg_F_ALT = temp;
  }

  public Integer CPL(Integer reg_A) {

    reg_A = (reg_A ^ 0x00FF) & 0x00FF;
    setH();
    setN();

    return reg_A;
  }

  public Integer RRCA(Integer reg_A) {

    boolean carry = (reg_A & 0x0001) != 0;

    reg_A = (reg_A >> 1);
    if (carry) {
      setC();
      reg_A = (reg_A | 0x0080);
    } else
      resetC();
    resetH();
    resetN();

    return reg_A;
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

  private static final int[] table8BitDec = new int[0x100];

  {
    for (int value1 = 0; value1 < 0x100; value1++) {
      data = 0x00;
      int value = value1;
      setHalfCarryFlagSub(value, 1);
      setPV(value == 0x80);
      value--;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setN();
      table8BitDec[value1] = data;
    }
  }

  /* 8 bit DEC */
  public Integer ALU8BitDec(Integer value) {
    data &= 0x01;
    data |= table8BitDec[value];
    return (value - 1) & 0x00ff;
  }

  /* 8 bit DEC */
  public Integer ALU8BitDec2(Integer value) {

    data &= 0x01;
    setHalfCarryFlagSub(value, 1);
    // setOverflowFlagSub(value, 1);
    setPV(value == 0x80);
    value--;
    setS((value & 0x0080) != 0);
    value = value & 0x00ff;
    setZ(value == 0);
    setN();

    return (value);
  }

  private static final int[] table8BitXor = new int[0x100];

  {
    for (int reg_A = 0; reg_A < 0x100; reg_A++) {
      data = 0;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      table8BitXor[reg_A] = data;
    }
  }

  /* 8 bit XOR (Version II) */
  public Integer ALU8BitXor(Integer value, Integer reg_A) {
    reg_A = reg_A ^ value;
    data = table8BitXor[reg_A];
    return reg_A;
  }

  /* 8 bit XOR (Version II) */
  public Integer ALU8BitXor2(Integer value, Integer reg_A) {

    data = 0;
    reg_A = reg_A ^ value;
    setS((reg_A & 0x0080) != 0);
    setZ(reg_A == 0);
    // resetH();
    setPV(parity[reg_A]);
    // resetN();
    // resetC();

    return reg_A;
  }

  private static final int[] table8BitAnd = new int[0x100];

  {
    for (int reg_A = 0; reg_A < 0x100; reg_A++) {
      data = 0x10;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      table8BitAnd[reg_A] = data;
    }
  }

  /* 8 bit AND (version II) */
  public Integer ALU8BitAnd(Integer reg_A, final Integer value) {
    reg_A = reg_A & value;
    data = table8BitAnd[reg_A];
    return reg_A;
  }

  public Integer ALU8BitAnd2(Integer value, Integer reg_A) {

    data = 0x10; // set the H flag
    reg_A = reg_A & value;
    setS((reg_A & 0x0080) != 0);
    setZ(reg_A == 0);
    // setH();
    setPV(parity[reg_A]);
    // resetN();
    // resetC();

    return reg_A;
  }

  private static final int[] table8BitOr = new int[0x100];

  {
    for (int reg_A = 0; reg_A < 0x100; reg_A++) {
      data = 0;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      table8BitOr[reg_A] = data;
    }
  }

  /* 8 bit OR (Version II) */
  public Integer ALU8BitOr(Integer A, final Integer value) {
    A = (A | value) & 0xff;
    data = booleanTable[A];
    return A;
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

    //
  }

  public Integer ALU8BitAdc(Integer value, Integer regA) {
    return adc8TableAluOperation.execute(value, regA);
  }

  public Integer ALU8BitAdd(Integer value, Integer regA) {
    return adc8TableAluOperation.executeWithoutCarry(value, regA);
  }

  public final Integer ALU8BitInc(final Integer value) {
    final int i = inc8Table[value & 0xFF];
    data = (data & 0x01) | i;
    return (value + 1) & 0xff;
  }

  /* 16 bit SBC */
  public Integer ALU16BitSBC(Integer DE, Integer HL) {

    int a = HL;
    int b = DE;
    int c = getC() ? 1 : 0;
    int lans = (a - b) - c;
    int ans = lans & 0xffff;
    setS((ans & (flag_S << 8)) != 0);
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

  /*
   * ALU Operations
   */

  /* half carry flag control */
  public final void setHalfCarryFlagAdd(int left, int right, int carry) {
    left = left & 0x000f;
    right = right & 0x000f;
    setH((right + left + carry) > 0x0f);
  }

  /* half carry flag control */
  /*
   * private final void setHalfCarryFlagAdd16(int left, int right, int carry) {
   * left = left & 0x0FFF; right = right & 0x0FFF; setH( (right + left + carry) >
   * 0x0FFF); }
   */
  /* half carry flag control */
  private final void setHalfCarryFlagAdd(int left, int right) {
    left = left & 0x000F;
    right = right & 0x000F;
    setH((right + left) > 0x0F);
  }

  /* half carry flag control */
  private final void setHalfCarryFlagSub(int left, int right) {
    left = left & 0x000F;
    right = right & 0x000F;
    setH(left < right);
  }

  /* half carry flag control */
  private final void setHalfCarryFlagSub(int left, int right, int carry) {
    left = left & 0x000F;
    right = right & 0x000F;
    setH(left < (right + carry));
  }

  /* half carry flag control */
  /*
   * private final void setHalfCarryFlagSub16(int left, int right, int carry) {
   * left = left & 0x0FFF; right = right & 0x0FFF; setH ( left < (right+carry) );
   * }
   */
  /* 2's compliment overflow flag control */
  public void setOverflowFlagAdd(int left, int right, int carry) {
    if (left > 127)
      left = left - 256;
    if (right > 127)
      right = right - 256;
    left = left + right + carry;
    setPV((left < -128) || (left > 127));
  }

  /* 2's compliment overflow flag control */
  private void setOverflowFlagAdd(int left, int right) {
    if (left > 127)
      left = left - 256;
    if (right > 127)
      right = right - 256;
    left = left + right;
    setPV((left < -128) || (left > 127));
  }

  /* 2's compliment overflow flag control */
  private void setOverflowFlagAdd16(int left, int right, int carry) {
    if (left > 32767)
      left = left - 65536;
    if (right > 32767)
      right = right - 65536;
    left = left + right + carry;
    setPV((left < -32768) || (left > 32767));
  }

  /* 2's compliment overflow flag control */
  private void setOverflowFlagSub(int left, int right, int carry) {
    if (left > 127)
      left = left - 256;
    if (right > 127)
      right = right - 256;
    left = left - right - carry;
    setPV((left < -128) || (left > 127));
  }

  /* 2's compliment overflow flag control */
  private void setOverflowFlagSub(int left, int right) {
    if (left > 127)
      left = left - 256;
    if (right > 127)
      right = right - 256;
    left = left - right;
    setPV((left < -128) || (left > 127));
  }

  /* 2's compliment overflow flag control */
  private void setOverflowFlagSub16(int left, int right, int carry) {
    if (left > 32767)
      left = left - 65536;
    if (right > 32767)
      right = right - 65536;
    left = left - right - carry;
    setPV((left < -32768) || (left > 32767));
  }

  /*
   * test & set flag states
   */
  private final boolean getS() {
    return ((data & flag_S) != 0);
  }

  @Override
  public boolean getZ() {
    return ((data & flag_Z) != 0);
  }

  private final boolean getH() {
    return ((data & flag_H) != 0);
  }

  private final boolean getPV() {
    return ((data & flag_PV) != 0);
  }

  private final boolean getN() {
    return ((data & flag_N) != 0);
  }

  public final boolean getC() {
    return ((data & flag_C) != 0);
  }

  private final void setS() {
    data = data | flag_S;
  }

  private final void setZ() {
    data = data | flag_Z;
  }

  private final void setH() {
    data = data | flag_H;
  }

  private final void setPV() {
    data = data | flag_PV;
  }

  private final void setN() {
    data = data | flag_N;
  }

  private final void setC() {
    data = data | flag_C;
  }

  private final void setS(boolean b) {
    if (b)
      setS();
    else
      resetS();
  }

  private final void setZ(boolean b) {
    if (b)
      setZ();
    else
      resetZ();
  }

  private final void setH(boolean b) {
    if (b)
      setH();
    else
      resetH();
  }

  public final void setPV(boolean b) {
    if (b)
      data = data | flag_PV;
    else
      data = data & flag_PV_N;
  }

  // private final void setN(boolean b) { if (b) setN(); else resetN(); }
  private final void setC(boolean b) {
    if (b)
      setC();
    else
      resetC();
  }

  private final void resetS() {
    data = data & flag_S_N;
  }

  private final void resetZ() {
    data = data & flag_Z_N;
  }

  public final void resetH() {
    data = data & flag_H_N;
  }

  private final void resetPV() {
    data = data & flag_PV_N;
  }

  public final void resetN() {
    data = data & flag_N_N;
  }

  private final void resetC() {
    data = data & flag_C_N;
  }
}