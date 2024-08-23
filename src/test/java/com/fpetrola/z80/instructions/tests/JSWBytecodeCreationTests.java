package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.instructions.base.RealCodeBytecodeCreationTestsBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
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
              int var1 = super.mem[super.IX];
              super.A = var1;
              int var2 = super.A - 255;
              super.F = var2;
              if (super.F != 0) {
                 label66: {
                    int var3 = super.A & 3;
                    super.A = var3;
                    super.F = super.A;
                    int var4 = super.A - 1;
                    super.F = var4;
                    if (super.F != 0) {
                       int var57 = super.A - 2;
                       super.F = var57;
                       if (super.F == 0) {
                          break label66;
                       }
                
                       int var58 = super.mem[super.IX];
                       int var59 = var58 & 128;
                       super.mem[super.IX] = var59;
                       super.F = var58;
                       if (super.F != 0) {
                          int var78 = super.IX + 1;
                          int var79 = super.mem[var78];
                          super.A = var79;
                          int var80 = super.A & 128;
                          super.F = var80;
                          if (super.F != 0) {
                             int var84 = super.A - 2;
                             super.A = var84;
                             super.F = super.A;
                             int var85 = super.A - 148;
                             super.F = var85;
                             if (super.F < 0) {
                                int var86 = super.A - 2;
                                super.A = var86;
                                super.F = super.A;
                                int var87 = super.A - 128;
                                super.F = var87;
                                if (super.F == 0) {
                                   int var88 = super.A ^ super.A;
                                   super.A = var88;
                                   super.F = super.A;
                                }
                             }
                          } else {
                             int var81 = super.A + 2;
                             super.A = var81;
                             super.F = super.A;
                             int var82 = super.A - 18;
                             super.F = var82;
                             if (super.F < 0) {
                                int var83 = super.A + 2;
                                super.A = var83;
                                super.F = super.A;
                             }
                          }
                       } else {
                          int var60 = super.IX + 1;
                          int var61 = super.mem[var60];
                          super.A = var61;
                          int var62 = super.A & 128;
                          super.F = var62;
                          if (super.F == 0) {
                             int var74 = super.A - 2;
                             super.A = var74;
                             super.F = super.A;
                             int var75 = super.A - 20;
                             super.F = var75;
                             if (super.F < 0) {
                                int var76 = super.A - 2;
                                super.A = var76;
                                super.F = super.A;
                                int var77 = super.A | super.A;
                                super.A = var77;
                                super.F = super.A;
                                if (super.F == 0) {
                                   super.A = 128;
                                }
                             }
                          } else {
                             int var63 = super.A + 2;
                             super.A = var63;
                             super.F = super.A;
                             int var64 = super.A - 146;
                             super.F = var64;
                             if (super.F < 0) {
                                int var73 = super.A + 2;
                                super.A = var73;
                                super.F = super.A;
                             }
                          }
                       }
                
                       int var65 = super.IX + 1;
                       super.mem[var65] = super.A;
                       int var66 = super.A & 127;
                       super.A = var66;
                       super.F = super.A;
                       int var67 = super.IX + 7;
                       int var68 = super.mem[var67];
                       int var69 = super.A - var68;
                       super.F = var69;
                       int var70 = super.IX + 7;
                       int var10000 = super.mem[var70];
                       int var71 = super.mem[super.IX];
                       super.A = var71;
                       int var72 = super.A ^ 128;
                       super.A = var72;
                       super.F = super.A;
                       super.mem[super.IX] = super.A;
                    }
                
                    int var5 = super.mem[super.IX];
                    int var6 = var5 & 128;
                    super.mem[super.IX] = var6;
                    super.F = var5;
                    if (super.F == 0) {
                       int var46 = super.mem[super.IX];
                       super.A = var46;
                       int var47 = super.A - 32;
                       super.A = var47;
                       super.F = super.A;
                       int var48 = super.A & 127;
                       super.A = var48;
                       super.F = super.A;
                       super.mem[super.IX] = super.A;
                       int var49 = super.A - 96;
                       super.F = var49;
                       int var50 = super.IX + 2;
                       int var51 = super.mem[var50];
                       super.A = var51;
                       int var52 = super.A & 31;
                       super.A = var52;
                       super.F = super.A;
                       int var53 = super.IX + 6;
                       int var54 = super.mem[var53];
                       int var55 = super.A - var54;
                       super.F = var55;
                       int var56 = super.IX + 6;
                       int var89 = super.mem[var56];
                       if (super.F != 0) {
                          var89 = super.IX + 2;
                          var89 = super.IX + 2;
                       }
                
                       super.mem[super.IX] = 129;
                    }
                
                    int var7 = super.mem[super.IX];
                    super.A = var7;
                    int var8 = super.A + 32;
                    super.A = var8;
                    super.F = super.A;
                    int var9 = super.A | 128;
                    super.A = var9;
                    super.F = super.A;
                    super.mem[super.IX] = super.A;
                    int var10 = super.A - 160;
                    super.F = var10;
                    int var11 = super.IX + 2;
                    int var12 = super.mem[var11];
                    super.A = var12;
                    int var13 = super.A & 31;
                    super.A = var13;
                    super.F = super.A;
                    int var14 = super.IX + 7;
                    int var15 = super.mem[var14];
                    int var16 = super.A - var15;
                    super.F = var16;
                    int var17 = super.IX + 7;
                    int var92 = super.mem[var17];
                    if (super.F != 0) {
                       var92 = super.IX + 2;
                    }
                
                    super.mem[super.IX] = 97;
                 }
                
                 int var18 = super.mem[super.IX];
                 super.A = var18;
                 int var19 = super.A ^ 8;
                 super.A = var19;
                 super.F = super.A;
                 super.mem[super.IX] = super.A;
                 int var20 = super.A & 24;
                 super.A = var20;
                 super.F = super.A;
                 if (super.F != 0) {
                    int var44 = super.mem[super.IX];
                    super.A = var44;
                    int var45 = super.A + 32;
                    super.A = var45;
                    super.F = super.A;
                    super.mem[super.IX] = super.A;
                 }
                
                 int var21 = super.IX + 3;
                 int var22 = super.mem[var21];
                 super.A = var22;
                 int var23 = super.IX + 4;
                 int var24 = super.mem[var23];
                 int var25 = super.A + var24;
                 super.A = var25;
                 int var26 = super.IX + 4;
                 int var94 = super.mem[var26];
                 int var27 = super.IX + 4;
                 var94 = super.mem[var27];
                 super.F = super.A;
                 int var28 = super.IX + 4;
                 var94 = super.mem[var28];
                 int var29 = super.IX + 3;
                 super.mem[var29] = super.A;
                 int var30 = super.IX + 7;
                 int var31 = super.mem[var30];
                 int var32 = super.A - var31;
                 super.F = var32;
                 int var33 = super.IX + 7;
                 var94 = super.mem[var33];
                 if (super.F < 0) {
                    int var37 = super.IX + 6;
                    int var38 = super.mem[var37];
                    int var39 = super.A - var38;
                    super.F = var39;
                    int var40 = super.IX + 6;
                    var94 = super.mem[var40];
                    if (super.F != 0) {
                    }
                
                    int var41 = super.IX + 6;
                    int var42 = super.mem[var41];
                    super.A = var42;
                    int var43 = super.IX + 3;
                    super.mem[var43] = super.A;
                 }
                
                 int var34 = super.IX + 4;
                 int var35 = super.mem[var34];
                 super.A = var35;
                 int var36 = super.IX + 4;
                 super.mem[var36] = super.A;
              }
           }
                
           public void $37302() {
              super.DE = 8;
              int var1 = super.IX + super.DE;
              super.IX = var1;
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
                 int var2 = super.mem[super.DE];
                 super.A = var2;
                 if (super.F != 0) {
                    int var22 = super.mem[super.HL];
                    int var23 = super.A & var22;
                    super.A = var23;
                    int var10000 = super.mem[super.HL];
                    var10000 = super.mem[super.HL];
                    super.F = super.A;
                    var10000 = super.mem[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var24 = super.mem[super.DE];
                    super.A = var24;
                    int var25 = super.mem[super.HL];
                    int var26 = super.A | var25;
                    super.A = var26;
                    var10000 = super.mem[super.HL];
                    var10000 = super.mem[super.HL];
                    super.F = super.A;
                    var10000 = super.mem[super.HL];
                 }
                
                 super.mem[super.HL] = super.A;
                 int var3 = super.L + 1;
                 super.L = var3;
                 int var4 = super.DE + 1;
                 super.DE = var4;
                 int var5 = super.C & 1;
                 super.F = var5;
                 int var6 = super.mem[super.DE];
                 super.A = var6;
                 if (super.F != 0) {
                    int var17 = super.mem[super.HL];
                    int var18 = super.A & var17;
                    super.A = var18;
                    int var32 = super.mem[super.HL];
                    var32 = super.mem[super.HL];
                    super.F = super.A;
                    var32 = super.mem[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var19 = super.mem[super.DE];
                    super.A = var19;
                    int var20 = super.mem[super.HL];
                    int var21 = super.A | var20;
                    super.A = var21;
                    var32 = super.mem[super.HL];
                    var32 = super.mem[super.HL];
                    super.F = super.A;
                    var32 = super.mem[super.HL];
                 }
                
                 super.mem[super.HL] = super.A;
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
              int var1 = super.mem[342550];
              super.A = var1;
              int var2 = super.A + super.B;
              super.A = var2;
              super.F = super.A;
              super.IXH = 130;
              super.IXL = super.A;
              int var3 = super.mem[342560];
              super.A = var3;
              int var4 = super.A & 1;
              super.A = var4;
              super.F = super.A;
              int var5 = super.A >> 1;
              super.A = var5;
              super.E = super.A;
              int var6 = super.mem[342580];
              super.A = var6;
              int var7 = super.A & 3;
              super.A = var7;
              super.F = super.A;
              int var8 = super.A >> 1;
              super.A = var8;
              int var9 = super.A >> 1;
              super.A = var9;
              int var10 = super.A >> 1;
              super.A = var10;
              int var11 = super.A | super.E;
              super.A = var11;
              super.F = super.A;
              super.E = super.A;
              super.D = 157;
              int var12 = super.mem[338240];
              super.A = var12;
              int var13 = super.A - 29;
              super.F = var13;
              if (super.F == 0) {
                 super.D = 182;
                 super.A = super.E;
                 int var32 = super.A ^ 128;
                 super.A = var32;
                 super.F = super.A;
                 super.E = super.A;
              }
                
              super.B = 16;
              int var14 = super.mem[342590];
              super.A = var14;
              int var15 = super.A & 31;
              super.A = var15;
              super.F = super.A;
              super.C = super.A;
                
              do {
                 int var16 = super.mem[super.IX];
                 super.A = var16;
                 int var17 = super.IX + 1;
                 int var18 = super.mem[var17];
                 super.H = var18;
                 int var19 = super.A | super.C;
                 super.A = var19;
                 super.F = super.A;
                 super.L = super.A;
                 int var20 = super.mem[super.DE];
                 super.A = var20;
                 int var21 = super.mem[super.HL];
                 int var22 = super.A | var21;
                 super.A = var22;
                 int var10000 = super.mem[super.HL];
                 var10000 = super.mem[super.HL];
                 super.F = super.A;
                 var10000 = super.mem[super.HL];
                 super.mem[super.HL] = super.A;
                 int var23 = super.HL + 1;
                 super.HL = var23;
                 int var24 = super.DE + 1;
                 super.DE = var24;
                 int var25 = super.mem[super.DE];
                 super.A = var25;
                 int var26 = super.mem[super.HL];
                 int var27 = super.A | var26;
                 super.A = var27;
                 var10000 = super.mem[super.HL];
                 var10000 = super.mem[super.HL];
                 super.F = super.A;
                 var10000 = super.mem[super.HL];
                 super.mem[super.HL] = super.A;
                 int var28 = super.IX + 1;
                 super.IX = var28;
                 int var29 = super.IX + 1;
                 super.IX = var29;
                 int var30 = super.DE + 1;
                 super.DE = var30;
                 int var31 = super.B + -1;
                 super.B = var31;
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
              int var1 = super.mem[419830];
              super.A = var1;
              super.L = super.A;
                
              do {
                 int var2 = super.mem[super.HL];
                 super.C = var2;
                 int var3 = super.C & -129;
                 super.C = var3;
                 int var4 = super.mem[338240];
                 super.A = var4;
                 int var5 = super.A | 64;
                 super.A = var5;
                 super.F = super.A;
                 int var6 = super.A - super.C;
                 super.F = var6;
                 if (super.F == 0) {
                    int var8 = super.mem[super.HL];
                    super.A = var8;
                    int var9 = super.A << 1;
                    super.A = var9;
                    int var10 = super.A & 1;
                    super.A = var10;
                    super.F = super.A;
                    int var11 = super.A + 92;
                    super.A = var11;
                    super.F = super.A;
                    super.D = super.A;
                    int var12 = super.H + 1;
                    super.H = var12;
                    int var13 = super.mem[super.HL];
                    super.E = var13;
                    int var14 = super.H + -1;
                    super.H = var14;
                    super.F = super.H;
                    int var15 = super.mem[super.DE];
                    super.A = var15;
                    int var16 = super.A & 7;
                    super.A = var16;
                    super.F = super.A;
                    int var17 = super.A - 7;
                    super.F = var17;
                    if (super.F != 0) {
                       int var18 = super.mem[342510];
                       super.A = var18;
                       int var19 = super.A + super.L;
                       super.A = var19;
                       super.F = super.A;
                       int var20 = super.A & 3;
                       super.A = var20;
                       super.F = super.A;
                       int var21 = super.A + 3;
                       super.A = var21;
                       super.F = super.A;
                       super.C = super.A;
                       int var22 = super.mem[super.DE];
                       super.A = var22;
                       int var23 = super.A & 248;
                       super.A = var23;
                       super.F = super.A;
                       int var24 = super.A | super.C;
                       super.A = var24;
                       super.F = super.A;
                       super.mem[super.DE] = super.A;
                       int var25 = super.mem[super.HL];
                       super.A = var25;
                       int var26 = super.A << 1;
                       super.A = var26;
                       int var27 = super.A << 1;
                       super.A = var27;
                       int var28 = super.A << 1;
                       super.A = var28;
                       int var29 = super.A << 1;
                       super.A = var29;
                       int var30 = super.A & 8;
                       super.A = var30;
                       super.F = super.A;
                       int var31 = super.A + 96;
                       super.A = var31;
                       super.F = super.A;
                       super.D = super.A;
                       super.HL = 32993;
                       super.B = 8;
                       this.$38555();
                    } else {
                       super.IX = 34172;
                
                       while(true) {
                          int var32 = super.IX + 2;
                          int var33 = super.mem[var32];
                          super.A = var33;
                          int var34 = super.A - 58;
                          super.F = var34;
                          if (super.F != 0) {
                             int var35 = super.mem[329900];
                             super.A = var35;
                             super.C = 128;
                
                             do {
                                int var36 = super.A ^ 24;
                                super.A = var36;
                                super.F = super.A;
                                super.E = super.A;
                                super.A = 144;
                                int var37 = super.A - super.C;
                                super.A = var37;
                                super.F = super.A;
                                super.B = super.A;
                                super.A = super.E;
                
                                do {
                                   int var38 = super.B + -1;
                                   super.B = var38;
                                } while(super.B != 0);
                
                                int var39 = super.C + -1;
                                super.C = var39;
                                super.F = super.C;
                                int var40 = super.C + -1;
                                super.C = var40;
                                super.F = super.C;
                             } while(super.F != 0);
                
                             int var41 = super.mem[342700];
                             super.A = var41;
                             int var42 = super.A + 1;
                             super.A = var42;
                             super.mem[342700] = super.A;
                             if (super.F == 0) {
                                super.A = 1;
                                super.mem[342710] = super.A;
                             }
                
                             int var43 = super.mem[super.HL];
                             int var44 = var43 & -65;
                             super.mem[super.HL] = var44;
                             super.mem[super.HL] = var43;
                             break;
                          }
                
                          int var45 = super.IX + 2;
                          super.mem[var45] = 48;
                       }
                    }
                 }
                
                 int var7 = super.L + 1;
                 super.L = var7;
              } while(super.F != 0);
                
           }
                
           public void $38555() {
              do {
                 int var1 = super.mem[super.HL];
                 super.A = var1;
                 super.mem[super.DE] = super.A;
                 int var2 = super.HL + 1;
                 super.HL = var2;
                 int var3 = super.D + 1;
                 super.D = var3;
                 int var4 = super.B + -1;
                 super.B = var4;
              } while(super.B != 0);
                
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void testJSWMoveWilly() {
    startAddress = 38555;
    endAddress = 38343;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    memoryReadOnly(false);

    stepUntilComplete();

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $34463() {
              super.HL = 16384;
              super.DE = 16385;
              super.BC = 6911;
              super.mem[super.HL] = 0;
              this.ldir();
              super.IX = 34187;
           }
                
           public void $34762() {
              int var1 = super.A ^ super.A;
              super.A = var1;
              super.F = super.A;
              super.mem[342540] = super.A;
              super.mem[342730] = super.A;
              super.mem[342530] = super.A;
              super.mem[342570] = super.A;
              super.mem[342510] = super.A;
              super.mem[342720] = super.A;
              super.mem[342710] = super.A;
              super.A = 7;
              super.mem[342520] = super.A;
              super.A = 208;
              super.mem[342550] = super.A;
              super.A = 33;
              super.mem[338240] = super.A;
              super.HL = 23988;
              super.HL = 34172;
              super.mem[super.HL] = 48;
              int var2 = super.HL + 1;
              super.HL = var2;
              super.mem[super.HL] = 48;
              int var3 = super.HL + 1;
              super.HL = var3;
              super.mem[super.HL] = 48;
              super.H = 164;
              int var4 = super.mem[419830];
              super.A = var4;
              super.L = super.A;
              super.mem[342700] = super.A;
                
              do {
                 int var5 = super.mem[super.HL];
                 int var6 = var5 | 64;
                 super.mem[super.HL] = var6;
                 super.mem[super.HL] = var5;
                 int var7 = super.L + 1;
                 super.L = var7;
              } while(super.F != 0);
                
              super.HL = 34274;
              int var8 = super.mem[super.HL];
              int var9 = var8 | 1;
              super.mem[super.HL] = var9;
              super.mem[super.HL] = var8;
                
              label70:
              while(true) {
                 super.HL = 16384;
                 super.DE = 16385;
                 super.BC = 6143;
                 super.mem[super.HL] = 0;
                 this.ldir();
                 super.HL = 38912;
                 super.BC = 768;
                 this.ldir();
                 super.HL = 23136;
                 super.DE = 23137;
                 super.BC = 31;
                 super.mem[super.HL] = 70;
                 this.ldir();
                 super.IX = 33876;
                 super.DE = 20576;
                 super.C = 32;
                 this.$38528();
                 super.DE = 22528;
                
                 do {
                    int var10 = super.mem[super.DE];
                    super.A = var10;
                    int var11 = super.A | super.A;
                    super.A = var11;
                    super.F = super.A;
                    if (super.F != 0) {
                       int var43 = super.A - 211;
                       super.F = var43;
                       if (super.F != 0) {
                          int var44 = super.A - 9;
                          super.F = var44;
                          if (super.F != 0) {
                             int var45 = super.A - 45;
                             super.F = var45;
                             if (super.F != 0) {
                                int var46 = super.A - 36;
                                super.F = var46;
                                if (super.F != 0) {
                                   super.C = 0;
                                   int var47 = super.A - 8;
                                   super.F = var47;
                                   if (super.F != 0) {
                                      int var55 = super.A - 41;
                                      super.F = var55;
                                      if (super.F != 0) {
                                         int var56 = super.A - 44;
                                         super.F = var56;
                                         int var57 = super.A - 5;
                                         super.F = var57;
                                         if (super.F != 0) {
                                            super.C = 16;
                                         }
                                      }
                                   }
                
                                   super.A = super.E;
                                   int var48 = super.A & 1;
                                   super.A = var48;
                                   super.F = super.A;
                                   int var49 = super.A << 1;
                                   super.A = var49;
                                   int var50 = super.A << 1;
                                   super.A = var50;
                                   int var51 = super.A << 1;
                                   super.A = var51;
                                   int var52 = super.A | super.C;
                                   super.A = var52;
                                   super.F = super.A;
                                   super.C = super.A;
                                   super.B = 0;
                                   super.HL = 33841;
                                   int var53 = super.HL + super.BC;
                                   super.HL = var53;
                                   int var54 = super.D & 1;
                                   super.F = var54;
                                   super.D = 64;
                                   if (super.F != 0) {
                                      super.D = 72;
                                   }
                
                                   super.B = 8;
                                   this.$38555();
                                }
                             }
                          }
                       }
                    }
                
                    int var12 = super.DE + 1;
                    super.DE = var12;
                    super.A = super.D;
                    int var13 = super.A - 90;
                    super.F = var13;
                 } while(super.F != 0);
                
                 super.BC = 31;
                 int var14 = super.A ^ super.A;
                 super.A = var14;
                 super.F = super.A;
                
                 do {
                    int var15 = super.A | super.E;
                    super.A = var15;
                    super.F = super.A;
                    int var16 = super.B + -1;
                    super.B = var16;
                 } while(super.B != 0);
                
                 int var17 = super.A & 32;
                 super.A = var17;
                 super.F = super.A;
                 if (super.F == 0) {
                    super.A = 1;
                    super.mem[342540] = super.A;
                 }
                
                 super.HL = 34299;
                 this.$38562();
                 if (super.F != 0) {
                    break;
                 }
                
                 int var32 = super.A ^ super.A;
                 super.A = var32;
                 super.F = super.A;
                 super.mem[342760] = super.A;
                
                 while(true) {
                    this.$35563();
                    super.HL = 23136;
                    super.DE = 23137;
                    super.BC = 31;
                    super.mem[super.HL] = 79;
                    this.ldir();
                    int var33 = super.mem[342760];
                    super.A = var33;
                    super.IX = 33876;
                    super.E = super.A;
                    super.D = 0;
                    int var34 = super.IX + super.DE;
                    super.IX = var34;
                    super.DE = 20576;
                    super.C = 32;
                    this.$38528();
                    int var35 = super.mem[342760];
                    super.A = var35;
                    int var36 = super.A & 31;
                    super.A = var36;
                    super.F = super.A;
                    int var37 = super.A + 50;
                    super.A = var37;
                    super.F = super.A;
                    this.$38622();
                    super.BC = 45054;
                    int var38 = super.A & 1;
                    super.A = var38;
                    super.F = super.A;
                    int var39 = super.A - 1;
                    super.F = var39;
                    if (super.F != 0) {
                       break label70;
                    }
                
                    int var40 = super.mem[342760];
                    super.A = var40;
                    int var41 = super.A + 1;
                    super.A = var41;
                    int var42 = super.A - 224;
                    super.F = var42;
                    super.mem[342760] = super.A;
                    if (super.F == 0) {
                       break;
                    }
                 }
              }
                
              super.HL = 34181;
              super.DE = 34175;
              super.BC = 6;
              this.ldir();
              super.HL = 39424;
              super.DE = 23040;
              super.BC = 256;
              this.ldir();
              int var18 = super.mem[338240];
              super.A = var18;
              int var19 = super.A | 192;
              super.A = var19;
              super.F = super.A;
              super.H = super.A;
              super.L = 0;
              super.DE = 32768;
              super.BC = 256;
              this.ldir();
              super.IX = 33008;
              super.DE = 33024;
              super.A = 8;
                
              do {
                 int var20 = super.mem[super.IX];
                 super.L = var20;
                 int var21 = super.L & -129;
                 super.L = var21;
                 super.H = 20;
                 int var22 = super.HL * 2;
                 super.HL = var22;
                 int var23 = super.HL * 2;
                 super.HL = var23;
                 int var24 = super.HL * 2;
                 super.HL = var24;
                 super.BC = 2;
                 this.ldir();
                 int var25 = super.IX + 1;
                 int var26 = super.mem[var25];
                 super.C = var26;
                 super.mem[super.HL] = super.C;
                 super.BC = 6;
                 this.ldir();
                 int var27 = super.IX + 1;
                 super.IX = var27;
                 int var28 = super.IX + 1;
                 super.IX = var28;
                 int var29 = super.A + -1;
                 super.A = var29;
                 super.F = super.A;
              } while(super.F != 0);
                
              super.HL = 34255;
              super.DE = 34263;
              super.BC = 7;
              this.ldir();
              this.$36147();
              super.HL = 20480;
              super.DE = 20481;
              super.BC = 2047;
              super.mem[super.HL] = 0;
              this.ldir();
              super.IX = 32896;
              super.C = 32;
              super.DE = 20480;
              this.$38528();
              super.IX = 34132;
              super.DE = 20576;
              super.C = 32;
              this.$38528();
              int var30 = super.mem[329900];
              super.A = var30;
              super.C = 254;
              int var31 = super.A ^ super.A;
              super.A = var31;
              super.F = super.A;
              super.mem[342620] = super.A;
           }
                
           public void $35211() {
              int var1 = super.mem[342520];
              super.A = var1;
              super.HL = 20640;
              int var2 = super.A | super.A;
              super.A = var2;
              super.F = super.A;
              if (super.F != 0) {
                 super.B = super.A;
                
                 do {
                    super.C = 0;
                    int var3 = super.mem[342730];
                    super.A = var3;
                    int var4 = super.A << 1;
                    super.A = var4;
                    int var5 = super.A << 1;
                    super.A = var5;
                    int var6 = super.A << 1;
                    super.A = var6;
                    int var7 = super.A & 96;
                    super.A = var7;
                    super.F = super.A;
                    super.E = super.A;
                    super.D = 157;
                    this.$37974();
                    int var8 = super.HL + 1;
                    super.HL = var8;
                    int var9 = super.HL + 1;
                    super.HL = var9;
                    int var10 = super.B + -1;
                    super.B = var10;
                 } while(super.B != 0);
                
              }
           }
                
           public void $35245() {
              this.$35211();
              super.HL = 24064;
              super.DE = 23552;
              super.BC = 512;
              this.ldir();
              super.HL = 28672;
              super.DE = 24576;
              super.BC = 4096;
              this.ldir();
              this.$37056();
              int var1 = super.mem[342710];
              super.A = var1;
              int var2 = super.A - 3;
              super.F = var2;
              if (super.F != 0) {
                 this.$36307();
              }
                
              int var3 = super.mem[342550];
              super.A = var3;
              int var4 = super.A - 225;
              super.F = var4;
              if (super.F >= 0) {
                 this.$38064();
              }
                
              int var5 = super.mem[342710];
              super.A = var5;
              int var6 = super.A - 3;
              super.F = var6;
              if (super.F != 0) {
                 this.$38344();
              }
                
              int var7 = super.mem[342710];
              super.A = var7;
              int var8 = super.A - 2;
              super.F = var8;
              if (super.F == 0) {
                 this.$38276();
              }
                
              this.$38196();
           }
                
           public void $35317() {
              super.HL = 24576;
              super.DE = 16384;
              super.BC = 4096;
              this.ldir();
              int var1 = super.mem[342710];
              super.A = var1;
              int var2 = super.A & 2;
              super.A = var2;
              super.F = super.A;
              int var3 = super.A >> 1;
              super.A = var3;
              super.HL = 34258;
              int var4 = super.mem[super.HL];
              int var5 = super.A | var4;
              super.A = var5;
              int var10000 = super.mem[super.HL];
              var10000 = super.mem[super.HL];
              super.F = super.A;
              var10000 = super.mem[super.HL];
              super.mem[super.HL] = super.A;
              int var6 = super.mem[342530];
              super.A = var6;
              int var7 = super.A | super.A;
              super.A = var7;
              super.F = super.A;
              if (super.F != 0) {
                 int var40 = super.A + -1;
                 super.A = var40;
                 super.F = super.A;
                 super.mem[342530] = super.A;
                 int var41 = super.A << 1;
                 super.A = var41;
                 int var42 = super.A << 1;
                 super.A = var42;
                 int var43 = super.A << 1;
                 super.A = var43;
                 int var44 = super.A & 56;
                 super.A = var44;
                 super.F = super.A;
                 super.HL = 23552;
                 super.DE = 23553;
                 super.BC = 511;
                 super.mem[super.HL] = super.A;
                 this.ldir();
              }
                
              super.HL = 23552;
              super.DE = 22528;
              super.BC = 512;
              this.ldir();
              super.IX = 34175;
              super.DE = 20601;
              super.C = 6;
              this.$38528();
              super.IX = 34172;
              super.DE = 20592;
              super.C = 3;
              this.$38528();
              int var8 = super.mem[342510];
              super.A = var8;
              int var9 = super.A + 1;
              super.A = var9;
              super.mem[342510] = super.A;
              if (super.F == 0) {
                 super.IX = 34175;
                 int var22 = super.IX + 4;
                 int var23 = super.mem[var22];
                 super.A = var23;
                 int var24 = super.A - 58;
                 super.F = var24;
                 if (super.F == 0) {
                    int var25 = super.IX + 4;
                    super.mem[var25] = 48;
                    int var26 = super.IX + 3;
                    int var27 = super.mem[var26];
                    super.A = var27;
                    int var28 = super.A - 54;
                    super.F = var28;
                    if (super.F == 0) {
                       int var29 = super.IX + 3;
                       super.mem[var29] = 48;
                       int var30 = super.mem[super.IX];
                       super.A = var30;
                       int var31 = super.A - 49;
                       super.F = var31;
                       int var32 = super.IX + 1;
                       int var33 = super.mem[var32];
                       super.A = var33;
                       int var34 = super.A - 51;
                       super.F = var34;
                       if (super.F == 0) {
                          int var35 = super.IX + 5;
                          int var36 = super.mem[var35];
                          super.A = var36;
                          int var37 = super.A - 112;
                          super.F = var37;
                          super.mem[super.IX] = 32;
                          int var38 = super.IX + 1;
                          super.mem[var38] = 49;
                          int var39 = super.IX + 5;
                          super.mem[var39] = 112;
                       }
                    }
                 }
              }
                
              super.BC = 65278;
              super.E = super.A;
              super.B = 127;
              int var10 = super.A | super.E;
              super.A = var10;
              super.F = super.A;
              int var11 = super.A & 1;
              super.A = var11;
              super.F = super.A;
              int var12 = super.mem[342720];
              super.A = var12;
              int var13 = super.A + 1;
              super.A = var13;
              super.mem[342720] = super.A;
              if (super.F != 0) {
                 super.B = 253;
                 int var20 = super.A & 31;
                 super.A = var20;
                 super.F = super.A;
                 int var21 = super.A - 31;
                 super.F = var21;
                 super.DE = 0;
              }
                
              while(true) {
                 super.B = 2;
                 int var14 = super.A & 31;
                 super.A = var14;
                 super.F = super.A;
                 int var15 = super.A - 31;
                 super.F = var15;
                 int var16 = super.E + 1;
                 super.E = var16;
                 if (super.F == 0) {
                    int var17 = super.D + 1;
                    super.D = var17;
                    if (super.F == 0) {
                       int var18 = super.mem[342750];
                       super.A = var18;
                       int var19 = super.A - 10;
                       super.F = var19;
                       if (super.F != 0) {
                          this.$35563();
                       }
                    }
                 }
              }
           }
                
           public void $35563() {
              super.HL = 22528;
              int var1 = super.mem[super.HL];
              super.A = var1;
              int var2 = super.A & 7;
              super.A = var2;
              super.F = super.A;
                
              do {
                 int var3 = super.mem[super.HL];
                 super.A = var3;
                 int var4 = super.A + 3;
                 super.A = var4;
                 super.F = super.A;
                 int var5 = super.A & 7;
                 super.A = var5;
                 super.F = super.A;
                 super.D = super.A;
                 int var6 = super.mem[super.HL];
                 super.A = var6;
                 int var7 = super.A + 24;
                 super.A = var7;
                 super.F = super.A;
                 int var8 = super.A & 184;
                 super.A = var8;
                 super.F = super.A;
                 int var9 = super.A | super.D;
                 super.A = var9;
                 super.F = super.A;
                 super.mem[super.HL] = super.A;
                 int var10 = super.HL + 1;
                 super.HL = var10;
                 super.A = super.H;
                 int var11 = super.A - 91;
                 super.F = var11;
              } while(super.F != 0);
                
           }
                
           public void $36147() {
              this.$36203();
              super.IX = 24064;
              super.A = 112;
              super.mem[361890] = super.A;
              this.$36171();
              super.IX = 24320;
              super.A = 120;
              super.mem[361890] = super.A;
           }
                
           public void $36171() {
              super.C = 0;
                
              do {
                 super.E = super.C;
                 int var1 = super.mem[super.IX];
                 super.A = var1;
                 super.HL = 32928;
                 super.BC = 54;
                 this.cpir();
                 super.C = super.E;
                 super.B = 8;
                 super.D = 120;
                
                 do {
                    int var2 = super.mem[super.HL];
                    super.A = var2;
                    super.mem[super.DE] = super.A;
                    int var3 = super.HL + 1;
                    super.HL = var3;
                    int var4 = super.D + 1;
                    super.D = var4;
                    int var5 = super.B + -1;
                    super.B = var5;
                 } while(super.B != 0);
                
                 int var6 = super.IX + 1;
                 super.IX = var6;
                 int var7 = super.C + 1;
                 super.C = var7;
              } while(super.F != 0);
                
           }
                
           public void $36203() {
              super.HL = 32768;
              super.IX = 24064;
                
              do {
                 int var1 = super.mem[super.HL];
                 super.A = var1;
                 int var2 = super.A << 1;
                 super.A = var2;
                 int var3 = super.A << 1;
                 super.A = var3;
                 this.$36288();
                 int var4 = super.mem[super.HL];
                 super.A = var4;
                 int var5 = super.A >> 1;
                 super.A = var5;
                 int var6 = super.A >> 1;
                 super.A = var6;
                 int var7 = super.A >> 1;
                 super.A = var7;
                 int var8 = super.A >> 1;
                 super.A = var8;
                 this.$36288();
                 int var9 = super.mem[super.HL];
                 super.A = var9;
                 int var10 = super.A >> 1;
                 super.A = var10;
                 int var11 = super.A >> 1;
                 super.A = var11;
                 this.$36288();
                 int var12 = super.mem[super.HL];
                 super.A = var12;
                 this.$36288();
                 int var13 = super.HL + 1;
                 super.HL = var13;
                 super.A = super.L;
                 int var14 = super.A & 128;
                 super.A = var14;
                 super.F = super.A;
              } while(super.F == 0);
                
              int var15 = super.mem[329850];
              super.A = var15;
              int var16 = super.A | super.A;
              super.A = var16;
              super.F = super.A;
              if (super.F != 0) {
                 super.B = super.A;
                 int var27 = super.mem[329730];
                 super.A = var27;
                
                 do {
                    super.mem[super.HL] = super.A;
                    int var28 = super.HL + 1;
                    super.HL = var28;
                    int var29 = super.B + -1;
                    super.B = var29;
                 } while(super.B != 0);
              }
                
              int var17 = super.mem[329890];
              super.A = var17;
              int var18 = super.A | super.A;
              super.A = var18;
              super.F = super.A;
              if (super.F != 0) {
                 int var19 = super.mem[329860];
                 super.A = var19;
                 int var20 = super.A & 1;
                 super.A = var20;
                 super.F = super.A;
                 int var21 = super.A << 1;
                 super.A = var21;
                 int var22 = super.A + 223;
                 super.A = var22;
                 super.F = super.A;
                 super.E = super.A;
                 super.D = 255;
                 int var23 = super.mem[329890];
                 super.A = var23;
                 super.B = super.A;
                 int var24 = super.mem[329640];
                 super.A = var24;
                
                 do {
                    super.mem[super.HL] = super.A;
                    int var25 = super.HL + super.DE;
                    super.HL = var25;
                    int var26 = super.B + -1;
                    super.B = var26;
                 } while(super.B != 0);
                
              }
           }
                
           public void $36288() {
              int var1 = super.A & 3;
              super.A = var1;
              super.F = super.A;
              super.C = super.A;
              int var2 = super.A << 1;
              super.A = var2;
              int var3 = super.A << 1;
              super.A = var3;
              int var4 = super.A << 1;
              super.A = var4;
              int var5 = super.A + super.C;
              super.A = var5;
              super.F = super.A;
              int var6 = super.A + 160;
              super.A = var6;
              super.F = super.A;
              super.E = super.A;
              super.D = 128;
              int var7 = super.mem[super.DE];
              super.A = var7;
              super.mem[super.IX] = super.A;
              int var8 = super.IX + 1;
              super.IX = var8;
           }
                
           public void $36307() {
              int var1 = super.mem[342620];
              super.A = var1;
              int var2 = super.A + -1;
              super.A = var2;
              super.F = super.A;
              int var3 = super.A & 128;
              super.F = var3;
              int var4 = super.mem[342570];
              super.A = var4;
              int var5 = super.A - 1;
              super.F = var5;
              if (super.F == 0) {
                 int var40 = super.mem[342610];
                 super.A = var40;
                 int var41 = super.A & 254;
                 super.A = var41;
                 super.F = super.A;
                 int var42 = super.A - 8;
                 super.A = var42;
                 super.F = super.A;
                 super.HL = 34255;
                 int var43 = super.mem[super.HL];
                 int var44 = super.A + var43;
                 super.A = var44;
                 int var10000 = super.mem[super.HL];
                 var10000 = super.mem[super.HL];
                 super.F = super.A;
                 var10000 = super.mem[super.HL];
                 super.mem[super.HL] = super.A;
                 int var45 = super.A - 240;
                 super.F = var45;
                 this.$36508();
                 int var46 = super.mem[329460];
                 super.A = var46;
                 int var47 = super.mem[super.HL];
                 int var48 = super.A - var47;
                 super.F = var48;
                 var10000 = super.mem[super.HL];
                 int var49 = super.HL + 1;
                 super.HL = var49;
                 int var50 = super.mem[super.HL];
                 int var51 = super.A - var50;
                 super.F = var51;
                 var10000 = super.mem[super.HL];
                 int var52 = super.mem[342610];
                 super.A = var52;
                 int var53 = super.A + 1;
                 super.A = var53;
                 super.mem[342610] = super.A;
                 int var54 = super.A - 8;
                 super.A = var54;
                 super.F = super.A;
                 int var55 = super.A + 1;
                 super.A = var55;
                 int var56 = super.A << 1;
                 super.A = var56;
                 int var57 = super.A << 1;
                 super.A = var57;
                 int var58 = super.A << 1;
                 super.A = var58;
                 super.D = super.A;
                 super.C = 32;
                 int var59 = super.mem[329900];
                 super.A = var59;
                
                 do {
                    int var60 = super.A ^ 24;
                    super.A = var60;
                    super.F = super.A;
                    super.B = super.D;
                
                    do {
                       int var61 = super.B + -1;
                       super.B = var61;
                    } while(super.B != 0);
                
                    int var62 = super.C + -1;
                    super.C = var62;
                    super.F = super.C;
                 } while(super.F != 0);
                
                 int var63 = super.mem[342610];
                 super.A = var63;
                 int var64 = super.A - 18;
                 super.F = var64;
                 int var65 = super.A - 16;
                 super.F = var65;
                 if (super.F != 0) {
                    int var66 = super.A - 13;
                    super.F = var66;
                 }
              }
                
              int var6 = super.mem[342550];
              super.A = var6;
              int var7 = super.A & 14;
              super.A = var7;
              super.F = super.A;
              if (super.F == 0) {
                 super.DE = 64;
                 int var26 = super.HL + super.DE;
                 super.HL = var26;
                 int var27 = super.H & 2;
                 super.F = var27;
                 int var28 = super.mem[329550];
                 super.A = var28;
                 int var29 = super.mem[super.HL];
                 int var30 = super.A - var29;
                 super.F = var30;
                 int var71 = super.mem[super.HL];
                 if (super.F != 0) {
                    int var31 = super.HL + 1;
                    super.HL = var31;
                    int var32 = super.mem[329550];
                    super.A = var32;
                    int var33 = super.mem[super.HL];
                    int var34 = super.A - var33;
                    super.F = var34;
                    var71 = super.mem[super.HL];
                    if (super.F != 0) {
                       int var35 = super.mem[329280];
                       super.A = var35;
                       int var36 = super.mem[super.HL];
                       int var37 = super.A - var36;
                       super.F = var37;
                       int var38 = super.mem[super.HL];
                       int var39 = super.A - var38;
                       super.F = var39;
                       var71 = super.mem[super.HL];
                    }
                 }
              }
                
              int var8 = super.mem[342570];
              super.A = var8;
              int var9 = super.A - 1;
              super.F = var9;
              super.HL = 34256;
              int var10 = super.mem[super.HL];
              int var11 = var10 & -3;
              super.mem[super.HL] = var11;
              super.mem[super.HL] = var10;
              int var12 = super.mem[342570];
              super.A = var12;
              int var13 = super.A | super.A;
              super.A = var13;
              super.F = super.A;
              int var14 = super.A + 1;
              super.A = var14;
              int var15 = super.A - 16;
              super.F = var15;
              if (super.F == 0) {
                 super.A = 12;
              }
                
              super.mem[342570] = super.A;
              int var16 = super.A << 1;
              super.A = var16;
              int var17 = super.A << 1;
              super.A = var17;
              int var18 = super.A << 1;
              super.A = var18;
              int var19 = super.A << 1;
              super.A = var19;
              super.D = super.A;
              super.C = 32;
              int var20 = super.mem[329900];
              super.A = var20;
                
              do {
                 int var21 = super.A ^ 24;
                 super.A = var21;
                 super.F = super.A;
                 super.B = super.D;
                
                 do {
                    int var22 = super.B + -1;
                    super.B = var22;
                 } while(super.B != 0);
                
                 int var23 = super.C + -1;
                 super.C = var23;
                 super.F = super.C;
              } while(super.F != 0);
                
              int var24 = super.mem[342550];
              super.A = var24;
              int var25 = super.A + 8;
              super.A = var25;
              super.F = super.A;
              super.mem[342550] = super.A;
           }
                
           public void $36508() {
              int var1 = super.A & 240;
              super.A = var1;
              super.F = super.A;
              super.L = super.A;
              int var2 = super.A ^ super.A;
              super.A = var2;
              super.F = super.A;
              super.H = super.A;
              int var3 = super.mem[342590];
              super.A = var3;
              int var4 = super.A & 31;
              super.A = var4;
              super.F = super.A;
              int var5 = super.A | super.L;
              super.A = var5;
              super.F = super.A;
              super.L = super.A;
           }
                
           public void $37056() {
              super.IX = 33024;
              int var1 = super.mem[super.IX];
              super.A = var1;
              int var2 = super.A - 255;
              super.F = var2;
              if (super.F != 0) {
                 label66: {
                    int var3 = super.A & 3;
                    super.A = var3;
                    super.F = super.A;
                    int var4 = super.A - 1;
                    super.F = var4;
                    if (super.F != 0) {
                       int var57 = super.A - 2;
                       super.F = var57;
                       if (super.F == 0) {
                          break label66;
                       }
                
                       int var58 = super.mem[super.IX];
                       int var59 = var58 & 128;
                       super.mem[super.IX] = var59;
                       super.F = var58;
                       if (super.F != 0) {
                          int var78 = super.IX + 1;
                          int var79 = super.mem[var78];
                          super.A = var79;
                          int var80 = super.A & 128;
                          super.F = var80;
                          if (super.F != 0) {
                             int var84 = super.A - 2;
                             super.A = var84;
                             super.F = super.A;
                             int var85 = super.A - 148;
                             super.F = var85;
                             if (super.F < 0) {
                                int var86 = super.A - 2;
                                super.A = var86;
                                super.F = super.A;
                                int var87 = super.A - 128;
                                super.F = var87;
                                if (super.F == 0) {
                                   int var88 = super.A ^ super.A;
                                   super.A = var88;
                                   super.F = super.A;
                                }
                             }
                          } else {
                             int var81 = super.A + 2;
                             super.A = var81;
                             super.F = super.A;
                             int var82 = super.A - 18;
                             super.F = var82;
                             if (super.F < 0) {
                                int var83 = super.A + 2;
                                super.A = var83;
                                super.F = super.A;
                             }
                          }
                       } else {
                          int var60 = super.IX + 1;
                          int var61 = super.mem[var60];
                          super.A = var61;
                          int var62 = super.A & 128;
                          super.F = var62;
                          if (super.F == 0) {
                             int var74 = super.A - 2;
                             super.A = var74;
                             super.F = super.A;
                             int var75 = super.A - 20;
                             super.F = var75;
                             if (super.F < 0) {
                                int var76 = super.A - 2;
                                super.A = var76;
                                super.F = super.A;
                                int var77 = super.A | super.A;
                                super.A = var77;
                                super.F = super.A;
                                if (super.F == 0) {
                                   super.A = 128;
                                }
                             }
                          } else {
                             int var63 = super.A + 2;
                             super.A = var63;
                             super.F = super.A;
                             int var64 = super.A - 146;
                             super.F = var64;
                             if (super.F < 0) {
                                int var73 = super.A + 2;
                                super.A = var73;
                                super.F = super.A;
                             }
                          }
                       }
                
                       int var65 = super.IX + 1;
                       super.mem[var65] = super.A;
                       int var66 = super.A & 127;
                       super.A = var66;
                       super.F = super.A;
                       int var67 = super.IX + 7;
                       int var68 = super.mem[var67];
                       int var69 = super.A - var68;
                       super.F = var69;
                       int var70 = super.IX + 7;
                       int var10000 = super.mem[var70];
                       int var71 = super.mem[super.IX];
                       super.A = var71;
                       int var72 = super.A ^ 128;
                       super.A = var72;
                       super.F = super.A;
                       super.mem[super.IX] = super.A;
                    }
                
                    int var5 = super.mem[super.IX];
                    int var6 = var5 & 128;
                    super.mem[super.IX] = var6;
                    super.F = var5;
                    if (super.F == 0) {
                       int var46 = super.mem[super.IX];
                       super.A = var46;
                       int var47 = super.A - 32;
                       super.A = var47;
                       super.F = super.A;
                       int var48 = super.A & 127;
                       super.A = var48;
                       super.F = super.A;
                       super.mem[super.IX] = super.A;
                       int var49 = super.A - 96;
                       super.F = var49;
                       int var50 = super.IX + 2;
                       int var51 = super.mem[var50];
                       super.A = var51;
                       int var52 = super.A & 31;
                       super.A = var52;
                       super.F = super.A;
                       int var53 = super.IX + 6;
                       int var54 = super.mem[var53];
                       int var55 = super.A - var54;
                       super.F = var55;
                       int var56 = super.IX + 6;
                       int var89 = super.mem[var56];
                       if (super.F != 0) {
                          var89 = super.IX + 2;
                          var89 = super.IX + 2;
                       }
                
                       super.mem[super.IX] = 129;
                    }
                
                    int var7 = super.mem[super.IX];
                    super.A = var7;
                    int var8 = super.A + 32;
                    super.A = var8;
                    super.F = super.A;
                    int var9 = super.A | 128;
                    super.A = var9;
                    super.F = super.A;
                    super.mem[super.IX] = super.A;
                    int var10 = super.A - 160;
                    super.F = var10;
                    int var11 = super.IX + 2;
                    int var12 = super.mem[var11];
                    super.A = var12;
                    int var13 = super.A & 31;
                    super.A = var13;
                    super.F = super.A;
                    int var14 = super.IX + 7;
                    int var15 = super.mem[var14];
                    int var16 = super.A - var15;
                    super.F = var16;
                    int var17 = super.IX + 7;
                    int var92 = super.mem[var17];
                    if (super.F != 0) {
                       var92 = super.IX + 2;
                    }
                
                    super.mem[super.IX] = 97;
                 }
                
                 int var18 = super.mem[super.IX];
                 super.A = var18;
                 int var19 = super.A ^ 8;
                 super.A = var19;
                 super.F = super.A;
                 super.mem[super.IX] = super.A;
                 int var20 = super.A & 24;
                 super.A = var20;
                 super.F = super.A;
                 if (super.F != 0) {
                    int var44 = super.mem[super.IX];
                    super.A = var44;
                    int var45 = super.A + 32;
                    super.A = var45;
                    super.F = super.A;
                    super.mem[super.IX] = super.A;
                 }
                
                 int var21 = super.IX + 3;
                 int var22 = super.mem[var21];
                 super.A = var22;
                 int var23 = super.IX + 4;
                 int var24 = super.mem[var23];
                 int var25 = super.A + var24;
                 super.A = var25;
                 int var26 = super.IX + 4;
                 int var94 = super.mem[var26];
                 int var27 = super.IX + 4;
                 var94 = super.mem[var27];
                 super.F = super.A;
                 int var28 = super.IX + 4;
                 var94 = super.mem[var28];
                 int var29 = super.IX + 3;
                 super.mem[var29] = super.A;
                 int var30 = super.IX + 7;
                 int var31 = super.mem[var30];
                 int var32 = super.A - var31;
                 super.F = var32;
                 int var33 = super.IX + 7;
                 var94 = super.mem[var33];
                 if (super.F < 0) {
                    int var37 = super.IX + 6;
                    int var38 = super.mem[var37];
                    int var39 = super.A - var38;
                    super.F = var39;
                    int var40 = super.IX + 6;
                    var94 = super.mem[var40];
                    if (super.F != 0) {
                    }
                
                    int var41 = super.IX + 6;
                    int var42 = super.mem[var41];
                    super.A = var42;
                    int var43 = super.IX + 3;
                    super.mem[var43] = super.A;
                 }
                
                 int var34 = super.IX + 4;
                 int var35 = super.mem[var34];
                 super.A = var35;
                 int var36 = super.IX + 4;
                 super.mem[var36] = super.A;
              }
           }
                
           public void $37302() {
              super.DE = 8;
              int var1 = super.IX + super.DE;
              super.IX = var1;
           }
                
           public void $37974() {
              super.B = 16;
                
              do {
                 int var1 = super.C & 1;
                 super.F = var1;
                 int var2 = super.mem[super.DE];
                 super.A = var2;
                 if (super.F != 0) {
                    int var22 = super.mem[super.HL];
                    int var23 = super.A & var22;
                    super.A = var23;
                    int var10000 = super.mem[super.HL];
                    var10000 = super.mem[super.HL];
                    super.F = super.A;
                    var10000 = super.mem[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var24 = super.mem[super.DE];
                    super.A = var24;
                    int var25 = super.mem[super.HL];
                    int var26 = super.A | var25;
                    super.A = var26;
                    var10000 = super.mem[super.HL];
                    var10000 = super.mem[super.HL];
                    super.F = super.A;
                    var10000 = super.mem[super.HL];
                 }
                
                 super.mem[super.HL] = super.A;
                 int var3 = super.L + 1;
                 super.L = var3;
                 int var4 = super.DE + 1;
                 super.DE = var4;
                 int var5 = super.C & 1;
                 super.F = var5;
                 int var6 = super.mem[super.DE];
                 super.A = var6;
                 if (super.F != 0) {
                    int var17 = super.mem[super.HL];
                    int var18 = super.A & var17;
                    super.A = var18;
                    int var32 = super.mem[super.HL];
                    var32 = super.mem[super.HL];
                    super.F = super.A;
                    var32 = super.mem[super.HL];
                    if (super.F != 0) {
                       return;
                    }
                
                    int var19 = super.mem[super.DE];
                    super.A = var19;
                    int var20 = super.mem[super.HL];
                    int var21 = super.A | var20;
                    super.A = var21;
                    var32 = super.mem[super.HL];
                    var32 = super.mem[super.HL];
                    super.F = super.A;
                    var32 = super.mem[super.HL];
                 }
                
                 super.mem[super.HL] = super.A;
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
                
           public void $38064() {
              int var1 = super.mem[330030];
              super.A = var1;
              super.mem[338240] = super.A;
              int var2 = super.mem[342590];
              super.A = var2;
              int var3 = super.A & 31;
              super.A = var3;
              super.F = super.A;
              int var4 = super.A + 160;
              super.A = var4;
              super.F = super.A;
              super.mem[342590] = super.A;
              super.A = 93;
              super.mem[342600] = super.A;
              super.A = 208;
              super.mem[342550] = super.A;
              int var5 = super.A ^ super.A;
              super.A = var5;
              super.F = super.A;
              super.mem[342570] = super.A;
           }
                
           public void $38196() {
              int var1 = super.mem[338240];
              super.A = var1;
              int var2 = super.A - 35;
              super.F = var2;
              int var3 = super.mem[342710];
              super.A = var3;
              int var4 = super.A | super.A;
              super.A = var4;
              super.F = super.A;
              int var5 = super.mem[342510];
              super.A = var5;
              int var6 = super.A & 2;
              super.A = var6;
              super.F = super.A;
              int var7 = super.A >> 1;
              super.A = var7;
              int var8 = super.A >> 1;
              super.A = var8;
              int var9 = super.A >> 1;
              super.A = var9;
              int var10 = super.A >> 1;
              super.A = var10;
              int var11 = super.A | 128;
              super.A = var11;
              super.F = super.A;
              super.E = super.A;
              int var12 = super.mem[342550];
              super.A = var12;
              int var13 = super.A - 208;
              super.F = var13;
              if (super.F != 0) {
                 super.E = 192;
                 int var14 = super.A - 192;
                 super.F = var14;
                 if (super.F < 0) {
                    super.E = 224;
                 }
              }
                
              super.D = 156;
              super.HL = 26734;
              super.C = 1;
              this.$37974();
              super.HL = 17733;
              super.HL = 1799;
           }
                
           public void $38276() {
              int var1 = super.mem[338240];
              super.A = var1;
              int var2 = super.A - 33;
              super.F = var2;
              if (super.F == 0) {
                 int var3 = super.mem[342590];
                 super.A = var3;
                 int var4 = super.A - 188;
                 super.F = var4;
                 if (super.F == 0) {
                    int var5 = super.A ^ super.A;
                    super.A = var5;
                    super.F = super.A;
                    super.mem[342510] = super.A;
                    super.A = 3;
                    super.mem[342710] = super.A;
                 }
              }
           }
                
           public void $38344() {
              super.B = 0;
              int var1 = super.mem[329860];
              super.A = var1;
              int var2 = super.A & 1;
              super.A = var2;
              super.F = super.A;
              int var3 = super.A + 64;
              super.A = var3;
              super.F = super.A;
              super.E = super.A;
              super.D = 0;
              int var4 = super.HL + super.DE;
              super.HL = var4;
              int var5 = super.mem[329640];
              super.A = var5;
              int var6 = super.mem[super.HL];
              int var7 = super.A - var6;
              super.F = var7;
              int var10000 = super.mem[super.HL];
              if (super.F == 0) {
                 int var8 = super.mem[342570];
                 super.A = var8;
                 int var9 = super.A | super.A;
                 super.A = var9;
                 super.F = super.A;
                 if (super.F == 0) {
                    int var10 = super.mem[342580];
                    super.A = var10;
                    int var11 = super.A & 3;
                    super.A = var11;
                    super.F = super.A;
                    int var12 = super.A << 1;
                    super.A = var12;
                    int var13 = super.A << 1;
                    super.A = var13;
                    super.B = super.A;
                    int var14 = super.mem[329860];
                    super.A = var14;
                    int var15 = super.A & 1;
                    super.A = var15;
                    super.F = super.A;
                    int var16 = super.A + -1;
                    super.A = var16;
                    super.F = super.A;
                    int var17 = super.A ^ 12;
                    super.A = var17;
                    super.F = super.A;
                    int var18 = super.A ^ super.B;
                    super.A = var18;
                    super.F = super.A;
                    int var19 = super.A & 12;
                    super.A = var19;
                    super.F = super.A;
                    super.B = super.A;
                 }
              }
                
              super.DE = 31;
              super.C = 15;
              this.$38430();
           }
                
           public void $38430() {
              int var1 = super.mem[329280];
              super.A = var1;
              int var2 = super.mem[super.HL];
              int var3 = super.A - var2;
              super.F = var3;
              int var10000 = super.mem[super.HL];
              if (super.F == 0) {
                 super.A = super.C;
                 int var7 = super.A & 15;
                 super.A = var7;
                 super.F = super.A;
                 if (super.F != 0) {
                    int var8 = super.mem[329280];
                    super.A = var8;
                    int var9 = super.A | 7;
                    super.A = var9;
                    super.F = super.A;
                    super.mem[super.HL] = super.A;
                 }
              }
                
              int var4 = super.mem[329550];
              super.A = var4;
              int var5 = super.mem[super.HL];
              int var6 = super.A - var5;
              super.F = var6;
              var10000 = super.mem[super.HL];
           }
                
           public void $38528() {
              do {
                 int var1 = super.mem[super.IX];
                 super.A = var1;
                 this.$38545();
                 int var2 = super.IX + 1;
                 super.IX = var2;
                 int var3 = super.E + 1;
                 super.E = var3;
                 super.A = super.D;
                 int var4 = super.A - 8;
                 super.A = var4;
                 super.F = super.A;
                 super.D = super.A;
                 int var5 = super.C + -1;
                 super.C = var5;
                 super.F = super.C;
              } while(super.F != 0);
                
           }
                
           public void $38545() {
              super.H = 7;
              super.L = super.A;
              int var1 = super.L | 128;
              super.L = var1;
              int var2 = super.HL * 2;
              super.HL = var2;
              int var3 = super.HL * 2;
              super.HL = var3;
              int var4 = super.HL * 2;
              super.HL = var4;
              super.B = 8;
                
              do {
                 int var5 = super.mem[super.HL];
                 super.A = var5;
                 super.mem[super.DE] = super.A;
                 int var6 = super.HL + 1;
                 super.HL = var6;
                 int var7 = super.D + 1;
                 super.D = var7;
                 int var8 = super.B + -1;
                 super.B = var8;
              } while(super.B != 0);
                
           }
                
           public void $38562() {
              while(true) {
                 int var1 = super.mem[super.HL];
                 super.A = var1;
                 int var2 = super.A - 255;
                 super.F = var2;
                 if (super.F == 0) {
                    return;
                 }
                
                 super.BC = 100;
                 int var3 = super.A ^ super.A;
                 super.A = var3;
                 super.F = super.A;
                 int var4 = super.mem[super.HL];
                 super.E = var4;
                 super.D = super.E;
                
                 while(true) {
                    int var5 = super.D + -1;
                    super.D = var5;
                    super.F = super.D;
                    if (super.F == 0) {
                       super.D = super.E;
                       int var10 = super.A ^ 24;
                       super.A = var10;
                       super.F = super.A;
                    }
                
                    int var6 = super.B + -1;
                    super.B = var6;
                    if (super.B == 0) {
                       super.A = super.C;
                       int var7 = super.A - 50;
                       super.F = var7;
                       if (super.F == 0) {
                       }
                
                       int var8 = super.C + -1;
                       super.C = var8;
                       super.F = super.C;
                       if (super.F == 0) {
                          this.$38601();
                          if (super.F != 0) {
                             return;
                          }
                
                          int var9 = super.HL + 1;
                          super.HL = var9;
                          break;
                       }
                    }
                 }
              }
           }
                
           public void $38601() {
              int var1 = super.mem[342540];
              super.A = var1;
              int var2 = super.A | super.A;
              super.A = var2;
              super.F = super.A;
              if (super.F != 0) {
                 int var5 = super.A & 16;
                 super.F = var5;
                 if (super.F != 0) {
                    return;
                 }
              }
                
              super.BC = 45054;
              int var3 = super.A & 1;
              super.A = var3;
              super.F = super.A;
              int var4 = super.A - 1;
              super.F = var4;
           }
                
           public void $38622() {
              super.E = super.A;
              super.C = 254;
                
              do {
                 super.D = super.A;
                 int var1 = super.D & -17;
                 super.D = var1;
                 int var2 = super.D & -9;
                 super.D = var2;
                 super.B = super.E;
                
                 do {
                    int var3 = super.A - super.B;
                    super.F = var3;
                    if (super.F == 0) {
                       super.D = 24;
                    }
                
                    int var4 = super.B + -1;
                    super.B = var4;
                 } while(super.B != 0);
                
                 int var5 = super.A + -1;
                 super.A = var5;
                 super.F = super.A;
              } while(super.F != 0);
                
           }
                
           public void $38555() {
           }
        }
        """, generateAndDecompile());
  }

}
