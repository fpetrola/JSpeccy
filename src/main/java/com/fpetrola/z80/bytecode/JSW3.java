package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int initial;
  public int[] memory;

  public void $9456() {
    byte B_L37974 = 0;
    int B_L38022 = B_L37974;
    int L_L37981 = this.initial;
    int HL_L37981 = this.initial;
    int DE_L37978 = this.initial;
    int C_L37976 = this.initial;
    int F_L37976 = this.initial;
    B_L37974 = 16;

    do {
      F_L37976 = C_L37976 & 1;
      int var8 = this.memory[DE_L37978];
      int HL_L37985 = 0;
      int H_L37985 = 0;
      int HL_L37984 = 0;
      int var56 = 0;
      int var101 = 0;
      if (F_L37976 != 0) {
        int var68 = this.memory[HL_L37981];
        int A_L37981 = var8 & var68;
        int var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        H_L37985 = var56;
        HL_L37985 = var101;
        if (A_L37981 != 0) {
          return;
        }

        int var73 = this.memory[DE_L37978];
        HL_L37984 = HL_L37981;
        int var75 = this.memory[HL_L37981];
        var10000 = var73 | var75;
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
      }

      int H_L38021 = this.initial;
      var101 = H_L38021 << 8 | L_L37981;
      this.memory[HL_L37985] = var8;
      int var16 = 0;
      int HL_L37997 = var16;
      int var18 = HL_L37985 & 255;
      int L_L37986 = var18 + 1;
      int DE_L37987 = DE_L37978 + 1;
      int F_L37988 = this.initial;
      F_L37988 = C_L37976 & 1;
      int var22 = this.memory[DE_L37987];
      if (F_L37988 != 0) {
        var56 = HL_L37981 >> 8;
        int var87 = HL_L37985 & 255;
        var16 = H_L37985 << 8 | L_L37986;
        int var58 = this.memory[var16];
        int A_L37993 = var22 & var58;
        var87 = this.memory[var16];
        var87 = this.memory[var16];
        var87 = this.memory[var16];
        if (A_L37993 != 0) {
          return;
        }

        int var63 = this.memory[DE_L37987];
        int var66 = this.memory[var16];
        var87 = var63 | var66;
        var87 = this.memory[var16];
        var87 = this.memory[var16];
        var87 = this.memory[var16];
      }

      this.memory[HL_L37997] = var22;
      int var27 = HL_L37997 & 255;
      int L_L37998 = var27 - 1;
      int var95 = HL_L37997 & 255;
      L_L37981 = L_L37998;
      int var29 = HL_L37997 >> 8;
      int H_L37999 = var29 + 1;
      int DE_L38000 = DE_L37987 + 1;
      DE_L37978 = DE_L38000;
      var95 = HL_L37997 >> 8;
      int A_L38002 = H_L37999 & 7;
      int F_L38002 = this.initial;
      if (A_L38002 == 0) {
        var95 = HL_L37997 >> 8;
        int A_L38007 = H_L37999 - 8;
        H_L37985 = A_L38007;
        var95 = HL_L37997 & 255;
        int A_L38011 = L_L37998 + 32;
        int var48 = HL_L37984 << 8;
        int var49 = A_L38011 & 255;
        HL_L37985 = var48 | var49;
        int A_L38014 = A_L38011 & 224;
        int F_L38014 = this.initial;
        if (A_L38014 == 0) {
          var95 = HL_L37997 >> 8;
          int A_L38019 = A_L38007 + 8;
          H_L37985 = A_L38019;
        }
      }

      --B_L38022;
    } while(B_L38022 != 0);

  }
}
