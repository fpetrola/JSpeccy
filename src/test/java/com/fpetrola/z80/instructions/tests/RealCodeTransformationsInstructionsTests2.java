package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class RealCodeTransformationsInstructionsTests2<T extends WordNumber> extends RealCodeTestBase<T> {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");

  @Test
  public void testJSW1() {
    startAddress = 37056;
    endAddress = 37307;
    firstAddress =startAddress;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
        public class JSW {
           public int initial;
           public int[] memory;

           public void $90C0() {
              int F = 100000;
              int IX_L37056 = '\\u8100';
              int IX_L37060 = IX_L37056;

              while(true) {
                 int var4 = this.memory[IX_L37060];
                 int F_L37063 = var4 - 255;
                 if (F_L37063 == 0) {
                    return;
                 }

                 int A_L37060 = var4 & 3;
                 if (A_L37060 != 0) {
                    F_L37063 = A_L37060 - 1;
                    if (F_L37063 != 0) {
                       F_L37063 = A_L37060 - 2;
                       if (F_L37063 != 0) {
                          int var58 = this.memory[IX_L37060];
                          int var59 = var58 & 128;
                          this.memory[IX_L37060] = var59;
                          if (var58 != 0) {
                             int var71 = IX_L37060 + 1;
                             int var72 = this.memory[var71];
                             F_L37063 = var72 & 128;
                             if (F_L37063 != 0) {
                                int A_L37087 = var72 - 2;
                                F_L37063 = A_L37087 - 148;
                                if (F_L37063 < 0) {
                                   A_L37087 -= 2;
                                   F_L37063 = A_L37087 - 128;
                                   if (F_L37063 == 0) {
                                      A_L37087 ^= A_L37087;
                                   }
                                }
                             } else {
                                int var97 = var72 + 2;
                                F_L37063 = var97 - 18;
                                if (F_L37063 < 0) {
                                   var97 += 2;
                                }
                             }
                          } else {
                             int var61 = IX_L37060 + 1;
                             int var62 = this.memory[var61];
                             F_L37063 = var62 & 128;
                             if (F_L37063 == 0) {
                                int A_L37119 = var62 - 2;
                                F_L37063 = A_L37119 - 20;
                                if (F_L37063 < 0) {
                                   A_L37119 -= 2;
                                   A_L37119 |= A_L37119;
                                   if (A_L37119 == 0) {
                                   }
                                }
                             } else {
                                int var93 = var62 + 2;
                                F_L37063 = var93 - 146;
                                if (F_L37063 < 0) {
                                   var93 += 2;
                                }
                             }
                          }

                          int var64 = IX_L37060 + 1;
                          this.memory[var64] = A_L37060;
                          int A_L37149 = A_L37060 & 127;
                          int var66 = IX_L37060 + 7;
                          int var67 = this.memory[var66];
                          int F_L37152 = A_L37149 - var67;
                          int var68 = IX_L37060 + 7;
                          int var108 = this.memory[var68];
                          if (F_L37152 == 0) {
                             int var69 = this.memory[IX_L37060];
                             int A_L37160 = var69 ^ 128;
                             this.memory[IX_L37060] = A_L37160;
                          }
                       } else {
                          label81: {
                             int var28 = this.memory[IX_L37060];
                             int A_L37247 = var28 ^ 8;
                             this.memory[IX_L37060] = A_L37247;
                             A_L37247 &= 24;
                             if (A_L37247 != 0) {
                                int var56 = this.memory[IX_L37060];
                                int A_L37259 = var56 + 32;
                                this.memory[IX_L37060] = A_L37259;
                             }

                             int var31 = IX_L37060 + 3;
                             int var32 = this.memory[var31];
                             int var34 = IX_L37060 + 4;
                             int var35 = this.memory[var34];
                             int A_L37267 = var32 + var35;
                             int var36 = IX_L37060 + 4;
                             int var103 = this.memory[var36];
                             int var38 = IX_L37060 + 4;
                             var103 = this.memory[var38];
                             int var39 = IX_L37060 + 4;
                             var103 = this.memory[var39];
                             int var40 = IX_L37060 + 3;
                             this.memory[var40] = A_L37267;
                             int var41 = IX_L37060 + 7;
                             int var42 = this.memory[var41];
                             int F_L37270 = A_L37267 - var42;
                             int var43 = IX_L37060 + 7;
                             var103 = this.memory[var43];
                             if (F_L37270 < 0) {
                                int var49 = IX_L37060 + 6;
                                int var50 = this.memory[var49];
                                F_L37270 = A_L37267 - var50;
                                int var51 = IX_L37060 + 6;
                                var103 = this.memory[var51];
                                if (F_L37270 != 0 && F_L37270 >= 0) {
                                   break label81;
                                }

                                int var52 = IX_L37060 + 6;
                                int var53 = this.memory[var52];
                                int var55 = IX_L37060 + 3;
                                this.memory[var55] = var53;
                             }

                             int var45 = IX_L37060 + 4;
                             int var46 = this.memory[var45];
                             int var48 = IX_L37060 + 4;
                             this.memory[var48] = var46;
                          }
                       }
                    } else {
                       int var9 = this.memory[IX_L37060];
                       int var10 = var9 & 128;
                       this.memory[IX_L37060] = var10;
                       if (var9 == 0) {
                          int var19 = this.memory[IX_L37060];
                          int A_L37177 = var19 - 32;
                          A_L37177 &= 127;
                          this.memory[IX_L37060] = A_L37177;
                          F_L37063 = A_L37177 - 96;
                          if (F_L37063 >= 0) {
                             int var21 = IX_L37060 + 2;
                             int var22 = this.memory[var21];
                             int A_L37191 = var22 & 31;
                             int var24 = IX_L37060 + 6;
                             int var25 = this.memory[var24];
                             F_L37063 = A_L37191 - var25;
                             int var26 = IX_L37060 + 6;
                             int var10000 = this.memory[var26];
                             if (F_L37063 != 0) {
                                var10000 = IX_L37060 + 2;
                                var10000 = IX_L37060 + 2;
                             } else {
                                this.memory[IX_L37060] = 129;
                             }
                          }
                       } else {
                          int var11 = this.memory[IX_L37060];
                          int A_L37212 = var11 + 32;
                          A_L37212 |= 128;
                          this.memory[IX_L37060] = A_L37212;
                          F_L37063 = A_L37212 - 160;
                          if (F_L37063 < 0) {
                             int var13 = IX_L37060 + 2;
                             int var14 = this.memory[var13];
                             int A_L37226 = var14 & 31;
                             int var16 = IX_L37060 + 7;
                             int var17 = this.memory[var16];
                             F_L37063 = A_L37226 - var17;
                             int var18 = IX_L37060 + 7;
                             int var101 = this.memory[var18];
                             if (F_L37063 != 0) {
                                var101 = IX_L37060 + 2;
                             } else {
                                this.memory[IX_L37060] = 97;
                             }
                          }
                       }
                    }
                 }

                 int DE_L37302 = 8;
                 int IX_L37305 = IX_L37060 + DE_L37302;
                 IX_L37060 = IX_L37305;
              }
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void testJSWMoveWilly1() {
    startAddress = 37974;
    endAddress = 38025;
    firstAddress = 37974;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
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
         int A_L38010;
         A_L38024 = A_L38010;
         int var8 = this.memory[DE_L37978];
         int HL_L37985;
         int H_L37985;
         int var36;
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
         int var13;
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
      } while(B_L37974 != 0);

      A_L38024 ^= A_L38024;
   }
}
""", generateAndDecompile());
  }


  @Test
  public void testJSWMoveWilly() {
    startAddress = 37974;
    endAddress = 38343;
    firstAddress = 38196;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
   public int initial;
   public int[] memory;

   public void $9456() {
      // $FF: Couldn't be decompiled
   }

   public void $9668() {
   }
}
""", generateAndDecompile());
  }
}
