package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.registers.Plain8BitRegister;
import com.fpetrola.z80.registers.RegisterName;

public class Base8080 extends Plain8BitRegister {

  /** Result is signed (negative) */
  public static final int FLAG_S = 0x80;

  /** Result is 0 */
  public static final int FLAG_Z = 0x40;

  /** Reserved copy bit 5 of result */
  public static final int FLAG_5 = 0x20;

  /** Half carry */
  public static final int FLAG_H = 0x10;

  /** Reserved copy bit 3 of result */
  public static final int FLAG_3 = 0x08;

  /** Parity / overflow */
  public static final int FLAG_PV = 0x04;

  /** Set if last aritmetis is SUB,SBC,DEC */
  public static final int FLAG_N = 0x02;

  /** Carry flag */
  public static final int FLAG_C = 0x01;
  static protected int dec8Table[] = new int[256];
  static protected int inc8Table[] = new int[256];
  static protected int adc8Table[] = new int[256 * 256 * 2];
  protected static int sbc8Table[] = new int[256 * 256 * 2];
  static protected int booleanTable[] = new int[256];

  protected static final boolean parityTable[] = { true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false,
      false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, };

  public Base8080(RegisterName name) {
    super(name);
  }

  static {
    int old, value, F;

    /**
     * Calculate table for flag
     */
    for (int i = 0; i < 256; i++) {
      /**
       * Calculate 8 bit decrement flags.
       */
      old = i;
      value = i;
      F = FLAG_N;
      if ((value == 0x80))
        F |= FLAG_PV;
      value = (value - 1) & 0xff;
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

      dec8Table[i] = F & 0xff;

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

      /**
       * Calculate flag for adc8
       */
      for (int j = 0; j < 256; j++) {
        for (int c = 0; c < 2; c++) {
          F = 0;
          int ans = i + j + c;
          if ((ans & 0x80) != 0)
            F |= FLAG_S;
          if ((ans & 0x100) != 0)
            F |= FLAG_C;
          if ((ans & 0xff) == 0)
            F |= FLAG_Z;
          if (((i ^ ans ^ j) & 0x10) != 0)
            F |= FLAG_H;
          if (((i ^ j ^ 0x80) & (j ^ ans) & 0x80) != 0)
            F |= FLAG_PV;
          if ((ans & 0x08) != 0)
            F |= FLAG_3;
          if ((ans & 0x20) != 0)
            F |= FLAG_5;

          adc8Table[((j & 0xff)) | (i << 8) | (c << 16)] = ((ans & 0xff) << 16) + F;
        }
      }

      /**
       * Calculate flag for sdc8
       */
      for (int j = 0; j < 256; j++) {
        for (int c = 0; c < 2; c++) {
          F = FLAG_N;
          int ans = i - j - c;
          if ((ans & 0x80) != 0)
            F |= FLAG_S;
          if ((ans & 0x100) != 0)
            F |= FLAG_C;
          if ((ans & 0xff) == 0)
            F |= FLAG_Z;
          if (((i ^ ans ^ j) & 0x10) != 0)
            F |= FLAG_H;
          if (((j ^ i) & (i ^ ans) & 0x80) != 0)
            F |= FLAG_PV;
          if ((ans & 0x08) != 0)
            F |= FLAG_3;
          if ((ans & 0x20) != 0)
            F |= FLAG_5;

          sbc8Table[((ans & 0xff)) | (i << 8) | (c << 16)] = F;

        }
      }

    }

  };
}