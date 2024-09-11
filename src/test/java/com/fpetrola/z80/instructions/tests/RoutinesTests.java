package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.ManualBytecodeGenerationTest;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineManager;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")

public class RoutinesTests<T extends WordNumber> extends ManualBytecodeGenerationTest<T> {

  private RoutineManager routineManager = RegisterTransformerInstructionSpy.routineFinder.routineManager;

  @Test
  public void callingSimpleRoutine() {
    setUpMemory();

    add(new Ld(r(A), c(2), f()));
    add(new Call(c(5), t(), r(PC), r(SP), mem()));
    add(new Ld(r(B), c(3), f()));
    add(new Ret(t(), r(SP), mem(), r(PC)));
    add(new Ld(r(C), c(4), f()));

    add(new Ld(r(D), c(5), f()));
    add(new Ret(t(), r(SP), mem(), r(PC)));

    step(6);

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 3);
    assertBlockAddresses(routines.get(1).blocks.get(0), 5, 6);
  }

  private void assertBlockAddresses(Block block, int start, int end) {
    Assert.assertEquals(start, block.getRangeHandler().getStartAddress());
    Assert.assertEquals(end, block.getRangeHandler().getEndAddress());
  }

  @Test
  public void callingSimpleRoutineWithContinuation() {
    setUpMemory();

    // Main routine
    add(new Ld(r(B), c(2), f()));         // Address 0
    add(new Call(c(3), t(), r(PC), r(SP), mem())); // Call inner routine at address 3
    add(new Ld(r(B), c(3), f()));         // Address 2, continues after inner routine

    // Inner routine
    add(new Ld(r(D), r(B), f()));         // Address 3
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from inner routine

  //  stepUntilComplete();
    step(1);

    Ld<WordNumber> ld0 = (Ld) getTransformedInstructionAt(0);
    Assert.assertEquals(2, ld0.getTarget().read().intValue());
    step(2);

//    Ld<WordNumber> ld0 = (Ld) getTransformedInstructionAt(0);
//    Assert.assertEquals(2, ld0.getTarget().read().intValue());

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert that the main routine has two blocks, with the inner routine in between
    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 1);  // Main routine block 1 (0 to 1)
    assertBlockAddresses(routines.get(0).blocks.get(1), 2, 3);  // Main routine block 2 (after return)

    // Assert inner routine
    Assert.assertEquals(1, routines.get(0).innerRoutines.size());
    assertBlockAddresses(routines.get(0).innerRoutines.get(0).blocks.get(0), 3, 4);  // Inner routine block
  }


  @Test
  public void multipleInnerRoutinesWithContinuation() {
    setUpMemory();

    // Main routine
    add(new Ld(r(A), c(1), f()));         // Address 0
    add(new Call(c(5), t(), r(PC), r(SP), mem())); // Call inner routine 1 at address 5
    add(new Ld(r(B), c(2), f()));         // Address 2, continues after inner routine 1
    add(new Call(c(7), t(), r(PC), r(SP), mem())); // Call inner routine 2 at address 10
    add(new Ld(r(C), c(3), f()));         // Address 4, continues after inner routine 2

    // Inner routine 1
    add(new Ld(r(D), c(4), f()));         // Address 5
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from inner routine 1

    // Inner routine 2
    add(new Ld(r(E), c(5), f()));         // Address 10
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from inner routine 2

    step(9);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert that the main routine has three blocks
    Assert.assertEquals(1, routines.size());
    Routine routine = routines.get(0);
    assertBlockAddresses( routine.blocks.get(0), 0, 1);  // Main routine block 1 (0 to 1)
    assertBlockAddresses(routine.blocks.get(1), 2, 3);  // Main routine block 2 (2 to 3)
    assertBlockAddresses(routine.blocks.get(2), 4, 5);  // Main routine block 3 (after inner routine 2)

    // Assert inner routines
    Assert.assertEquals(2, routine.innerRoutines.size());

    // Inner routine 1
    assertBlockAddresses(routine.innerRoutines.get(0).blocks.get(0), 5, 6);  // Inner routine 1 block

    // Inner routine 2
    assertBlockAddresses(routine.innerRoutines.get(1).blocks.get(0), 10, 11); // Inner routine 2 block
  }

  @Test
  public void interleavedRoutinesTest() {
    setUpMemory();

    // Main routine (non-consecutive blocks)
    add(new Ld(r(A), c(1), f()));         // Address 0
    add(new Call(c(5), t(), r(PC), r(SP), mem())); // Call inner routine 1 at address 5
    add(new Ld(r(B), c(2), f()));         // Address 2, after inner routine 1
    add(new Call(c(7), t(), r(PC), r(SP), mem())); // Call inner routine 2 at address 15
    add(new Ld(r(C), c(3), f()));         // Address 4, after inner routine 2

    // Inner routine 1 (interleaved)
    add(new Ld(r(D), c(4), f()));         // Address 5
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from inner routine 1

    // Inner routine 2 (interleaved)
    add(new Ld(r(E), c(5), f()));         // Address 15
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from inner routine 2

    step(9);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert that the main routine has non-consecutive blocks
    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 1);  // Main routine block 1 (0 to 1)
    assertBlockAddresses(routines.get(0).blocks.get(1), 2, 3);  // Main routine block 2 (after inner routine 1)
    assertBlockAddresses(routines.get(0).blocks.get(2), 4, 5);  // Main routine block 3 (after inner routine 2)

    // Assert inner routines
    Assert.assertEquals(2, routines.get(0).innerRoutines.size());

    // Inner routine 1
    assertBlockAddresses(routines.get(0).innerRoutines.get(0).blocks.get(0), 5, 6);  // Inner routine 1

    // Inner routine 2
    assertBlockAddresses(routines.get(0).innerRoutines.get(1).blocks.get(0), 15, 16); // Inner routine 2
  }


  @Test
  public void recursiveInnerRoutineWithContinuation() {
    setUpMemory();

    // Main routine
    add(new Ld(r(A), c(1), f()));         // Address 0
    add(new Call(c(5), t(), r(PC), r(SP), mem())); // Call recursive inner routine
    add(new Ld(r(B), c(2), f()));         // Address 2, continues after recursive call

    // Recursive inner routine
    add(new Ld(r(C), c(3), f()));         // Address 5
    add(new Call(c(5), t(), r(PC), r(SP), mem())); // Recursive call
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from recursive call

    step(7);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert that the main routine has two blocks
    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 1);  // Main routine block 1 (0 to 1)
    assertBlockAddresses(routines.get(0).blocks.get(1), 2, 3);  // Main routine block 2 (after recursive call)

    // Assert recursive inner routine
    Assert.assertEquals(1, routines.get(0).innerRoutines.size());
    assertBlockAddresses(routines.get(0).innerRoutines.get(0).blocks.get(0), 5, 7);  // Recursive inner routine block
  }

  @Test
  public void simpleRoutineTest() {
    setUpMemory();

    // Routine with contiguous block
    add(new Ld(r(A), c(10), f()));   // Instruction at address 0
    add(new Ld(r(B), c(20), f()));   // Instruction at address 1
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine

    step(3);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert routine with one block
    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 2);  // Routine from 0 to 2
  }

  @Test
  public void nestedRoutineTest() {
    setUpMemory();

    // Routine 1
    add(new Ld(r(A), c(10), f()));         // Instruction at address 0
    add(new Call(c(4), t(), r(PC), r(SP), mem())); // Call Routine 2 at address 10
    add(new Ld(r(B), c(20), f()));         // Instruction at address 3 (after return)
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 1

    // Routine 2
    add(new Ld(r(C), c(30), f()));         // Instruction at address 10
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 2

    step(5);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert routines were created
    Assert.assertEquals(2, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 3);  // Routine 1 from 0 to 3
    assertBlockAddresses(routines.get(1).blocks.get(0), 10, 11); // Routine 2 from 10 to 11
  }

  @Test
  public void nonConsecutiveBlocksTest() {
    setUpMemory();

    // Routine 1 - Block 1
    add(new Ld(r(A), c(10), f()));         // Instruction at address 0
    add(new Call(c(4), t(), r(PC), r(SP), mem())); // Call Routine 2
    add(new Ld(r(B), c(20), f()));         // Instruction after return at address 20
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 1

    // Routine 2
    add(new Ld(r(C), c(30), f()));         // Instruction at address 10
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 2

    step(5);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert non-consecutive blocks
    Assert.assertEquals(2, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 1);  // Routine 1 - Block 1
    assertBlockAddresses(routines.get(0).blocks.get(1), 20, 21); // Routine 1 - Block 2
    assertBlockAddresses(routines.get(1).blocks.get(0), 10, 11); // Routine 2
  }

  @Test
  public void recursiveRoutineTest() {
    setUpMemory();

    // Routine 1 (Self-recursive)
    add(new Ld(r(A), c(2), f()));
    add(new Dec(r(A), f()));
    add(new Call(c(1), nz(), r(PC), r(SP), mem())); // Recursive call to self
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 1

    step(7);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert recursive routine was handled
    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 3);  // Routine 1 (recursive)
  }


  @Test
  public void multipleCallsAndRetsTest() {
    setUpMemory();

    // Routine 1
    add(new Ld(r(A), c(50), f()));         // Instruction at address 0
    add(new Call(c(6), t(), r(PC), r(SP), mem())); // Call Routine 2
    add(new Ld(r(B), c(60), f()));         // Instruction at address 3
    add(new Call(c(8), t(), r(PC), r(SP), mem())); // Call Routine 3
    add(new Ld(r(C), c(70), f()));         // Instruction at address 6
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 1

    // Routine 2
    add(new Ld(r(D), c(80), f()));         // Instruction at address 10
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 2

    // Routine 3
    add(new Ld(r(E), c(90), f()));         // Instruction at address 20
    add(new Ret(t(), r(SP), mem(), r(PC))); // Return from routine 3

    step(8);
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    // Assert routines and blocks
    Assert.assertEquals(3, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 2);  // Routine 1 - Block 1
    assertBlockAddresses(routines.get(0).blocks.get(1), 3, 5);  // Routine 1 - Block 2
    assertBlockAddresses(routines.get(0).blocks.get(2), 6, 7);  // Routine 1 - Block 3
    assertBlockAddresses(routines.get(1).blocks.get(0), 10, 11); // Routine 2
    assertBlockAddresses(routines.get(2).blocks.get(0), 20, 21); // Routine 3
  }

  /*

    add(new Cp(r(B), c(2), f()));
    add(new JR(c(2), z(), r(PC)));
    add(new Ld(iRRn(r(IX), 1), r(A), f()));
    add(new JP(c(djnzLine), t(), r(PC)));
    add(new Ld(iRRn(r(IX), 2), r(A), f()));

    add(new DJNZ(c(-6), bnz(), r(PC)));
    add(new Add16(r(IX), c(20), f()));
   */
}
