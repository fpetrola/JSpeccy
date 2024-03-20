package com.fpetrola.z80.registers.flag;

public class TableFlagRegisterInitTables extends TableFlagRegisterBase {
  protected final TableAluOperation cpTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
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

      return new Alu8BitResult(ans, data);
    }
  };
  protected final TableAluOperation orTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a | value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation xorTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
      data = 0;
      int reg_A = a ^ value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation andTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
      data = 0x10;
      int reg_A = a & value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation dec8TableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(value, data);
    }
  };

  protected final TableAluOperation inc8TableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
      data = carry;
      int value = a;
      setHalfCarryFlagAdd(value, 1);
      setPV(value == 0x7F);
      value++;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setUnusedFlags(value);

      return new Alu8BitResult(value, data);
    }
  };

  protected final TableAluOperation adc8TableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
      data = 0;
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
      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation sbc8TableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
      data = 0;
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

      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation sub8TableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int value, int carry) {
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

      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation negTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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
      return new Alu8BitResult(reg_A, data);
    }
  };

  protected final TableAluOperation rraTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation rlcTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation sraTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation sraTableAluOperation1 = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation rrcTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation rlcTableAluOperation1 = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation rrcaTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
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

      return new Alu8BitResult(a, data);
    }
  };

  protected final TableAluOperation rldTableAluOperation = new TableAluOperation(this) {
    public Alu8BitResult execute(int a, int carry) {
      data = 0;
      if ((a & 0x80) == 0)
        resetS();
      else
        setS();
      setZ(a == 0);
      resetH();
      setPV(parity[a]);
      resetN();
      return new Alu8BitResult(a, data);
    }
  };

  protected TableAluOperation ldiTableAluOperation = new TableAluOperation((bc, carry) -> {
    resetH();
    resetN();
    setPV(bc != 0);
    return new Alu8BitResult(bc, data);
  }, this);
  protected TableAluOperation iniTableAluOperation = new TableAluOperation((reg_B, carry) -> {
    setZ(reg_B == 0);
    setN();
    return new Alu8BitResult(reg_B, data);
  }, this);
  protected TableAluOperation outiTableAluOperation = new TableAluOperation((reg_B, carry) -> {
    reg_B = (reg_B - 1) & lsb;
    setZ(reg_B == 0);
    setN();
    return new Alu8BitResult(reg_B, data);
  }, this);
  protected TableAluOperation sllTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation ccfTableAluOperation = new TableAluOperation((reg_A, carry) -> {
    data = carry;
    if (getC())
      setH();
    else
      resetH();
    data = data ^ FLAG_C;
    resetN();
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation scfTableAluOperation = new TableAluOperation((reg_A, carry) -> {
    setC();
    resetH();
    resetN();
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation rlaTableAluOperation = new TableAluOperation((reg_A, carry1) -> {
    data = carry1;
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

    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation rlcaTableAluOperation = new TableAluOperation((reg_A, carry1) -> {
    data = carry1;
    boolean carry = (reg_A & 0x0080) != 0;
    reg_A = ((reg_A << 1) & 0x00FF);
    if (carry) {
      setC();
      reg_A = (reg_A | 0x0001);
    } else
      resetC();
    resetH();
    resetN();

    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation slaTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation rlTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation cplTableAluOperation = new TableAluOperation((reg_A, carry) -> {
    data = carry;
    reg_A = (reg_A ^ 0x00FF) & 0x00FF;
    setH();
    setN();
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation inCTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;
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
    return new Alu8BitResult(temp, data);
  }, this);
  protected AluOperation testBitTableAluOperation = new AluOperation((bit, value, carry) -> {
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

    return new Alu8BitResult(value, data);
  }, this);
  protected AluOperation add16TableAluOperation = new AluOperation((value2, value, carry) -> {
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
      return new Alu8BitResult((result & lsw), data);
    } else {
      resetC();
      return new Alu8BitResult(result, data);
    }
  }, this);

  protected AluOperation adc16TableAluOperation = new AluOperation((b, a, carry) -> {
    int c = carry;
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

    return new Alu8BitResult(ans, data);
  }, this);

  protected AluOperation sbc16TableAluOperation = new AluOperation((HL, DE, carry) -> {
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

    return new Alu8BitResult(ans, data);
  }, this);

  protected AluOperation ldarTableAluOperation = new TableAluOperation((reg_R, reg_A, carry) -> {
    reg_A = reg_R & 0x7F;
    setS((reg_A & FLAG_S) != 0);
    setZ(reg_A == 0);
    resetH();
    resetN();
    setPV(carry == 1);

    return new Alu8BitResult(reg_A, data);
  }, this);


  protected AluOperation cpiTableAluOperation = new TableAluOperation((reg_A, value, carry) -> {
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
//      result--;
//    if ((result & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((result & 0x00008) == 0)
//      reset3();
//    else
//      set3();

    return new Alu8BitResult(reg_A, data);
  }, this);

  protected AluOperation cpdTableAluOperation = new TableAluOperation((reg_A, value, carry) -> {
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
//      result--;
//    if ((result & 0x00002) == 0)
//      reset5();
//    else
//      set5();
//    if ((result & 0x00008) == 0)
//      reset3();
//    else
//      set3();

    return new Alu8BitResult(reg_A, data);
  }, this);
  protected AluOperation daaTableAluOperation = new DAATableAluOperation(this);

  public TableFlagRegisterInitTables(String name) {
    super(name);
  }
}
