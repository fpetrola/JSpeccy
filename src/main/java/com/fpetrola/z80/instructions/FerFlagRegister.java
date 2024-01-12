package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class FerFlagRegister extends Base8080 implements IFlagRegister {
  public FerFlagRegister(String name) {
    super(name);
    // TODO Auto-generated constructor stub
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

  public void LDI(int reg_A, int value, int bc) {
    resetH();
    resetN();
    setPV(checkNotZero(bc));
    int temp = value + reg_A;
  }

  private boolean checkNotZero(int bc) {
    return bc != 0;
  }

  public void LDD(int reg_A, int hl, int bc) {
    resetH();
    resetN();
    setPV(checkNotZero(bc));
    int temp = reg_A + hl;
  }

  public int DAA(int reg_A) {

    int ans = reg_A;
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

  public int shiftGenericSLL(int temp) {

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

  public void CCF(int reg_A) {

    if (getC())
      setH();
    else
      resetH();
    flipC();
    resetN();

  }

  private final void flipC() {
    data = data ^ flag_C;
  }

  public void SCF(int reg_A) {
    setC();
    resetH();
    resetN();

  }

  public int RRA(int reg_A) {

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

  public int RLA(int reg_A) {

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
  public int ALU16BitAdd(int value, int value2) {

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

  public int RLCA(OpcodeReference target) {
    int reg_A = target.read();
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
  public int ALU8BitSbc(int value, int reg_A) {

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
  public int ALU16BitADC(int a, int b) {

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

  public int shiftGenericRR(int temp) {

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

  public int LDAR(int reg_A, int reg_R, boolean iff2) {

    reg_A = reg_R & 0x7F;
    setS((reg_A & flag_S) != 0);
    setZ(reg_A == 0);
    resetH();
    resetN();
    setPV(iff2);
    return reg_A;
  }

  public int shiftGenericSLA(int temp) {

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

  public int shiftGenericSRL(int temp) {

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

  public int shiftGenericSRA(int temp) {

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
  public int ALU8BitSub(int value, int reg_A) {

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

  public int NEG(int reg_A) {

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

  public int shiftGenericRRC(int temp) {

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

  public int shiftGenericRLC(int temp) {

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

  public void CPI(int value, int reg_A, int bcValue) {

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
    setPV(checkNotZero(bcValue));
    setN();
    //
    if (getH())
      result--;
  }

  public final int sbc8(int b, int c, int A) {
    int ans = (A - b - c) & 0xff;
//    data = sbc8Table[ans][A][c];
    return ans;
  }

  public final void cp(int v) {

  }

  public int ALU8BitCp(int v, int reg_A) {
    int ans = (reg_A - v) & 0xff;
    data = sbc8Table[(reg_A << 8) | ans];
    return ans;
  }

  /* 8 bit CP */
  public int ALU8BitCp2(int b, int reg_A) {

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

  public int shiftGenericRL(int temp) {

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

  public int EXAFAF(RegisterPair AF1, RegisterPair AF2) {

    Register F1 = AF1.getLow();
    Register F2 = AF2.getLow();

    Register A1 = AF1.getHigh();
    Register A2 = AF2.getHigh();

    int reg_A = A1.read();
    int reg_A_ALT = A2.read();
    data = F1.read();
    int data_ALT = F2.read();

    int temp;

    temp = reg_A;
    reg_A = reg_A_ALT;
    reg_A_ALT = temp;
    temp = data;
    data = data_ALT;
    data_ALT = temp;

    F1.write(data & 0xD7);
    F2.write(data_ALT & 0xD7);
    A1.write(reg_A);
    A2.write(reg_A_ALT);

    return reg_A;
  }

  public int CPL(int reg_A) {

    reg_A = (reg_A ^ 0x00FF) & 0x00FF;
    setH();
    setN();

    return reg_A;
  }

  public int RRCA(int reg_A) {

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
  public void inC(int temp) {

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
  public int ALU8BitDec2(int value) {
    data &= 0x01;
    data |= table8BitDec[value];
    return (value - 1) & 0x00ff;
  }

  /* 8 bit DEC */
  public int ALU8BitDec(int value) {

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
  public int ALU8BitXor(int value, int reg_A) {
    reg_A = reg_A ^ value;
    data = table8BitXor[reg_A];
    return reg_A;
  }

  /* 8 bit XOR (Version II) */
  public int ALU8BitXor2(int value, int reg_A) {

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
  public int ALU8BitAnd(final int value, int reg_A) {
    reg_A = reg_A & value;
    data = table8BitAnd[reg_A];
    return reg_A;
  }

  public int ALU8BitAnd2(int value, int reg_A) {

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
  public int ALU8BitOr(final int value, int A) {
    A = (A | value) & 0xff;
    data = booleanTable[A];
    return A;
  }

  public void testBit(int value, int bit) {

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

  public int ALU8BitAdc(int value, int regA) {
    return (data = adc8Table[(regA << 8) | value | (data & 0x01) << 16]) >> 16;
  }

  public int ALU8BitAdd(int value, int regA) {
    return (data = adc8Table[(regA << 8) | value]) >> 16;
  }

  public final int ALU8BitInc(final int value) {
    final int i = inc8Table[value];
    data = (data & 0x01) | i;
    return (value + 1) & 0xff;
  }

  /* 16 bit SBC */
  public int ALU16BitSBC(int HL, int DE) {

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

  private final boolean getZ() {
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