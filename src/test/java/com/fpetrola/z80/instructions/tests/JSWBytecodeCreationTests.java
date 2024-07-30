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
      int IX_L37056 = 33024;

      while(true) {
         int var2 = this.memory[IX_L37056];
         int F_L37063 = 65535;
         F_L37063 = var2 - 255;
         if (F_L37063 == 0) {
            return;
         }

         int A_L37063 = var2 & 3;
         if (A_L37063 != 0) {
            int F_L37065 = A_L37063 - 1;
            if (F_L37065 != 0) {
               F_L37065 = A_L37063 - 2;
               if (F_L37065 != 0) {
                  int var35 = this.memory[IX_L37056];
                  int var36 = var35 & 128;
                  this.memory[IX_L37056] = var36;
                  if (var35 != 0) {
                     int var42 = IX_L37056 + 1;
                     A_L37063 = this.memory[var42];
                     F_L37065 = A_L37063 & 128;
                     if (F_L37065 != 0) {
                        A_L37063 -= 2;
                        F_L37065 = A_L37063 - 148;
                        if (F_L37065 < 0) {
                           A_L37063 -= 2;
                           F_L37065 = A_L37063 - 128;
                           if (F_L37065 == 0) {
                              A_L37063 ^= A_L37063;
                           }
                        }
                     } else {
                        A_L37063 += 2;
                        F_L37065 = A_L37063 - 18;
                        if (F_L37065 < 0) {
                           A_L37063 += 2;
                        }
                     }
                  } else {
                     int var37 = IX_L37056 + 1;
                     A_L37063 = this.memory[var37];
                     F_L37065 = A_L37063 & 128;
                     if (F_L37065 == 0) {
                        A_L37063 -= 2;
                        F_L37065 = A_L37063 - 20;
                        if (F_L37065 < 0) {
                           A_L37063 -= 2;
                           A_L37063 |= A_L37063;
                           if (A_L37063 == 0) {
                           }
                        }
                     } else {
                        A_L37063 += 2;
                        F_L37065 = A_L37063 - 146;
                        if (F_L37065 < 0) {
                           A_L37063 += 2;
                        }
                     }
                  }

                  int var38 = IX_L37056 + 1;
                  this.memory[var38] = A_L37063;
                  A_L37063 &= 127;
                  int var39 = IX_L37056 + 7;
                  int var40 = this.memory[var39];
                  F_L37065 = A_L37063 - var40;
                  int var41 = IX_L37056 + 7;
                  int var94 = this.memory[var41];
                  if (F_L37065 == 0) {
                     A_L37063 = this.memory[IX_L37056];
                     A_L37063 ^= 128;
                     this.memory[IX_L37056] = A_L37063;
                  }
               } else {
                  label81: {
                     A_L37063 = this.memory[IX_L37056];
                     A_L37063 ^= 8;
                     this.memory[IX_L37056] = A_L37063;
                     A_L37063 &= 24;
                     if (A_L37063 != 0) {
                        A_L37063 = this.memory[IX_L37056];
                        A_L37063 += 32;
                        this.memory[IX_L37056] = A_L37063;
                     }

                     int var18 = IX_L37056 + 3;
                     A_L37063 = this.memory[var18];
                     int var19 = IX_L37056 + 4;
                     int var20 = this.memory[var19];
                     A_L37063 += var20;
                     int var21 = IX_L37056 + 4;
                     int var89 = this.memory[var21];
                     int var22 = IX_L37056 + 4;
                     var89 = this.memory[var22];
                     int var23 = IX_L37056 + 4;
                     var89 = this.memory[var23];
                     int var24 = IX_L37056 + 3;
                     this.memory[var24] = A_L37063;
                     int var25 = IX_L37056 + 7;
                     int var26 = this.memory[var25];
                     F_L37065 = A_L37063 - var26;
                     int var27 = IX_L37056 + 7;
                     var89 = this.memory[var27];
                     if (F_L37065 < 0) {
                        int var30 = IX_L37056 + 6;
                        int var31 = this.memory[var30];
                        F_L37065 = A_L37063 - var31;
                        int var32 = IX_L37056 + 6;
                        var89 = this.memory[var32];
                        if (F_L37065 != 0 && F_L37065 >= 0) {
                           break label81;
                        }

                        int var33 = IX_L37056 + 6;
                        A_L37063 = this.memory[var33];
                        int var34 = IX_L37056 + 3;
                        this.memory[var34] = A_L37063;
                     }

                     int var28 = IX_L37056 + 4;
                     A_L37063 = this.memory[var28];
                     int var29 = IX_L37056 + 4;
                     this.memory[var29] = A_L37063;
                  }
               }
            } else {
               int var8 = this.memory[IX_L37056];
               int var9 = var8 & 128;
               this.memory[IX_L37056] = var9;
               if (var8 == 0) {
                  A_L37063 = this.memory[IX_L37056];
                  A_L37063 -= 32;
                  A_L37063 &= 127;
                  this.memory[IX_L37056] = A_L37063;
                  F_L37065 = A_L37063 - 96;
                  if (F_L37065 >= 0) {
                     int var14 = IX_L37056 + 2;
                     A_L37063 = this.memory[var14];
                     A_L37063 &= 31;
                     int var15 = IX_L37056 + 6;
                     int var16 = this.memory[var15];
                     F_L37065 = A_L37063 - var16;
                     int var17 = IX_L37056 + 6;
                     int var10000 = this.memory[var17];
                     if (F_L37065 != 0) {
                        var10000 = IX_L37056 + 2;
                        var10000 = IX_L37056 + 2;
                     } else {
                        this.memory[IX_L37056] = 129;
                     }
                  }
               } else {
                  A_L37063 = this.memory[IX_L37056];
                  A_L37063 += 32;
                  A_L37063 |= 128;
                  this.memory[IX_L37056] = A_L37063;
                  F_L37065 = A_L37063 - 160;
                  if (F_L37065 < 0) {
                     int var10 = IX_L37056 + 2;
                     A_L37063 = this.memory[var10];
                     A_L37063 &= 31;
                     int var11 = IX_L37056 + 7;
                     int var12 = this.memory[var11];
                     F_L37065 = A_L37063 - var12;
                     int var13 = IX_L37056 + 7;
                     int var87 = this.memory[var13];
                     if (F_L37065 != 0) {
                        var87 = IX_L37056 + 2;
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
      int B_L37974 = 16;

      int var33;
      do {
         int F_L37976 = this.initial;
         int C = '\\uffff';
         F_L37976 = C & 1;
         int DE_L37978 = '\\uffff';
         int var7 = this.memory[DE_L37978];
         int HL_L37981;
         int L_L37981;
         int H_L37981;
         if (F_L37976 != 0) {
            int H = 255;
            H_L37981 = H;
            L_L37981 = this.initial;
            int var26 = H << 8 | L_L37981;
            HL_L37981 = var26;
            int var27 = this.memory[var26];
            var33 = var7 & var27;
            int var10000 = this.memory[var26];
            var10000 = this.memory[var26];
            var10000 = this.memory[var26];
            if (var33 != 0) {
               return;
            }

            var33 = this.memory[DE_L37978];
            int var28 = this.memory[var26];
            var33 |= var28;
            var10000 = this.memory[var26];
            var10000 = this.memory[var26];
            var10000 = this.memory[var26];
         }

         this.memory[HL_L37981] = var33;
         ++L_L37981;
         int var14 = H_L37981 << 8;
         int var15 = L_L37981 & 255;
         HL_L37981 = var14 | var15;
         int DE_L37978 = DE_L37978 + 1;
         int F_L37982 = C & 1;
         var33 = this.memory[DE_L37978];
         if (F_L37982 != 0) {
            int var23 = this.memory[HL_L37981];
            var33 &= var23;
            int var47 = this.memory[HL_L37981];
            var47 = this.memory[HL_L37981];
            var47 = this.memory[HL_L37981];
            if (var33 != 0) {
               return;
            }

            var33 = this.memory[DE_L37978];
            int var24 = this.memory[HL_L37981];
            var33 |= var24;
            var47 = this.memory[HL_L37981];
            var47 = this.memory[HL_L37981];
            var47 = this.memory[HL_L37981];
         }

         this.memory[HL_L37981] = var33;
         --L_L37981;
         int var17 = H_L37981 << 8;
         int var18 = L_L37981 & 255;
         int var53 = var17 | var18;
         int var19 = H_L37981 << 8;
         int var20 = L_L37981 & 255;
         HL_L37981 = var19 | var20;
         ++H_L37981;
         H_L37981 = H_L37981;
         ++DE_L37978;
         var33 = H_L37981 & 7;
         if (var33 == 0) {
            var33 = H_L37981 - 8;
            H_L37981 = var33;
            int var38 = L_L37981 + 32;
            L_L37981 = var38;
            int var21 = var33 << 8;
            int var22 = var38 & 255;
            HL_L37981 = var21 | var22;
            var33 = var38 & 224;
            if (var33 == 0) {
               var33 += 8;
               H_L37981 = var33;
            }
         }

         --B_L37974;
      } while(B_L37974 != 0);

      var33 ^= var33;
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
      int B_L37974 = 16;

      int var39;
      do {
         int F_L37976 = this.initial;
         int C_L37976 = this.initial;
         F_L37976 = C_L37976 & 1;
         int D_L37978 = this.initial;
         int E_L37978 = this.initial;
         int var6 = D_L37978 << 8 | E_L37978;
         int var8 = this.memory[var6];
         int HL_L38238;
         int L_L37981;
         int H_L37981;
         if (F_L37976 != 0) {
            H_L37981 = this.initial;
            L_L37981 = this.initial;
            int var32 = H_L37981 << 8 | L_L37981;
            HL_L38238 = var32;
            int var33 = this.memory[var32];
            var39 = var8 & var33;
            int var10000 = this.memory[var32];
            var10000 = this.memory[var32];
            var10000 = this.memory[var32];
            if (var39 != 0) {
               return;
            }

            var39 = this.memory[var6];
            int var34 = this.memory[var32];
            var39 |= var34;
            var10000 = this.memory[var32];
            var10000 = this.memory[var32];
            var10000 = this.memory[var32];
         }

         this.memory[HL_L38238] = var39;
         ++L_L37981;
         int var15 = H_L37981 << 8;
         int var16 = L_L37981 & 255;
         HL_L38238 = var15 | var16;
         int DE_L37978 = var6 + 1;
         int F_L37982 = C_L37976 & 1;
         var39 = this.memory[DE_L37978];
         if (F_L37982 != 0) {
            int var30 = this.memory[HL_L38238];
            var39 &= var30;
            int var52 = this.memory[HL_L38238];
            var52 = this.memory[HL_L38238];
            var52 = this.memory[HL_L38238];
            if (var39 != 0) {
               return;
            }

            var39 = this.memory[DE_L37978];
            int var31 = this.memory[HL_L38238];
            var39 |= var31;
            var52 = this.memory[HL_L38238];
            var52 = this.memory[HL_L38238];
            var52 = this.memory[HL_L38238];
         }

         this.memory[HL_L38238] = var39;
         --L_L37981;
         int var18 = H_L37981 << 8;
         int var19 = L_L37981 & 255;
         int var58 = var18 | var19;
         int var20 = H_L37981 << 8;
         int var21 = L_L37981 & 255;
         var58 = var20 | var21;
         ++H_L37981;
         int var22 = H_L37981 << 8;
         int var23 = L_L37981 & 255;
         HL_L38238 = var22 | var23;
         ++DE_L37978;
         var39 = H_L37981 & 7;
         if (var39 == 0) {
            var39 = H_L37981 - 8;
            H_L37981 = var39;
            int var24 = var39 << 8;
            int var25 = L_L37981 & 255;
            var58 = var24 | var25;
            int var44 = L_L37981 + 32;
            L_L37981 = var44;
            int var26 = var39 << 8;
            int var27 = var44 & 255;
            HL_L38238 = var26 | var27;
            var39 = var44 & 224;
            if (var39 == 0) {
               var39 += 8;
               H_L37981 = var39;
               int var28 = var39 << 8;
               int var29 = var44 & 255;
               HL_L38238 = var28 | var29;
            }
         }

         --B_L37974;
      } while(B_L37974 != 0);

      var39 ^= var39;
   }

   public void $9668() {
   }
}
""", generateAndDecompile());
  }
}
