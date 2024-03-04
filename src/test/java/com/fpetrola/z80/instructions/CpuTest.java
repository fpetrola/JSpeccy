package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> extends ContextDriverDelegator<T> {
  private ContextDriver<T> firstContext;
  private ContextDriver<T> secondContext;

  @Before
  public <T2 extends WordNumber> void setUp() {
    InstructionSpy spy = new AbstractInstructionSpy<>() {
      public void process() {
        System.out.println("procesando");
      }
    };
    TraceableWordNumber.instructionSpy = spy;

    firstContext = new CPUExecutionContext<T>(spy);

    InstructionSpy spy2 = new AbstractInstructionSpy<>() {
      public void process() {
        System.out.println("procesando");
      }
    };
    secondContext = new CPUExecutionContext<T>(spy2) {
      protected InstructionFetcherForTest createInstructionFetcher(InstructionSpy spy, CPUExecutionContext<T> executionContext) {
        return new TransformerInstructionFetcher(state, new SpyInstructionExecutor(spy), executionContext);
      }
    };

    useFirst();
    useSecond();
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

}
