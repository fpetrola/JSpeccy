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
                
                 int A_L37066 = var2 & 3;
                 if (A_L37066 != 0) {
                    int F_L37065 = A_L37066 - 1;
                    if (F_L37065 != 0) {
                       F_L37065 = A_L37066 - 2;
                       if (F_L37065 != 0) {
                          int var72 = this.memory[IX_L37056];
                          int var73 = var72 & 128;
                          this.memory[IX_L37056] = var73;
                          int A_L37096;
                          if (var72 != 0) {
                             int var85 = IX_L37056 + 1;
                             int var86 = this.memory[var85];
                             F_L37065 = var86 & 128;
                             if (F_L37065 != 0) {
                                int A_L37094 = var86 - 2;
                                A_L37096 = A_L37094;
                                F_L37065 = A_L37094 - 148;
                                if (F_L37065 < 0) {
                                   A_L37096 = A_L37094 - 2;
                                   F_L37065 = A_L37096 - 128;
                                   if (F_L37065 == 0) {
                                      A_L37096 ^= A_L37096;
                                   }
                                }
                             } else {
                                A_L37096 += 2;
                                F_L37065 = A_L37096 - 18;
                                if (F_L37065 < 0) {
                                   A_L37096 += 2;
                                }
                             }
                          } else {
                             int var74 = IX_L37056 + 1;
                             A_L37096 = this.memory[var74];
                             F_L37065 = A_L37096 & 128;
                             if (F_L37065 == 0) {
                                A_L37096 -= 2;
                                F_L37065 = A_L37096 - 20;
                                if (F_L37065 < 0) {
                                   A_L37096 -= 2;
                                   A_L37096 |= A_L37096;
                                   if (A_L37096 == 0) {
                                   }
                                }
                             } else {
                                A_L37096 += 2;
                                F_L37065 = A_L37096 - 146;
                                if (F_L37065 < 0) {
                                   A_L37096 += 2;
                                }
                             }
                          }
                
                          int var76 = IX_L37056 + 1;
                          this.memory[var76] = A_L37096;
                          int A_L37152 = A_L37096 & 127;
                          int var78 = IX_L37056 + 7;
                          int var79 = this.memory[var78];
                          F_L37065 = A_L37152 - var79;
                          int var80 = IX_L37056 + 7;
                          int var118 = this.memory[var80];
                          if (F_L37065 == 0) {
                             int var81 = this.memory[IX_L37056];
                             int A_L37163 = var81 ^ 128;
                             this.memory[IX_L37056] = A_L37163;
                          }
                       } else {
                          label81: {
                             int var36 = this.memory[IX_L37056];
                             int A_L37250 = var36 ^ 8;
                             this.memory[IX_L37056] = A_L37250;
                             int A_L37255 = A_L37250 & 24;
                             if (A_L37255 != 0) {
                                int var68 = this.memory[IX_L37056];
                                int A_L37262 = var68 + 32;
                                this.memory[IX_L37056] = A_L37262;
                             }
                
                             int var41 = IX_L37056 + 3;
                             int var42 = this.memory[var41];
                             int var44 = IX_L37056 + 4;
                             int var45 = this.memory[var44];
                             int A_L37270 = var42 + var45;
                             int var47 = IX_L37056 + 4;
                             int var112 = this.memory[var47];
                             int var48 = IX_L37056 + 4;
                             var112 = this.memory[var48];
                             int var49 = IX_L37056 + 4;
                             var112 = this.memory[var49];
                             int var51 = IX_L37056 + 3;
                             this.memory[var51] = A_L37270;
                             int var52 = IX_L37056 + 7;
                             int var53 = this.memory[var52];
                             F_L37065 = A_L37270 - var53;
                             int var55 = IX_L37056 + 7;
                             var112 = this.memory[var55];
                             if (F_L37065 < 0) {
                                int var60 = IX_L37056 + 6;
                                int var61 = this.memory[var60];
                                F_L37065 = A_L37270 - var61;
                                int var62 = IX_L37056 + 6;
                                var112 = this.memory[var62];
                                if (F_L37065 != 0 && F_L37065 >= 0) {
                                   break label81;
                                }
                
                                int var63 = IX_L37056 + 6;
                                int var64 = this.memory[var63];
                                int var67 = IX_L37056 + 3;
                                this.memory[var67] = var64;
                             }
                
                             int var56 = IX_L37056 + 4;
                             var112 = this.memory[var56];
                             int A_L37299 = this.initial;
                             int var59 = IX_L37056 + 4;
                             this.memory[var59] = A_L37299;
                          }
                       }
                    } else {
                       int var10 = this.memory[IX_L37056];
                       int var11 = var10 & 128;
                       this.memory[IX_L37056] = var11;
                       if (var10 == 0) {
                          int var24 = this.memory[IX_L37056];
                          int A_L37180 = var24 - 32;
                          int A_L37182 = A_L37180 & 127;
                          this.memory[IX_L37056] = A_L37182;
                          F_L37065 = A_L37182 - 96;
                          if (F_L37065 >= 0) {
                             int var29 = IX_L37056 + 2;
                             int var30 = this.memory[var29];
                             int A_L37194 = var30 & 31;
                             int var33 = IX_L37056 + 6;
                             int var34 = this.memory[var33];
                             F_L37065 = A_L37194 - var34;
                             int var35 = IX_L37056 + 6;
                             int var10000 = this.memory[var35];
                             if (F_L37065 != 0) {
                                var10000 = IX_L37056 + 2;
                                var10000 = IX_L37056 + 2;
                             } else {
                                this.memory[IX_L37056] = 129;
                             }
                          }
                       } else {
                          int var12 = this.memory[IX_L37056];
                          int A_L37215 = var12 + 32;
                          int A_L37217 = A_L37215 | 128;
                          this.memory[IX_L37056] = A_L37217;
                          F_L37065 = A_L37217 - 160;
                          if (F_L37065 < 0) {
                             int var17 = IX_L37056 + 2;
                             int var18 = this.memory[var17];
                             int A_L37229 = var18 & 31;
                             int var21 = IX_L37056 + 7;
                             int var22 = this.memory[var21];
                             F_L37065 = A_L37229 - var22;
                             int var23 = IX_L37056 + 7;
                             int var110 = this.memory[var23];
                             if (F_L37065 != 0) {
                                var110 = IX_L37056 + 2;
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
                
              int B_L38022;
              int A_L38014;
              do {
                 int F_L37976 = this.initial;
                 int C = '\\uffff';
                 F_L37976 = C & 1;
                 int DE_L37978 = '\\uffff';
                 int var7 = this.memory[DE_L37978];
                 int A_L37978 = var7;
                 int HL_L37981;
                 int L_L37981;
                 int H_L37981;
                 if (F_L37976 != 0) {
                    int H = 255;
                    H_L37981 = H;
                    L_L37981 = this.initial;
                    int var36 = H << 8 | L_L37981;
                    HL_L37981 = var36;
                    int var37 = this.memory[var36];
                    A_L37978 = var7 & var37;
                    int var10000 = this.memory[var36];
                    var10000 = this.memory[var36];
                    var10000 = this.memory[var36];
                    if (A_L37978 != 0) {
                       return;
                    }
                
                    A_L37978 = this.memory[DE_L37978];
                    int var38 = this.memory[var36];
                    A_L37978 |= var38;
                    var10000 = this.memory[var36];
                    var10000 = this.memory[var36];
                    var10000 = this.memory[var36];
                 }
                
                 this.memory[HL_L37981] = A_L37978;
                 ++L_L37981;
                 int var13 = H_L37981 << 8;
                 int var14 = L_L37981 & 255;
                 HL_L37981 = var13 | var14;
                 int DE_L37978 = DE_L37978 + 1;
                 int F_L37982 = C & 1;
                 int var16 = this.memory[DE_L37978];
                 int A_L37990 = var16;
                 if (F_L37982 != 0) {
                    int var33 = this.memory[HL_L37981];
                    A_L37990 = var16 & var33;
                    int var54 = this.memory[HL_L37981];
                    var54 = this.memory[HL_L37981];
                    var54 = this.memory[HL_L37981];
                    if (A_L37990 != 0) {
                       return;
                    }
                
                    A_L37990 = this.memory[DE_L37978];
                    int var34 = this.memory[HL_L37981];
                    A_L37990 |= var34;
                    var54 = this.memory[HL_L37981];
                    var54 = this.memory[HL_L37981];
                    var54 = this.memory[HL_L37981];
                 }
                
                 this.memory[HL_L37981] = A_L37990;
                 --L_L37981;
                 int var18 = H_L37981 << 8;
                 int var19 = L_L37981 & 255;
                 int var60 = var18 | var19;
                 int var20 = H_L37981 << 8;
                 int var21 = L_L37981 & 255;
                 HL_L37981 = var20 | var21;
                 ++H_L37981;
                 H_L37981 = H_L37981;
                 ++DE_L37978;
                 int A_L38002 = H_L37981 & 7;
                 if (A_L38002 == 0) {
                    int A_L38007 = H_L37981 - 8;
                    H_L37981 = A_L38007;
                    int A_L38011 = L_L37981 + 32;
                    L_L37981 = A_L38011;
                    int var31 = A_L38007 << 8;
                    int var32 = A_L38011 & 255;
                    HL_L37981 = var31 | var32;
                    A_L38014 = A_L38011 & 224;
                    if (A_L38014 == 0) {
                       A_L38014 = A_L38007 + 8;
                       H_L37981 = A_L38014;
                    }
                 }
                
                 B_L38022 = B_L37974 - 1;
              } while(B_L38022 != 0);
                
              A_L38014 ^= A_L38014;
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
                
              int A_L38014;
              do {
                 int F_L37976 = this.initial;
                 int C_L37976 = this.initial;
                 F_L37976 = C_L37976 & 1;
                 int DE_L37978 = 40160;
                 int var5 = this.memory[DE_L37978];
                 int A_L37978 = var5;
                 int HL_L38238;
                 int L_L37981;
                 int H_L37981;
                 if (F_L37976 != 0) {
                    H_L37981 = this.initial;
                    L_L37981 = this.initial;
                    int var38 = H_L37981 << 8 | L_L37981;
                    HL_L38238 = var38;
                    int var39 = this.memory[var38];
                    A_L37978 = var5 & var39;
                    int var10000 = this.memory[var38];
                    var10000 = this.memory[var38];
                    var10000 = this.memory[var38];
                    if (A_L37978 != 0) {
                       return;
                    }
                
                    A_L37978 = this.memory[DE_L37978];
                    int var40 = this.memory[var38];
                    A_L37978 |= var40;
                    var10000 = this.memory[var38];
                    var10000 = this.memory[var38];
                    var10000 = this.memory[var38];
                 }
                
                 this.memory[HL_L38238] = A_L37978;
                 ++L_L37981;
                 int var11 = H_L37981 << 8;
                 int var12 = L_L37981 & 255;
                 HL_L38238 = var11 | var12;
                 ++DE_L37978;
                 int F_L37982 = C_L37976 & 1;
                 int var14 = this.memory[DE_L37978];
                 int A_L37990 = var14;
                 if (F_L37982 != 0) {
                    int var36 = this.memory[HL_L38238];
                    A_L37990 = var14 & var36;
                    int var56 = this.memory[HL_L38238];
                    var56 = this.memory[HL_L38238];
                    var56 = this.memory[HL_L38238];
                    if (A_L37990 != 0) {
                       return;
                    }
                
                    A_L37990 = this.memory[DE_L37978];
                    int var37 = this.memory[HL_L38238];
                    A_L37990 |= var37;
                    var56 = this.memory[HL_L38238];
                    var56 = this.memory[HL_L38238];
                    var56 = this.memory[HL_L38238];
                 }
                
                 this.memory[HL_L38238] = A_L37990;
                 --L_L37981;
                 int var16 = H_L37981 << 8;
                 int var17 = L_L37981 & 255;
                 int var62 = var16 | var17;
                 int var18 = H_L37981 << 8;
                 int var19 = L_L37981 & 255;
                 var62 = var18 | var19;
                 ++H_L37981;
                 int var20 = H_L37981 << 8;
                 int var21 = L_L37981 & 255;
                 HL_L38238 = var20 | var21;
                 ++DE_L37978;
                 int A_L38002 = H_L37981 & 7;
                 if (A_L38002 == 0) {
                    int A_L38007 = H_L37981 - 8;
                    H_L37981 = A_L38007;
                    int var27 = A_L38007 << 8;
                    int var28 = L_L37981 & 255;
                    var62 = var27 | var28;
                    int A_L38011 = L_L37981 + 32;
                    L_L37981 = A_L38011;
                    int var32 = A_L38007 << 8;
                    int var33 = A_L38011 & 255;
                    HL_L38238 = var32 | var33;
                    A_L38014 = A_L38011 & 224;
                    if (A_L38014 == 0) {
                       A_L38014 = A_L38007 + 8;
                       H_L37981 = A_L38014;
                       int var34 = A_L38014 << 8;
                       int var35 = A_L38011 & 255;
                       HL_L38238 = var34 | var35;
                    }
                 }
                
                 --B_L37974;
              } while(B_L37974 != 0);
                
              A_L38014 ^= A_L38014;
           }
                
           public void $9668() {
           }
        }
        """, generateAndDecompile());
  }
}
