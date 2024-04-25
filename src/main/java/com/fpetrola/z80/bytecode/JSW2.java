package com.fpetrola.z80.bytecode;

import java.util.Arrays;

public class JSW2 {
  public int[] memory;

  public void $90C0() {
    int IX_L37056 = '脀';
    int IX_L37267 = IX_L37056;
    int IX_L37305 = IX_L37267;
    int IX_L37294 = IX_L37267;
    int IX_L37060 = IX_L37056;
    IX_L37267 = (int)IX_L37060;
    int IX_L37149 = (int)IX_L37060;
    IX_L37060 = IX_L37056;

    while(true) {
      int var7 = IX_L37060 + 0;
      int var8 = this.memory[var7];
      int F = 0;
      int F_L37063 = var8 - 255;
      int A_L37060 = var8 & 3;
      if (F_L37063 != 0) {
        F_L37063 = A_L37060 - 1;
        if (F_L37063 != 0) {
          F_L37063 = A_L37060 - 2;
          if (F_L37063 != 0) {
            int var108 = IX_L37060 + 0;
            if (F_L37063 != 0) {
              int var60 = IX_L37060 + 1;
              A_L37060 = this.memory[var60];
              if (F_L37063 != 0) {
                F_L37063 = A_L37060 - 148;
                if (F_L37063 < 0) {
                  F_L37063 = A_L37060 - 128;
                  if (F_L37063 == 0) {
                    A_L37060 ^= A_L37060;
                  }
                }
              } else {
                A_L37060 += 2;
                F_L37063 = A_L37060 - 18;
                if (F_L37063 < 0) {
                  A_L37060 += 2;
                }
              }
            } else {
              int var52 = IX_L37060 + 1;
              A_L37060 = this.memory[var52];
              if (F_L37063 == 0) {
                F_L37063 = A_L37060 - 20;
                if (F_L37063 < 0) {
                  A_L37060 |= A_L37060;
                  if (F_L37063 == 0) {
                  }
                }
              } else {
                A_L37060 += 2;
                F_L37063 = A_L37060 - 146;
                if (F_L37063 < 0) {
                  A_L37060 += 2;
                }
              }
            }

            int var53 = IX_L37149 + 1;
            this.memory[var53] = A_L37060;
            int var96 = A_L37060 & 127;
            int var55 = IX_L37149 + 7;
            int var56 = this.memory[var55];
            int F_L37152 = var96 - var56;
            int var57 = IX_L37149 + 7;
            var108 = this.memory[var57];
            if (F_L37152 == 0) {
              int var58 = IX_L37149 + 0;
              var96 = this.memory[var58];
              var96 ^= 128;
              int var59 = IX_L37149 + 0;
              this.memory[var59] = var96;
            }
          } else {
            label76: {
              int var28 = IX_L37060 + 0;
              A_L37060 = this.memory[var28];
              A_L37060 ^= 8;
              int var29 = IX_L37060 + 0;
              this.memory[var29] = A_L37060;
              A_L37060 &= 24;
              if (F_L37063 != 0) {
                int var50 = IX_L37060 + 0;
                A_L37060 = this.memory[var50];
                A_L37060 += 32;
                int var51 = IX_L37060 + 0;
                this.memory[var51] = A_L37060;
              }

              int var30 = IX_L37267 + 3;
              int var31 = this.memory[var30];
              int var34 = IX_L37267 + 4;
              int var35 = this.memory[var34];
              int A_L37267 = var31 + var35;
              int var36 = IX_L37267 + 4;
              int var105 = this.memory[var36];
              int var37 = IX_L37267 + 3;
              this.memory[var37] = A_L37267;
              int var38 = IX_L37267 + 7;
              int var39 = this.memory[var38];
              int F_L37270 = A_L37267 - var39;
              int var40 = IX_L37267 + 7;
              var105 = this.memory[var40];
              if (F_L37270 < 0) {
                int var45 = IX_L37267 + 6;
                int var46 = this.memory[var45];
                F_L37270 = A_L37267 - var46;
                int var47 = IX_L37267 + 6;
                var105 = this.memory[var47];
                if (F_L37270 != 0 && F_L37270 >= 0) {
                  break label76;
                }

                int var48 = IX_L37267 + 6;
                A_L37267 = this.memory[var48];
                int var49 = IX_L37267 + 3;
                this.memory[var49] = A_L37267;
              }

              int var41 = IX_L37294 + 4;
              int var42 = this.memory[var41];
              int var44 = IX_L37294 + 4;
              this.memory[var44] = var42;
            }
          }
        } else {
          int var10000 = IX_L37060 + 0;
          if (F_L37063 == 0) {
            int var20 = IX_L37060 + 0;
            A_L37060 = this.memory[var20];
            A_L37060 &= 127;
            int var21 = IX_L37060 + 0;
            this.memory[var21] = A_L37060;
            F_L37063 = A_L37060 - 96;
            if (F_L37063 >= 0) {
              int var22 = IX_L37060 + 2;
              A_L37060 = this.memory[var22];
              A_L37060 &= 31;
              int var23 = IX_L37060 + 6;
              int var24 = this.memory[var23];
              F_L37063 = A_L37060 - var24;
              int var25 = IX_L37060 + 6;
              var10000 = this.memory[var25];
              if (F_L37063 != 0) {
                var10000 = IX_L37060 + 2;
              } else {
                int var26 = IX_L37060 + 0;
                this.memory[var26] = 129;
              }
            }
          } else {
            int var13 = IX_L37060 + 0;
            A_L37060 = this.memory[var13];
            A_L37060 += 32;
            A_L37060 |= 128;
            int var14 = IX_L37060 + 0;
            this.memory[var14] = A_L37060;
            F_L37063 = A_L37060 - 160;
            if (F_L37063 < 0) {
              int var15 = IX_L37060 + 2;
              A_L37060 = this.memory[var15];
              A_L37060 &= 31;
              int var16 = IX_L37060 + 7;
              int var17 = this.memory[var16];
              F_L37063 = A_L37060 - var17;
              int var18 = IX_L37060 + 7;
              var10000 = this.memory[var18];
              if (F_L37063 != 0) {
                var10000 = IX_L37060 + 2;
              } else {
                int var19 = IX_L37060 + 0;
                this.memory[var19] = 97;
              }
            }
          }
        }
      }

      int DE_L37302 = 8;
      IX_L37305 += DE_L37302;
      IX_L37060 = IX_L37305;
    }
  }


  public void $0() {
    final int B_L0 = 2;
    final int IX_L1 = 1000;
    final int A_L2 = 100;
    int B_L2 = B_L0;
    int B_L3;
    do {
      int F_L3 = 13;
      final int F_L4;
      F_L3 = (F_L4 = B_L2 - 2);
      if (F_L4 != 0) {
        final int A_L3 = 13;
        final int IX_L2 = 13;
        this.memory[IX_L2 + 1] = A_L3;
      } else {
        final int A_L4 = A_L2;
        this.memory[IX_L1 + 2] = A_L4;
      }
      B_L3 = (B_L2 = B_L2);
    } while (B_L3 != 0);
  }

  public static void main(String[] args) {
    JSW2 jsw2 = new JSW2();
    jsw2.memory = new int[0x10000];
    Arrays.fill(jsw2.memory, 1);
    jsw2.$0();
  }
}
