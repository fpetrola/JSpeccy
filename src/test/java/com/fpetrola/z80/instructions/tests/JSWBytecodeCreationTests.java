package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.base.RealCodeBytecodeCreationTestsBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class JSWBytecodeCreationTests<T extends WordNumber> extends RealCodeBytecodeCreationTestsBase<T> {
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
              int F = '\\uffff';
              int IX_L37056 = 33024;

              while(true) {
                 int var3 = this.memory[IX_L37056];
                 int F = var3 - 255;
                 if (F == 0) {
                    return;
                 }

                 int A = var3 & 3;
                 if (A != 0) {
                    F = A - 1;
                    if (F != 0) {
                       F = A - 2;
                       if (F != 0) {
                          int var43 = this.memory[IX_L37056];
                          int var44 = var43 & 128;
                          this.memory[IX_L37056] = var44;
                          if (var43 != 0) {
                             int var52 = IX_L37056 + 1;
                             int var53 = this.memory[var52];
                             F = var53 & 128;
                             if (F != 0) {
                                A = var53 - 2;
                                F = A - 148;
                                if (F < 0) {
                                   A -= 2;
                                   F = A - 128;
                                   if (F == 0) {
                                      A ^= A;
                                   }
                                }
                             } else {
                                A = var53 + 2;
                                F = A - 18;
                                if (F < 0) {
                                   A += 2;
                                }
                             }
                          } else {
                             int var45 = IX_L37056 + 1;
                             int var46 = this.memory[var45];
                             F = var46 & 128;
                             if (F == 0) {
                                A = var46 - 2;
                                F = A - 20;
                                if (F < 0) {
                                   A -= 2;
                                   A |= A;
                                   if (A == 0) {
                                      A = 128;
                                   }
                                }
                             } else {
                                A = var46 + 2;
                                F = A - 146;
                                if (F < 0) {
                                   A += 2;
                                }
                             }
                          }

                          int var47 = IX_L37056 + 1;
                          this.memory[var47] = A;
                          A &= 127;
                          int var48 = IX_L37056 + 7;
                          int var49 = this.memory[var48];
                          F = A - var49;
                          int var50 = IX_L37056 + 7;
                          int var93 = this.memory[var50];
                          if (F == 0) {
                             int var51 = this.memory[IX_L37056];
                             A = var51 ^ 128;
                             this.memory[IX_L37056] = A;
                          }
                       } else {
                          label81: {
                             int var21 = this.memory[IX_L37056];
                             A = var21 ^ 8;
                             this.memory[IX_L37056] = A;
                             A &= 24;
                             if (A != 0) {
                                int var42 = this.memory[IX_L37056];
                                A = var42 + 32;
                                this.memory[IX_L37056] = A;
                             }

                             int var22 = IX_L37056 + 3;
                             int var23 = this.memory[var22];
                             int var24 = IX_L37056 + 4;
                             int var25 = this.memory[var24];
                             A = var23 + var25;
                             int var26 = IX_L37056 + 4;
                             int var88 = this.memory[var26];
                             int var27 = IX_L37056 + 4;
                             var88 = this.memory[var27];
                             int var28 = IX_L37056 + 4;
                             var88 = this.memory[var28];
                             int var29 = IX_L37056 + 3;
                             this.memory[var29] = A;
                             int var30 = IX_L37056 + 7;
                             int var31 = this.memory[var30];
                             F = A - var31;
                             int var32 = IX_L37056 + 7;
                             var88 = this.memory[var32];
                             if (F < 0) {
                                int var36 = IX_L37056 + 6;
                                int var37 = this.memory[var36];
                                F = A - var37;
                                int var38 = IX_L37056 + 6;
                                var88 = this.memory[var38];
                                if (F != 0 && F >= 0) {
                                   break label81;
                                }

                                int var39 = IX_L37056 + 6;
                                int var40 = this.memory[var39];
                                int var41 = IX_L37056 + 3;
                                this.memory[var41] = var40;
                             }

                             int var33 = IX_L37056 + 4;
                             int var34 = this.memory[var33];
                             int var35 = IX_L37056 + 4;
                             this.memory[var35] = var34;
                          }
                       }
                    } else {
                       int var7 = this.memory[IX_L37056];
                       int var8 = var7 & 128;
                       this.memory[IX_L37056] = var8;
                       if (var7 == 0) {
                          int var15 = this.memory[IX_L37056];
                          A = var15 - 32;
                          A &= 127;
                          this.memory[IX_L37056] = A;
                          F = A - 96;
                          if (F >= 0) {
                             int var16 = IX_L37056 + 2;
                             int var17 = this.memory[var16];
                             A = var17 & 31;
                             int var18 = IX_L37056 + 6;
                             int var19 = this.memory[var18];
                             F = A - var19;
                             int var20 = IX_L37056 + 6;
                             int var10000 = this.memory[var20];
                             if (F != 0) {
                                var10000 = IX_L37056 + 2;
                                var10000 = IX_L37056 + 2;
                             } else {
                                this.memory[IX_L37056] = 129;
                             }
                          }
                       } else {
                          int var9 = this.memory[IX_L37056];
                          A = var9 + 32;
                          A |= 128;
                          this.memory[IX_L37056] = A;
                          F = A - 160;
                          if (F < 0) {
                             int var10 = IX_L37056 + 2;
                             int var11 = this.memory[var10];
                             A = var11 & 31;
                             int var12 = IX_L37056 + 7;
                             int var13 = this.memory[var12];
                             F = A - var13;
                             int var14 = IX_L37056 + 7;
                             int var86 = this.memory[var14];
                             if (F != 0) {
                                var86 = IX_L37056 + 2;
                             } else {
                                this.memory[IX_L37056] = 97;
                             }
                          }
                       }
                    }
                 }

                 int DE_L37302 = 8;
                 IX_L37056 += DE_L37302;
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
              int H = this.initial;
              int L = this.initial;
              int C = '\\uffff';
              int F = 65535;
              int B = 16;

              int var37;
              do {
                 F = C & 1;
                 int DE_L37978 = 65535;
                 int var3 = this.memory[DE_L37978];
                 var37 = var3;
                 int HL_L37981;
                 if (F != 0) {
                    HL_L37981 = 65535;
                    int var30 = this.memory[HL_L37981];
                    var37 = var3 & var30;
                    int var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    if (var37 != 0) {
                       return;
                    }

                    int var31 = this.memory[DE_L37978];
                    int var32 = this.memory[HL_L37981];
                    var37 = var31 | var32;
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                 }

                 this.memory[HL_L37981] = var37;
                 ++L;
                 HL_L37981 = L & 255;
                 HL_L37981 = L & 255;
                 ++DE_L37978;
                 F = C & 1;
                 int var10 = this.memory[DE_L37978];
                 var37 = var10;
                 if (F != 0) {
                    int var27 = this.memory[HL_L37981];
                    var37 = var10 & var27;
                    int var56 = this.memory[HL_L37981];
                    var56 = this.memory[HL_L37981];
                    var56 = this.memory[HL_L37981];
                    if (var37 != 0) {
                       return;
                    }

                    int var28 = this.memory[DE_L37978];
                    int var29 = this.memory[HL_L37981];
                    var37 = var28 | var29;
                    var56 = this.memory[HL_L37981];
                    var56 = this.memory[HL_L37981];
                    var56 = this.memory[HL_L37981];
                 }

                 this.memory[HL_L37981] = var37;
                 --L;
                 HL_L37981 = L & 255;
                 HL_L37981 = L & 255;
                 HL_L37981 = L & 255;
                 HL_L37981 = L & 255;
                 ++H;
                 int var11 = H << 8;
                 int var12 = L & 255;
                 int var62 = var11 | var12;
                 int var13 = H << 8;
                 int var14 = L & 255;
                 HL_L37981 = var13 | var14;
                 ++DE_L37978;
                 var37 = H & 7;
                 if (var37 == 0) {
                    var37 = H - 8;
                    H = var37;
                    int var15 = var37 << 8;
                    int var16 = L & 255;
                    var62 = var15 | var16;
                    int var17 = var37 << 8;
                    int var18 = L & 255;
                    var62 = var17 | var18;
                    int var39 = L + 32;
                    L = var39;
                    int var19 = var37 << 8;
                    int var20 = var39 & 255;
                    var62 = var19 | var20;
                    int var21 = var37 << 8;
                    int var22 = var39 & 255;
                    HL_L37981 = var21 | var22;
                    var37 = var39 & 224;
                    if (var37 == 0) {
                       var37 += 8;
                       H = var37;
                       int var23 = var37 << 8;
                       int var24 = var39 & 255;
                       var62 = var23 | var24;
                       int var25 = var37 << 8;
                       int var26 = var39 & 255;
                       HL_L37981 = var25 | var26;
                    }
                 }

                 --B;
              } while(B != 0);

              var37 ^= var37;
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
      int L = this.initial;
      int H = this.initial;
      int E = this.initial;
      int D = this.initial;
      int C = this.initial;
      int F = this.initial;
      int B = 16;

      int var39;
      do {
         F = C & 1;
         int var10 = D << 8 | E;
         int var3 = this.memory[var10];
         var39 = var3;
         int HL_L37981;
         if (F != 0) {
            int var31 = H << 8 | L;
            HL_L37981 = var31;
            int var32 = this.memory[var31];
            var39 = var3 & var32;
            int var10000 = this.memory[var31];
            var10000 = this.memory[var31];
            var10000 = this.memory[var31];
            if (var39 != 0) {
               return;
            }

            int var33 = this.memory[var10];
            int var34 = this.memory[var31];
            var39 = var33 | var34;
            var10000 = this.memory[var31];
            var10000 = this.memory[var31];
            var10000 = this.memory[var31];
         }

         this.memory[HL_L37981] = var39;
         ++L;
         int var13 = H << 8;
         int var14 = L & 255;
         HL_L37981 = var13 | var14;
         int DE_L37978 = var10 + 1;
         F = C & 1;
         int var15 = this.memory[DE_L37978];
         var39 = var15;
         if (F != 0) {
            int var28 = this.memory[HL_L37981];
            var39 = var15 & var28;
            int var52 = this.memory[HL_L37981];
            var52 = this.memory[HL_L37981];
            var52 = this.memory[HL_L37981];
            if (var39 != 0) {
               return;
            }

            int var29 = this.memory[DE_L37978];
            int var30 = this.memory[HL_L37981];
            var39 = var29 | var30;
            var52 = this.memory[HL_L37981];
            var52 = this.memory[HL_L37981];
            var52 = this.memory[HL_L37981];
         }

         this.memory[HL_L37981] = var39;
         --L;
         int var16 = H << 8;
         int var17 = L & 255;
         int var58 = var16 | var17;
         int var18 = H << 8;
         int var19 = L & 255;
         var58 = var18 | var19;
         ++H;
         int var20 = H << 8;
         int var21 = L & 255;
         HL_L37981 = var20 | var21;
         ++DE_L37978;
         var39 = H & 7;
         if (var39 == 0) {
            var39 = H - 8;
            H = var39;
            int var22 = var39 << 8;
            int var23 = L & 255;
            var58 = var22 | var23;
            int var41 = L + 32;
            L = var41;
            int var24 = var39 << 8;
            int var25 = var41 & 255;
            HL_L37981 = var24 | var25;
            var39 = var41 & 224;
            if (var39 == 0) {
               var39 += 8;
               H = var39;
               int var26 = var39 << 8;
               int var27 = var41 & 255;
               HL_L37981 = var26 | var27;
            }
         }

         --B;
      } while(B != 0);

      var39 ^= var39;
   }

   public void $9668() {
   }
}
""", generateAndDecompile());
  }
}
