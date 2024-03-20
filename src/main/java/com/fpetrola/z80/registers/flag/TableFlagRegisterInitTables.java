package com.fpetrola.z80.registers.flag;

public class TableFlagRegisterInitTables extends TableFlagRegisterBase {
  protected final TableAluOperation cpTableAluOperation = new TableAluOperation((a, value, carry) -> {
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
  }, this);
  protected final TableAluOperation orTableAluOperation = new TableAluOperation((a, value, carry) -> {
    data = 0;
    int reg_A = a | value;
    setS((reg_A & 0x0080) != 0);
    setZ(reg_A == 0);
    setPV(parity[reg_A]);
    setUnusedFlags(reg_A);
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected final TableAluOperation xorTableAluOperation = new TableAluOperation((a, value, carry) -> {
    data = 0;
    int reg_A = a ^ value;
    setS((reg_A & 0x0080) != 0);
    setZ(reg_A == 0);
    setPV(parity[reg_A]);
    setUnusedFlags(reg_A);
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected final TableAluOperation andTableAluOperation = new TableAluOperation((a, value, carry) -> {
    data = 0x10;
    int reg_A = a & value;
    setS((reg_A & 0x0080) != 0);
    setZ(reg_A == 0);
    setPV(parity[reg_A]);
    setUnusedFlags(reg_A);
    return new Alu8BitResult(reg_A, data);
  }, this);
  protected final TableAluOperation dec8TableAluOperation = new TableAluOperation((a, carry) -> {
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
  }, this);
  protected final TableAluOperation inc8TableAluOperation = new TableAluOperation((a, carry) -> {
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
  }, this);
  protected final TableAluOperation adc8TableAluOperation = new TableAluOperation((a, value, carry) -> {
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
  }, this);
  protected final TableAluOperation sbc8TableAluOperation = new TableAluOperation((a, value, carry) -> {
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
  }, this);
  protected final TableAluOperation sub8TableAluOperation = new TableAluOperation((a, value, carry) -> {
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
  }, this);
  protected final TableAluOperation negTableAluOperation = new TableAluOperation((a, carry) -> {
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
  }, this);

  protected TableAluOperation rraTableAluOperation = new TableAluOperation((reg_A, carry1) -> {
    data = carry1;
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

    return new Alu8BitResult(reg_A, data);
  }, this);
  protected TableAluOperation rlcTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation sraTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation sraTableAluOperation1 = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation rrcTableAluOperation = new TableAluOperation((temp, carry1) -> {
    data = carry1;

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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation rlcTableAluOperation1 = new TableAluOperation((temp, carry1) -> {
    data = carry1;

    temp = temp << 1;
    if ((temp & 0x0FF00) != 0) {
      setC();
      temp = temp | 0x01;
    } else
      resetC();
    // standard flag updates
    if ((temp & FLAG_S) == 0)
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

    return new Alu8BitResult(temp, data);
  }, this);
  protected TableAluOperation rrcaTableAluOperation = new TableAluOperation((reg_A, carry1) -> {
    data = carry1;
    boolean carry = (reg_A & 0x0001) != 0;

    reg_A = (reg_A >> 1);
    if (carry) {
      setC();
      reg_A = (reg_A | 0x0080);
    } else
      resetC();
    resetH();
    resetN();

    return new Alu8BitResult(reg_A, data);
  }, this);

  protected TableAluOperation rldTableAluOperation = new TableAluOperation((reg_A, carry) -> {
    if ((reg_A & 0x80) == 0)
      resetS();
    else
      setS();
    setZ(reg_A == 0);
    resetH();
    setPV(parity[reg_A]);
    resetN();
    return new Alu8BitResult(reg_A, data);
  }, this);

  protected TableAluOperation rldTableAluOperation1 = new TableAluOperation((bc, carry) -> {
    resetH();
    resetN();
    setPV(checkNotZero(bc));
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
    flipC();
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


  public TableFlagRegisterInitTables(String name) {
    super(name);

  }

  protected boolean checkNotZero(int bc) {
    return bc != 0;
  }

  private void flipC() {
    data = data ^ FLAG_C;
  }
}
