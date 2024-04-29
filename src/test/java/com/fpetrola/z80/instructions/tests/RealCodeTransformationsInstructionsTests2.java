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
                  int var56 = this.memory[IX_L37060];
                  int var57 = var56 & 128;
                  this.memory[IX_L37060] = var57;
                  if (var56 != 0) {
                     int var69 = IX_L37060 + 1;
                     int var70 = this.memory[var69];
                     F_L37063 = var70 & 128;
                     if (F_L37063 != 0) {
                        int A_L37087 = var70 - 2;
                        F_L37063 = A_L37087 - 148;
                        if (F_L37063 < 0) {
                           A_L37087 -= 2;
                           F_L37063 = A_L37087 - 128;
                           if (F_L37063 == 0) {
                              A_L37087 ^= A_L37087;
                           }
                        }
                     } else {
                        int var96 = var70 + 2;
                        F_L37063 = var96 - 18;
                        if (F_L37063 < 0) {
                           var96 += 2;
                        }
                     }
                  } else {
                     int var59 = IX_L37060 + 1;
                     int var60 = this.memory[var59];
                     F_L37063 = var60 & 128;
                     if (F_L37063 == 0) {
                        int A_L37119 = var60 - 2;
                        F_L37063 = A_L37119 - 20;
                        if (F_L37063 < 0) {
                           A_L37119 -= 2;
                           A_L37119 |= A_L37119;
                           if (A_L37119 == 0) {
                           }
                        }
                     } else {
                        int var92 = var60 + 2;
                        F_L37063 = var92 - 146;
                        if (F_L37063 < 0) {
                           var92 += 2;
                        }
                     }
                  }

                  int var62 = IX_L37060 + 1;
                  this.memory[var62] = A_L37060;
                  int A_L37149 = A_L37060 & 127;
                  int var64 = IX_L37060 + 7;
                  int var65 = this.memory[var64];
                  int F_L37152 = A_L37149 - var65;
                  int var66 = IX_L37060 + 7;
                  int var105 = this.memory[var66];
                  if (F_L37152 == 0) {
                     int var67 = this.memory[IX_L37060];
                     int A_L37160 = var67 ^ 128;
                     this.memory[IX_L37060] = A_L37160;
                  }
               } else {
                  label81: {
                     int var28 = this.memory[IX_L37060];
                     int A_L37247 = var28 ^ 8;
                     this.memory[IX_L37060] = A_L37247;
                     A_L37247 &= 24;
                     if (A_L37247 != 0) {
                        int var54 = this.memory[IX_L37060];
                        int A_L37259 = var54 + 32;
                        this.memory[IX_L37060] = A_L37259;
                     }

                     int var31 = IX_L37060 + 3;
                     int var32 = this.memory[var31];
                     int var34 = IX_L37060 + 4;
                     int var35 = this.memory[var34];
                     int A_L37267 = var32 + var35;
                     int var36 = IX_L37060 + 4;
                     int var102 = this.memory[var36];
                     int var37 = IX_L37060 + 3;
                     this.memory[var37] = A_L37267;
                     int F_L37270 = this.initial;
                     int var39 = IX_L37060 + 7;
                     int var40 = this.memory[var39];
                     F_L37270 = A_L37267 - var40;
                     int var41 = IX_L37060 + 7;
                     var102 = this.memory[var41];
                     if (F_L37270 < 0) {
                        int var47 = IX_L37060 + 6;
                        int var48 = this.memory[var47];
                        F_L37270 = A_L37267 - var48;
                        int var49 = IX_L37060 + 6;
                        var102 = this.memory[var49];
                        if (F_L37270 != 0 && F_L37270 >= 0) {
                           break label81;
                        }

                        int var50 = IX_L37060 + 6;
                        int var51 = this.memory[var50];
                        int var53 = IX_L37060 + 3;
                        this.memory[var53] = var51;
                     }

                     int var43 = IX_L37060 + 4;
                     int var44 = this.memory[var43];
                     int var46 = IX_L37060 + 4;
                     this.memory[var46] = var44;
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
                     int var100 = this.memory[var18];
                     if (F_L37063 != 0) {
                        var100 = IX_L37060 + 2;
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
      int F = 100000;
      int E = 100000;

      while(true) {
         int B_L37974 = 16;
         int B_L38022 = B_L37974;
         int C_L37976 = this.initial;
         int F_L37976 = this.initial;

         int DE_L38000;
         do {
            F_L37976 = C_L37976 & 1;
            int var116;
            int DE_L37978 = var116;
            int D_L38000 = this.initial;
            var116 = D_L38000 << 8 | E;
            int var11 = this.memory[DE_L37978];
            int HL_L37985;
            int L_L37997;
            int H_L37997;
            int H_L37985;
            if (F_L37976 != 0) {
               int var117;
               int HL_L37981 = var117;
               H_L37997 = this.initial;
               L_L37997 = this.initial;
               var117 = H_L37997 << 8 | L_L37997;
               int var78 = this.memory[HL_L37981];
               int A_L37978 = var11 & var78;
               int var10000 = this.memory[HL_L37981];
               var10000 = this.memory[HL_L37981];
               var10000 = this.memory[HL_L37981];
               H_L37985 = this.initial;
               if (A_L37978 != 0) {
                  return;
               }

               int var79 = this.memory[DE_L37978];
               int var81 = this.memory[HL_L37981];
               int A_L37983 = var79 | var81;
               var10000 = this.memory[HL_L37981];
               var10000 = this.memory[HL_L37981];
               var10000 = this.memory[HL_L37981];
               HL_L37985 = this.initial;
            }

            this.memory[HL_L37985] = var11;
            int var16;
            int HL_L37997 = var16;
            int var18 = HL_L37985 & 255;
            int L_L37985 = var18 + 1;
            int DE_L37987 = DE_L37978 + 1;
            int F_L37986 = this.initial;
            F_L37986 = C_L37976 & 1;
            int var21 = this.memory[DE_L37987];
            if (F_L37986 != 0) {
               var16 = H_L37985 << 8 | L_L37985;
               int var73 = this.memory[var16];
               int A_L37990 = var21 & var73;
               int var102 = this.memory[var16];
               var102 = this.memory[var16];
               var102 = this.memory[var16];
               if (A_L37990 != 0) {
                  return;
               }

               int var74 = this.memory[DE_L37987];
               int var76 = this.memory[var16];
               int A_L37995 = var74 | var76;
               var102 = this.memory[var16];
               var102 = this.memory[var16];
               var102 = this.memory[var16];
            }

            this.memory[HL_L37997] = var21;
            --L_L37997;
            int var27 = H_L37997 << 8;
            int var28 = L_L37997 & 255;
            int var108 = var27 | var28;
            int var31 = H_L37997 << 8;
            int var32 = L_L37997 & 255;
            var108 = var31 | var32;
            ++H_L37997;
            int var33 = H_L37997 << 8;
            int var34 = L_L37997 & 255;
            int var87 = var33 | var34;
            DE_L38000 = DE_L37987 + 1;
            int A_L38001 = H_L37997 & 7;
            if (A_L38001 == 0) {
               int A_L38006 = H_L37997 - 8;
               H_L37985 = A_L38006;
               int A_L38010 = L_L37997 + 32;
               int var68 = var87 << 8;
               int var69 = A_L38010 & 255;
               HL_L37985 = var68 | var69;
               A_L38010 &= 224;
               if (A_L38010 == 0) {
                  int A_L38018 = A_L38006 + 8;
                  H_L37985 = A_L38018;
               }
            }

            --B_L38022;
         } while(B_L38022 != 0);

         int var36 = this.memory['\\u8420'];
         int F_L38199 = var36 - 35;
         if (F_L38199 != 0) {
            int var39 = this.memory['\\u8420'];
            F_L38199 = var39 - 33;
            if (F_L38199 != 0) {
               return;
            }

            int var41 = this.memory['\\u85cb'];
            int A_L38304 = var41 & 1;
            int var44 = this.memory['\\u85df'];
            F_L38199 = var44 - 3;
            if (F_L38199 == 0) {
               int var113 = DE_L38000 & 255;
               int E_L38312 = A_L38304 | 64;
            }

            this.$9668();
            return;
         }

         int var46 = this.memory['\\u85df'];
         int A_L38203 = var46 | var46;
         if (A_L38203 != 0) {
            int var48 = this.memory['\\u85d3'];
            int A_L38262 = var48 & 31;
            F_L38199 = A_L38262 - 6;
            if (F_L38199 >= 0) {
               return;
            }

            int A_L38270 = 2;
            this.memory['\\u85df'] = A_L38270;
            return;
         }

         int var51 = this.memory['\\u85cb'];
         int A_L38209 = var51 & 2;
         A_L38209 |= 128;
         int var53 = DE_L38000 << 8;
         int var54 = A_L38209 & 255;
         int var110 = var53 | var54;
         int var55 = this.memory['\\u85cf'];
         F_L38199 = var55 - 208;
         short E_L38234;
         if (F_L38199 != 0) {
            int E_L38228 = 192;
            int var61 = DE_L38000 << 8;
            int var62 = E_L38228 & 255;
            var110 = var61 | var62;
            F_L38199 = var55 - 192;
            if (F_L38199 < 0) {
               E_L38234 = 224;
               int var85 = E_L38234 & 255;
            }
         }

         int var57 = 156 << 8;
         int var59 = E_L38234 & 255;
         var110 = var57 | var59;
         int var88 = true;
         C_L37976 = 1;
      }
   }

   public void $9668() {
   }
}
""", generateAndDecompile());
  }
}
