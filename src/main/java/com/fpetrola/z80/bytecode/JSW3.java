package com.fpetrola.z80.bytecode;

public class JSW3 {
  public int[] memory;

  public void $90C0() {
    int F = 100000;
    int IX_L37056 = '脀';
    int IX = 0;
    int IX_L37060 = IX;

    while (IX_L37060 > 0) {
      IX = 33908;
      int var5 = IX_L37060 + 0;
      int var6 = this.memory[var5];
      int F_L37063 = var6 - 255;
      int A_L37060 = var6 & 3;
      if (F_L37063 != 0) {
        F_L37063 = A_L37060 - 1;
        if (F_L37063 != 0) {
          F_L37063 = A_L37060 - 2;
          if (F_L37063 != 0) {
            int var117 = IX_L37060 + 0;
            if (F_L37063 != 0) {
              int var61 = IX_L37060 + 1;
              A_L37060 = this.memory[var61];
              if (F_L37063 != 0) {
                A_L37060 -= 2;
                F_L37063 = A_L37060 - 148;
                if (F_L37063 < 0) {
                  A_L37060 -= 2;
                  F_L37063 = A_L37060 - 128;
                  if (F_L37063 == 0) {
                    A_L37060 ^= A_L37060;
                    F_L37063 = A_L37060 - A_L37060;
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
              int var53 = IX_L37060 + 1;
              A_L37060 = this.memory[var53];
              if (F_L37063 == 0) {
                A_L37060 -= 2;
                F_L37063 = A_L37060 - 20;
                if (F_L37063 < 0) {
                  A_L37060 -= 2;
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

            int var54 = IX_L37060 + 1;
            this.memory[var54] = A_L37060;
            int var103 = A_L37060 & 127;
            int var56 = IX_L37060 + 7;
            int var57 = this.memory[var56];
            int F_L37152 = var103 - var57;
            int var58 = IX_L37060 + 7;
            var117 = this.memory[var58];
            if (F_L37152 == 0) {
              int var59 = IX_L37060 + 0;
              var103 = this.memory[var59];
              var103 ^= 128;
              F_L37152 = var103 - 128;
              int var60 = IX_L37060 + 0;
              this.memory[var60] = var103;
            }
          } else {
            label76:
            {
              int var26 = IX_L37060 + 0;
              A_L37060 = this.memory[var26];
              A_L37060 ^= 8;
              F_L37063 = A_L37060 - 8;
              int var27 = IX_L37060 + 0;
              this.memory[var27] = A_L37060;
              A_L37060 &= 24;
              if (F_L37063 != 0) {
                int var50 = IX_L37060 + 0;
                A_L37060 = this.memory[var50];
                A_L37060 += 32;
                int var51 = IX_L37060 + 0;
                this.memory[var51] = A_L37060;
              }

              int var29 = IX_L37060 + 3;
              int var30 = this.memory[var29];
              int var33 = IX_L37060 + 4;
              int var34 = this.memory[var33];
              int A_L37267 = var30 + var34;
              int var35 = IX_L37060 + 4;
              int var114 = this.memory[var35];
              int var36 = IX_L37060 + 3;
              this.memory[var36] = A_L37267;
              int var37 = IX_L37060 + 7;
              int var38 = this.memory[var37];
              int F_L37270 = A_L37267 - var38;
              int var39 = IX_L37060 + 7;
              var114 = this.memory[var39];
              if (F_L37270 < 0) {
                int var45 = IX_L37060 + 6;
                int var46 = this.memory[var45];
                F_L37270 = A_L37267 - var46;
                int var47 = IX_L37060 + 6;
                var114 = this.memory[var47];
                if (F_L37270 != 0 && F_L37270 >= 0) {
                  break label76;
                }

                int var48 = IX_L37060 + 6;
                A_L37267 = this.memory[var48];
                int var49 = IX_L37060 + 3;
                this.memory[var49] = A_L37267;
              }

              int var41 = IX_L37060 + 4;
              int var42 = this.memory[var41];
              int var44 = IX_L37060 + 4;
              this.memory[var44] = var42;
            }
          }
        } else {
          int var10000 = IX_L37060 + 0;
          if (F_L37063 == 0) {
            int var18 = IX_L37060 + 0;
            A_L37060 = this.memory[var18];
            A_L37060 -= 32;
            A_L37060 &= 127;
            int var19 = IX_L37060 + 0;
            this.memory[var19] = A_L37060;
            F_L37063 = A_L37060 - 96;
            if (F_L37063 >= 0) {
              int var20 = IX_L37060 + 2;
              A_L37060 = this.memory[var20];
              A_L37060 &= 31;
              int var21 = IX_L37060 + 6;
              int var22 = this.memory[var21];
              F_L37063 = A_L37060 - var22;
              int var23 = IX_L37060 + 6;
              var10000 = this.memory[var23];
              if (F_L37063 != 0) {
                var10000 = IX_L37060 + 2;
                var10000 = IX_L37060 + 2;
              } else {
                int var24 = IX_L37060 + 0;
                this.memory[var24] = 129;
              }
            }
          } else {
            int var11 = IX_L37060 + 0;
            A_L37060 = this.memory[var11];
            A_L37060 += 32;
            A_L37060 |= 128;
            int var12 = IX_L37060 + 0;
            this.memory[var12] = A_L37060;
            F_L37063 = A_L37060 - 160;
            if (F_L37063 < 0) {
              int var13 = IX_L37060 + 2;
              A_L37060 = this.memory[var13];
              A_L37060 &= 31;
              int var14 = IX_L37060 + 7;
              int var15 = this.memory[var14];
              F_L37063 = A_L37060 - var15;
              int var16 = IX_L37060 + 7;
              var10000 = this.memory[var16];
              if (F_L37063 != 0) {
                var10000 = IX_L37060 + 2;
              } else {
                int var17 = IX_L37060 + 0;
                this.memory[var17] = 97;
              }
            }
          }
        }
      }

      int DE_L37302 = 8;
      int IX_L37305 = IX_L37056 + DE_L37302;
      IX_L37060 = IX_L37305;
    }
  }
}
