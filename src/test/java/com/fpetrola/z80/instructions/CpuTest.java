package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.instructions.old.RegisterTransformerInstructionSpy;
import com.fpetrola.z80.instructions.transformations.TransformerInstructionFetcher;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import org.junit.Before;

import java.util.stream.IntStream;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("ALL")
public abstract class CpuTest<T extends WordNumber> extends ContextDriverDelegator<T> {
  private ContextDriver<T> firstContext;
  private ContextDriver<T> secondContext;

  public CpuTest() {
    super(null);
  }

  @Before
  public <T2 extends WordNumber> void setUp() {


    firstContext = new CPUExecutionContext<T>() {
      protected InstructionSpy createSpy() {
        InstructionSpy spy = new AbstractInstructionSpy<>();
        TraceableWordNumber.instructionSpy = spy;
        return spy;
      }
    };

    secondContext = new CPUExecutionContext<T>() {
      protected InstructionFetcherForTest createInstructionFetcher(InstructionSpy spy, CPUExecutionContext<T> executionContext) {
        return new TransformerInstructionFetcher(state, new SpyInstructionExecutor(spy), instructionCloner);
      }

      @Override
      protected RegisterTransformerInstructionSpy createSpy() {
        return new RegisterTransformerInstructionSpy();
      }
    };

    useBoth();
    setUpMemory();
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
    return new MemoryAccessOpcodeReference(this, c);
  }

  protected void step(int i) {
    IntStream.range(0, i).forEach(i2 -> step());
  }

  protected int readMemAt(int i) {
    T read = mem().read(createValue(i));
    assertNotNull(read);
    return read.intValue();
  }
}
