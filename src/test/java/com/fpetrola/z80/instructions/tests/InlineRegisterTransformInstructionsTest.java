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
              int B_L1 = 3;
              int A_L2 = 1;
              int D_L3 = 2;
              int H_L4 = 7;
              int H_L5 = H_L4 + 1;
              this.memory[1000] = H_L5;
              int D_L7 = D_L3 + H_L5;
              int F_L7 = this.initial;

              do {
                 int var10 = A_L2 ^ D_L7;
                 A_L2 = var10 + B_L1;
                 this.memory[1002] = A_L2;
                 this.memory[1001] = D_L7;
                 --B_L1;
              } while(B_L1 != 0);

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
      int F_L0 = false;
      int B_L1 = 3;
      int DE_L2 = 520;
      int A_L3 = 0;
      int C_L4 = 0;
      int H_L5 = 7;
      int D_L7 = DE_L2 >> 8;
      D_L7 += A_L3;
      int E_L8 = DE_L2 & 255;
      E_L8 += A_L3;
      int C_L9 = C_L4 + B_L1;
      int C_L10 = C_L9 + B_L1;
      int B_L11 = B_L1;
      int C_L11 = C_L10 + B_L1;

      do {
         int A_L8 = this.memory[B_L11];
         ++A_L8;
         this.memory[1002] = A_L8;
         this.memory[E_L8] = D_L7;
         ++H_L5;
         ++D_L7;
         --B_L11;
      } while(B_L11 != 0);

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
              int D_L1 = 100;
              int var3 = this.memory[D_L1];
              int A_L3 = var3 + 1;
              this.memory[1002] = A_L3;
              this.memory[1003] = A_L3;
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
              int B_L1 = 3;
              int D_L2 = 4;

              do {
                 this.memory[B_L1] = D_L2++;
                 --B_L1;
              } while(B_L1 != 0);

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
      int B_L1 = 3;
      int C_L2 = false;
      int A_L3 = 10;

      do {
         A_L3 = B_L1 + 1;
         --B_L1;
      } while(B_L1 != 0);

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
      int A_L3 = 4;
      int DE_L4 = 520;
      int H_L5 = 7;
      int HL_L7 = H_L5 << 8 | A_L3;
      HL_L7 *= 2;
      HL_L7 *= 2;
      HL_L7 *= 2;
      int B_L10 = 3;

      do {
         int A_L6 = this.memory[HL_L7];
         this.memory[DE_L4] = A_L6;
         ++HL_L7;
         int D_L4 = this.initial;
         ++D_L4;
         --B_L10;
      } while(B_L10 != 0);

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
              int var3 = this.memory[HL_L0];
              this.memory[100] = var3;
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
              int B_L0 = 3;
              int IX_L1 = 33024;
                
              do {
                 int var3 = IX_L1 + 4;
                 int var10000 = this.memory[var3];
                 IX_L1 += 3;
                 --B_L0;
              } while(B_L0 != 0);
                
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
              int B_L0 = 3;
              int IX_L1 = 33024;
                
              do {
                 int A_L2 = 100;
                 int var5 = IX_L1 + 4;
                 this.memory[var5] = A_L2;
                 IX_L1 += 3;
                 --B_L0;
              } while(B_L0 != 0);
                
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
              int B_L0 = 3;
              int IX_L1 = 1000;
              int A_L2 = 100;

              do {
                 int F_L3 = 65535;
                 F_L3 = B_L0 - 3;
                 byte A_L9;
                 if (F_L3 != 0) {
                    int F_L4 = B_L0 - 2;
                    if (F_L4 != 0) {
                       int A_L7 = this.initial;
                       int var11 = IX_L1 + 1;
                       this.memory[var11] = A_L7;
                    } else {
                       int var9 = IX_L1 + 2;
                       this.memory[var9] = A_L9;
                    }
                 } else {
                    A_L9 = A_L2;
                    int var7 = IX_L1 + 3;
                    this.memory[var7] = A_L2;
                 }

                 --B_L0;
              } while(B_L0 != 0);

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
              int B_L0 = 2;
              int IX_L1 = 1000;
              int A_L2 = 100;

              do {
                 int F_L3 = 65535;
                 F_L3 = B_L0 - 2;
                 if (F_L3 != 0) {
                    int A_L5 = this.initial;
                    int var8 = IX_L1 + 1;
                    this.memory[var8] = A_L5;
                 } else {
                    int var6 = IX_L1 + 2;
                    this.memory[var6] = A_L2;
                 }

                 --B_L0;
              } while(B_L0 != 0);

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
              int B_L0 = 3;

              do {
                 this.memory[1000] = B_L0--;
                 int F = '\\uffff';
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
              int B_L0 = 2;
              int IX_L1 = 1000;

              do {
                 int F_L3 = 65535;
                 F_L3 = B_L0 - 2;
                 byte A_L5;
                 if (F_L3 != 0) {
                    A_L5 = 10;
                 }

                 int var4 = IX_L1 + 1;
                 this.memory[var4] = A_L5;
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
              int B_L0 = 3;
              int D_L1 = 300;
                
              int var4;
              int C_L3;
              do {
                 D_L1 = (char)65535;
                 int var3 = D_L1 + 4;
                 var4 = this.memory[var3];
                 int C = '\\uffff';
                 C_L3 = C + 2;
                 int F_L3 = '\\uffff';
                 D_L1 += 3;
                 --B_L0;
              } while(B_L0 != 0);
                
              int C_L6 = C_L3 + var4;
           }
        }
        """, generateAndDecompile());
  }
}
