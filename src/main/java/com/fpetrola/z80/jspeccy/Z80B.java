
package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.*;
import com.fpetrola.z80.graph.GraphFrame;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.NullInstructionSpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;
import com.fpetrola.z80.transformations.*;
import machine.Clock;
import z80core.IZ80;
import z80core.MemIoOps;
import z80core.Timer;

import java.io.File;

public class Z80B extends RegistersBase implements IZ80 {
  public static String FILE= "console2X.txt";

  private MemIoOps memIoImpl;
  public OOZ80 z80;
  private Timer timer;
  private final Clock clock;
  private volatile boolean executing;
  private InstructionSpy spy;

  public Z80B(MemIoOps memIoOps, GraphFrame graphFrame) {
    super();
    this.clock = Clock.getInstance();
    this.memIoImpl = memIoOps;
    // spy = new RoutineGrouperSpy(graphFrame);
   // spy = new SyncInstructionSpy();
    spy= new NullInstructionSpy();
    FILE = "console2B.txt";

    z80= createCompleteZ80(memIoOps,true, spy);
    setState(z80.getState());

   // Z80Cpu z802 = createCompleteZ80(memIoOps, false, new NullInstructionSpy());
    //z802 = createMutationsZ80(memory, io, instructionExecutor);
//    z802.reset();
//    spy.setSecondZ80(z802);
    reset();

    timer = new Timer("Z80");
  }

  private OOZ80 createCompleteZ80(MemIoOps memIoOps, boolean traditional, InstructionSpy spy1) {
    TraceableWordNumber.instructionSpy = spy1;
    MemoryImplementation memory = new MemoryImplementation(memIoOps, spy1);
    IOImplementation io = new IOImplementation(memIoOps);

    State state = new State(io, new SpyRegisterBankFactory(spy1).createBank(), spy1.wrapMemory(memory));
    InstructionExecutor instructionExecutor = new SpyInstructionExecutor(getSpy());

    TransformerInstructionExecutor transformerInstructionExecutor = createInstructionTransformer(state, instructionExecutor);
    InstructionExecutor instructionExecutor1 = traditional ? instructionExecutor : transformerInstructionExecutor;
    return createZ80(state, new OpcodeConditions(state.getFlag()), instructionExecutor1);
  }

  private TransformerInstructionExecutor createInstructionTransformer(State state, InstructionExecutor instructionExecutor) {
    InstructionFactory instructionFactory = new InstructionFactory(state);
    InstructionTransformer instructionTransformer = new InstructionTransformer(instructionFactory, new VirtualRegisterFactory(instructionExecutor, new RegisterNameBuilder()));
    TransformerInstructionExecutor transformerInstructionExecutor = new TransformerInstructionExecutor(state.getPc(), instructionExecutor, instructionTransformer);
    return transformerInstructionExecutor;
  }

  private Z80Cpu createMutationsZ80(MemoryImplementation memory, IOImplementation io, InstructionExecutor instructionExecutor) {
    final ReadOnlyMemoryImplementation memory1 = new ReadOnlyMemoryImplementation(memory);
    State state2 = new State(new ReadOnlyIOImplementation(io), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(memory1));
    Z80Cpu z802 = createZ80(state2, new MutableOpcodeConditions(state2), instructionExecutor);
    return z802;
  }

  private OOZ80 createZ80(State state, OpcodeConditions opcodeConditions, InstructionExecutor instructionExecutor1) {
    return new OOZ80(state, new DefaultInstructionFetcher(state, opcodeConditions, new FetchNextOpcodeInstructionFactory(getSpy(), state), instructionExecutor1));
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