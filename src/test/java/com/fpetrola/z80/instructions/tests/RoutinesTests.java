package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.instructions.base.ManualBytecodeGenerationTest;
import com.fpetrola.z80.instructions.base.SymbolicExecutionAdapter;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineManager;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")

public class RoutinesTests<T extends WordNumber> extends ManualBytecodeGenerationTest<T> {
  private SymbolicExecutionAdapter symbolicExecutionAdapter;
  private RoutineManager routineManager = RegisterTransformerInstructionSpy.routineFinder.routineManager;

  protected Function<State<T>, OpcodeConditions> getStateOpcodeConditionsFactory() {
    return state -> getSymbolicExecutionAdapter(state).createOpcodeConditions(state);
  }

  @Test
  public void callingSimpleRoutine() {
    setUpMemory();
    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(2)));
        add(Call(t(), c(5)));
        add(Ld(r(B), c(3)));
        add(Ret(t()));
        add(Ld(r(C), c(4)));

        add(Ld(r(D), c(5)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals(2, routines.size());

    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 3);
    assertBlockAddresses(routines.get(1).blocks.get(0), 5, 6);
  }

  protected void stepUntilComplete() {
    symbolicExecutionAdapter.stepUntilComplete(this, getState(), 0, 0);
  }

  private void assertBlockAddresses(Block block, int start, int end) {
    Assert.assertEquals(start, block.getRangeHandler().getStartAddress());
    Assert.assertEquals(end, block.getRangeHandler().getEndAddress());
  }

  @Test
  public void callingSimpleRoutineWithContinuation() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(B), c(2)));
        add(Call(t(), c(3)));
        add(Ld(r(B), c(3)));


        add(Ld(r(D), r(B)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(2, routines.size());

    Routine routine0 = routines.get(0);
    Assert.assertEquals(2, routine0.blocks.size());

    assertBlockAddresses(routine0.blocks.get(0), 0, 2);
    assertBlockAddresses(routine0.blocks.get(1), 3, 4);
    Assert.assertEquals(1, routine0.innerRoutines.size());
    Routine innerRoutine = routine0.innerRoutines.iterator().next();
    Block innerRoutineBlock = innerRoutine.blocks.get(0);
    assertBlockAddresses(innerRoutineBlock, 3, 4);

    Assert.assertEquals(innerRoutine, routines.get(1));
  }


  @Test
  public void multipleInnerRoutinesWithContinuation() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {

        add(Ld(r(A), c(1)));
        add(Call(t(), c(5)));
        add(Ld(r(B), c(2)));
        add(Call(t(), c(7)));
        add(Ld(r(C), c(3)));


        add(Ld(r(D), c(4)));
        add(Ret(t()));


        add(Ld(r(E), c(5)));
        add(Ret(t()));

      }
    };

    stepUntilComplete();

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(3, routines.size());
    Routine routine0 = routines.get(0);
    assertBlockAddresses(routine0.blocks.get(0), 0, 4);
    assertBlockAddresses(routine0.blocks.get(1), 5, 6);


    Assert.assertEquals(1, routine0.innerRoutines.size());


    Iterator<Routine> iterator = routine0.innerRoutines.iterator();
    assertBlockAddresses(iterator.next().blocks.get(0), 5, 6);

    Routine routine1 = routines.get(1);
    assertBlockAddresses(routine1.blocks.get(0), 5, 6);
    Routine routine2 = routines.get(2);
    assertBlockAddresses(routine2.blocks.get(0), 7, 8);
  }

  @Test
  public void interleavedRoutinesTest() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {

        add(Ld(r(A), c(1)));
        add(Call(t(), c(5)));
        add(Ld(r(B), c(2)));
        add(JP(c(7), t()));
        add(Ld(r(C), c(3)));


        add(Ld(r(D), c(4)));
        add(Ret(t()));


        add(Ld(r(E), c(5)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(2, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 3);
    assertBlockAddresses(routines.get(0).blocks.get(1), 7, 8);


    Assert.assertEquals(0, routines.get(0).innerRoutines.size());
    assertBlockAddresses(routines.get(1).blocks.get(0), 5, 6);
  }


  @Test
  public void recursiveInnerRoutineWithContinuation() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(1)));
        add(Call(t(), c(5)));
        add(Ld(r(B), c(2)));


        add(Ld(r(C), c(3)));
        add(Call(t(), c(5)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(2, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 4);
    assertBlockAddresses(routines.get(0).blocks.get(1), 5, 5);


    Assert.assertEquals(1, routines.get(0).innerRoutines.size());

    Iterator<Routine> iterator = routines.get(0).innerRoutines.iterator();

    Block subroutineBlock = iterator.next().blocks.get(0);
    assertBlockAddresses(subroutineBlock, 5, 5);

    Assert.assertEquals(subroutineBlock, routines.get(0).blocks.get(1));
  }

  @Test
  public void simpleRoutineTest() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(10)));
        add(Ld(r(B), c(20)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(1, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 2);
  }

  @Test
  public void nonConsecutiveBlocksTest() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(1)));
        add(JP(c(10), t()));
        add(Ld(r(A), c(2)));
        add(Ld(r(A), c(3)));
        add(Call(t(), c(8)));
        add(Ld(r(B), c(2)));
        add(Ret(t()));
        add(Ld(r(C), c(3)));

        add(Ld(r(D), c(4)));
        add(Ret(t()));

        add(Ld(r(E), c(5)));
        add(JP(c(3), t()));
      }
    };

    stepUntilComplete();
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(2, routines.size());
    assertBlockAddresses(routines.get(0).blocks.get(0), 0, 1);
    assertBlockAddresses(routines.get(0).blocks.get(1), 3, 6);
    assertBlockAddresses(routines.get(0).blocks.get(2), 10, 11);
    assertBlockAddresses(routines.get(1).blocks.get(0), 8, 9);
  }

  @Test
  public void recursiveRoutineTest() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {

        add(Ld(r(A), c(2)));
        add(Dec(r(A)));
        add(Call(nz(), c(1)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();

    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();
    Assert.assertEquals(2, routines.size());

    Routine routine0 = routines.get(0);
    assertBlockAddresses(routine0.blocks.get(0), 0, 0);
    assertBlockAddresses(routine0.blocks.get(1), 1, 3);
    Block innerRoutineBlock = routine0.innerRoutines.iterator().next().blocks.get(0);
    assertBlockAddresses(innerRoutineBlock, 1, 3);

    Assert.assertEquals(innerRoutineBlock, routines.get(1).blocks.get(0));

  }


  @Test
  public void multipleCallsAndRetsTest() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {

        add(Ld(r(A), c(50)));
        add(Call(t(), c(6)));
        add(Ld(r(B), c(60)));
        add(Call(t(), c(8)));
        add(Ld(r(C), c(70)));
        add(Ret(t()));


        add(Ld(r(D), c(80)));
        add(Ret(t()));


        add(Ld(r(E), c(90)));
        add(Ret(t()));
      }
    };


    stepUntilComplete();
    generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(3, routines.size());
    Routine routine0 = routines.get(0);
    assertBlockAddresses(routine0.blocks.get(0), 0, 5);
    assertBlockAddresses(routines.get(1).blocks.get(0), 6, 7);
    assertBlockAddresses(routines.get(2).blocks.get(0), 8, 9);
  }

  public SymbolicExecutionAdapter getSymbolicExecutionAdapter(State<T> state) {
    if (symbolicExecutionAdapter == null)
      symbolicExecutionAdapter = new SymbolicExecutionAdapter(state);
    return symbolicExecutionAdapter;
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
