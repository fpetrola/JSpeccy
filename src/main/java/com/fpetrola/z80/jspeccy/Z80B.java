
package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.spy.DefaultInstructionSpy;
import com.fpetrola.z80.spy.InstructionSpy;

import machine.Clock;
import z80core.IZ80;
import z80core.MemIoOps;
import z80core.NotifyOps;
import z80core.Timer;

public class Z80B extends RegistersBase implements IZ80 {
  private MemIoOps MemIoImpl;
  StateImpl state;
  public OOZ80 z80;
  private Timer timer;
  private final Clock clock;
  private long start = System.currentTimeMillis();
  private  volatile boolean executing;

  public Z80B(MemIoOps memory, NotifyOps notify, GraphFrame graph) {
    super();
    this.clock = Clock.getInstance();
    MemIoImpl = memory;
//    SpyInterface spy = new NullSpy();
    InstructionSpy spy = new DefaultInstructionSpy();
    MemoryImplementation memoryOOZ80 = new MemoryImplementation(memory);
    IOImplementation io = new IOImplementation(memory);
    state = new StateImpl(this, spy, memoryOOZ80, io);
    spy.setState(state);
    initBase(state);
    z80 = new OOZ80(state, graph, spy, clock);
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    for (int i = 0; i < 0xFFFF; i++) {
      int peek8 = MemIoImpl.peek83(i);
      MemIoImpl.poke82(i, peek8);
    }
    z80.update();
    timer.reset();
  }

  public void enableSpy(boolean b) {
    z80.getSpy().enable(b);
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    z80.getSpy().setSpritesArray(bitsWritten);
  }

  public synchronized boolean isExecuting() {
    return executing;
  }
}