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

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $90C0() {
      int F = 100000;
      int IX_L37056 = '脀';
      int IX_L37060 = IX_L37056;

      while(true) {
         int var4 = this.memory[IX_L37060];
         int F_L37063 = var4 - 255;
         int A_L37060 = var4 & 3;
         if (F_L37063 != 0) {
            F_L37063 = A_L37060 - 1;
            if (F_L37063 != 0) {
               F_L37063 = A_L37060 - 2;
               if (F_L37063 != 0) {
                  if (F_L37063 != 0) {
                     int var47 = IX_L37060 + 1;
                     A_L37060 = this.memory[var47];
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
                     int var41 = IX_L37060 + 1;
                     A_L37060 = this.memory[var41];
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

                  int var42 = IX_L37060 + 1;
                  this.memory[var42] = A_L37060;
                  int var88 = A_L37060 & 127;
                  int var44 = IX_L37060 + 7;
                  int var45 = this.memory[var44];
                  int F_L37152 = var88 - var45;
                  int var46 = IX_L37060 + 7;
                  int var101 = this.memory[var46];
                  if (F_L37152 == 0) {
                     var88 = this.memory[IX_L37060];
                     var88 ^= 128;
                     F_L37152 = var88 - 128;
                     this.memory[IX_L37060] = var88;
                  }
               } else {
                  label76: {
                     A_L37060 = this.memory[IX_L37060];
                     A_L37060 ^= 8;
                     F_L37063 = A_L37060 - 8;
                     this.memory[IX_L37060] = A_L37060;
                     A_L37060 &= 24;
                     if (F_L37063 != 0) {
                        A_L37060 = this.memory[IX_L37060];
                        A_L37060 += 32;
                        this.memory[IX_L37060] = A_L37060;
                     }

                     int var19 = IX_L37060 + 3;
                     int var20 = this.memory[var19];
                     int var23 = IX_L37060 + 4;
                     int var24 = this.memory[var23];
                     int A_L37267 = var20 + var24;
                     int var25 = IX_L37060 + 4;
                     int var98 = this.memory[var25];
                     int var26 = IX_L37060 + 3;
                     this.memory[var26] = A_L37267;
                     int var27 = IX_L37060 + 7;
                     int var28 = this.memory[var27];
                     int F_L37270 = A_L37267 - var28;
                     int var29 = IX_L37060 + 7;
                     var98 = this.memory[var29];
                     if (F_L37270 < 0) {
                        int var35 = IX_L37060 + 6;
                        int var36 = this.memory[var35];
                        F_L37270 = A_L37267 - var36;
                        int var37 = IX_L37060 + 6;
                        var98 = this.memory[var37];
                        if (F_L37270 != 0 && F_L37270 >= 0) {
                           break label76;
                        }

                        int var38 = IX_L37060 + 6;
                        A_L37267 = this.memory[var38];
                        int var39 = IX_L37060 + 3;
                        this.memory[var39] = A_L37267;
                     }

                     int var31 = IX_L37060 + 4;
                     int var32 = this.memory[var31];
                     int var34 = IX_L37060 + 4;
                     this.memory[var34] = var32;
                  }
               }
            } else if (F_L37063 == 0) {
               A_L37060 = this.memory[IX_L37060];
               A_L37060 -= 32;
               A_L37060 &= 127;
               this.memory[IX_L37060] = A_L37060;
               F_L37063 = A_L37060 - 96;
               if (F_L37063 >= 0) {
                  int var13 = IX_L37060 + 2;
                  A_L37060 = this.memory[var13];
                  A_L37060 &= 31;
                  int var14 = IX_L37060 + 6;
                  int var15 = this.memory[var14];
                  F_L37063 = A_L37060 - var15;
                  int var16 = IX_L37060 + 6;
                  int var10000 = this.memory[var16];
                  if (F_L37063 != 0) {
                     var10000 = IX_L37060 + 2;
                     var10000 = IX_L37060 + 2;
                  } else {
                     this.memory[IX_L37060] = 129;
                  }
               }
            } else {
               A_L37060 = this.memory[IX_L37060];
               A_L37060 += 32;
               A_L37060 |= 128;
               this.memory[IX_L37060] = A_L37060;
               F_L37063 = A_L37060 - 160;
               if (F_L37063 < 0) {
                  int var9 = IX_L37060 + 2;
                  A_L37060 = this.memory[var9];
                  A_L37060 &= 31;
                  int var10 = IX_L37060 + 7;
                  int var11 = this.memory[var10];
                  F_L37063 = A_L37060 - var11;
                  int var12 = IX_L37060 + 7;
                  int var96 = this.memory[var12];
                  if (F_L37063 != 0) {
                     var96 = IX_L37060 + 2;
                  } else {
                     this.memory[IX_L37060] = 97;
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
    startAddress = 38196;
    endAddress = 38343;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $9534() {
      int F = 100000;
      int var2 = this.memory['萠'];
      int F_L38199 = var2 - 35;
      if (F_L38199 == 0) {
         int A_L38196 = this.memory['藟'];
         A_L38196 |= A_L38196;
         if (F_L38199 == 0) {
            A_L38196 = this.memory['藋'];
            A_L38196 &= 2;
            A_L38196 |= 128;
            A_L38196 = this.memory['藏'];
            F_L38199 = A_L38196 - 208;
            if (F_L38199 != 0) {
               F_L38199 = A_L38196 - 192;
               if (F_L38199 < 0) {
               }
            }

            this.$9456();
         }

         A_L38196 = this.memory['藓'];
         A_L38196 &= 31;
         F_L38199 = A_L38196 - 6;
         this.memory['藟'] = A_L38196;
      }

      int var12 = this.memory['萠'];
      F_L38199 = var12 - 33;
      var12 = this.memory['藋'];
      var12 &= 1;
      var12 = this.memory['藟'];
      F_L38199 = var12 - 3;
      if (F_L38199 == 0) {
      }

      this.$9668();
   }

   public void $9456() {
   }

   public void $9668() {
   }
}
""", generateAndDecompile());
  }
}
