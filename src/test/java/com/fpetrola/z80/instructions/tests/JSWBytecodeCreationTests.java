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
    startAddress = 38026;
    endAddress = 38621;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    memoryReadOnly(true);

    System.out.println(state.getMemory().read(WordNumber.createValue(34480)).intValue());

    stepUntilComplete();

    Assert.assertEquals(""" 

""", generateAndDecompile());
  }

}
