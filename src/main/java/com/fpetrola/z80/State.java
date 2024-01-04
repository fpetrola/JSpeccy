package com.fpetrola.z80;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterBank;
import com.fpetrola.z80.registers.RegisterName;

public class State {
  public enum IntMode2 {
    IM0, IM1, IM2
  };

  public RegisterBank registers;

  public State(RegisterBank registers) {
    init(registers);
  }

  protected void init(RegisterBank registers) {
    this.registers = registers;
    states = registers.get(RegisterName.STATES);
    registerF = registers.get(RegisterName.F);
  }

  public State() {
  }

  int[] intModes = new int[] { 0x40, 0x80, 0x100 };

  private boolean halted;
  private boolean iff1;
  private boolean iff2;
  private IntMode2 intMode;
  private Register states;
  private Register registerF;

//  public State() {
//    this.registers = new RegisterBank();
//  }

  public Register getRegister(RegisterName name) {
    return this.registers.get(name);
  }

  public Register getRegisterAlternate(RegisterName name) {
    return this.registers.getAlternate(name);
  }

  public void setHalt(boolean halted) {
    this.halted = halted;
  }

  public boolean isHalted() {
//    return this.halted = (states.read() & 0x01) != 0;
//    halted = getStatesBitSet().get(0);
//   
//    z80.setIFF1(b.get(1));
//    z80.setIFF2(b.get(2));
////    z80.setINTLine(b.get(3));
//    z80.setNMI(b.get(4));
//    z80.setPendingEI(b.get(5));
//    if (b.get(6))
//      z80.setIM(IntMode.IM0);
//    if (b.get(7))
//      z80.setIM(IntMode.IM1);
//    if (b.get(8))
//      z80.setIM(IntMode.IM2);

    return this.halted;
  }

  public void enableInterrupt() {
    states.write(states.read() | 0x06);
    iff1 = iff2 = true;
  }

  public void resetInterrupt() {
    states.write(states.read() & 0xF9);
    iff1 = iff2 = false;
  }

  @Override
  public String toString() {
    return "registers=" + registers + ", halted=" + halted + ", iff1=" + isIff1() + ", iff2=" + isIff2();
  }

  public boolean isIff1() {
    return iff1;
  }

  public void setIff1(boolean iff1) {
//    b.set(0, z80.isHalted());
//    b.set(2, z80.isIFF2());
////    b.set(3, z80.isINTLine());
//    b.set(3, false);
//    b.set(4, z80.isNMI());
//    b.set(5, z80.isPendingEI());
//    b.set(6, z80.getIM() == IntMode.IM0);
//    b.set(7, z80.getIM() == IntMode.IM1);
//    b.set(8, z80.getIM() == IntMode.IM2);
    int i = states.read();
    states.write(iff1 ? (i | 0x0002) : (i & 0xFFFD));
    this.iff1 = iff1;
  }

  public boolean isIff2() {
    return iff2;
  }

  public void setIff2(boolean iff2) {
    int i = states.read();
    states.write(iff2 ? (i | 0x0004) : (i & 0xFFFB));
    this.iff2 = iff2;
  }

  public IntMode2 modeINT() {
    return IntMode2.values()[(((states.read() & 0x1C0) >>> 6) / 2) % 3];
  }

  public void setIntMode(IntMode2 intMode) {
    states.write((states.read() & 0xFE3F) | intModes[intMode.ordinal()]);
    this.intMode = intMode;
  }

  public void updateFromEmulator() {
    registers.updateFromEmulator();
  }

  public boolean isZ() {
    return (registerF.read() & 0x40) != 0;
  }
}
