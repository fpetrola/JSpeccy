package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.instructions.old.*;
import com.fpetrola.z80.transformations.InstructionFetcherForTest;
import com.fpetrola.z80.transformations.InstructionTransformer;
import com.fpetrola.z80.transformations.RegisterNameBuilder;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.fpetrola.z80.registers.Flags.ZERO_FLAG;
import static org.junit.Assert.assertEquals;

public abstract class CPUExecutionContext<T extends WordNumber> implements ContextDriver<T> {
  InstructionExecutor instructionExecutor;
  OpcodeTargets ot;
  State<T> state;
  Z80Cpu<T> z80;
  InstructionFetcherForTest instructionFetcher;
  OpcodeConditions opc;
  InstructionFactory new___;
  Register<T> flag;
  InstructionTransformer<T> instructionCloner;

  public CPUExecutionContext() {
    InstructionSpy spy = createSpy();
    instructionExecutor = new SpyInstructionExecutor(spy);
    InstructionFactory instructionFactory = new InstructionFactory();
    instructionCloner = new InstructionTransformer(instructionFactory, new VirtualRegisterFactory(instructionExecutor, new RegisterNameBuilder()));
    state = new State(new MockedIO(), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(new MockedMemory()));
    instructionFactory.setState(state);
    ot = new OpcodeTargets(state);
    instructionFetcher = createInstructionFetcher(spy, this);
    z80 = new OOZ80(state, instructionFetcher);
    z80.reset();
    instructionFetcher.reset();
    new___ = new InstructionFactory<>(state);
    flag = state.getFlag();
    opc = new OpcodeConditions(flag);
    spy.reset(state);
  }

  protected InstructionFetcherForTest createInstructionFetcher(InstructionSpy spy, CPUExecutionContext<T> executionContext) {
    return new InstructionFetcherForTest(state, new SpyInstructionExecutor(spy));
  }

  @Override
  public int add(Instruction<T> instruction) {
    return instructionFetcher.add(instruction);
  }

  @Override
  public void step() {
    z80.execute();
  }

  @Override
  public Register<T> r(RegisterName registerName) {
    return state.r(registerName);
  }

  @Override
  public MockedMemory<T> mem() {
    return (MockedMemory<T>) (state.getMemory() instanceof MemorySpy memorySpy ? memorySpy.getMemory() : state.getMemory());
  }

  @Override
  public MockedMemory<T> initMem(Supplier<T[]> supplier) {
    mem().init(supplier);
    return mem();
  }

  @Override
  public Register<T> f() {
    return flag;
  }

  @Override
  public Register<T> pc() {
    return state.getPc();
  }

  @Override
  public OpcodeReference iRR(Register<T> memoryReader) {
    return ot.iRR(memoryReader);
  }

  @Override
  public ImmutableOpcodeReference c(int value) {
    return ot.c(value);
  }

  @Override
  public OpcodeReference iiRR(Register<T> memoryWriter) {
    return ot.iiRR(memoryWriter);
  }

  @Override
  public Condition nz()
  {
    return opc.nf(ZERO_FLAG);
  }

  @Override
  public Condition t()
  {
    return opc.t();
  }

  @Override
  public OpcodeReference nn(ImmutableOpcodeReference<T> r) {
    return new Memory16BitReference(state.getMemory(), r, 0);
  }

  @Override
  public Instruction getInstructionAt(int i) {
    return instructionFetcher.getInstructionAt(i);
  }

  @Override
  public Instruction getTransformedInstructionAt(int i) {
    return instructionFetcher.getTransformedInstructionAt(i);
  }

  @Override
  public <T extends WordNumber> ChainedRegister<T> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T> register) {
    if (immutableOpcodeReference instanceof Register<?>)
      return pair((Register<T>) immutableOpcodeReference, register);
    else
      return pair(cr(refHigh -> new Ld(refHigh, immutableOpcodeReference, f())), register);
  }

  @Override
  public <T extends WordNumber> ChainedRegister<T> pair(Register<T> high, Register<T> low) {
    ChainedComposed16BitRegister<T> result = new ChainedComposed16BitRegister(high, low);
    addUser(result, high);
    addUser(result, low);

    return result;
  }

  @Override
  public <T extends WordNumber> void addUser(ChainedComposed16BitRegister<T> result, Register<T> high1) {
    if (high1 instanceof OldVirtualPlain8BitRegister<T>) {
      ((OldVirtualPlain8BitRegister<T>) high1).addUser(result);
    }
  }

  @Override
  public <T extends WordNumber> OldVirtualPlain8BitRegister<T> cr(InstructionAdapter ia, ChainedRegister... regs) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction instruction = ia.adapt(register);
    OldVirtualPlain8BitRegister result = new OldVirtualPlain8BitRegister(instruction, register);
    Stream.of(regs).forEach(r -> r.addUser(result));
    return result;
  }

  protected abstract InstructionSpy createSpy();
}