package com.fpetrola.z80;

import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;

public class State {
  public enum OOIntMode {
    IM0, IM1, IM2
  };

  public RegisterBank registers;
  private SpyInterface spy;
  private Memory memory;
  private IO io;
  private int[] intModes = new int[] { 0x40, 0x80, 0x100 };
  private boolean halted;
  private boolean iff1;
  private boolean iff2;
  private OOIntMode intMode;
  private Register states;
  private Register registerF;
  private boolean intLine;
  private boolean activeNMI;
  private boolean pendingEI;
  private boolean flagQ;
  private boolean pinReset;

  public State() {
  }

  public void init(RegisterBank registers, SpyInterface spy, Memory memory, IO io) {
    this.registers = registers;
    this.spy = spy;
    this.io = io;
    this.memory = spy.wrapMemory(memory);
    registerF = registers.get(RegisterName.F);
  }

  public Register getRegister(RegisterName name) {
    return spy.wrapOpcodeRegister(this.registers.get(name), name);
  }

  public Register getRegisterAlternate(RegisterName name) {
    return spy.wrapOpcodeRegister(this.registers.getAlternate(name), name);
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

  public OOIntMode modeINT() {
    return intMode;
  }

  public void setIntMode(OOIntMode intMode) {
    this.intMode = intMode;
  }

  public boolean isZ() {
    return (registerF.read() & 0x40) != 0;
  }

  public Memory getMemory() {
    return memory;
  }

  public IO getIo() {
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
}
