package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class RealCodeTransformationsInstructionsTests2<T extends WordNumber> extends RealCodeTestBase<T> {

  @Test
  public void testJSW1() {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");
//
    startAddress = 37056;
    endAddress = 37307;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $90C0() {
      int F = true;
      int IX_L37056 = '脀';
      int IX_L37060 = IX_L37056;

      while(true) {
         int var4 = IX_L37060 + 0;
         int var5 = this.memory[var4];
         int IX_L37267;
         int IX_L37305 = IX_L37267;
         int F_L37063 = var5 - 255;
         int A_L37060 = var5 & 3;
         if (F_L37063 != 0) {
            F_L37063 = A_L37060 - 1;
            if (F_L37063 != 0) {
               F_L37063 = A_L37060 - 2;
               if (F_L37063 != 0) {
                  int var114 = IX_L37060 + 0;
                  int IX_L37149;
                  int A_L37149;
                  if (F_L37063 != 0) {
                     int var60 = IX_L37060 + 1;
                     A_L37060 = this.memory[var60];
                     IX_L37149 = IX_L37060;
                     if (F_L37063 != 0) {
                        A_L37060 -= 2;
                        F_L37063 = A_L37060 - 148;
                        A_L37149 = A_L37060;
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
                     int var50 = IX_L37060 + 1;
                     A_L37060 = this.memory[var50];
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

                  int var52 = IX_L37149 + 1;
                  this.memory[var52] = A_L37149;
                  A_L37149 &= 127;
                  int var55 = IX_L37149 + 7;
                  int var56 = this.memory[var55];
                  int F_L37152 = A_L37149 - var56;
                  int var57 = IX_L37149 + 7;
                  var114 = this.memory[var57];
                  if (F_L37152 == 0) {
                     int var58 = IX_L37149 + 0;
                     int var104 = this.memory[var58];
                     A_L37149 = var104 ^ 128;
                     F_L37152 = A_L37149 - 128;
                     int var59 = IX_L37149 + 0;
                     this.memory[var59] = A_L37149;
                  }
               } else {
                  label76: {
                     int var25 = IX_L37060 + 0;
                     A_L37060 = this.memory[var25];
                     A_L37060 ^= 8;
                     F_L37063 = A_L37060 - 8;
                     int var26 = IX_L37060 + 0;
                     this.memory[var26] = A_L37060;
                     IX_L37267 = IX_L37060;
                     A_L37060 &= 24;
                     if (F_L37063 != 0) {
                        int var48 = IX_L37060 + 0;
                        A_L37060 = this.memory[var48];
                        A_L37060 += 32;
                        int var49 = IX_L37060 + 0;
                        this.memory[var49] = A_L37060;
                     }

                     int var27 = IX_L37060 + 3;
                     int var28 = this.memory[var27];
                     int var31 = IX_L37060 + 4;
                     int var32 = this.memory[var31];
                     int A_L37267 = var28 + var32;
                     int var33 = IX_L37060 + 4;
                     int var111 = this.memory[var33];
                     int var34 = IX_L37060 + 3;
                     this.memory[var34] = A_L37267;
                     int var35 = IX_L37060 + 7;
                     int var36 = this.memory[var35];
                     int F_L37270 = A_L37267 - var36;
                     int var37 = IX_L37060 + 7;
                     var111 = this.memory[var37];
                     if (F_L37270 < 0) {
                        int var43 = IX_L37060 + 6;
                        int var44 = this.memory[var43];
                        F_L37270 = A_L37267 - var44;
                        int var45 = IX_L37060 + 6;
                        var111 = this.memory[var45];
                        if (F_L37270 != 0 && F_L37270 >= 0) {
                           break label76;
                        }

                        int var46 = IX_L37060 + 6;
                        A_L37267 = this.memory[var46];
                        int var47 = IX_L37060 + 3;
                        this.memory[var47] = A_L37267;
                     }

                     int var39 = IX_L37060 + 4;
                     int var40 = this.memory[var39];
                     int var42 = IX_L37060 + 4;
                     this.memory[var42] = var40;
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
         IX_L37305 += DE_L37302;
         IX_L37060 = IX_L37305;
      }
   }
}
""", generateAndDecompile());
  }

}
