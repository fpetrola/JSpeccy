package com.fpetrola.z80.minizx;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.minizx.emulation.Helper;
import com.fpetrola.z80.minizx.emulation.MiniZXWithEmulation;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class DefaultSyncChecker implements SyncChecker {
  static Semaphore semaphore = new Semaphore(2);
  volatile int checking;
  volatile int checkingEmu;
  volatile static Stack<StateSync> stateSync = new Stack();
  MiniZXWithEmulation miniZXWithEmulation;
  static OOZ80<WordNumber> ooz80;
  private SpectrumApplication spectrumApplication;

  public DefaultSyncChecker() {
    this.ooz80 = Helper.createOOZ80(JetSetWilly3.io);
  }

  @Override
  public int getByteFromEmu(Integer index) {
    WordNumber datum = ooz80.getState().getMemory().getData()[index];
    if (datum == null)
      datum = WordNumber.createValue(0);
    return datum.intValue();
  }

  @Override
  public void init(SpectrumApplication spectrumApplication) {
    this.spectrumApplication = spectrumApplication;
    Register<WordNumber> pc = ooz80.getState().getPc();
    Memory<WordNumber> memory = ooz80.getState().getMemory();
    memory.addMemoryWriteListener((MemoryWriteListener<WordNumber>) (address, value) -> {
      checkSyncEmu(address.intValue(), value.intValue(), pc.read().intValue());
    });
    memory.addMemoryReadListener((MemoryReadListener<WordNumber>) (address, value) -> {
      checkSyncEmu(address.intValue(), value.intValue(), pc.read().intValue());
    });

    miniZXWithEmulation = new MiniZXWithEmulation(ooz80, this.spectrumApplication);
    miniZXWithEmulation.copyStateBackToEmulation();
    pc.write(WordNumber.createValue(34762));
    new Thread(() -> miniZXWithEmulation.emulate()).start();
  }

  @Override
  public void checkSyncEmu(int address, int value, int pc) {
    System.out.println("sync emu: " + pc);
    while (checking == 0) ;
    if (checking != pc)
      System.out.print("");
    else {
      checkMatching(pc);
      checking = 0;
    }
  }

  @Override
  public void checkSyncJava(int address, int value, int pc) {
    System.out.println("sync java: " + pc);

    checking = pc;
    while (checking != 0) ;
  }

  @Override
  public void checkMatching(int pc) {
    if (!miniZXWithEmulation.stateIsMatching()) {
      System.out.println("not matching at: " + pc);
    } else {
      System.out.println("ok at: " + pc);
    }
    stateSync.clear();
  }
}