package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.ComplexInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.transformations.*;
import org.junit.Before;

import java.util.function.Function;

import static com.fpetrola.z80.registers.RegisterName.B;
import static org.junit.Assert.assertEquals;

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
    Function<State<T>, OpcodeConditions> stateOpcodeConditionsFunction = state -> new OpcodeConditions(state.getFlag(), state.getRegister(B));

    firstContext = new CPUExecutionContext<T>(registerTransformerInstructionSpy, stateOpcodeConditionsFunction) {
      protected InstructionSpy createSpy() {
        ComplexInstructionSpy spy = new AbstractInstructionSpy<>();
        TraceableWordNumber.instructionSpy = spy;
        return spy;
      }
    };

    Function<State<T>, OpcodeConditions> stateOpcodeConditionsFunction1 = getStateOpcodeConditionsFactory();
    secondContext = new CPUExecutionContext<T>(registerTransformerInstructionSpy, stateOpcodeConditionsFunction1) {
      protected InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor) {
        TransformerInstructionExecutor instructionExecutor1 = new TransformerInstructionExecutor(this.state.getPc(), this.instructionExecutor, false, (InstructionTransformer) instructionCloner);
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

  protected Function<State<T>, OpcodeConditions> getStateOpcodeConditionsFactory() {
    return state -> new OpcodeConditions(state.getFlag(), state.getRegister(B));
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
