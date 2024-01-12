
package com.fpetrola.z80;

import com.fpetrola.z80.instructions.OpcodesSpy;

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

  public Z80B(MemIoOps memory, NotifyOps notify, GraphFrame graph) {
    super();
    this.clock = Clock.getInstance();
    MemIoImpl = memory;
    OpcodesSpy spy = new OpcodesSpy();
    MemoryImplementation memoryOOZ80 = new MemoryImplementation(memory);
    IOImplementation io = new IOImplementation(memory);
    state = new StateImpl(this, spy, memoryOOZ80, io);
    initBase(state);
    z80 = new OOZ80(state, graph, spy, clock);
    reset();

    timer = new Timer("Z80");
  }

  public void execute(int statesLimit) {
    while (clock.getTstates() < statesLimit) {
      z80.execute();
    }
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
  }

  public void enableSpy(boolean b) {
    z80.getSpy().enable(b);
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    z80.getSpy().setSpritesArray(bitsWritten);
  }
}