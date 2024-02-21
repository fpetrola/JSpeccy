package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.NullInstructionSpy;
import org.junit.Before;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> {
  protected Register<T> a;
  protected Register<T> b;
  protected Register<T> d;
  protected Register<T> e;
  protected Register<T> h;
  protected Register<T> l;
  protected Register<T> hl;
  protected Register<T> de;
  protected Register<T> pc;
  protected OOZ80 z80;
  protected OpcodeConditions opc;
  protected MemoryForTest memory;
  protected OpcodeTargets ot;
  protected State<T> state;
  protected InstructionFetcherForTest instructionFetcher;
  protected NestedInstructionExecutor nestedInstructionExecutor;

  @Before
  public <T2 extends WordNumber> void setUp() {
    NullInstructionSpy spy = new NullInstructionSpy();
    TraceableWordNumber.instructionSpy = spy;
    nestedInstructionExecutor = new NestedInstructionExecutor();

    memory = new MemoryForTest();
    state = new State(spy, memory, new MyIO());
    a = state.getRegister(A);
    b = state.getRegister(B);
    d = state.getRegister(D);
    e = state.getRegister(E);
    h = state.getRegister(H);
    l = state.getRegister(L);
    hl = state.getRegister(HL);
    de = state.getRegister(DE);
    pc = state.getRegister(PC);
    ot = new OpcodeTargets(state);
    opc = new OpcodeConditions(state);

    instructionFetcher = new InstructionFetcherForTest(state);
    z80 = new OOZ80(state, instructionFetcher, new SpyInstructionExecutor(spy));
    z80.reset();
    instructionFetcher.reset();
  }

  public int add(Instruction<T> ld) {
    return instructionFetcher.add(ld);
  }

  protected void step() {
    z80.execute();
  }

}
