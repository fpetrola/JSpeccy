package com.fpetrola.z80.registers.flag;

public class Base8080 extends Integer8BitRegister {
  public static final int FLAG_S = 0x80;
  public static final int FLAG_Z = 0x40;
  public static final int FLAG_5 = 0x20;
  public static final int FLAG_H = 0x10;
  public static final int FLAG_3 = 0x08;
  public static final int FLAG_PV = 0x04;
  public static final int FLAG_N = 0x02;
  public static final int FLAG_C = 0x01;
  static protected int inc8Table[] = new int[256];
  static protected int booleanTable[] = new int[256];

  protected static final boolean parityTable[] = {true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false,
      false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true,};

  public Base8080(String name) {
    super(name);
  }

  static {
    int old, value, F;

    /**
     * Calculate table for flag
     */
    for (int i = 0; i < 256; i++) {
      /**
       * Calculate 8 bit increment flags.
       */
      F = 0;
      old = i;
      value = i;

      if ((value == 0x7f))
        F |= FLAG_PV;
      value = (value + 1) & 0xff;
      if ((value & 0x80) != 0)
        F |= FLAG_S;
      if (value == 0)
        F |= FLAG_Z;
      if ((value & 0x08) != 0)
        F |= FLAG_3;
      if ((value & 0x20) != 0)
        F |= FLAG_5;

      if (((old ^ value) & 0x10) != 0)
        F |= FLAG_H;

      inc8Table[i] = F & 0xff;

      /**
       * Calculate flag for boolean operation
       */
      F = 0;
      if ((i & 0x80) != 0)
        F |= FLAG_S;
      if (i == 0)
        F |= FLAG_Z;
      if (parityTable[i])
        F |= FLAG_PV;

      if ((i & 0x08) != 0)
        F |= FLAG_3;

      if ((i & 0x20) != 0)
        F |= FLAG_5;

      booleanTable[i] = F & 0xff;
    }
  }

  ;
}