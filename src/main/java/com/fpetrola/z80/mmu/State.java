package com.fpetrola.z80.mmu;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.*;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.SpyRegisterBank;

import static com.fpetrola.z80.registers.RegisterName.*;

public class State<T extends WordNumber> {
  public enum InterruptionMode {
    IM0, IM1, IM2;
  }

  public RegisterBank<T> registers;

  private Memory<T> memory;

  private IO<T> io;
  private boolean halted;
  private boolean iff1;
  private boolean iff2;
  private InterruptionMode intMode;
  private Register<T> registerF;
  private boolean intLine;
  private boolean activeNMI;
  private boolean pendingEI;
  private boolean flagQ;
  private boolean pinReset;
  private Register<T> pc;

  private Register<T> memptr;

  private Register<T> regI;
  private Register<T> registerSP;
  private Register<T> registerR;
  private Register<T> registerB;

  public State(InstructionSpy spy, Memory<T> memory, IO io) {
    RegisterBank<T> registerBank = new SpyRegisterBank(spy);
    this.registers = registerBank.initSimpleBank();
    this.io = io;
    this.memory = spy.wrapMemory(memory);

    registerF = this.registers.get(RegisterName.F);
    pc = this.getRegister(PC);
    memptr = this.getRegister(RegisterName.MEMPTR);
    regI = this.getRegister(I);
    registerR = this.getRegister(RegisterName.R);
    registerB = this.getRegister(RegisterName.B);
    registerSP = this.getRegister(SP);
  }

  public FlagRegister<T> getFlag() {
    return (FlagRegister) getRegister(F);
  }

  public Register<T> r(RegisterName name) {
    return this.registers.get(name);
  }

  public Register<T> getRegister(RegisterName name) {
    return this.registers.get(name);
  }

  public void setHalted(boolean halted) {
    this.halted = halted;
  }

  public boolean isHalted() {
    return this.halted;
  }

  public void enableInterrupt() {
    iff1 = iff2 = true;
  }

  public void resetInterrupt() {
    iff1 = iff2 = false;
  }

  public String toString() {
    return "registers=" + registers + ", halted=" + halted + ", iff1=" + isIff1() + ", iff2=" + isIff2();
  }

  public boolean isIff1() {
    return iff1;
  }

  public void setIff1(boolean iff1) {
    this.iff1 = iff1;
  }

  public boolean isIff2() {
    return iff2;
  }

  public void setIff2(boolean iff2) {
    this.iff2 = iff2;
  }

  public InterruptionMode getInterruptionMode() {
    return intMode;
  }

  public void setIntMode(InterruptionMode intMode) {
    this.intMode = intMode;
  }

  public Memory<T> getMemory() {
    return memory;
  }

  public IO<T> getIo() {
    return io;
  }

  public void setINTLine(boolean intLine) {
    this.intLine = intLine;
  }

  public boolean isIntLine() {
    return intLine;
  }

  public boolean isActiveNMI() {
    return activeNMI;
  }

  public void setActiveNMI(boolean activeNMI) {
    this.activeNMI = activeNMI;
  }

  public boolean isPendingEI() {
    return pendingEI;
  }

  public void setPendingEI(boolean pendingEI) {
    this.pendingEI = pendingEI;
  }

  public boolean isFlagQ() {
    return flagQ;
  }

  public void setFlagQ(boolean flagQ) {
    this.flagQ = flagQ;
  }

  public boolean isPinReset() {
    return pinReset;
  }

  public void setPinReset(boolean pinReset) {
    this.pinReset = pinReset;
  }


  public Register<T> getPc() {
    return pc;
  }

  public Register<T> getMemptr() {
    return memptr;
  }

  public Register<T> getRegI() {
    return regI;
  }

  public Register<T> getRegisterSP() {
    return registerSP;
  }

  public Register<T> getRegisterR() {
    return registerR;
  }

  public Register<T> getRegisterB() {
    return registerB;
  }
}
