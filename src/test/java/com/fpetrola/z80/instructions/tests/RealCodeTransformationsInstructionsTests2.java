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
                          int var42 = this.memory[IX_L37060];
                          int var43 = var42 & 128;
                          this.memory[IX_L37060] = var43;
                          if (var42 != 0) {
                             int var51 = IX_L37060 + 1;
                             A_L37060 = this.memory[var51];
                             F_L37063 = A_L37060 & 128;
                             if (F_L37063 != 0) {
                                A_L37060 -= 2;
                                F_L37063 = A_L37060 - 148;
                                if (F_L37063 < 0) {
                                   A_L37060 -= 2;
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
                             int var45 = IX_L37060 + 1;
                             A_L37060 = this.memory[var45];
                             F_L37063 = A_L37060 & 128;
                             if (F_L37063 == 0) {
                                A_L37060 -= 2;
                                F_L37063 = A_L37060 - 20;
                                if (F_L37063 < 0) {
                                   A_L37060 -= 2;
                                   A_L37060 |= A_L37060;
                                   if (A_L37060 == 0) {
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

                          int var46 = IX_L37060 + 1;
                          this.memory[var46] = A_L37060;
                          int var92 = A_L37060 & 127;
                          int var48 = IX_L37060 + 7;
                          int var49 = this.memory[var48];
                          int F_L37152 = var92 - var49;
                          int var50 = IX_L37060 + 7;
                          int var105 = this.memory[var50];
                          if (F_L37152 == 0) {
                             var92 = this.memory[IX_L37060];
                             var92 ^= 128;
                             this.memory[IX_L37060] = var92;
                          }
                       } else {
                          label81: {
                             A_L37060 = this.memory[IX_L37060];
                             A_L37060 ^= 8;
                             this.memory[IX_L37060] = A_L37060;
                             A_L37060 &= 24;
                             if (A_L37060 != 0) {
                                A_L37060 = this.memory[IX_L37060];
                                A_L37060 += 32;
                                this.memory[IX_L37060] = A_L37060;
                             }

                             int var21 = IX_L37060 + 3;
                             int var22 = this.memory[var21];
                             int var24 = IX_L37060 + 4;
                             int var25 = this.memory[var24];
                             int A_L37267 = var22 + var25;
                             int var26 = IX_L37060 + 4;
                             int var102 = this.memory[var26];
                             int var27 = IX_L37060 + 3;
                             this.memory[var27] = A_L37267;
                             int F_L37270 = this.initial;
                             int var29 = IX_L37060 + 7;
                             int var30 = this.memory[var29];
                             F_L37270 = A_L37267 - var30;
                             int var31 = IX_L37060 + 7;
                             var102 = this.memory[var31];
                             if (F_L37270 < 0) {
                                int var37 = IX_L37060 + 6;
                                int var38 = this.memory[var37];
                                F_L37270 = A_L37267 - var38;
                                int var39 = IX_L37060 + 6;
                                var102 = this.memory[var39];
                                if (F_L37270 != 0 && F_L37270 >= 0) {
                                   break label81;
                                }

                                int var40 = IX_L37060 + 6;
                                A_L37267 = this.memory[var40];
                                int var41 = IX_L37060 + 3;
                                this.memory[var41] = A_L37267;
                             }

                             int var33 = IX_L37060 + 4;
                             int var34 = this.memory[var33];
                             int var36 = IX_L37060 + 4;
                             this.memory[var36] = var34;
                          }
                       }
                    } else {
                       int var9 = this.memory[IX_L37060];
                       int var10 = var9 & 128;
                       this.memory[IX_L37060] = var10;
                       if (var9 == 0) {
                          A_L37060 = this.memory[IX_L37060];
                          A_L37060 -= 32;
                          A_L37060 &= 127;
                          this.memory[IX_L37060] = A_L37060;
                          F_L37063 = A_L37060 - 96;
                          if (F_L37063 >= 0) {
                             int var15 = IX_L37060 + 2;
                             A_L37060 = this.memory[var15];
                             A_L37060 &= 31;
                             int var16 = IX_L37060 + 6;
                             int var17 = this.memory[var16];
                             F_L37063 = A_L37060 - var17;
                             int var18 = IX_L37060 + 6;
                             int var10000 = this.memory[var18];
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
                             int var11 = IX_L37060 + 2;
                             A_L37060 = this.memory[var11];
                             A_L37060 &= 31;
                             int var12 = IX_L37060 + 7;
                             int var13 = this.memory[var12];
                             F_L37063 = A_L37060 - var13;
                             int var14 = IX_L37060 + 7;
                             int var100 = this.memory[var14];
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
    startAddress = 38196;
    endAddress = 38343;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
        public class JSW {
           public int initial;
           public int[] memory;

           public void $9534() {
              int F = 100000;
              int var2 = this.memory['\\u8420'];
              int F_L38199 = var2 - 35;
              if (F_L38199 == 0) {
                 int var9 = this.memory['\\u85df'];
                 var9 |= var9;
                 if (var9 == 0) {
                    var9 = this.memory['\\u85cb'];
                    var9 &= 2;
                    var9 |= 128;
                    var9 = this.memory['\\u85cf'];
                    F_L38199 = var9 - 208;
                    if (F_L38199 != 0) {
                       F_L38199 = var9 - 192;
                       if (F_L38199 < 0) {
                       }
                    }

                    this.$9456();
                 } else {
                    var9 = this.memory['\\u85d3'];
                    var9 &= 31;
                    F_L38199 = var9 - 6;
                    if (F_L38199 < 0) {
                       this.memory['\\u85df'] = var9;
                    }
                 }
              } else {
                 int A_L38196 = this.memory['\\u8420'];
                 F_L38199 = A_L38196 - 33;
                 if (F_L38199 == 0) {
                    A_L38196 = this.memory['\\u85cb'];
                    A_L38196 &= 1;
                    A_L38196 = this.memory['\\u85df'];
                    F_L38199 = A_L38196 - 3;
                    if (F_L38199 == 0) {
                       int var22 = A_L38196 | 64;
                    }

                    this.$9668();
                 }
              }
           }

           public void $9456() {
           }

           public void $9668() {
           }
        }
        """, generateAndDecompile());
  }
}
