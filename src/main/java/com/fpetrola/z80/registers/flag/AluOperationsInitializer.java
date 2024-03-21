package com.fpetrola.z80.registers.flag;

public class AluOperationsInitializer {
  public static final TableAluOperation cpTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      int b = value;
      int wans = a - b;
      int ans = wans & 0xff;
      data = 0x02;
      setS((ans & FLAG_S) != 0);
      set3((b & FLAG_3) != 0);
      set5((b & FLAG_5) != 0);
      setZ(ans == 0);
      setC((wans & 0x100) != 0);
      setH((((a & 0x0f) - (b & 0x0f)) & FLAG_H) != 0);
      setPV(((a ^ b) & (a ^ ans) & 0x80) != 0);

      return new AluResult(ans, data);
    }
  };

  public static final TableAluOperation xorTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a ^ value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new AluResult(reg_A, data);
    }
  };

  public static final TableAluOperation dec8TableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      int value = a;
      setHalfCarryFlagSub(value, 1);
      setPV(value == 0x80);
      value--;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setN();
      setUnusedFlags(value);

      return new AluResult(value, data);
    }
  };

  public static final TableAluOperation inc8TableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      int value = a;
      setHalfCarryFlagAdd(value, 1);
      setPV(value == 0x7F);
      value++;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setUnusedFlags(value);

      return new AluResult(value, data);
    }
  };

  public static final TableAluOperation adc8TableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      data = carry;
      int reg_A = a;
      int local_reg_A = reg_A;
      setHalfCarryFlagAdd(local_reg_A, value, carry);
      setOverflowFlagAdd(local_reg_A, value, carry);
      local_reg_A = local_reg_A + value + carry;
      setS((local_reg_A & 0x0080) != 0);
      setC((local_reg_A & 0xff00) != 0);
      local_reg_A = local_reg_A & 0x00ff;
      setZ(local_reg_A == 0);
      resetN();
      reg_A = local_reg_A;
      setUnusedFlags(reg_A);
      return new AluResult(reg_A, data);
    }
  };

  public static final TableAluOperation sbc8TableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      data = carry;
      int local_reg_A = a;
      setHalfCarryFlagSub(local_reg_A, value, carry);
      setOverflowFlagSub(local_reg_A, value, carry);
      local_reg_A = local_reg_A - value - carry;
      setS((local_reg_A & 0x0080) != 0);
      setC((local_reg_A & 0xff00) != 0);
      local_reg_A = local_reg_A & 0x00ff;
      setZ(local_reg_A == 0);
      setN();
      int reg_A = local_reg_A;
      setUnusedFlags(reg_A);

      return new AluResult(reg_A, data);
    }
  };

  public static final TableAluOperation sub8TableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a;
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
      setUnusedFlags(reg_A);

      return new AluResult(reg_A, data);
    }
  };

  public static final TableAluOperation negTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = 0;
      int reg_A = a;
      setHalfCarryFlagSub(0, reg_A, 0);
      setOverflowFlagSub(0, reg_A, 0);
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
      setUnusedFlags(reg_A);
      return new AluResult(reg_A, data);
    }
  };

  public static final TableAluOperation rraTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x01) != 0;

      a = (a >> 1);
      if (getC())
        a = (a | 0x0080);
      if (c)
        setC();
      else
        resetC();
      resetH();
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rlcTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      boolean tempC;
      // do shift operation
      tempC = getC();
      setC((a & 0x0001) != 0);
      a = a >> 1;
      if (tempC)
        a = a | 0x80;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if (a == 0)
        setZ();
      else
        resetZ();
      resetH();
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation sraTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      setC((a & 0x0001) != 0);
      a = a >> 1;
      // standard flag updates
      setS((a & 0x0080) != 0);
      setZ(a == 0);
      resetH();
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation sraTableAluOperation1 = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      setC((a & 0x0001) != 0);
      if ((a & 0x0080) == 0) {
        a = a >> 1;
        resetS();
      } else {
        a = (a >> 1) | 0x0080;
        setS();
      }
      // standard flag updates
      if (a == 0)
        setZ();
      else
        resetZ();
      resetH();
      setPV(parity[a]);
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rrcTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      setC((a & 0x0001) != 0);
      a = a >> 1;
      if (getC())
        a = a | 0x80;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if (a == 0)
        setZ();
      else
        resetZ();
      resetH();
      setPV(parity[a]);
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rlcTableAluOperation1 = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      a = a << 1;
      if ((a & 0x0FF00) != 0) {
        setC();
        a = a | 0x01;
      } else
        resetC();
      // standard flag updates
      if ((a & FLAG_S) == 0)
        resetS();
      else
        setS();
      if ((a & 0x00FF) == 0)
        setZ();
      else
        resetZ();
      resetH();
      resetN();
      // put value back
      a = a & 0x00FF;
      setPV(parity[a]);

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rrcaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x0001) != 0;

      a = (a >> 1);
      if (c) {
        setC();
        a = (a | 0x0080);
      } else
        resetC();
      resetH();
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rldTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = 0;
      if ((a & 0x80) == 0)
        resetS();
      else
        setS();
      setZ(a == 0);
      resetH();
      setPV(parity[a]);
      resetN();
      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation ldiTableAluOperation = new TableAluOperation() {
    public AluResult execute(int bc, int carry) {
      data = 0;
      resetH();
      resetN();
      setPV(bc != 0);
      return new AluResult(bc, data);
    }
  };

  public static final TableAluOperation iniTableAluOperation = new TableAluOperation() {
    public AluResult execute(int b, int carry) {
      data = 0;
      setZ(b == 0);
      setN();
      return new AluResult(b, data);
    }
  };

  public static final TableAluOperation outiTableAluOperation = new TableAluOperation() {
    public AluResult execute(int b, int carry) {
      b = (b - 1) & lsb;
      setZ(b == 0);
      setN();
      return new AluResult(b, data);
    }
  };

  public static final TableAluOperation sllTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      a = (a << 1) | 0x01;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if ((a & 0x00FF) == 0)
        setZ();
      else
        resetZ();
      resetH();
      if ((a & 0x0FF00) != 0)
        setC();
      else
        resetC();
      a = a & 0x00FF;
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation ccfTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      if (getC())
        setH();
      else
        resetH();
      data = data ^ FLAG_C;
      resetN();
      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation scfTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      setC();
      resetH();
      resetN();
      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rlaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x0080) != 0;

      a = ((a << 1) & 0x00FF);
      if (getC())
        a = a | 0x01;
      if (c)
        setC();
      else
        resetC();
      resetH();
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rlcaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      boolean c = (a & 0x0080) != 0;
      a = ((a << 1) & 0x00FF);
      if (c) {
        setC();
        a = (a | 0x0001);
      } else
        resetC();
      resetH();
      resetN();

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation slaTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      a = a << 1;
      // standard flag updates
      setS((a & 0x0080) != 0);
      if ((a & 0x00FF) == 0)
        setZ();
      else
        resetZ();
      resetH();
      if ((a & 0x0FF00) != 0)
        setC();
      else
        resetC();
      a = a & 0x00FF;
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation rlTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;

      // do shift operation
      a = a << 1;
      if (getC())
        a = a | 0x01;
      // standard flag updates
      setS((a & 0x0080) != 0);
      resetH();
      if ((a & 0x0FF00) == 0)
        resetC();
      else
        setC();
      a = a & lsb;
      if ((a & 0x00FF) == 0)
        setZ();
      else
        resetZ();
      setPV(parity[a]);
      resetN();
      // put value back

      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation cplTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      a = (a ^ 0x00FF) & 0x00FF;
      setH();
      setN();
      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation inCTableAluOperation = new TableAluOperation() {
    public AluResult execute(int a, int carry) {
      data = carry;
      if ((a & 0x0080) == 0)
        resetS();
      else
        setS();
      if (a == 0)
        setZ();
      else
        resetZ();
      if (parity[a])
        setPV();
      else
        resetPV();
      resetN();
      resetH();
      return new AluResult(a, data);
    }
  };

  public static final TableAluOperation testBitTableAluOperation = new TableAluOperation() {
    public AluResult execute(int bit, int value, int carry) {
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
          setS(value != 0);
          break;
        }
      }
      setZ(0 == value);
      setPV(0 == value);
      resetN();
      setH();

      return new AluResult(value, data);
    }
  };

  public static final AluOperation add16TableAluOperation = new AluOperation() {
    public AluResult execute(int value2, int value, int carry) {
      data = carry;
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
        return new AluResult((result & lsw), data);
      } else {
        resetC();
        return new AluResult(result, data);
      }
    }
  };

  public static final AluOperation adc16TableAluOperation = new AluOperation() {
    public AluResult execute(int b, int a, int carry) {
      data = carry;
      int c = carry;
      int lans = a + b + c;
      int ans = lans & 0xffff;
      setS((ans & (FLAG_S << 8)) != 0);
      setZ(ans == 0);
      setC(lans > 0xFFFF);
      // setPV( ((a ^ b) & (a ^ value) & 0x8000)!=0 );
      setOverflowFlagAdd16(a, b, c);
      if ((((a & 0x0fff) + (b & 0x0fff) + c) & 0x1000) != 0)
        setH();
      else
        resetH();
      resetN();

      return new AluResult(ans, data);
    }
  };

  public static final AluOperation sbc16TableAluOperation = new AluOperation() {
    public AluResult execute(int HL, int DE, int carry) {
      data = carry;
      int a = HL;
      int b = DE;
      int c = getC() ? 1 : 0;
      int lans = (a - b) - c;
      int ans = lans & 0xffff;
      setS((ans & (FLAG_S << 8)) != 0);
      setZ(ans == 0);
      setC(lans < 0);
      // setPV( ((a ^ b) & (a ^ value) & 0x8000)!=0 );
      setOverflowFlagSub16(a, b, c);
      if ((((a & 0x0fff) - (b & 0x0fff) - c) & 0x1000) != 0)
        setH();
      else
        resetH();
      setN();

      return new AluResult(ans, data);
    }
  };

  public static final AluOperation ldarTableAluOperation = new AluOperation() {
    public AluResult execute(int reg_R, int reg_A, int carry) {
      reg_A = reg_R & 0x7F;
      setS((reg_A & FLAG_S) != 0);
      setZ(reg_A == 0);
      resetH();
      resetN();
      setPV(carry == 1);

      return new AluResult(reg_A, data);
    }
  };

  public static final AluOperation cpiTableAluOperation = new AluOperation() {
    public AluResult execute(int reg_A, int value, int carry) {
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
      setPV(carry == 1);
      setN();
      //
//    if (getH())
//      value--;
//    if ((value & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((value & 0x00008) == 0)
//      reset3();
//    else
//      set3();

      return new AluResult(reg_A, data);
    }
  };

  public static final AluOperation cpdTableAluOperation = new AluOperation() {
    public AluResult execute(int reg_A, int value, int carry) {
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
      setPV(carry == 1);
      setN();
      //
//    if (getH())
//      value--;
//    if ((value & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((value & 0x00008) == 0)
//      reset3();
//    else
//      set3();

      return new AluResult(reg_A, data);
    }
  };

  public static AluOperation daaTableAluOperation = new AluOperation() {
    public AluResult execute(int registerA, int carry, int flags) {
      // pc:4
      // The following algorithm is from comp.sys.sinclair's FAQ.
      int c, d;

      if (registerA > 0x99 || ((flags & FLAG_C) != 0)) {
        c = FLAG_C;
        d = 0x60;
      } else {
        c = d = 0;
      }

      if ((registerA & 0x0f) > 0x09 || ((flags & FLAG_H) != 0)) {
        d += 0x06;
      }

      int regA = ((flags & FLAG_N) != 0 ? registerA - d : registerA + d) & 0xFF;
      flags = TABLE_SZ[regA]
          | PARITY_TABLE[regA]
          | TABLE_XY[regA]
          | ((regA ^ registerA) & FLAG_H)
          | (flags & FLAG_N)
          | c;
      int Q = flags;

      return new AluResult(regA, flags);
    }
  };
}
