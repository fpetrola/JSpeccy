package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")

public class InlineRegisterTransformInstructionsTests<T extends WordNumber> extends BytecodeGenerationTransformInstructionsTests<T> {

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
   public int[] memory;

   public void $0() {
      int F_L0 = 20;
      int B_L1 = 3;
      int B_L9 = B_L1;
      int A_L2 = 1;
      int A_L8 = A_L2;
      int D_L3 = 2;
      int H_L4 = 7;
      int H_L4 = H_L4 + 1;
      this.memory[1000] = H_L4;
      int D_L3 = D_L3 + H_L4;
      int D_L8 = D_L3;
      byte F_L8 = F_L0;

      do {
         A_L8 ^= D_L8;
         A_L8 += B_L9;
         this.memory[1002] = A_L8;
         this.memory[1001] = D_L8;
         --B_L9;
      } while(F_L8 != 0);

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
    add(new DJNZ(c(-8), r(B), r(PC)));
    //add(new Ret(t(), r(SP), mem(), r(PC)));

    step(30);
    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int F_L0 = false;
      int B_L1 = 3;
      int DE_L2 = 520;
      int A_L3 = 0;
      int C_L4 = 0;
      int H_L5 = 7;
      int H_L16 = H_L5;
      int var8 = DE_L2 >> 8;
      int D_L2 = var8 + A_L3;
      int D_L15 = D_L2;
      int var11 = DE_L2 & 255;
      int E_L2 = var11 + A_L3;
      int E_L15 = E_L2;
      int C_L4 = C_L4 + B_L1;
      C_L4 += B_L1;
      int var10000 = C_L4 + B_L1;
      int B_L12 = B_L1;

      do {
         int var15 = this.memory[B_L12];
         int A_L12 = var15 + 1;
         this.memory[1002] = A_L12;
         this.memory[E_L15] = D_L15;
         ++H_L16;
         ++D_L15;
         --B_L12;
      } while(B_L12 != 0);

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

    String decompiled = finishTest(endAddress);

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int D_L1 = 100;
      int var2 = this.memory[D_L1];
      int A_L2 = var2 + 1;
      this.memory[1002] = A_L2;
      this.memory[1003] = A_L2;
   }
}
""", decompiled);
  }


  @Test
  public void incrementDBeforeDJNZ() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(D), c(4), f()));

    add(new Ld(iRR(r(B)), r(D), f()));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-3), r(B), r(PC)));

    step(10);

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int F_L0 = false;
      int B_L1 = 3;
      int B_L3 = B_L1;
      int D_L2 = 4;
      int D_L3 = D_L2;

      do {
         this.memory[B_L3] = D_L3++;
         --B_L3;
      } while(B_L3 != 0);

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
    add(new DJNZ(c(-4), r(B), r(PC)));
    add(new Ld(r(D), r(C), f()));

    step(17);
    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int F_L0 = false;
      int B_L1 = 3;
      int B_L4 = B_L1;

      do {
         int A_L5 = B_L4 + 1;
         --B_L4;
      } while(B_L4 != 0);

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
    add(new DJNZ(c(-5), r(B), r(PC)));
//    add(new Inc(r(A), f()));
//    add(new Ld(iRR(r(HL)), r(A), f()));
//    add(new JP(c(2), t(), r(PC)));


    step(26);
    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int F_L0 = false;
      int B_L1 = 3;
      int A_L3 = 4;
      int DE_L4 = 520;
      int DE_L12 = DE_L4;
      int H_L5 = 7;
      int var8 = H_L5 << 8 | A_L3;
      int HL_L7 = var8 * 2;
      HL_L7 *= 2;
      HL_L7 *= 2;
      int HL_L11 = HL_L7;
      int B_L15 = B_L1;

      do {
         int var12 = this.memory[HL_L11];
         this.memory[DE_L12] = var12;
         ++HL_L11;
         int var14 = DE_L12 >> 8;
         int D_L12 = var14 + 1;
         int var16 = D_L12 << 8;
         int var17 = DE_L12 & 255;
         DE_L12 = var16 | var17;
         --B_L15;
      } while(B_L15 != 0);

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
   public int[] memory;

   public void $0() {
      int HL_L0 = '\\uf43d';
      int F = true;
      int HL_L0 = HL_L0 + 3;
      int var10000 = this.memory[HL_L0];
      ++HL_L0;
      int var4 = this.memory[HL_L0];
      this.memory[100] = var4;
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

    add(new DJNZ(c(-4), r(B), r(PC)));
    add(new Add16(r(IX), c(3), f()));

    step(5);
    step(1);
    step(6);


    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int B_L0 = 3;
      int B_L5 = B_L0;
      int IX_L1 = '脀';
      int IX_L2 = IX_L1;

      do {
         int var5 = IX_L2 + 4;
         int var10000 = this.memory[var5];
         int F = true;
         IX_L2 += 3;
         --B_L5;
      } while(B_L5 != 0);

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

    add(new DJNZ(c(-4), r(B), r(PC)));
    add(new Add16(r(IX), c(3), f()));

    step(5);
    step(1);
    step(6);

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int B_L0 = 3;
      int B_L5 = B_L0;
      int IX_L1 = '脀';
      int IX_L3 = IX_L1;

      do {
         int A_L2 = 100;
         int var6 = IX_L3 + 4;
         this.memory[var6] = A_L2;
         int F = true;
         IX_L3 += 3;
         --B_L5;
      } while(B_L5 != 0);

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

    add(new DJNZ(c(-10), r(B), r(PC)));
    add(new Add16(r(IX), c(20), f()));

    step(21);
    step(1);

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int B_L0 = 3;
      int B_L3 = B_L0;
      int IX_L1 = 1000;
      byte A_L2 = 100;

      int B_L12;
      do {
         int F = true;
         int F_L3 = B_L3 - 3;
         if (F_L3 != 0) {
            F_L3 = B_L3 - 2;
            if (F_L3 != 0) {
               int var10 = IX_L1 + 1;
               this.memory[var10] = A_L2;
            } else {
               int var9 = IX_L1 + 2;
               this.memory[var9] = A_L2;
            }
         } else {
            int var8 = IX_L1 + 3;
            this.memory[var8] = A_L2;
         }

         B_L12 = B_L3 - 1;
         B_L3 = B_L12;
      } while(B_L12 != 0);

      int IX_L1 = IX_L1 + 20;
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

    add(new DJNZ(c(-6), r(B), r(PC)));
    add(new Add16(r(IX), c(20), f()));

    step(12);
    step(1);

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
      int B_L0 = 2;
      int B_L3 = B_L0;
      int IX_L1 = 1000;
      byte A_L2 = 100;

      do {
         int F = true;
         int F_L3 = B_L3 - 2;
         if (F_L3 != 0) {
            int var8 = IX_L1 + 1;
            this.memory[var8] = A_L2;
         } else {
            int var7 = IX_L1 + 2;
            this.memory[var7] = A_L2;
         }

         --B_L3;
      } while(B_L3 != 0);

      int IX_L1 = IX_L1 + 20;
   }
}
""", generateAndDecompile());
  }

}
