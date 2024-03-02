package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;
import org.junit.Before;

@SuppressWarnings("ALL")
public class CpuTest<T extends WordNumber> {
  protected OpcodeTargets ot;
  private State<T> state;
  private OOZ80<T> z80;
  protected InstructionFetcherForTest instructionFetcher;
  private NestedInstructionExecutor nestedInstructionExecutor;
  private OpcodeConditions opc;
  private InstructionFactory new___;
  private FlagRegister<T> flag;

  @Before
  public <T2 extends WordNumber> void setUp() {
    InstructionSpy spy = new AbstractInstructionSpy<>() {
      public void process() {
        System.out.println("procesando");
      }
    };

    TraceableWordNumber.instructionSpy = spy;

    nestedInstructionExecutor = new NestedInstructionExecutor();

    final MockedMemory memory = new MockedMemory();
    state = new State(new MockedIO(), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(memory));
    ot = new OpcodeTargets(state);
    instructionFetcher = new InstructionFetcherForTest(state);
    z80 = new OOZ80(state, instructionFetcher, new SpyInstructionExecutor(spy));
    z80.reset();
    instructionFetcher.reset();
    new___ = new InstructionFactory<>(state);
    flag = state.getFlag();
    opc = new OpcodeConditions(flag);
  }

  public int add(Instruction<T> ld) {
    return instructionFetcher.add(ld);
  }

  protected void step() {
    z80.execute();
  }

  protected Register<T> r(RegisterName registerName) {
    return state.r(registerName);
  }

  protected MockedMemory<T> _m1() {
    return (MockedMemory<T>) ((MemorySpy<T>) z80.getState().getMemory()).getMemory();
  }

  public FlagRegister<T> f() {
    return flag;
  }

  protected RegisterPair<T> pair(Register<T> cr, Register<T> cr1) {
    return new Composed16BitRegister<>(RegisterName.VIRTUAL, cr, cr1);
  }

  protected Register<T> cr(InstructionAdapter ia) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction instruction = ia.adapt(register);
    return new VirtualPlain8BitRegister(instruction, register, this.nestedInstructionExecutor);
  }

  protected OpcodeReference iRR(Register<T> memoryReader) {
    return ot.iRR(memoryReader);
  }

  protected ImmutableOpcodeReference c(int value) {
    return ot.c(value);
  }

  protected OpcodeReference iiRR(Register<T> memoryWriter) {
    return ot.iiRR(memoryWriter);
  }
}
