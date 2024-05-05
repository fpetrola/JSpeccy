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
              byte IX_L37060;
              int IX_L37305 = IX_L37060;
              byte IX_L37267;
              int IX_L37294 = IX_L37267;
              IX_L37267 = (byte)IX_L37060;
              int IX_L37149 = (byte)IX_L37060;
              int F = 100000;
              byte IX_L37056;
              IX_L37060 = IX_L37056;
              IX_L37056 = 33024;
                
              while(true) {
                 int var8 = this.memory[IX_L37060];
                 int F_L37063 = var8 - 255;
                 if (F_L37063 == 0) {
                    return;
                 }
                
                 int A_L37060 = var8 & 3;
                 if (A_L37060 != 0) {
                    F_L37063 = A_L37060 - 1;
                    if (F_L37063 != 0) {
                       F_L37063 = A_L37060 - 2;
                       if (F_L37063 != 0) {
                          int var58 = this.memory[IX_L37060];
                          int var59 = var58 & 128;
                          this.memory[IX_L37060] = var59;
                          int A_L37149;
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
                                int var100 = var72 + 2;
                                F_L37063 = var100 - 18;
                                if (F_L37063 < 0) {
                                   var100 += 2;
                                }
                             }
                          } else {
                             int var60 = IX_L37060 + 1;
                             int var61 = this.memory[var60];
                             F_L37063 = var61 & 128;
                             if (F_L37063 == 0) {
                                int A_L37119 = var61 - 2;
                                F_L37063 = A_L37119 - 20;
                                if (F_L37063 < 0) {
                                   A_L37119 -= 2;
                                   A_L37119 |= A_L37119;
                                   if (A_L37119 == 0) {
                                   }
                                }
                             } else {
                                int var96 = var61 + 2;
                                F_L37063 = var96 - 146;
                                if (F_L37063 < 0) {
                                   var96 += 2;
                                   A_L37149 = A_L37060;
                                }
                             }
                          }
                
                          int var63 = IX_L37149 + 1;
                          this.memory[var63] = A_L37149;
                          A_L37149 &= 127;
                          int var66 = IX_L37149 + 7;
                          int var67 = this.memory[var66];
                          int F_L37152 = A_L37149 - var67;
                          int var68 = IX_L37149 + 7;
                          int var111 = this.memory[var68];
                          if (F_L37152 == 0) {
                             int var69 = this.memory[IX_L37149];
                             int A_L37160 = var69 ^ 128;
                             this.memory[IX_L37149] = A_L37160;
                          }
                       } else {
                          label81: {
                             int var30 = this.memory[IX_L37060];
                             int A_L37247 = var30 ^ 8;
                             this.memory[IX_L37060] = A_L37247;
                             A_L37247 &= 24;
                             if (A_L37247 != 0) {
                                int var56 = this.memory[IX_L37060];
                                int A_L37259 = var56 + 32;
                                this.memory[IX_L37060] = A_L37259;
                             }
                
                             int var32 = IX_L37267 + 3;
                             int var33 = this.memory[var32];
                             int var35 = IX_L37267 + 4;
                             int var36 = this.memory[var35];
                             int A_L37267 = var33 + var36;
                             int var37 = IX_L37267 + 4;
                             int var106 = this.memory[var37];
                             int var39 = IX_L37267 + 4;
                             var106 = this.memory[var39];
                             int var40 = IX_L37267 + 4;
                             var106 = this.memory[var40];
                             int var41 = IX_L37267 + 3;
                             this.memory[var41] = A_L37267;
                             int var42 = IX_L37267 + 7;
                             int var43 = this.memory[var42];
                             int F_L37270 = A_L37267 - var43;
                             int var44 = IX_L37267 + 7;
                             var106 = this.memory[var44];
                             if (F_L37270 < 0) {
                                int var49 = IX_L37267 + 6;
                                int var50 = this.memory[var49];
                                F_L37270 = A_L37267 - var50;
                                int var51 = IX_L37267 + 6;
                                var106 = this.memory[var51];
                                if (F_L37270 != 0 && F_L37270 >= 0) {
                                   break label81;
                                }
                
                                int var52 = IX_L37267 + 6;
                                int var53 = this.memory[var52];
                                int var55 = IX_L37267 + 3;
                                this.memory[var55] = var53;
                             }
                
                             int var45 = IX_L37294 + 4;
                             int var46 = this.memory[var45];
                             int var48 = IX_L37294 + 4;
                             this.memory[var48] = var46;
                          }
                       }
                    } else {
                       int var12 = this.memory[IX_L37060];
                       int var13 = var12 & 128;
                       this.memory[IX_L37060] = var13;
                       if (var12 == 0) {
                          int var22 = this.memory[IX_L37060];
                          int A_L37177 = var22 - 32;
                          A_L37177 &= 127;
                          this.memory[IX_L37060] = A_L37177;
                          F_L37063 = A_L37177 - 96;
                          if (F_L37063 >= 0) {
                             int var24 = IX_L37060 + 2;
                             int var25 = this.memory[var24];
                             int A_L37191 = var25 & 31;
                             int var27 = IX_L37060 + 6;
                             int var28 = this.memory[var27];
                             F_L37063 = A_L37191 - var28;
                             int var29 = IX_L37060 + 6;
                             int var10000 = this.memory[var29];
                             if (F_L37063 != 0) {
                                var10000 = IX_L37060 + 2;
                                var10000 = IX_L37060 + 2;
                             } else {
                                this.memory[IX_L37060] = 129;
                             }
                          }
                       } else {
                          int var14 = this.memory[IX_L37060];
                          int A_L37212 = var14 + 32;
                          A_L37212 |= 128;
                          this.memory[IX_L37060] = A_L37212;
                          F_L37063 = A_L37212 - 160;
                          if (F_L37063 < 0) {
                             int var16 = IX_L37060 + 2;
                             int var17 = this.memory[var16];
                             int A_L37226 = var17 & 31;
                             int var19 = IX_L37060 + 7;
                             int var20 = this.memory[var19];
                             F_L37063 = A_L37226 - var20;
                             int var21 = IX_L37060 + 7;
                             int var104 = this.memory[var21];
                             if (F_L37063 != 0) {
                                var104 = IX_L37060 + 2;
                             } else {
                                this.memory[IX_L37060] = 97;
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
      byte DE_L37987;
      int DE_L38000 = DE_L37987;
      byte var3;
      int HL_L37997 = (byte)var3;
      byte DE_L37978;
      DE_L37987 = DE_L37978;
      byte HL_L37981;
      int HL_L37985 = HL_L37981;
      HL_L37981 = this.initial;
      DE_L37978 = this.initial;
      int B_L37974 = 16;
      int C_L37976 = this.initial;
      int F_L37976 = this.initial;

      int A_L38024;
      do {
         F_L37976 = C_L37976 & 1;
         int A_L38010;
         A_L38024 = A_L38010;
         int var13 = this.memory[DE_L37978];
         int A_L37985;
         int H_L37985;
         int var36;
         if (F_L37976 != 0) {
            int var43 = this.memory[HL_L37981];
            int A_L37978 = var13 & var43;
            int var10000 = this.memory[HL_L37981];
            var10000 = this.memory[HL_L37981];
            var10000 = this.memory[HL_L37981];
            if (A_L37978 != 0) {
               return;
            }

            int var44 = this.memory[DE_L37978];
            int var46 = this.memory[HL_L37981];
            int A_L37983 = var44 | var46;
            var10000 = this.memory[HL_L37981];
            var10000 = this.memory[HL_L37981];
            var10000 = this.memory[HL_L37981];
            H_L37985 = var36;
            A_L37985 = A_L37978;
         }

         this.memory[HL_L37985] = A_L37985;
         int var16 = HL_L37985 & 255;
         int L_L37985 = var16 + 1;
         ++DE_L37987;
         int F_L37986 = this.initial;
         F_L37986 = C_L37976 & 1;
         int var19 = this.memory[DE_L37987];
         int A_L37997;
         if (F_L37986 != 0) {
            var36 = HL_L37981 >> 8;
            int var37 = H_L37985 << 8 | L_L37985;
            int var39 = this.memory[var37];
            int A_L37990 = var19 & var39;
            int var62 = this.memory[var37];
            var62 = this.memory[var37];
            var62 = this.memory[var37];
            if (A_L37990 != 0) {
               return;
            }

            int var40 = this.memory[DE_L37987];
            int var42 = this.memory[var37];
            int A_L37995 = var40 | var42;
            var62 = this.memory[var37];
            var62 = this.memory[var37];
            var62 = this.memory[var37];
            A_L37997 = A_L37990;
         }

         var3 = H_L37985 << 8 | L_L37985;
         this.memory[HL_L37997] = A_L37997;
         int var24 = HL_L37997 & 255;
         int L_L37997 = var24 - 1;
         HL_L37981 = L_L37997 & 255;
         HL_L37981 = L_L37997 & 255;
         int var26 = HL_L37997 >> 8;
         int H_L37997 = var26 + 1;
         int var28 = H_L37997 << 8;
         int var29 = L_L37997 & 255;
         HL_L37981 = var28 | var29;
         ++DE_L38000;
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
              int F = 100000;
              byte DE_L37987;
              int DE_L38000 = DE_L37987;
              byte var4;
              int HL_L37997 = (byte)var4;
              byte DE_L37978;
              DE_L37987 = DE_L37978;
              byte HL_L38238;
              int HL_L37985 = HL_L38238;
              byte var9;
              int HL_L37981 = var9;
              byte var11;
              DE_L37978 = (byte)var11;
              int B_L37974 = 16;
              int C_L37976 = this.initial;
              int F_L37976 = this.initial;

              int var61;
              do {
                 F_L37976 = C_L37976 & 1;
                 int D_L38000 = this.initial;
                 int E_L38234 = this.initial;
                 var11 = D_L38000 << 8 | E_L38234;
                 int var17 = this.memory[DE_L37978];
                 int A_L37985;
                 int H_L37985;
                 int L_L37997;
                 int H_L37997;
                 int var43;
                 if (F_L37976 != 0) {
                    H_L37997 = this.initial;
                    L_L37997 = this.initial;
                    var9 = H_L37997 << 8 | L_L37997;
                    int var50 = this.memory[HL_L37981];
                    int A_L37978 = var17 & var50;
                    int var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    if (A_L37978 != 0) {
                       return;
                    }

                    int var51 = this.memory[DE_L37978];
                    int var53 = this.memory[HL_L37981];
                    int A_L37983 = var51 | var53;
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    var10000 = this.memory[HL_L37981];
                    H_L37985 = var43;
                    A_L37985 = A_L37978;
                 }

                 int HL = '\\u860a';
                 this.memory[HL_L37985] = A_L37985;
                 int var21 = HL_L37985 & 255;
                 int L_L37985 = var21 + 1;
                 ++DE_L37987;
                 int F_L37986 = this.initial;
                 F_L37986 = C_L37976 & 1;
                 int var24 = this.memory[DE_L37987];
                 int A_L37997;
                 if (F_L37986 != 0) {
                    var43 = HL >> 8;
                    int var44 = H_L37985 << 8 | L_L37985;
                    int var46 = this.memory[var44];
                    int A_L37990 = var24 & var46;
                    int var67 = this.memory[var44];
                    var67 = this.memory[var44];
                    var67 = this.memory[var44];
                    if (A_L37990 != 0) {
                       return;
                    }

                    int var47 = this.memory[DE_L37987];
                    int var49 = this.memory[var44];
                    int A_L37995 = var47 | var49;
                    var67 = this.memory[var44];
                    var67 = this.memory[var44];
                    var67 = this.memory[var44];
                    A_L37997 = A_L37990;
                 }

                 var4 = H_L37985 << 8 | L_L37985;
                 this.memory[HL_L37997] = A_L37997;
                 --L_L37997;
                 int var31 = H_L37997 << 8;
                 int var32 = L_L37997 & 255;
                 int var73 = var31 | var32;
                 int var33 = H_L37997 << 8;
                 int var34 = L_L37997 & 255;
                 var73 = var33 | var34;
                 ++H_L37997;
                 int var35 = H_L37997 << 8;
                 int var36 = L_L37997 & 255;
                 HL_L37981 = var35 | var36;
                 ++DE_L38000;
                 int A_L38001 = H_L37997 & 7;
                 if (A_L38001 == 0) {
                    int A_L38006 = H_L37997 - 8;
                    var61 = L_L37997 + 32;
                    var61 &= 224;
                    if (var61 == 0) {
                       int A_L38018 = A_L38006 + 8;
                    }
                 }

                 --B_L37974;
              } while(B_L37974 != 0);

              int A_L38024 = var61 ^ var61;
           }

           public void $9668() {
           }
        }
        """, generateAndDecompile());
  }
}
