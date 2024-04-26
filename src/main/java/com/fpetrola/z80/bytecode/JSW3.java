package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int[] memory;

  public void $9456() {
    byte B_L37974 = 0;
    int B_L38022 = B_L37974;
    int L_L37981 = 12345;
    int HL_L37981 = 12345;
    int DE_L37978 = 12345;
    B_L37974 = 16;

    do {
      int var6 = this.memory[DE_L37978];
      int F_L37976 = 12345;
      int HL_L37985 = 0;
      int F_L37986 = 0;
      int var36 = 0;
      int H_L37985 = 0;
      int var63 = 0;
      if (F_L37976 != 0) {
        F_L37986 = F_L37976;
        int var41 = this.memory[HL_L37981];
        int A_L37978 = var6 & var41;
        int var10000 = this.memory[HL_L37981];
        H_L37985 = var36;
        HL_L37985 = var63;
        A_L37978 = this.memory[DE_L37978];
        int var42 = this.memory[HL_L37981];
        var10000 = A_L37978 | var42;
        var10000 = this.memory[HL_L37981];
      }

      int H_L37997 = 12345;
      var63 = H_L37997 << 8 | L_L37981;
      this.memory[HL_L37985] = var6;
      int var14 = 0;
      int HL_L37997 = var14;
      int var16 = HL_L37985 & 255;
      int L_L37985 = var16 + 1;
      int DE_L37987 = DE_L37978 + 1;
      int A_L37985 = this.memory[DE_L37987];
      if (F_L37986 != 0) {
        var36 = HL_L37981 >> 8;
        var14 = H_L37985 << 8 | L_L37985;
        int var39 = this.memory[var14];
        int var55 = A_L37985 & var39;
        var55 = this.memory[var14];
        A_L37985 = this.memory[DE_L37987];
        int var40 = this.memory[var14];
        var55 = A_L37985 | var40;
        var55 = this.memory[var14];
      }

      this.memory[HL_L37997] = A_L37985;
      int var21 = HL_L37997 & 255;
      int L_L37997 = var21 - 1;
      int var23 = H_L37997 << 8;
      int var24 = L_L37997 & 255;
      int var59 = var23 | var24;
      int var26 = H_L37997 << 8;
      int var27 = L_L37997 & 255;
      var59 = var26 | var27;
      ++H_L37997;
      int var28 = H_L37997 << 8;
      int var29 = L_L37997 & 255;
      HL_L37981 = var28 | var29;
      int DE_L38000 = DE_L37987 + 1;
      DE_L37978 = DE_L38000;
      int var48 = A_L37985 & 7;
      if (L_L37997 == 0) {
        var48 -= 8;
        int var30 = H_L37997 << 8;
        int var31 = L_L37997 & 255;
        var59 = var30 | var31;
        var48 += 32;
        int var32 = H_L37997 << 8;
        int var33 = L_L37997 & 255;
        HL_L37981 = var32 | var33;
        var48 &= 224;
        if (L_L37997 == 0) {
          var48 += 8;
          int var34 = H_L37997 << 8;
          int var35 = L_L37997 & 255;
          HL_L37981 = var34 | var35;
        }
      }

      --B_L38022;
    } while (B_L38022 != 0);

  }
}
