package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.transformations.InstructionFetcherForTest;
import com.fpetrola.z80.transformations.InstructionTransformer;
import com.fpetrola.z80.transformations.TransformerInstructionExecutor;
import com.fpetrola.z80.transformations.TransformerInstructionFetcher;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("ALL")
public abstract class TwoZ80Test<T extends WordNumber> extends ContextDriverDelegator<T> {
  private Z80ContextDriver<T> firstContext;
  private Z80ContextDriver<T> secondContext;
  private RegisterTransformerInstructionSpy registerTransformerInstructionSpy = new RegisterTransformerInstructionSpy();

  public TwoZ80Test() {
    super(null);
  }

  @Before
  public <T2 extends WordNumber> void setUp() {
    firstContext = new CPUExecutionContext<T>(registerTransformerInstructionSpy) {
      protected InstructionSpy createSpy() {
        InstructionSpy spy = new AbstractInstructionSpy<>();
        TraceableWordNumber.instructionSpy = spy;
        return spy;
      }
    };

    secondContext = new CPUExecutionContext<T>(registerTransformerInstructionSpy) {
      protected InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor) {
        TransformerInstructionExecutor instructionExecutor1 = new TransformerInstructionExecutor(this.state.getPc(), this.instructionExecutor, (InstructionTransformer) instructionCloner);
        return buildInstructionFetcher(this.state, instructionExecutor1, spy);
      }

      @Override
      protected RegisterTransformerInstructionSpy createSpy() {
        return registerTransformerInstructionSpy;
      }
    };

    useBoth();
    setUpMemory();
  }

  protected InstructionFetcherForTest buildInstructionFetcher(State state, TransformerInstructionExecutor instructionExecutor1, InstructionSpy spy) {
    return new TransformerInstructionFetcher(state, instructionExecutor1);
  }

  protected void useFirst() {
    currentContext = firstContext;
  }

  protected void useSecond() {
    currentContext = secondContext;
  }

  protected void useBoth() {
    currentContext = new ContextDriverMux<T>(firstContext, secondContext);
  }

  protected <J> J assertTypeAndCast(Class<? extends J> expected, Object i1) {
    assertEquals(expected, i1.getClass());
    J ld1 = (J) i1;
    return ld1;
  }

  public void stepFirst() {
    firstContext.step();
  }

  public void stepSecond() {
    secondContext.step();
  }

  protected abstract void setUpMemory();

  protected OpcodeReference mm(ImmutableOpcodeReference<T> c) {
    return new MemoryAccessOpcodeReference(c, this.mem());
  }

  @Override
  public RegisterTransformerInstructionSpy getRegisterTransformerInstructionSpy() {
    return registerTransformerInstructionSpy;
  }
}
