
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
  private int opCode;

  public Z80B(MemIoOps memory, NotifyOps notify, GraphFrame graph) {
    super();
    this.clock = Clock.getInstance();
    MemIoImpl = memory;
    OpcodesSpy spy = new OpcodesSpy();
    MemoryImplementation memoryOOZ80 = new MemoryImplementation(memory);
    IOImplementation io = new IOImplementation(memory);
    state = new StateImpl(this, spy, memoryOOZ80, io);
    
    initBase(state);
    z80 = new OOZ80(state, graph, spy);
    reset();

    timer = new Timer("Z80");
  }

  private void interruption() {
    clock.addTstates(7);
    z80.interruption();
  }

  public void execute(int statesLimit) {
    while (clock.getTstates() < statesLimit) {

      if (isActiveNMI()) {
        setActiveNMI(false);
//        lastFlagQ = false;
//        nmi();
        continue;
      }

      if (isActiveINT()) {
        if (isIFF1() && !isPendingEI()) {
//          lastFlagQ = false;
          interruption();
        }
      }

      try {

        z80.execute(1);
        if (isPendingEI() && opCode != 0xFB) {
          setPendingEI(false);
          z80.endInterruption();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void setBreakpoint(int address, boolean state) {
  }

  public void reset() {
    z80.reset();
  }

  public State getState() {
    return state;
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int peek8 = MemIoImpl.peek83(i);
      MemIoImpl.poke82(i, peek8);
    }
    z80.update();
//    state.updateFromEmulator();
  }

  public void enableSpy(boolean b) {
    z80.getSpy().enable(b);
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    z80.getSpy().setSpritesArray(bitsWritten);
  }
}