package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlockType;
import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.blocks.ranges.RangeHandler;
import com.fpetrola.z80.blocks.references.ReferencesHandler;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.ManualBytecodeGenerationTest;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")

public class InlineRegisterTransformInstructionsTest<T extends WordNumber> extends ManualBytecodeGenerationTest<T> {

  private BlocksManager blocksManager;

  @Before
  public void setUp() {
    super.setUp();
    blocksManager = RegisterTransformerInstructionSpy.blocksManager;
    blocksManager.clear();
  }

  @Test
  public void testJRNZSimpleLoop() {
    add(new Ld(r(F), c(20), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(A), c(1), f()));
    add(new Ld(r(D), c(2), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Add(r(D), r(H), f()));
    add(new Xor(r(A), r(D), f()));
    add(new Add(r(A), r(B), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(mm(c(memPosition + 1)), r(D), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-6), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 100)), r(H), f()));

    step(20);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.F = 20;
              super.B = 3;
              super.A = 1;
              super.D = 2;
              super.H = 7;
              int var1 = super.H + 1;
              super.H = var1;
              super.mem[1000] = super.H;
              int var2 = super.D + super.H;
              super.D = var2;
              super.F = super.D;
                
              do {
                 int var3 = super.A ^ super.D;
                 super.A = var3;
                 super.F = super.A;
                 int var4 = super.A + super.B;
                 super.A = var4;
                 super.F = super.A;
                 super.mem[1002] = super.A;
                 super.mem[1001] = super.D;
                 int var5 = super.B + -1;
                 super.B = var5;
                 super.F = super.B;
              } while(super.F != 0);
                
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void createPlainExecution() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(DE), c(520), f()));
    add(new Ld(r(A), c(0), f()));
    add(new Ld(r(C), c(0), f()));

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));
    add(new Add(r(D), r(A), f()));
    add(new Add(r(E), r(A), f()));

    add(new Add(r(C), r(B), f()));
    add(new Add(r(C), r(B), f()));
    add(new Add(r(C), r(B), f()));
    add(new Ld(r(A), iRR(r(B)), f()));
    add(new Inc(r(A), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(iRR(r(E)), r(D), f()));
    add(new Inc(r(H), f()));
    add(new Inc(r(D), f()));
    add(new Ld(r(F), c(1), f()));
    add(new DJNZ(c(-8), bnz(), r(PC)));
    //add(new Ret(t(), r(SP), mem(), r(PC)));

    step(30);
    Assert.assertEquals("""
import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW extends SpectrumApplication {
   public void $0() {
      super.F = 0;
      super.B = 3;
      super.DE = 520;
      super.A = 0;
      super.C = 0;
      super.H = 7;
      super.L = super.A;
      int var1 = super.D + super.A;
      super.D = var1;
      super.F = super.D;
      int var2 = super.E + super.A;
      super.E = var2;
      super.F = super.E;
      int var3 = super.C + super.B;
      super.C = var3;
      super.F = super.C;
      int var4 = super.C + super.B;
      super.C = var4;
      super.F = super.C;
      int var5 = super.C + super.B;
      super.C = var5;
      super.F = super.C;

      do {
         int var6 = super.mem[super.B];
         super.A = var6;
         int var7 = super.A + 1;
         super.A = var7;
         super.mem[1002] = super.A;
         super.mem[super.E] = super.D;
         int var8 = super.H + 1;
         super.H = var8;
         int var9 = super.D + 1;
         super.D = var9;
         super.F = 1;
         int var10 = super.B + -1;
         super.B = var10;
      } while(super.B != 0);

   }
}
""", generateAndDecompile());
  }

  @Test
  public void createPlainExecution2() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(D), c(100), f()));
    add(new Ld(r(A), iRR(r(D)), f()));
    add(new Inc(r(A), f()));
    add(new Ld(r(B), r(A), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(mm(c(memPosition + 3)), r(B), f()));

    int endAddress = 7;
    step(endAddress);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.F = 0;
              super.D = 100;
              int var1 = super.mem[super.D];
              super.A = var1;
              int var2 = super.A + 1;
              super.A = var2;
              super.B = super.A;
              super.mem[1002] = super.A;
              super.mem[1003] = super.B;
           }
        }
        """, generateAndDecompile());
  }


  @Test
  public void incrementDBeforeDJNZ() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(D), c(4), f()));

    add(new Ld(iRR(r(B)), r(D), f()));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-3), bnz(), r(PC)));

    step(10);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.F = 0;
              super.B = 3;
              super.D = 4;
                
              do {
                 super.mem[super.B] = super.D;
                 int var1 = super.D + 1;
                 super.D = var1;
                 int var2 = super.B + -1;
                 super.B = var2;
              } while(super.B != 0);
                
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void bug2() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(C), c(0), f()));
    add(new Ld(r(A), c(10), f()));

    add(new Ld(r(C), r(B), f()));
    add(new Ld(r(A), r(C), f()));
    add(new Inc(r(A), f()));
    add(new DJNZ(c(-4), bnz(), r(PC)));
    add(new Ld(r(D), r(C), f()));

    step(17);
    Assert.assertEquals("""
