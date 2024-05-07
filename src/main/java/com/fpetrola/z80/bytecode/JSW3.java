package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int initial;
  public int[] memory;

  public void $9456() {
    int HL_L37981 = this.initial;
    int DE_L37978 = this.initial;
    int B_L37974 = 16;
    int C_L37976 = this.initial;
    int F_L37976 = this.initial;

    int var34 = 0;
    do {
      F_L37976 = C_L37976 & 1;
      int var9 = this.memory[DE_L37978];
      if (F_L37976 != 0) {
        HL_L37981 = HL_L37981;
        int var27 = this.memory[HL_L37981];
        var34 = var9 & var27;
        int var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        if (var34 != 0) {
          return;
        }

        var34 = this.memory[DE_L37978];
        int var28 = this.memory[HL_L37981];
        var34 |= var28;
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
        var10000 = this.memory[HL_L37981];
      }

      this.memory[HL_L37981] = var34;
      int L_L37981 = this.initial;
      ++L_L37981;
      HL_L37981 = L_L37981 & 255;
      DE_L37978 = DE_L37978 + 1;
      int F_L37982 = C_L37976 & 1;
      var34 = this.memory[DE_L37978];
      if (F_L37982 != 0) {
        int var25 = this.memory[HL_L37981];
        var34 &= var25;
        int var51 = this.memory[HL_L37981];
        var51 = this.memory[HL_L37981];
        var51 = this.memory[HL_L37981];
        if (var34 != 0) {
          return;
        }

        var34 = this.memory[DE_L37978];
        int var26 = this.memory[HL_L37981];
        var34 |= var26;
        var51 = this.memory[HL_L37981];
        var51 = this.memory[HL_L37981];
        var51 = this.memory[HL_L37981];
      }

      this.memory[HL_L37981] = var34;
      --L_L37981;
      HL_L37981 = L_L37981 & 255;
      HL_L37981 = L_L37981 & 255;
      int H_L37981 = this.initial;
      ++H_L37981;
      int var17 = H_L37981 << 8;
      int var18 = L_L37981 & 255;
      HL_L37981 = var17 | var18;
      ++DE_L37978;
      var34 = H_L37981 & 7;
      if (var34 == 0) {
        var34 = H_L37981 - 8;
        int var19 = var34 << 8;
        int var20 = L_L37981 & 255;
        int var57 = var19 | var20;
        int var39 = L_L37981 + 32;
        int var21 = var34 << 8;
        int var22 = var39 & 255;
        HL_L37981 = var21 | var22;
        var34 = var39 & 224;
        if (var34 == 0) {
          var34 += 8;
          int var23 = var34 << 8;
          int var24 = var39 & 255;
          HL_L37981 = var23 | var24;
        }
      }

      --B_L37974;
    } while(B_L37974 != 0);

    var34 ^= var34;
  }
}