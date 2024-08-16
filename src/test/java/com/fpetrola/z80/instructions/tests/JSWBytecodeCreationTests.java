package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.instructions.base.MockedMemory;
import com.fpetrola.z80.instructions.base.RealCodeBytecodeCreationTestsBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.MemorySpy;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@SuppressWarnings("ALL")
public class JSWBytecodeCreationTests<T extends WordNumber> extends RealCodeBytecodeCreationTestsBase<T> {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");

  @Test
  public void testJSW1() {
    startAddress = 37056;
    endAddress = 37307;
    firstAddress = startAddress;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $37056() {
              super.IX = 33024;
                
              while(true) {
                 int var1 = super.memory[super.IX];
                 super.A = var1;
                 int var2 = super.A - 255;
                 super.F = var2;
                 if (super.F == 0) {
                    return;
                 }
                
                 int var3 = super.A & 3;
                 super.A = var3;
                 super.F = super.A;
                 if (super.F != 0) {
                    int var5 = super.A - 1;
                    super.F = var5;
                    if (super.F != 0) {
                       int var30 = super.A - 2;
                       super.F = var30;
                       if (super.F != 0) {
                          int var59 = super.memory[super.IX];
                          int var60 = var59 & 128;
                          super.memory[super.IX] = var60;
                          super.F = var59;
                          if (super.F != 0) {
                             int var79 = super.IX + 1;
                             int var80 = super.memory[var79];
                             super.A = var80;
                             int var81 = super.A & 128;
                             super.F = var81;
                             if (super.F != 0) {
                                int var85 = super.A - 2;
                                super.A = var85;
                                super.F = super.A;
                                int var86 = super.A - 148;
                                super.F = var86;
                                if (super.F < 0) {
                                   int var87 = super.A - 2;
                                   super.A = var87;
                                   super.F = super.A;
                                   int var88 = super.A - 128;
                                   super.F = var88;
                                   if (super.F == 0) {
                                      int var89 = super.A ^ super.A;
                                      super.A = var89;
                                      super.F = super.A;
                                   }
                                }
                             } else {
                                int var82 = super.A + 2;
                                super.A = var82;
                                super.F = super.A;
                                int var83 = super.A - 18;
                                super.F = var83;
                                if (super.F < 0) {
                                   int var84 = super.A + 2;
                                   super.A = var84;
                                   super.F = super.A;
                                }
                             }
                          } else {
                             int var61 = super.IX + 1;
                             int var62 = super.memory[var61];
                             super.A = var62;
                             int var63 = super.A & 128;
                             super.F = var63;
                             if (super.F == 0) {
                                int var75 = super.A - 2;
                                super.A = var75;
                                super.F = super.A;
                                int var76 = super.A - 20;
                                super.F = var76;
                                if (super.F < 0) {
                                   int var77 = super.A - 2;
                                   super.A = var77;
                                   super.F = super.A;
                                   int var78 = super.A | super.A;
                                   super.A = var78;
                                   super.F = super.A;
                                   if (super.F == 0) {
                                      super.A = 128;
                                   }
                                }
                             } else {
                                int var64 = super.A + 2;
                                super.A = var64;
                                super.F = super.A;
                                int var65 = super.A - 146;
                                super.F = var65;
                                if (super.F < 0) {
                                   int var74 = super.A + 2;
                                   super.A = var74;
                                   super.F = super.A;
                                }
                             }
                          }
                
                          int var66 = super.IX + 1;
                          super.memory[var66] = super.A;
                          int var67 = super.A & 127;
                          super.A = var67;
                          super.F = super.A;
                          int var68 = super.IX + 7;
                          int var69 = super.memory[var68];
                          int var70 = super.A - var69;
                          super.F = var70;
                          int var71 = super.IX + 7;
                          int var99 = super.memory[var71];
                          if (super.F == 0) {
                             int var72 = super.memory[super.IX];
                             super.A = var72;
                             int var73 = super.A ^ 128;
                             super.A = var73;
                             super.F = super.A;
                             super.memory[super.IX] = super.A;
                          }
                       } else {
                          label81: {
                             int var31 = super.memory[super.IX];
                             super.A = var31;
                             int var32 = super.A ^ 8;
                             super.A = var32;
                             super.F = super.A;
                             super.memory[super.IX] = super.A;
                             int var33 = super.A & 24;
                             super.A = var33;
                             super.F = super.A;
                             if (super.F != 0) {
                                int var57 = super.memory[super.IX];
                                super.A = var57;
                                int var58 = super.A + 32;
                                super.A = var58;
                                super.F = super.A;
                                super.memory[super.IX] = super.A;
                             }
                
                             int var34 = super.IX + 3;
                             int var35 = super.memory[var34];
                             super.A = var35;
                             int var36 = super.IX + 4;
                             int var37 = super.memory[var36];
                             int var38 = super.A + var37;
                             super.A = var38;
                             int var39 = super.IX + 4;
                             int var94 = super.memory[var39];
                             int var40 = super.IX + 4;
                             var94 = super.memory[var40];
                             super.F = super.A;
                             int var41 = super.IX + 4;
                             var94 = super.memory[var41];
                             int var42 = super.IX + 3;
                             super.memory[var42] = super.A;
                             int var43 = super.IX + 7;
                             int var44 = super.memory[var43];
                             int var45 = super.A - var44;
                             super.F = var45;
                             int var46 = super.IX + 7;
                             var94 = super.memory[var46];
                             if (super.F < 0) {
                                int var50 = super.IX + 6;
                                int var51 = super.memory[var50];
                                int var52 = super.A - var51;
                                super.F = var52;
                                int var53 = super.IX + 6;
                                var94 = super.memory[var53];
                                if (super.F != 0 && super.F >= 0) {
                                   break label81;
                                }
                
                                int var54 = super.IX + 6;
                                int var55 = super.memory[var54];
                                super.A = var55;
                                int var56 = super.IX + 3;
                                super.memory[var56] = super.A;
                             }
                
                             int var47 = super.IX + 4;
                             int var48 = super.memory[var47];
                             super.A = var48;
                             int var49 = super.IX + 4;
                             super.memory[var49] = super.A;
                          }
                       }
                    } else {
                       int var6 = super.memory[super.IX];
                       int var7 = var6 & 128;
                       super.memory[super.IX] = var7;
                       super.F = var6;
                       if (super.F == 0) {
                          int var19 = super.memory[super.IX];
                          super.A = var19;
                          int var20 = super.A - 32;
                          super.A = var20;
                          super.F = super.A;
                          int var21 = super.A & 127;
                          super.A = var21;
                          super.F = super.A;
                          super.memory[super.IX] = super.A;
                          int var22 = super.A - 96;
                          super.F = var22;
                          if (super.F >= 0) {
                             int var23 = super.IX + 2;
                             int var24 = super.memory[var23];
                             super.A = var24;
                             int var25 = super.A & 31;
                             super.A = var25;
                             super.F = super.A;
                             int var26 = super.IX + 6;
                             int var27 = super.memory[var26];
                             int var28 = super.A - var27;
                             super.F = var28;
                             int var29 = super.IX + 6;
                             int var10000 = super.memory[var29];
                             if (super.F != 0) {
                                var10000 = super.IX + 2;
                                var10000 = super.IX + 2;
                             } else {
                                super.memory[super.IX] = 129;
                             }
                          }
                       } else {
                          int var8 = super.memory[super.IX];
                          super.A = var8;
                          int var9 = super.A + 32;
                          super.A = var9;
                          super.F = super.A;
                          int var10 = super.A | 128;
                          super.A = var10;
                          super.F = super.A;
                          super.memory[super.IX] = super.A;
                          int var11 = super.A - 160;
                          super.F = var11;
                          if (super.F < 0) {
                             int var12 = super.IX + 2;
                             int var13 = super.memory[var12];
                             super.A = var13;
                             int var14 = super.A & 31;
                             super.A = var14;
                             super.F = super.A;
                             int var15 = super.IX + 7;
                             int var16 = super.memory[var15];
                             int var17 = super.A - var16;
                             super.F = var17;
                             int var18 = super.IX + 7;
                             int var92 = super.memory[var18];
                             if (super.F != 0) {
                                var92 = super.IX + 2;
                             } else {
                                super.memory[super.IX] = 97;
                             }
                          }
                       }
                    }
                 }
                
                 super.DE = 8;
                 int var4 = super.IX + super.DE;
                 super.IX = var4;
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
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $37974() {
              super.B = 16;
                
              do {
                 int var1 = super.C & 1;
                 super.F = var1;
                 int var2 = super.memory[super.DE];
                 super.A = var2;
                 if (super.F != 0) {
                    int var22 = super.memory[super.HL];
                    int var23 = super.A & var22;
                    super.A = var23;
                    int var10000 = super.memory[super.HL];
                    var10000 = super.memory[super.HL];
                    super.F = super.A;
                    var10000 = super.memory[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var24 = super.memory[super.DE];
                    super.A = var24;
                    int var25 = super.memory[super.HL];
                    int var26 = super.A | var25;
                    super.A = var26;
                    var10000 = super.memory[super.HL];
                    var10000 = super.memory[super.HL];
                    super.F = super.A;
                    var10000 = super.memory[super.HL];
                 }
                
                 super.memory[super.HL] = super.A;
                 int var3 = super.L + 1;
                 super.L = var3;
                 int var4 = super.DE + 1;
                 super.DE = var4;
                 int var5 = super.C & 1;
                 super.F = var5;
                 int var6 = super.memory[super.DE];
                 super.A = var6;
                 if (super.F != 0) {
                    int var17 = super.memory[super.HL];
                    int var18 = super.A & var17;
                    super.A = var18;
                    int var32 = super.memory[super.HL];
                    var32 = super.memory[super.HL];
                    super.F = super.A;
                    var32 = super.memory[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var19 = super.memory[super.DE];
                    super.A = var19;
                    int var20 = super.memory[super.HL];
                    int var21 = super.A | var20;
                    super.A = var21;
                    var32 = super.memory[super.HL];
                    var32 = super.memory[super.HL];
                    super.F = super.A;
                    var32 = super.memory[super.HL];
                 }
                
                 super.memory[super.HL] = super.A;
                 int var7 = super.L + -1;
                 super.L = var7;
                 super.F = super.L;
                 int var8 = super.H + 1;
                 super.H = var8;
                 int var9 = super.DE + 1;
                 super.DE = var9;
                 super.A = super.H;
                 int var10 = super.A & 7;
                 super.A = var10;
                 super.F = super.A;
                 if (super.F == 0) {
                    super.A = super.H;
                    int var13 = super.A - 8;
                    super.A = var13;
                    super.F = super.A;
                    super.H = super.A;
                    super.A = super.L;
                    int var14 = super.A + 32;
                    super.A = var14;
                    super.F = super.A;
                    super.L = super.A;
                    int var15 = super.A & 224;
                    super.A = var15;
                    super.F = super.A;
                    if (super.F == 0) {
                       super.A = super.H;
                       int var16 = super.A + 8;
                       super.A = var16;
                       super.F = super.A;
                       super.H = super.A;
                    }
                 }
                
                 int var11 = super.B + -1;
                 super.B = var11;
              } while(super.B != 0);
                
              int var12 = super.A ^ super.A;
              super.A = var12;
              super.F = super.A;
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

    MockedMemory<T> memory = (MockedMemory<T>) ((MemorySpy<T>) state.getMemory()).getMemory();
    memory.enableReadyOnly();

    stepUntilComplete();

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $37974() {
              super.B = 16;
                
              do {
                 int var1 = super.C & 1;
                 super.F = var1;
                 int var2 = super.memory[super.DE];
                 super.A = var2;
                 if (super.F != 0) {
                    int var22 = super.memory[super.HL];
                    int var23 = super.A & var22;
                    super.A = var23;
                    int var10000 = super.memory[super.HL];
                    var10000 = super.memory[super.HL];
                    super.F = super.A;
                    var10000 = super.memory[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var24 = super.memory[super.DE];
                    super.A = var24;
                    int var25 = super.memory[super.HL];
                    int var26 = super.A | var25;
                    super.A = var26;
                    var10000 = super.memory[super.HL];
                    var10000 = super.memory[super.HL];
                    super.F = super.A;
                    var10000 = super.memory[super.HL];
                 }
                
                 super.memory[super.HL] = super.A;
                 int var3 = super.L + 1;
                 super.L = var3;
                 int var4 = super.DE + 1;
                 super.DE = var4;
                 int var5 = super.C & 1;
                 super.F = var5;
                 int var6 = super.memory[super.DE];
                 super.A = var6;
                 if (super.F != 0) {
                    int var17 = super.memory[super.HL];
                    int var18 = super.A & var17;
                    super.A = var18;
                    int var32 = super.memory[super.HL];
                    var32 = super.memory[super.HL];
                    super.F = super.A;
                    var32 = super.memory[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var19 = super.memory[super.DE];
                    super.A = var19;
                    int var20 = super.memory[super.HL];
                    int var21 = super.A | var20;
                    super.A = var21;
                    var32 = super.memory[super.HL];
                    var32 = super.memory[super.HL];
                    super.F = super.A;
                    var32 = super.memory[super.HL];
                 }
                
                 super.memory[super.HL] = super.A;
                 int var7 = super.L + -1;
                 super.L = var7;
                 super.F = super.L;
                 int var8 = super.H + 1;
                 super.H = var8;
                 int var9 = super.DE + 1;
                 super.DE = var9;
                 super.A = super.H;
                 int var10 = super.A & 7;
                 super.A = var10;
                 super.F = super.A;
                 if (super.F == 0) {
                    super.A = super.H;
                    int var13 = super.A - 8;
                    super.A = var13;
                    super.F = super.A;
                    super.H = super.A;
                    super.A = super.L;
                    int var14 = super.A + 32;
                    super.A = var14;
                    super.F = super.A;
                    super.L = super.A;
                    int var15 = super.A & 224;
                    super.A = var15;
                    super.F = super.A;
                    if (super.F == 0) {
                       super.A = super.H;
                       int var16 = super.A + 8;
                       super.A = var16;
                       super.F = super.A;
                       super.H = super.A;
                    }
                 }
                
                 int var11 = super.B + -1;
                 super.B = var11;
              } while(super.B != 0);
                
              int var12 = super.A ^ super.A;
              super.A = var12;
              super.F = super.A;
           }
                
           public void $38504() {
           }
        }
        """, generateAndDecompile());
  }


  @Test
  public void testJSDrawWilly() {
    startAddress = 38455;
    endAddress = 38527;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $38455() {
              int var1 = super.memory['\\u85cf'];
              super.A = var1;
              int var2 = super.A + super.B;
              super.A = var2;
              super.F = super.A;
              super.IXH = 130;
              super.IXL = super.A;
              int var3 = super.memory['\\u85d0'];
              super.A = var3;
              int var4 = super.A & 1;
              super.A = var4;
              super.F = super.A;
              super.E = super.A;
              int var5 = super.memory['\\u85d2'];
              super.A = var5;
              int var6 = super.A & 3;
              super.A = var6;
              super.F = super.A;
              int var7 = super.A | super.E;
              super.A = var7;
              super.F = super.A;
              super.E = super.A;
              super.D = 157;
              int var8 = super.memory['\\u8420'];
              super.A = var8;
              int var9 = super.A - 29;
              super.F = var9;
              if (super.F == 0) {
                 super.D = 182;
                 super.A = super.E;
                 int var28 = super.A ^ 128;
                 super.A = var28;
                 super.F = super.A;
                 super.E = super.A;
              }
                
              super.B = 16;
              int var10 = super.memory['\\u85d3'];
              super.A = var10;
              int var11 = super.A & 31;
              super.A = var11;
              super.F = super.A;
              super.C = super.A;
                
              do {
                 int var12 = super.memory[super.IX];
                 super.A = var12;
                 int var13 = super.IX + 1;
                 int var14 = super.memory[var13];
                 super.H = var14;
                 int var15 = super.A | super.C;
                 super.A = var15;
                 super.F = super.A;
                 super.L = super.A;
                 int var16 = super.memory[super.DE];
                 super.A = var16;
                 int var17 = super.memory[super.HL];
                 int var18 = super.A | var17;
                 super.A = var18;
                 int var10000 = super.memory[super.HL];
                 var10000 = super.memory[super.HL];
                 super.F = super.A;
                 var10000 = super.memory[super.HL];
                 super.memory[super.HL] = super.A;
                 int var19 = super.HL + 1;
                 super.HL = var19;
                 int var20 = super.DE + 1;
                 super.DE = var20;
                 int var21 = super.memory[super.DE];
                 super.A = var21;
                 int var22 = super.memory[super.HL];
                 int var23 = super.A | var22;
                 super.A = var23;
                 var10000 = super.memory[super.HL];
                 var10000 = super.memory[super.HL];
                 super.F = super.A;
                 var10000 = super.memory[super.HL];
                 super.memory[super.HL] = super.A;
                 int var24 = super.IX + 1;
                 super.IX = var24;
                 int var25 = super.IX + 1;
                 super.IX = var25;
                 int var26 = super.DE + 1;
                 super.DE = var26;
                 int var27 = super.B + -1;
                 super.B = var27;
              } while(super.B != 0);
                
           }
        }
        """, generateAndDecompile());
  }


  @Test
  public void test_Draw_the_items() {
    startAddress = 37841;
    endAddress = 37973;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    List<Block> blocks = RegisterTransformerInstructionSpy.blocksManager.getBlocks();
    blocks.forEach(b -> {
      if (b.getBlockType() instanceof CodeBlockType codeBlockType) {
        System.out.println(codeBlockType.getBlock().getRangeHandler().getStartAddress() + "-->" + codeBlockType.routine);
      }
    });

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $37841() {
              super.H = 164;
              int var1 = super.memory['\\ua3ff'];
              super.A = var1;
              super.L = super.A;
                
              do {
                 int var2 = super.memory[super.HL];
                 super.C = var2;
                 int var3 = super.C & -129;
                 super.C = var3;
                 int var4 = super.memory['\\u8420'];
                 super.A = var4;
                 int var5 = super.A | 64;
                 super.A = var5;
                 super.F = super.A;
                 int var6 = super.A - super.C;
                 super.F = var6;
                 if (super.F == 0) {
                    int var8 = super.memory[super.HL];
                    super.A = var8;
                    int var9 = super.A & 1;
                    super.A = var9;
                    super.F = super.A;
                    int var10 = super.A + 92;
                    super.A = var10;
                    super.F = super.A;
                    super.D = super.A;
                    int var11 = super.H + 1;
                    super.H = var11;
                    int var12 = super.memory[super.HL];
                    super.E = var12;
                    int var13 = super.H + -1;
                    super.H = var13;
                    super.F = super.H;
                    int var14 = super.memory[super.DE];
                    super.A = var14;
                    int var15 = super.A & 7;
                    super.A = var15;
                    super.F = super.A;
                    int var16 = super.A - 7;
                    super.F = var16;
                    if (super.F != 0) {
                       int var17 = super.memory['\\u85cb'];
                       super.A = var17;
                       int var18 = super.A + super.L;
                       super.A = var18;
                       super.F = super.A;
                       int var19 = super.A & 3;
                       super.A = var19;
                       super.F = super.A;
                       int var20 = super.A + 3;
                       super.A = var20;
                       super.F = super.A;
                       super.C = super.A;
                       int var21 = super.memory[super.DE];
                       super.A = var21;
                       int var22 = super.A & 248;
                       super.A = var22;
                       super.F = super.A;
                       int var23 = super.A | super.C;
                       super.A = var23;
                       super.F = super.A;
                       super.memory[super.DE] = super.A;
                       int var24 = super.memory[super.HL];
                       super.A = var24;
                       int var25 = super.A & 8;
                       super.A = var25;
                       super.F = super.A;
                       int var26 = super.A + 96;
                       super.A = var26;
                       super.F = super.A;
                       super.D = super.A;
                       super.HL = 32993;
                       super.B = 8;
                       this.$38555();
                    } else {
                       super.IX = 34172;
                
                       while(true) {
                          int var27 = super.IX + 2;
                          int var28 = super.memory[var27];
                          super.A = var28;
                          int var29 = super.A - 58;
                          super.F = var29;
                          if (super.F != 0) {
                             int var30 = super.memory['\\u80de'];
                             super.A = var30;
                             super.C = 128;
                
                             do {
                                int var31 = super.A ^ 24;
                                super.A = var31;
                                super.F = super.A;
                                super.E = super.A;
                                super.A = 144;
                                int var32 = super.A - super.C;
                                super.A = var32;
                                super.F = super.A;
                                super.B = super.A;
                                super.A = super.E;
                
                                do {
                                   int var33 = super.B + -1;
                                   super.B = var33;
                                } while(super.B != 0);
                
                                int var34 = super.C + -1;
                                super.C = var34;
                                super.F = super.C;
                                int var35 = super.C + -1;
                                super.C = var35;
                                super.F = super.C;
                             } while(super.F != 0);
                
                             int var36 = super.memory['\\u85de'];
                             super.A = var36;
                             int var37 = super.A + 1;
                             super.A = var37;
                             super.memory['\\u85de'] = super.A;
                             if (super.F == 0) {
                                super.A = 1;
                                super.memory['\\u85df'] = super.A;
                             }
                
                             int var38 = super.memory[super.HL];
                             int var39 = var38 & -65;
                             super.memory[super.HL] = var39;
                             super.memory[super.HL] = var38;
                             break;
                          }
                
                          int var40 = super.IX + 2;
                          super.memory[var40] = 48;
                       }
                    }
                 }
                
                 int var7 = super.L + 1;
                 super.L = var7;
              } while(super.F != 0);
                
           }
                
           public void $38555() {
           }
        }
        """, generateAndDecompile());
  }

}
