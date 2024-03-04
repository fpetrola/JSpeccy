package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import org.junit.Before;
import scala.collection.mutable.SynchronizedBuffer$class;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> {
  private CPUExecutionContext<T> currentContext;
  private CPUExecutionContext<T> firstContext;
  private CPUExecutionContext<T> secondContext;
  private Z80Cpu<T> currentCpu;

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
    currentCpu= firstContext.z80;
  }

  protected void useSecond() {
    currentContext = secondContext;
    currentCpu= secondContext.z80;
  }

  public int add(Instruction<T> ld) {
    return currentContext.instructionFetcher.add(ld);
  }

  protected void step() {
    currentCpu.execute();
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

  protected ChainedRegister<T> pair(Register<T> high, Register<T> low) {
    ChainedComposed16BitRegister<T> result = new ChainedComposed16BitRegister(high, low);
    addUser(result, high);
    addUser(result, low);

    return result;
  }

  private void addUser(ChainedComposed16BitRegister<T> result, Register<T> high1) {
    if (high1 instanceof VirtualPlain8BitRegister<T>) {
      ((VirtualPlain8BitRegister<T>) high1).addUser(result);
    }
  }

  protected ChainedRegister<T> cr(InstructionAdapter ia, ChainedRegister... regs) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction instruction = ia.adapt(register);
    VirtualPlain8BitRegister result = new VirtualPlain8BitRegister(instruction, register);
    Stream.of(regs).forEach(r -> r.addUser(result));
    return result;
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

  protected ChainedRegister<T> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T> register) {
    if (immutableOpcodeReference instanceof Register<?>)
      return pair((Register<T>) immutableOpcodeReference, register);
    else
      return pair(cr(refHigh -> new Ld(refHigh, immutableOpcodeReference, f())), register);
  }

  protected void useBoth() {
    currentContext = firstContext;
    currentCpu = new SynchronizedZ80s(firstContext, secondContext);
  }
}
