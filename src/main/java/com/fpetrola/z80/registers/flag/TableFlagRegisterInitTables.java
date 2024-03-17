package com.fpetrola.z80.registers.flag;

public class TableFlagRegisterInitTables extends TableFlagRegisterBase {
  protected final TableAluOperation cpTableAluOperation;
  protected final TableAluOperation orTableAluOperation;
  protected final TableAluOperation xorTableAluOperation;
  protected final TableAluOperation andTableAluOperation;
  protected final TableAluOperation dec8TableAluOperation;
  protected final TableAluOperation inc8TableAluOperation;
  protected final TableAluOperation adc8TableAluOperation;
  protected final TableAluOperation sbc8TableAluOperation;
  protected final TableAluOperation sub8TableAluOperation;

  public TableFlagRegisterInitTables(String name) {
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
      data = 0;
      int local_reg_A = a;
      setC(carry == 1);

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
      int reg_A = local_reg_A;
      setUnusedFlags(reg_A);

      return new Alu8BitResult(reg_A, data);
    }, this);

    cpTableAluOperation = new TableAluOperation((a, value, carry) -> {
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

    inc8TableAluOperation = new TableAluOperation((a, carry) -> {
      data = 0;
      int value = a;
      setC(carry == 1);
      if (getC())
        data = 0x01;
      else
        data = 0x00;
      setHalfCarryFlagAdd(value, 1);
      setPV(value == 0x7F);
      value++;
      setS((value & 0x0080) != 0);
      value = value & 0x00ff;
      setZ(value == 0);
      setUnusedFlags(value);

      return new Alu8BitResult(value, data);
    }, this);

    dec8TableAluOperation = new TableAluOperation((a, carry) -> {
      data = 0;
      int value = a;
      setC(carry == 1);
      if (getC())
        data = 0x01;
      else
        data = 0x00;
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

    orTableAluOperation = new TableAluOperation((a, value, carry) -> {
      data = 0;
      int reg_A = a | value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }, this);

    xorTableAluOperation = new TableAluOperation((a, value, carry) -> {
      data = 0;
      int reg_A = a ^ value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }, this);

    andTableAluOperation = new TableAluOperation((a, value, carry) -> {
      data = 0x10;
      int reg_A = a & value;
      setS((reg_A & 0x0080) != 0);
      setZ(reg_A == 0);
      setPV(parity[reg_A]);
      setUnusedFlags(reg_A);
      return new Alu8BitResult(reg_A, data);
    }, this);

    sub8TableAluOperation = new TableAluOperation((a, value, carry) -> {
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
  }
}