import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW extends SpectrumApplication {
   public void $0() {
      super.F = 0;
      super.B = 3;
      super.C = 0;
      super.A = 10;

      do {
         super.C = super.B;
         super.A = super.C;
         int var1 = super.A + 1;
         super.A = var1;
         int var2 = super.B + -1;
         super.B = var2;
      } while(super.B != 0);

      super.D = super.C;
   }
}
""", generateAndDecompile());
  }

  @Test
  public void firstLoop() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(C), c(0), f()));
    add(new Ld(r(A), c(4), f()));

    add(new Ld(r(DE), c(520), f()));
    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));

    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Ld(r(B), c(3), f()));

    add(new Ld(r(A), iRR(r(HL)), f()));
    add(new Ld(iRR(r(DE)), r(A), f()));
    add(new Inc16(r(HL)));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-5), bnz(), r(PC)));
//    add(new Inc(r(A), f()));
//    add(new Ld(iRR(r(HL)), r(A), f()));
//    add(new JP(c(2), t(), r(PC)));


    step(26);
    Assert.assertEquals("""
import com.fpetrola.z80.transformations.SpectrumApplication;

public class JSW extends SpectrumApplication {
   public void $0() {
      super.F = 0;
      super.B = 3;
      super.C = 0;
      super.A = 4;
      super.DE = 520;
      super.H = 7;
      super.L = super.A;
      int var1 = super.HL * 2;
      super.HL = var1;
      int var2 = super.HL * 2;
      super.HL = var2;
      int var3 = super.HL * 2;
      super.HL = var3;
      super.B = 3;

      do {
         int var4 = super.mem[super.HL];
         super.A = var4;
         super.mem[super.DE] = super.A;
         int var5 = super.HL + 1;
         super.HL = var5;
         int var6 = super.D + 1;
         super.D = var6;
         int var7 = super.B + -1;
         super.B = var7;
      } while(super.B != 0);

   }
}
""", generateAndDecompile());
  }


  @Test
  public void bug3() {
    setUpMemory();
    add(new Ld(r(HL), c(62525), f()));
    add(new Add16(r(HL), c(3), f()));
    add(new Ld(r(A), iRR(r(HL)), f()));

    add(new Inc(r(HL), f()));
    add(new Ld(r(H), iRR(r(HL)), f()));
    add(new Ld(mm(c(100)), r(H), f()));

    step(4);
    step(1);
    step(1);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.HL = 62525;
              int var1 = super.HL + 3;
              super.HL = var1;
              int var2 = super.mem[super.HL];
              super.A = var2;
              int var3 = super.HL + 1;
              super.HL = var3;
              int var4 = super.mem[super.HL];
              super.H = var4;
              super.mem[100] = super.H;
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void bugIX() {
    setUpMemory();
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(IX), c(33024), f()));
    add(new Ld(r(A), iRRn(r(IX), 4), f()));
    add(new Ld(r(C), c(100), f()));
    add(new Add16(r(IX), c(3), f()));

    add(new DJNZ(c(-4), bnz(), r(PC)));
    add(new Add16(r(IX), c(3), f()));

    step(5);
    step(1);
    step(6);


    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 3;
              super.IX = 33024;
                
              do {
                 int var1 = super.IX + 4;
                 int var2 = super.mem[var1];
                 super.A = var2;
                 super.C = 100;
                 int var3 = super.IX + 3;
                 super.IX = var3;
                 int var4 = super.B + -1;
                 super.B = var4;
              } while(super.B != 0);
                
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void bugIXAsTarget() {
    setUpMemory();
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(IX), c(33024), f()));
    add(new Ld(r(A), c(100), f()));
    add(new Ld(iRRn(r(IX), 4), r(A), f()));
    add(new Add16(r(IX), c(3), f()));

    add(new DJNZ(c(-4), bnz(), r(PC)));
    add(new Add16(r(IX), c(3), f()));

    step(5);
    step(1);
    step(6);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 3;
              super.IX = 33024;
                
              do {
                 super.A = 100;
                 int var1 = super.IX + 4;
                 super.mem[var1] = super.A;
                 int var2 = super.IX + 3;
                 super.IX = var2;
                 int var3 = super.B + -1;
                 super.B = var3;
              } while(super.B != 0);
                
           }
        }
        """, generateAndDecompile());
  }


  @Test
  public void bugForwardJumps() {
    setUpMemory();
    int djnzLine = 12;

    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(IX), c(1000), f()));
    add(new Ld(r(A), c(100), f()));

    add(new Cp(r(B), c(3), f()));
    add(new JR(c(4), z(), r(PC)));
    add(new Cp(r(B), c(2), f()));
    add(new JR(c(4), z(), r(PC)));
    add(new Ld(iRRn(r(IX), 1), r(A), f()));
    add(new JR(c(3), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 3), r(A), f()));
    add(new JR(c(1), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 2), r(A), f()));

    add(new DJNZ(c(-10), bnz(), r(PC)));
    add(new Add16(r(IX), c(20), f()));

    step(21);
    step(1);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 3;
              super.IX = 1000;
              super.A = 100;
                
              do {
                 int var1 = super.B - 3;
                 super.F = var1;
                 if (super.F != 0) {
                    int var5 = super.B - 2;
                    super.F = var5;
                    if (super.F != 0) {
                       int var7 = super.IX + 1;
                       super.mem[var7] = super.A;
                    } else {
                       int var6 = super.IX + 2;
                       super.mem[var6] = super.A;
                    }
                 } else {
                    int var2 = super.IX + 3;
                    super.mem[var2] = super.A;
                 }
                
                 int var3 = super.B + -1;
                 super.B = var3;
              } while(super.B != 0);
                
              int var4 = super.IX + 20;
              super.IX = var4;
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void bugForward2Jumps() {
    setUpMemory();
    int djnzLine = 8;

    add(new Ld(r(B), c(2), f()));
    add(new Ld(r(IX), c(1000), f()));
    add(new Ld(r(A), c(100), f()));

    add(new Cp(r(B), c(2), f()));
    add(new JR(c(2), z(), r(PC)));
    add(new Ld(iRRn(r(IX), 1), r(A), f()));
    add(new JP(c(djnzLine), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 2), r(A), f()));

    add(new DJNZ(c(-6), bnz(), r(PC)));
    add(new Add16(r(IX), c(20), f()));

    step(12);
    step(1);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 2;
              super.IX = 1000;
              super.A = 100;
                
              do {
                 int var1 = super.B - 2;
                 super.F = var1;
                 if (super.F != 0) {
                    int var5 = super.IX + 1;
                    super.mem[var5] = super.A;
                 } else {
                    int var2 = super.IX + 2;
                    super.mem[var2] = super.A;
                 }
                
                 int var3 = super.B + -1;
                 super.B = var3;
              } while(super.B != 0);
                
              int var4 = super.IX + 20;
              super.IX = var4;
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void testJRNZMoreSimpleLoop() {
    add(new Ld(r(B), c(3), f()));

    add(new Ld(mm(c(memPosition)), r(B), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-3), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 100)), r(B), f()));

    step(11);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 3;
                
              do {
                 super.mem[1000] = super.B;
                 int var1 = super.B + -1;
                 super.B = var1;
                 super.F = super.B;
              } while(super.F != 0);
                
              super.mem[1100] = super.B;
           }
        }
        """, generateAndDecompile());
  }


  @Test
  public void forwardReference2Jumps() {
    setUpMemory();
    int djnzLine = 8;

    add(new Ld(r(B), c(2), f()));
    add(new Ld(r(IX), c(1000), f()));
    add(new Ld(r(A), c(1), f()));

    add(new Cp(r(B), c(2), f()));
    add(new JR(c(2), z(), r(PC)));
    add(new Ld(r(A), c(10), f()));
    add(new JR(c(1), t(), r(PC)));
    add(new Ld(r(A), c(20), f()));

    add(new Ld(iRRn(r(IX), 1), r(A), f()));
    add(new DJNZ(c(-7), bnz(), r(PC)));
    add(new Add16(r(IX), c(13), f()));
    add(new Ld(iRRn(r(IX), 1), r(B), f()));

    step(16);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 2;
              super.IX = 1000;
              super.A = 1;
                
              do {
                 int var1 = super.B - 2;
                 super.F = var1;
                 if (super.F != 0) {
                    super.A = 10;
                 } else {
                    super.A = 20;
                 }
                
                 int var2 = super.IX + 1;
                 super.mem[var2] = super.A;
                 int var3 = super.B + -1;
                 super.B = var3;
              } while(super.B != 0);
                
              int var4 = super.IX + 13;
              super.IX = var4;
              int var5 = super.IX + 1;
              super.mem[var5] = super.B;
           }
        }
        """, generateAndDecompile());

    //testBlocks();
  }

  public void testBlocks() {
    List<Block> blocks = blocksManager.getBlocks();

    assertNotNull(blocks, "Blocks list should not be null");
    assertFalse(blocks.isEmpty(), "Blocks list should not be empty");
    assertEquals(5, blocks.size(), "Blocks list size does not match");

    // Obtener instancias de los bloques al principio
    Block block0 = blocks.get(0);
    Block block1 = blocks.get(1);
    Block block2 = blocks.get(2);
    Block block3 = blocks.get(3);
    Block block4 = blocks.get(4);

    assertBlockAttributes(block0, 0, 4, "WHOLE_MEMORY", true, List.of(block1, block2), List.of(block2));
    assertBlockAttributes(block1, 5, 6, null, true, List.of(block2), List.of(block0));
    assertBlockAttributes(block2, 7, 9, null, true, List.of(block0, block3), List.of(block0, block1));
    assertBlockAttributes(block3, 10, 12, null, false, List.of(), List.of(block2));

    RangeHandler rangeHandler = block4.getRangeHandler();
    assertNotNull(rangeHandler, "RangeHandler should not be null");
    assertEquals(13, rangeHandler.getStartAddress(), "RangeHandler start address does not match");
    assertEquals(0xFFFF, rangeHandler.getEndAddress(), "RangeHandler end address does not match");
  }

  private void assertBlockAttributes(Block block, int expectedStartAddress, int expectedEndAddress, String expectedCallType, boolean expectedCompleted, List<Block> expectedNextBlocks, List<Block> expectedPreviousBlocks) {
    assertNotNull(block, "Block should not be null");

    // Verificar ReferencesHandler
    ReferencesHandler referencesHandler = block.getReferencesHandler();
    assertNotNull(referencesHandler, "ReferencesHandler should not be null");

    // Verificar RangeHandler
    RangeHandler rangeHandler = block.getRangeHandler();
    assertNotNull(rangeHandler, "RangeHandler should not be null");
    assertEquals(expectedStartAddress, rangeHandler.getStartAddress(), "RangeHandler start address does not match");
    assertEquals(expectedEndAddress, rangeHandler.getEndAddress(), "RangeHandler end address does not match");

    // Verificar CallType
    String callType = block.getCallType();
    if (expectedCallType != null) {
      assertEquals(expectedCallType, callType, "CallType does not match");
    } else {
      assertNull(callType, "CallType should be null");
    }

    // Verificar BlocksManager
    BlocksManager blocksManager = block.getBlocksManager();
    assertNotNull(blocksManager, "BlocksManager should not be null");

    // Verificar BlockType
    BlockType blockType = block.getBlockType();
    assertNotNull(blockType, "BlockType should not be null");

    // Verificar si el bloque está completado
    boolean completed = block.isCompleted();
    assertEquals(expectedCompleted, completed, "Block completion status does not match");

    // Verificar nextBlocks
    List<Block> nextBlocks = ((CodeBlockType) block.getBlockType()).getNextBlocks();
    assertNotNull(nextBlocks, "NextBlocks should not be null");
    assertEquals(expectedNextBlocks.size(), nextBlocks.size(), "NextBlocks size does not match");
    assertTrue(nextBlocks.containsAll(expectedNextBlocks), "NextBlocks does not contain all expected blocks");

    // Verificar previousBlocks
    List<Block> previousBlocks = ((CodeBlockType) block.getBlockType()).getPreviousBlocks();
    assertNotNull(previousBlocks, "PreviousBlocks should not be null");
    assertEquals(expectedPreviousBlocks.size(), previousBlocks.size(), "PreviousBlocks size does not match");
    assertTrue(previousBlocks.containsAll(expectedPreviousBlocks), "PreviousBlocks does not contain all expected blocks");
  }


  @Test
  public void bugIXB() {
    setUpMemory();
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(D), c(300), f()));
    add(new Ld(r(A), iRRn(r(D), 4), f()));
    add(new Add(r(C), c(2), f()));
    add(new Add(r(D), c(3), f()));

    add(new DJNZ(c(-4), bnz(), r(PC)));
    add(new Add(r(C), r(A), f()));

    step(6);
    step(9);


    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 3;
              super.D = 300;
                
              do {
                 int var1 = super.D + 4;
                 int var2 = super.mem[var1];
                 super.A = var2;
                 int var3 = super.C + 2;
                 super.C = var3;
                 super.F = super.C;
                 int var4 = super.D + 3;
                 super.D = var4;
                 super.F = super.D;
                 int var5 = super.B + -1;
                 super.B = var5;
              } while(super.B != 0);
                
              int var6 = super.C + super.A;
              super.C = var6;
              super.F = super.C;
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void testLdOr() {
    add(new Ld(r(HL), c(100), f()));
    add(new Or(r(A), iRR(r(HL)), f()));
    add(new Ld(iRR(r(HL)), r(A), f()));

    step(3);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.HL = 100;
              int var1 = super.mem[super.HL];
              int var2 = super.A | var1;
              super.A = var2;
              int var10000 = super.mem[super.HL];
              var10000 = super.mem[super.HL];
              super.F = super.A;
              var10000 = super.mem[super.HL];
              super.mem[super.HL] = super.A;
           }
        }
        """, generateAndDecompile());
  }

  @Test
  public void testBugSet() {
    add(new Ld(r(IX), c(253), f()));
    add(new Ld(iRRn(r(IX), 0), c(1), f()));
    add(new Ld(iRRn(r(IX), 1), c(2), f()));
    add(new Ld(iRRn(r(IX), 2), c(3), f()));

    add(new Ld(r(HL), c(200), f()));
    add(new Inc(r(HL), f()));

    add(new Ld(r(H), c(0), f()));
    add(new Ld(r(A), c(10), f()));
    add(new Ld(r(L), c(253), f()));

    add(new SET(iRR(r(HL)), 6, f()));
    add(new Sub(r(A), r(L), f()));
    add(new Or(r(A), c(0x2C), f()));
    add(new JR(c(-3), nz(), r(PC)));

    add(new Ld(r(HL), c(29), f()));

    step(6);
    step(13);

    Assert.assertEquals("""
        import com.fpetrola.z80.transformations.SpectrumApplication;
                
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.IX = 253;
              super.mem[super.IX] = 1;
              int var1 = super.IX + 1;
              super.mem[var1] = 2;
              int var2 = super.IX + 2;
              super.mem[var2] = 3;
              super.HL = 200;
              int var3 = super.HL + 1;
              super.HL = var3;
              super.H = 0;
              super.A = 10;
              super.L = 253;
              int var4 = super.mem[super.HL];
              int var5 = var4 | 64;
              super.mem[super.HL] = var5;
              super.mem[super.HL] = var4;
                
              do {
                 int var6 = super.A - super.L;
                 super.A = var6;
                 super.F = super.A;
                 int var7 = super.A | 44;
                 super.A = var7;
                 super.F = super.A;
              } while(super.F != 0);
                
           }
        }
        """, generateAndDecompile());
  }

}
