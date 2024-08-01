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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int H = 7;
              int D = 2;
              int A = 1;
              int B = 3;
              int F = true;
              ++H;
              this.memory[1000] = H;
              D += H;
                
              do {
                 int var8 = A ^ D;
                 A = var8 + B;
                 this.memory[1002] = A;
                 this.memory[1001] = D;
                 --B;
              } while(B != 0);
                
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
public class JSW {
   public int initial;
   public int[] memory;

   public void $0() {
      byte DE_L2;
      int E = DE_L2 & 255;
      int D = DE_L2 >> 8;
      int H = 7;
      int C = 0;
      int A = 0;
      int B = 3;
      DE_L2 = 520;
      D += A;
      E += A;
      C += B;
      C += B;
      int var10000 = C + B;

      do {
         int var8 = this.memory[B];
         A = var8 + 1;
         this.memory[1002] = A;
         this.memory[E] = D;
         ++H;
         ++D;
         --B;
      } while(B != 0);

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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int D = 100;
              int var3 = this.memory[D];
              int A = var3 + 1;
              this.memory[1002] = A;
              this.memory[1003] = A;
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int D = 4;
              int B = 3;
                
              do {
                 this.memory[B] = D++;
                 --B;
              } while(B != 0);
                
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
public class JSW {
   public int initial;
   public int[] memory;

   public void $0() {
      int A = 10;
      int B = 3;
      int C_L2 = false;

      do {
         A = B + 1;
         --B;
      } while(B != 0);

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
public class JSW {
   public int initial;
   public int[] memory;

   public void $0() {
      int D = this.initial;
      int H = 7;
      int A = 4;
      int B = 3;
      int DE_L4 = 520;
      int HL_L7 = H << 8 | A;
      HL_L7 *= 2;
      HL_L7 *= 2;
      HL_L7 *= 2;
      B = 3;

      do {
         int var8 = this.memory[HL_L7];
         this.memory[DE_L4] = var8;
         ++HL_L7;
         ++D;
         --B;
      } while(B != 0);

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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int HL_L0 = 62525;
              HL_L0 += 3;
              int var10000 = this.memory[HL_L0];
              ++HL_L0;
              int var2 = this.memory[HL_L0];
              int H_L3 = this.initial;
              this.memory[100] = H_L3;
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int B = 3;
              int IX_L1 = 33024;
                
              do {
                 int var3 = IX_L1 + 4;
                 int var10000 = this.memory[var3];
                 IX_L1 += 3;
                 --B;
              } while(B != 0);
                
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int A = 100;
              int B = 3;
              int IX_L1 = 33024;
                
              do {
                 int var4 = IX_L1 + 4;
                 this.memory[var4] = A;
                 IX_L1 += 3;
                 --B;
              } while(B != 0);
                
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
    add(new JP(c(djnzLine), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 3), r(A), f()));
    add(new JP(c(djnzLine), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 2), r(A), f()));

    add(new DJNZ(c(-10), bnz(), r(PC)));
    add(new Add16(r(IX), c(20), f()));

    step(21);
    step(1);

    Assert.assertEquals("""
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int F = '\\uffff';
              int A = 100;
              int B = 3;
              int IX_L1 = 1000;
                
              do {
                 int F = B - 3;
                 if (F != 0) {
                    F = B - 2;
                    if (F != 0) {
                       int var8 = IX_L1 + 1;
                       this.memory[var8] = A;
                    } else {
                       int var7 = IX_L1 + 2;
                       this.memory[var7] = A;
                    }
                 } else {
                    int var6 = IX_L1 + 3;
                    this.memory[var6] = A;
                 }
                
                 --B;
              } while(B != 0);
                
              IX_L1 += 20;
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int F = '\\uffff';
              int A = 100;
              int B = 2;
              int IX_L1 = 1000;
                
              do {
                 int F = B - 2;
                 if (F != 0) {
                    int var7 = IX_L1 + 1;
                    this.memory[var7] = A;
                 } else {
                    int var6 = IX_L1 + 2;
                    this.memory[var6] = A;
                 }
                
                 --B;
              } while(B != 0);
                
              IX_L1 += 20;
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int F = '\\uffff';
              int B_L0 = 3;
                
              do {
                 this.memory[1000] = B_L0--;
              } while(B_L0 != 0);
                
              this.memory[1100] = B_L0;
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int F = '\\uffff';
              int A = 1;
              int B_L0 = 2;
              int IX_L1 = 1000;
                
              do {
                 int F = B_L0 - 2;
                 if (F != 0) {
                    A = 10;
                 } else {
                    A = 20;
                 }
                
                 int var6 = IX_L1 + 1;
                 this.memory[var6] = A;
                 --B_L0;
              } while(B_L0 != 0);
                
              IX_L1 += 13;
              int var7 = IX_L1 + 1;
              this.memory[var7] = B_L0;
           }
        }
        """, generateAndDecompile());

    testBlocks();
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
    assertBlockAttributes(block3, 10, 11, null, false, List.of(), List.of(block2));

    RangeHandler rangeHandler = block4.getRangeHandler();
    assertNotNull(rangeHandler, "RangeHandler should not be null");
    assertEquals(12, rangeHandler.getStartAddress(), "RangeHandler start address does not match");
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
        public class JSW {
           public int initial;
           public int[] memory;
                
           public void $0() {
              int F = '\\uffff';
              int C = 65535;
              int D = 300;
              int B = 3;
                
              int var3;
              do {
                 int var7 = D + 4;
                 var3 = this.memory[var7];
                 C += 2;
                 C = C;
                 D += 3;
                 --B;
              } while(B != 0);
                
              C += var3;
           }
        }
        """, generateAndDecompile());
  }
}
