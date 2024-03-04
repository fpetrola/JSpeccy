
package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.NullInstructionSpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;
import machine.Clock;
import z80core.IZ80;
import z80core.MemIoOps;
import z80core.Timer;

import java.io.File;

public class Z80B extends RegistersBase implements IZ80 {
  private MemIoOps memIoImpl;
  public OOZ80 z80;
  private Timer timer;
  private final Clock clock;
  private long start = System.currentTimeMillis();
  private volatile boolean executing;
  private InstructionSpy spy;

  public Z80B(MemIoOps memIoOps, GraphFrame graphFrame) {
    super();
    this.clock = Clock.getInstance();
    this.memIoImpl = memIoOps;
   // spy = new RoutineGrouperSpy(graphFrame);
    spy = new NullInstructionSpy();

    TraceableWordNumber.instructionSpy = spy;
    MemoryImplementation memory = new MemoryImplementation(memIoOps, spy);
    IOImplementation io = new IOImplementation(memIoOps);
    State state = new State(io, new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(memory));
    SpyInstructionExecutor instructionExecutor = new SpyInstructionExecutor(getSpy());

    z80 = createZ80(state, new OpcodeConditions(state.getFlag()), instructionExecutor);
    final ReadOnlyMemoryImplementation memory1 = new ReadOnlyMemoryImplementation(memory);
    State state2 = new State(new ReadOnlyIOImplementation(io), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(memory1));
    Z80Cpu z802 = createZ80(state2, new MutableOpcodeConditions(state2), instructionExecutor);
    z802.reset();
    spy.setSecondZ80(z802);
    setState(state);
    reset();

    timer = new Timer("Z80");
  }

  private OOZ80 createZ80(State state, OpcodeConditions opcodeConditions, SpyInstructionExecutor instructionExecutor1) {
    return new OOZ80(state, new DefaultInstructionFetcher(state, opcodeConditions, new FetchNextOpcodeInstructionFactory(getSpy(), state)), instructionExecutor1);
  }

  public void execute(int statesLimit) {
    executing = true;
    while (clock.getTstates() < statesLimit) {
//      timer.start();
      clock.addTstates(1);

      z80.execute();
//      long end = timer.end();

//      if (System.currentTimeMillis() - start > 1000)
//        MemIoImpl.poke8(16384, 255);
//      start = System.currentTimeMillis();
    }
    executing = false;
  }

  public void setBreakpoint(int address, boolean state) {
  }

  public void reset() {
    z80.reset();
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int peek8 = memIoImpl.peek83(i);
      memIoImpl.poke82(i, peek8);
    }
    z80.update();
    spy.reset(getState());
    timer.reset();
  }

  public void enableSpy(boolean b) {
    getSpy().enable(b);
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    getSpy().setSpritesArray(bitsWritten);
  }

  public synchronized boolean isExecuting() {
    return executing;
  }

  @Override
  public void setLoadedFile(File fileSnapshot) {
    spy.setGameName(fileSnapshot.getName());
  }

  @Override
  public InstructionSpy getSpy() {
    return spy;
  }
}