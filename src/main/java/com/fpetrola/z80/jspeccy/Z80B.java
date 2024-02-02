
package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.blocks.spy.RoutineGrouperSpy;

import machine.Clock;
import z80core.IZ80;
import z80core.MemIoOps;
import z80core.Timer;

public class Z80B extends RegistersBase implements IZ80 {
  private MemIoOps memIoImpl;
  public OOZ80 z80;
  private Timer timer;
  private final Clock clock;
  private long start = System.currentTimeMillis();
  private  volatile boolean executing;

  public Z80B(MemIoOps memIoOps, GraphFrame graphFrame) {
    super();
    this.clock = Clock.getInstance();
    this.memIoImpl = memIoOps;
//    SpyInterface spy = new NullSpy();
    InstructionSpy spy = new RoutineGrouperSpy(graphFrame);
    MemoryImplementation memory = new MemoryImplementation(memIoOps);
    IOImplementation io = new IOImplementation(memIoOps);
    State state = new State(spy, memory, io);
    z80 = new OOZ80(state, new InstructionFetcher(state, new OpcodeConditions(state)));

    State state2 = new State(spy, new ReadOnlyMemoryImplementation(memory), new ReadOnlyIOImplementation(io));
    OOZ80 z802 = new OOZ80(state2, new InstructionFetcher(state2, new MutableOpcodeConditions(state2)));
    spy.setSecondZ80(z802);
    setState(state);
    reset();

    timer = new Timer("Z80");
  }

  public void execute(int statesLimit) {
    executing = true;
    while (clock.getTstates() < statesLimit) {
//      timer.start();
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
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < 0xFFFF; i++) {
      int peek8 = memIoImpl.peek83(i);
      memIoImpl.poke82(i, peek8);
    }
    z80.update();
    timer.reset();
  }

  public void enableSpy(boolean b) {
    z80.getInstructionFetcher().getSpy().enable(b);
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    z80.getInstructionFetcher().getSpy().setSpritesArray(bitsWritten);
  }

  public synchronized boolean isExecuting() {
    return executing;
  }
}