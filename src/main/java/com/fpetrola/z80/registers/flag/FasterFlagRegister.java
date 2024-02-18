package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public class FasterFlagRegister extends FlagRegister {

  private int sz5h3pnFlags;
  private int carryFlag;
  private boolean flagQ;
  private static final int sz53n_addTable[] = new int[256];
  public static final int sz53pn_addTable[] = new int[256];
  private static final int sz53n_subTable[] = new int[256];
  private static final int sz53pn_subTable[] = new int[256];

  private static final int CARRY_MASK = 0x01;
  private static final int ADDSUB_MASK = 0x02;
  private static final int PARITY_MASK = 0x04;
  private static final int OVERFLOW_MASK = 0x04; // alias de PARITY_MASK
  private static final int BIT3_MASK = 0x08;
  private static final int HALFCARRY_MASK = 0x10;
  private static final int BIT5_MASK = 0x20;
  private static final int ZERO_MASK = 0x40;
  private static final int SIGN_MASK = 0x80;
  // Máscaras de conveniencia
  private static final int FLAG_53_MASK = BIT5_MASK | BIT3_MASK;
  private static final int FLAG_SZ_MASK = SIGN_MASK | ZERO_MASK;
  private static final int FLAG_SZHN_MASK = FLAG_SZ_MASK | HALFCARRY_MASK | ADDSUB_MASK;
  private static final int FLAG_SZP_MASK = FLAG_SZ_MASK | PARITY_MASK;
  private static final int FLAG_SZHP_MASK = FLAG_SZP_MASK | HALFCARRY_MASK;

  static {
    boolean evenBits;

    for (int idx = 0; idx < 256; idx++) {
      if (idx > 0x7f) {
        sz53n_addTable[idx] |= SIGN_MASK;
      }

      evenBits = true;
      for (int mask = 0x01; mask < 0x100; mask <<= 1) {
        if ((idx & mask) != 0) {
          evenBits = !evenBits;
        }
      }

      sz53n_addTable[idx] |= (idx & FLAG_53_MASK);
      sz53n_subTable[idx] = sz53n_addTable[idx] | ADDSUB_MASK;

      if (evenBits) {
        sz53pn_addTable[idx] = sz53n_addTable[idx] | PARITY_MASK;
        sz53pn_subTable[idx] = sz53n_subTable[idx] | PARITY_MASK;
      } else {
        sz53pn_addTable[idx] = sz53n_addTable[idx];
        sz53pn_subTable[idx] = sz53n_subTable[idx];
      }
    }

    sz53n_addTable[0] |= ZERO_MASK;
    sz53pn_addTable[0] |= ZERO_MASK;
    sz53n_subTable[0] |= ZERO_MASK;
    sz53pn_subTable[0] |= ZERO_MASK;
  }

  public FasterFlagRegister(RegisterName h) {
    super(h);
  }

  public void ldi(int regA, int work8, RegisterPair<Integer> bc) {
    work8 += regA;

    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZ_MASK) | (work8 & BIT3_MASK);

    if ((work8 & ADDSUB_MASK) != 0) {
      sz5h3pnFlags |= BIT5_MASK;
    }

    if (bc.getLow().read() != 0 || bc.getHigh().read() != 0) {
      sz5h3pnFlags |= PARITY_MASK;
    }
  }

  public int RRA(int regA) {
    carryFlag = data & 0x01;

    int oldCarry = carryFlag;
    carryFlag = (regA & CARRY_MASK);
    regA >>>= 1;
    if (oldCarry > 0) {
      regA |= SIGN_MASK;
    }
    sz5h3pnFlags = (sz5h3pnFlags & FLAG_SZP_MASK) | (regA & FLAG_53_MASK);
    flagQ = true;

    data = sz5h3pnFlags | carryFlag;
    return regA;
  }

  public int ALU8BitAdd(int value, int value2) {
    int res = value2 + value;

    carryFlag = (res > 0xff) ? 0x01 : 0x00;
    res &= 0xff;
    sz5h3pnFlags = sz53n_addTable[res];

    /*
     * El módulo 16 del resultado será menor que el módulo 16 del registro A si ha
     * habido HalfCarry. Sucede lo mismo para todos los métodos suma SIN carry
     */
    if ((res & 0x0f) < (value2 & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK;
    }

    if (((value2 ^ ~value) & (value2 ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK;
    }

    value2 = res;
    flagQ = true;

    data = sz5h3pnFlags | carryFlag;
    return res;
  }

  public int ALU8BitAnd(int oper8, int regA) {
    regA = regA & oper8;
    data = sz5h3pnFlags = sz53pn_addTable[regA] | HALFCARRY_MASK;
    flagQ = true;
    return regA;
  }

  public int ALU8BitXor(int oper8, int regA) {
    regA = (regA ^ oper8) & 0xff;
    data = sz5h3pnFlags = sz53pn_addTable[regA];
    flagQ = true;
    return regA;
  }

  public int ALU8BitOr(int oper8, int regA) {
    regA = (regA | oper8) & 0xff;
    data = sz5h3pnFlags = sz53pn_addTable[regA];
    flagQ = true;
    return regA;
  }

  public int ALU8BitDec(int oper8) {
    oper8 = (oper8 - 1) & 0xff;

    sz5h3pnFlags = sz53n_subTable[oper8];

    if ((oper8 & 0x0f) == 0x0f) {
      sz5h3pnFlags |= HALFCARRY_MASK;
    }

    if (oper8 == 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK;
    }

    flagQ = true;
    data = sz5h3pnFlags | carryFlag;

    return oper8;
  }

  public int ALU8BitInc(int value) {
    carryFlag = data & 0x01;
    value = (value + 1) & 0xff;

    sz5h3pnFlags = sz53n_addTable[value];

    if ((value & 0x0f) == 0) {
      sz5h3pnFlags |= HALFCARRY_MASK;
    }

    if (value == 0x80) {
      sz5h3pnFlags |= OVERFLOW_MASK;
    }

    flagQ = true;
    data = sz5h3pnFlags | carryFlag;

    return value;
  }

  @Override
  public Integer ALU8BitCp(Integer oper8, Integer regA) {
    int res = regA - (oper8 & 0xff);

    carryFlag = res < 0 ? 0x01 : 0x00;
    res &= 0xff;

    sz5h3pnFlags = (sz53n_addTable[oper8] & FLAG_53_MASK) | // No necesito preservar H, pero está a 0 en la tabla de todas formas
        (sz53n_subTable[res] & FLAG_SZHN_MASK);

    if ((res & 0x0f) > (regA & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK;
    }

    if (((regA ^ oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK;
    }

    flagQ = true;
    data = sz5h3pnFlags | carryFlag;
    return res;
  }

  @Override
  public Integer ALU8Assign(Integer value) {
    return value;
  }

  public int shiftGenericRR(int oper8) {
    carryFlag = oper8 & CARRY_MASK;
    oper8 >>>= 1;
    if ((data & 0x01) != 0) {
      oper8 |= SIGN_MASK;
    }
    sz5h3pnFlags = sz53pn_addTable[oper8];
    flagQ = true;
    data = sz5h3pnFlags | carryFlag;

    return oper8;
  }

  public int ALU8BitSub(int oper8, int regA) {
    int res = regA - oper8;

    carryFlag = res < 0 ? 0x01 : 0x00;
    res &= 0xff;
    sz5h3pnFlags = sz53n_subTable[res];

    /*
     * El módulo 16 del resultado será mayor que el módulo 16 del registro A si ha
     * habido HalfCarry. Sucede lo mismo para todos los métodos resta SIN carry,
     * incluido cp
     */
    if ((res & 0x0f) > (regA & 0x0f)) {
      sz5h3pnFlags |= HALFCARRY_MASK;
    }

    if (((regA ^ oper8) & (regA ^ res)) > 0x7f) {
      sz5h3pnFlags |= OVERFLOW_MASK;
    }

    flagQ = true;
    data = sz5h3pnFlags | carryFlag;

    return res;
  }
}