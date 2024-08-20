package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.*;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;
import com.fpetrola.z80.transformations.InstructionTransformer;
import com.fpetrola.z80.transformations.RegisterNameBuilder;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;

import java.util.function.Supplier;

public abstract class DefaultZ80InstructionDriver<T extends WordNumber> implements Z80InstructionDriver<T> {
  protected RegisterTransformerInstructionSpy registerTransformerInstructionSpy;
  InstructionExecutor instructionExecutor;
  protected State<T> state;
  Z80Cpu<T> z80;
  InstructionFetcher instructionFetcher;
  InstructionFactory new___;
  protected InstructionTransformer<T> instructionCloner;
  protected VirtualRegisterFactory virtualRegisterFactory;

  protected abstract InstructionSpy createSpy();

  public DefaultZ80InstructionDriver(RegisterTransformerInstructionSpy registerTransformerInstructionSpy) {
    this.registerTransformerInstructionSpy = registerTransformerInstructionSpy;

    InstructionSpy spy = createSpy();
    instructionExecutor = new SpyInstructionExecutor(spy);
    InstructionFactory instructionFactory = new InstructionFactory();
    virtualRegisterFactory = new VirtualRegisterFactory(instructionExecutor, new RegisterNameBuilder());
    instructionCloner = new InstructionTransformer(instructionFactory, virtualRegisterFactory);
    state = new State(new MockedIO(), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(new MockedMemory()));
    instructionFactory.setState(state);
    instructionFetcher = createInstructionFetcher(spy, state, instructionExecutor);
    z80 = new OOZ80(state, instructionFetcher);
    z80.reset();
    instructionFetcher.reset();
    new___ = new InstructionFactory<>(state);

    spy.reset(state);
  }

  protected abstract InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor);

  public void step() {
    z80.execute();
  }

  public MockedMemory<T> mem() {
    return (MockedMemory<T>) (state.getMemory() instanceof MemorySpy memorySpy ? memorySpy.getMemory() : state.getMemory());
  }

  public MockedMemory<T> initMem(Supplier<T[]> supplier) {
    mem().init(supplier);
    return mem();
  }

  public RegisterTransformerInstructionSpy getRegisterTransformerInstructionSpy() {
    return registerTransformerInstructionSpy;
  }
}
