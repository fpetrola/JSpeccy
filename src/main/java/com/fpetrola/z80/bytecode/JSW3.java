package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int initial;
  public int[] memory;

  public void $9456() {
    int HL_L37981 = this.initial;
    int DE_L37978 = this.initial;
    int C_L37976 = this.initial;
    int F_L37976 = this.initial;
    int B_L37974 = 16;

    int A_L38024;
    do {
      F_L37976 = C_L37976 & 1;
      int A_L38010 = 0;
      A_L38024 = A_L38010;
      int var8 = this.memory[DE_L37978];
      int HL_L37985 = 0;
      int H_L37985 = 0;
      int var36 = 0;
      if (F_L37976 != 0) {
        int var42 = this.memory[HL_L37981];
        int A_L37978 = var8 & var42;
        int var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        H_L37985 = var36;
        HL_L37985 = HL_L37981;
        if (A_L37978 != 0) {
          return;
        }

        int var43 = this.memory[DE_L37978];
        int var45 = this.memory[HL_L37981];
        int A_L37983 = var43 | var45;
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
      }

      this.memory[HL_L37985] = var8;
      int var13 = 0;
      int HL_L37997 = var13;
      int var15 = HL_L37985 & 255;
      int L_L37985 = var15 + 1;
      int DE_L37987 = DE_L37978 + 1;
      int F_L37986 = this.initial;
      F_L37986 = C_L37976 & 1;
      int var18 = this.memory[DE_L37987];
      if (F_L37986 != 0) {
        var36 = HL_L37981 >> 8;
        var13 = H_L37985 << 8 | L_L37985;
        int var38 = this.memory[var13];
        int A_L37990 = var18 & var38;
        int var57 = this.memory[var13];
        var57 = this.memory[var13];
        var57 = this.memory[var13];
        if (A_L37990 != 0) {
          return;
        }

        int var39 = this.memory[DE_L37987];
        int var41 = this.memory[var13];
        int A_L37995 = var39 | var41;
        var57 = this.memory[var13];
        var57 = this.memory[var13];
        var57 = this.memory[var13];
      }

      this.memory[HL_L37997] = var18;
      int var22 = HL_L37997 & 255;
      int L_L37997 = var22 - 1;
      HL_L37981 = L_L37997 & 255;
      HL_L37981 = L_L37997 & 255;
      int var25 = HL_L37997 >> 8;
      int H_L37997 = var25 + 1;
      int var27 = H_L37997 << 8;
      int var28 = L_L37997 & 255;
      HL_L37981 = var27 | var28;
      int DE_L38000 = DE_L37987 + 1;
      DE_L37978 = DE_L38000;
      int A_L38001 = H_L37997 & 7;
      if (A_L38001 == 0) {
        int A_L38006 = H_L37997 - 8;
        A_L38010 = L_L37997 + 32;
        HL_L37985 = A_L38010 & 255;
        A_L38010 &= 224;
        if (A_L38010 == 0) {
          int A_L38018 = A_L38006 + 8;
          H_L37985 = A_L38018;
        }
      }

      --B_L37974;
    } while (B_L37974 != 0);

    A_L38024 ^= A_L38024;
  }
}