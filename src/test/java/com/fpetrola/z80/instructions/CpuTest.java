package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> {
  private CPUExecutionContext<T> currentContext;
  private CPUExecutionContext<T> firstContext;
  private CPUExecutionContext<T> secondContext;

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
    secondContext = new CPUExecutionContext<T>(spy2);

    useFirst();
    useSecond();
  }

  protected void useFirst() {
    currentContext = firstContext;
  }

  protected void useSecond() {
    currentContext = secondContext;
  }

  public int add(Instruction<T> ld) {
    return currentContext.instructionFetcher.add(ld);
  }

  protected void step() {
    currentContext.z80.execute();
  }

  protected Register<T> r(RegisterName registerName) {
    return currentContext.state.r(registerName);
  }

  protected MockedMemory<T> mem() {
    return (MockedMemory<T>) ((MemorySpy<T>) currentContext.state.getMemory()).getMemory();
  }

  public FlagRegister<T> f() {
    return currentContext.flag;
  }

  protected RegisterPair<T> pair(Register<T> cr, Register<T> cr1) {
    return new Composed16BitRegister<>(RegisterName.VIRTUAL, cr, cr1);
  }

  protected Register<T> cr(InstructionAdapter ia) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction instruction = ia.adapt(register);
    return new VirtualPlain8BitRegister(instruction, register, this.currentContext.nestedInstructionExecutor);
  }

  protected OpcodeReference iRR(Register<T> memoryReader) {
    return currentContext.ot.iRR(memoryReader);
  }

  protected ImmutableOpcodeReference c(int value) {
    return currentContext.ot.c(value);
  }

  protected OpcodeReference iiRR(Register<T> memoryWriter) {
    return currentContext.ot.iiRR(memoryWriter);
  }

  protected Instruction getInstructionAt(int i) {
    return currentContext.instructionFetcher.getInstructionAt(i);
  }

  protected <J> J assertTypeAndCast(Class<? extends J> expected, Object i1) {
    assertEquals(expected, i1.getClass());
    J ld1 = (J) i1;
    return ld1;
  }

  protected RegisterPair<T> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T> register) {
    return pair(cr(refHigh -> new Ld(refHigh, immutableOpcodeReference, f())), cr(refLow -> new Ld(refLow, register, f())));
  }
}
