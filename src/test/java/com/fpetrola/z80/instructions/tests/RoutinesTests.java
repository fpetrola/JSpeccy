package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.instructions.base.InstructionFactory;
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

  public SymbolicExecutionAdapter getSymbolicExecutionAdapter(State<T> state) {
    if (symbolicExecutionAdapter == null)
      symbolicExecutionAdapter = new SymbolicExecutionAdapter(state);
    return symbolicExecutionAdapter;
  }

  protected Function<State<T>, OpcodeConditions> getStateOpcodeConditionsFactory() {
    return state -> getSymbolicExecutionAdapter(state).createOpcodeConditions(state);
  }

  @Override
  protected Function<State<T>, InstructionFactory> getInstructionFactoryFactory() {
    return state -> getSymbolicExecutionAdapter(state).createInstructionFactory(state);
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

    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 2;
              this.$5();
              super.B = 3;
           }
        
           public void $5() {
              super.D = 5;
           }
        }
        """, resultingJava);


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

    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.B = 2;
              this.$3();
              super.B = 3;
              this.$3();
           }
        
           public void $3() {
              super.D = super.B;
           }
        }
        """, resultingJava);


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

    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 1;
              this.$5();
              super.B = 2;
              this.$7();
              super.C = 3;
              this.$5();
           }
        
           public void $5() {
              super.D = 4;
           }
        
           public void $7() {
              super.E = 5;
           }
        }
        """, resultingJava);


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
    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 1;
              this.$5();
              super.B = 2;
              super.E = 5;
           }
        
           public void $5() {
              super.D = 4;
           }
        }
        """, resultingJava);


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
        add(Ld(r(D), c(6)));
        add(Ret(t()));
      }
    };

    stepUntilComplete();

    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 1;
              this.$5();
              super.B = 2;
              super.C = 3;
              this.$5();
              this.$5();
           }
        
           public void $5() {
              super.D = 6;
           }
        }
        """, resultingJava);

    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals(2, routines.size());
    Routine routine0 = routines.get(0);
    Routine routine1 = routines.get(1);
    assertBlockAddresses(routine0.blocks.get(0), 0, 4);
    assertBlockAddresses(routine0.blocks.get(1), 5, 6);

    Assert.assertEquals(1, routine0.innerRoutines.size());

    Iterator<Routine> iterator = routine0.innerRoutines.iterator();

    Block subroutineBlock = iterator.next().blocks.get(0);
    assertBlockAddresses(subroutineBlock, 5, 6);

    Assert.assertEquals(subroutineBlock, routine0.blocks.get(1));
    Assert.assertEquals(subroutineBlock, routine1.blocks.get(0));
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
    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 10;
              super.B = 20;
           }
        }
        """, resultingJava);


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
    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 1;
              super.E = 5;
              super.A = 3;
              this.$8();
              super.B = 2;
           }
        
           public void $8() {
              super.D = 4;
           }
        }
        """, resultingJava);


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

    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 2;
              this.$1();
           }
        
           public void $1() {
              int var1 = super.A - 1 & 255;
              super.A = var1;
              super.F = super.A;
              if (super.F != 0) {
                 this.$1();
              }
        
           }
        }
        """, resultingJava);


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
    String resultingJava = generateAndDecompile();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 50;
              this.$6();
              super.B = 60;
              this.$8();
              super.C = 70;
           }
        
           public void $6() {
              super.D = 80;
           }
        
           public void $8() {
              super.E = 90;
           }
        }
        """, resultingJava);


    List<Routine> routines = routineManager.getRoutines();


    Assert.assertEquals(3, routines.size());
    Routine routine0 = routines.get(0);
    assertBlockAddresses(routine0.blocks.get(0), 0, 5);
    assertBlockAddresses(routines.get(1).blocks.get(0), 6, 7);
    assertBlockAddresses(routines.get(2).blocks.get(0), 8, 9);
  }

  @Test
  public void poppingReturnAddress() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(2)));
        add(Call(t(), c(6)));
        add(Ld(r(C), c(3)));
        add(Ld(r(C), c(4)));
        add(Ld(r(C), c(5))); // 4
        add(Ret(t()));

        add(Ld(r(D), c(4)));  // 6
        add(JP(c(10), t()));
        add(Ld(r(E), c(5)));
        add(Ret(t()));

        add(Pop(r(HL)));  // 10
        add(Ld(r(A), c(6)));
        add(JP(c(4), t()));
      }
    };


    stepUntilComplete();
    String resultingJava = generateAndDecompile();
    List<Routine> routines = routineManager.getRoutines();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 2;
              this.$6();
              if (!this.decPops()) {
                 super.C = 3;
                 super.C = 4;
              } else {
                 super.A = 6;
              }
        
              super.C = 5;
           }
        
           public void $6() {
              super.D = 4;
              this.incPops();
           }
        }
        """, resultingJava);


    Assert.assertEquals(2, routines.size());
    Routine routine0 = routines.get(0);
    Assert.assertEquals(0, routine0.getStartAddress());
    Assert.assertEquals(12, routine0.getEndAddress());

    Routine routine1 = routines.get(1);
    Assert.assertEquals(6, routine1.getStartAddress());
    Assert.assertEquals(7, routine1.getEndAddress());
  }

  @Test
  public void popping2ReturnAddresses() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(2)));
        add(Call(t(), c(6)));
        add(Ld(r(C), c(3)));
        add(Ld(r(C), c(4)));
        add(Ld(r(C), c(5))); // 4
        add(Ret(t()));

        add(Ld(r(D), c(4)));  // 6
        add(Call(t(), c(11)));
        add(Ret(t()));
        add(Ld(r(E), c(5)));
        add(Ret(t()));

        add(Dec(r(A))); // 11
        add(JP(c(15), nz()));
        add(Ld(r(E), c(8)));
        add(Ret(t()));

        add(Pop(r(HL))); // 15
        add(Pop(r(HL)));
        add(Ld(r(A), c(61)));
        add(Ld(r(B), c(62)));
        add(JP(c(4), t()));
      }
    };


    stepUntilComplete();
    String resultingJava = generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.A = 2;
              this.$6();
              if (!this.decPops()) {
                 super.C = 3;
                 super.C = 4;
              } else {
                 super.A = 61;
                 super.B = 62;
              }
        
              super.C = 5;
           }
        
           public void $6() {
              super.D = 4;
              this.$11();
              this.decPops();
              this.incPops();
           }
        
           public void $11() {
              int var1 = super.A - 1 & 255;
              super.A = var1;
              super.F = super.A;
              if (super.F != 0) {
                 this.incPops();
              } else {
                 this.incPops();
                 super.E = 8;
              }
           }
        }
        """, resultingJava);


    Assert.assertEquals(3, routines.size());
    Routine routine0 = routines.get(0);
    Assert.assertEquals(0, routine0.getStartAddress());
    Assert.assertEquals(19, routine0.getEndAddress());

    Routine routine1 = routines.get(1);
    Assert.assertEquals(6, routine1.getStartAddress());
    Assert.assertEquals(8, routine1.getEndAddress());

    Routine routine2 = routines.get(2);
    Assert.assertEquals(11, routine2.getStartAddress());
    Assert.assertEquals(14, routine2.getEndAddress());
  }


  @Test
  public void popping2ReturnAddressesB() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(A), c(2)));
        add(Call(t(), c(7)));
        add(Ld(r(C), c(2)));
        add(Call(t(), c(22)));
        add(Ld(r(C), c(3)));
        add(Ld(r(C), c(5))); // 5
        add(Ret(t()));

        add(Ld(r(D), c(4)));  // 7
        add(Cp(c(3)));
        add(Call(z(), c(13)));
        add(Ret(t()));
        add(Ld(r(E), c(5)));
        add(Ret(t()));

        add(Ld(r(C), c(40))); // 13
        add(JP(c(16), t()));
        add(Ret(t()));

        add(Pop(r(HL))); // 16
        add(Ld(r(E), c(71)));
        add(Pop(r(HL)));
        add(Ld(r(A), c(61)));
        add(Ld(r(B), c(62)));
        add(JP(c(4), t()));

        add(Ld(r(D), c(41)));  // 22
        add(Ld(r(E), c(51)));
        add(JP(c(18), t()));
      }
    };


    stepUntilComplete();
    String resultingJava = generateAndDecompile();

    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              label11: {
                 super.A = 2;
                 this.$7();
                 if (!this.decPops()) {
                    super.C = 2;
                    this.$22();
                    if (!this.decPops()) {
                       break label11;
                    }
                 }
        
                 super.A = 61;
                 super.B = 62;
              }
        
              super.C = 3;
              super.C = 5;
           }
        
           public void $7() {
              super.D = 4;
              int var1 = super.A - 3;
              super.F = var1;
              if (super.F == 0) {
                 this.$13();
                 if (this.decPops()) {
                    super.E = 71;
                    this.incPops();
                    return;
                 }
              }
        
           }
        
           public void $13() {
              super.C = 40;
              this.incPops();
           }
        
           public void $22() {
              super.D = 41;
              super.E = 51;
              this.incPops();
           }
        }
        """, resultingJava);


    Assert.assertEquals(4, routines.size());
    Routine routine0 = routines.get(0);
    Assert.assertEquals(0, routine0.getStartAddress());
    Assert.assertEquals(21, routine0.getEndAddress());

    Routine routine1 = routines.get(1);
    Assert.assertEquals(7, routine1.getStartAddress());
    Assert.assertEquals(17, routine1.getEndAddress());

    Routine routine2 = routines.get(2);
    Assert.assertEquals(13, routine2.getStartAddress());
    Assert.assertEquals(14, routine2.getEndAddress());

    Routine routine3 = routines.get(3);
    Assert.assertEquals(22, routine3.getStartAddress());
    Assert.assertEquals(24, routine3.getEndAddress());
  }


  @Test
  public void poppingReturnAddressWithPendingBranches() {
    setUpMemory();

    symbolicExecutionAdapter.new SymbolicInstructionFactoryDelegator() {
      {
        add(Ld(r(H), c(1)));
        add(Ld(r(A), c(2)));
        add(Call(t(), c(7)));
        add(Ld(r(C), c(3)));
        add(Ld(r(C), c(4)));
        add(Ld(r(C), c(5))); // 5
        add(Ret(t()));

        add(Ld(r(D), c(4)));  // 7
        add(Cp(c(3)));
        add(JP(c(19), nz()));
        add(Ld(r(H), c(2)));
        add(Ld(r(D), r(H)));  // 11
        add(JP(c(16), t()));

        add(Ld(r(E), c(5)));
        add(Ret(t()));
        add(Ld(r(H), c(3)));

        add(Pop(r(HL)));  // 16
        add(Ld(r(A), c(6)));
        add(JP(c(5), t()));

        add(Ld(r(A), c(61))); // 19
        add(JP(c(11), t()));
      }
    };


    stepUntilComplete();
    String resultingJava = generateAndDecompile();
    List<Routine> routines = routineManager.getRoutines();
    Assert.assertEquals("""
        import com.fpetrola.z80.minizx.SpectrumApplication;
        
        public class JSW extends SpectrumApplication {
           public void $0() {
              super.H = 1;
              super.A = 2;
              this.$7();
              if (!this.decPops()) {
                 super.C = 3;
                 super.C = 4;
              } else {
                 super.A = 6;
              }
        
              super.C = 5;
           }
        
           public void $7() {
              super.D = 4;
              int var1 = super.A - 3;
              super.F = var1;
              if (super.F == 0) {
                 super.H = 2;
              } else {
                 super.A = 61;
              }
        
              super.D = super.H;
              this.incPops();
           }
        }
        """, resultingJava);


    Assert.assertEquals(2, routines.size());
    Routine routine0 = routines.get(0);
    Assert.assertEquals(0, routine0.getStartAddress());
    Assert.assertEquals(18, routine0.getEndAddress());

    Routine routine1 = routines.get(1);
    Assert.assertEquals(7, routine1.getStartAddress());
    Assert.assertEquals(20, routine1.getEndAddress());
  }

}
