package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import org.junit.Before;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> {
  protected OpcodeTargets ot;

  protected State<T> state;
  protected OOZ80<T> z80;
  protected InstructionFetcherForTest instructionFetcher;
  protected NestedInstructionExecutor nestedInstructionExecutor;
  private OpcodeConditions opc;

  InstructionFactory new___;

  @Before
  public <T2 extends WordNumber> void setUp() {
    InstructionSpy spy = new AbstractInstructionSpy<>() {
      public void process() {
        System.out.println("procesando");
      }
    };

    TraceableWordNumber.instructionSpy = spy;

    nestedInstructionExecutor = new NestedInstructionExecutor();

    state = new State(spy, new MockedMemory(), new MockedIO());
    ot = new OpcodeTargets(state);
    instructionFetcher = new InstructionFetcherForTest(state);
    z80 = new OOZ80(state, instructionFetcher, new SpyInstructionExecutor(spy));
    z80.reset();
    instructionFetcher.reset();
    new___ = new InstructionFactory<>(state);
    opc = new OpcodeConditions(state.getFlag());
  }

  public int add(Instruction<T> ld) {
    return instructionFetcher.add(ld);
  }

  protected void step() {
    z80.execute();
  }

}
