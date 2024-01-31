package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import snapshots.Z80State;
import z80core.Z80.IntMode;

public abstract class RegistersBase {

  private State state;

  public RegistersBase() {
    super();
  }

  public void xor(int oper8) {

  }

  public void cp(int oper8) {
  }

  public final int getRegPC() {
    return getState().getRegister(RegisterName.PC).read();
  }

  public final void setRegPC(int address) {
    getState().getRegister(RegisterName.PC).write(address & 0xffff);
  }

  public void setFlags(int regF) {
  }

  public final void setRegDE(int word) {
    getState().getRegister(RegisterName.DE).write(word & 0xffff);
  }

  public final int getRegA() {
    return getState().getRegister(RegisterName.A).read();
  }

  public final void setRegA(int value) {
    getState().getRegister(RegisterName.A).write(value & 0xff);
  }

  public final int getRegB() {
    return getState().getRegister(RegisterName.B).read();
  }

  public final void setRegB(int value) {
    getState().getRegister(RegisterName.B).write(value & 0xff);
  }

  public final int getRegC() {
    return getState().getRegister(RegisterName.C).read();
  }

  public final void setRegC(int value) {
    getState().getRegister(RegisterName.C).write(value & 0xff);
  }

  public final int getRegD() {
    return getState().getRegister(RegisterName.D).read();
  }

  public final void setRegD(int value) {
    getState().getRegister(RegisterName.D).write(value & 0xff);
  }

  public final int getRegE() {
    return getState().getRegister(RegisterName.E).read();
  }

  public final void setRegE(int value) {
    getState().getRegister(RegisterName.E).write(value & 0xff);
  }

  public final int getRegH() {
    return getState().getRegister(RegisterName.H).read();
  }

  public final void setRegH(int value) {
    getState().getRegister(RegisterName.H).write(value & 0xff);
  }

  public final int getRegL() {
    return getState().getRegister(RegisterName.L).read();
  }

  public final void setRegL(int value) {
    getState().getRegister(RegisterName.L).write(value & 0xff);
  }

  public final int getRegAx() {
    return getState().getRegister(RegisterName.Ax).read();
  }

  public final void setRegAx(int value) {
    getState().getRegister(RegisterName.Ax).write(value & 0xff);
  }

  public final int getRegFx() {
    return getState().getRegister(RegisterName.Fx).read();
  }

  public final void setRegFx(int value) {
    getState().getRegister(RegisterName.Fx).write(value & 0xff);
  }

  public final int getRegBx() {
    return getState().getRegister(RegisterName.Bx).read();
  }

  public final void setRegBx(int value) {
    getState().getRegister(RegisterName.Bx).write(value & 0xff);
  }

  public final int getRegCx() {
    return getState().getRegister(RegisterName.Cx).read();
  }

  public final void setRegCx(int value) {
    getState().getRegister(RegisterName.Cx).write(value & 0xff);
  }

  public final int getRegDx() {
    return getState().getRegister(RegisterName.Dx).read();
  }

  public final void setRegDx(int value) {
    getState().getRegister(RegisterName.Dx).write(value & 0xff);
  }

  public final int getRegEx() {
    return getState().getRegister(RegisterName.Ex).read();
  }

  public final void setRegEx(int value) {
    getState().getRegister(RegisterName.Ex).write(value & 0xff);
  }

  public final int getRegHx() {
    return getState().getRegister(RegisterName.Hx).read();
  }

  public final void setRegHx(int value) {
    getState().getRegister(RegisterName.Hx).write(value & 0xff);
  }

  public final int getRegLx() {
    return getState().getRegister(RegisterName.Lx).read();
  }

  public final void setRegLx(int value) {
    getState().getRegister(RegisterName.Lx).write(value & 0xff);
  }

  public final int getRegAF() {
    return getState().getRegister(RegisterName.AF).read();
  }

  public final void setRegAF(int word) {
    getState().getRegister(RegisterName.AF).write(word & 0xffff);
  }

  public final int getRegAFx() {
    return getState().getRegister(RegisterName.AFx).read();
  }

  public final void setRegAFx(int word) {
    getState().getRegister(RegisterName.AFx).write(word & 0xffff);
  }

  public final int getRegBC() {
    return getState().getRegister(RegisterName.BC).read();
  }

  public final void setRegBC(int word) {
    getState().getRegister(RegisterName.BC).write(word & 0xff);
  }

  public final int getFlags() {
    return getState().getRegister(RegisterName.F).read();
  }

  public final int getRegHLx() {
    return getState().getRegister(RegisterName.HLx).read();
  }

  public final void setRegHLx(int word) {
    getState().getRegister(RegisterName.HLx).write(word & 0xffff);
  }

  public final int getRegSP() {
    return getState().getRegister(RegisterName.SP).read();
  }

  public final void setRegSP(int word) {
    getState().getRegister(RegisterName.SP).write(word & 0xffff);
  }

  public final int getRegIX() {
    return getState().getRegister(RegisterName.IX).read();
  }

  public final void setRegIX(int word) {
    getState().getRegister(RegisterName.IX).write(word & 0xffff);
  }

  public final int getRegIY() {
    return getState().getRegister(RegisterName.IY).read();
  }

  public final void setRegIY(int word) {
    getState().getRegister(RegisterName.IY).write(word & 0xffff);
  }

  public final int getRegI() {
    return getState().getRegister(RegisterName.I).read();
  }

  public final void setRegI(int value) {
    getState().getRegister(RegisterName.I).write(value & 0xff);
  }

  public final int getRegR() {
    return getState().getRegister(RegisterName.R).read();
  }

  public final void setRegR(int value) {
    getState().getRegister(RegisterName.R).write(value & 0xff);
  }

  public final int getPairIR() {
    return getState().getRegister(RegisterName.IR).read();
  }

  public final int getMemPtr() {
    return getState().getRegister(RegisterName.MEMPTR).read();
  }

  public final void setMemPtr(int word) {
    getState().getRegister(RegisterName.MEMPTR).write(word & 0xffff);
  }

  public final boolean isCarryFlag() {
    return (getFlags() & 0x01) != 0;
  }

  public final void setCarryFlag(boolean carryState) {
    Register f = getState().getRegister(RegisterName.F);
    if (carryState)
      f.write(f.read() | 0x01);
    else
      f.write(f.read() & 0xFE);
  }

  public final int getRegDE() {
    return getState().getRegister(RegisterName.DE).read();
  }

  public final void setZ80State(Z80State state) {
    setRegA(state.getRegA());
    setFlags(state.getRegF());
    setRegB(state.getRegB());
    setRegC(state.getRegC());
    setRegD(state.getRegD());
    setRegE(state.getRegE());
    setRegH(state.getRegH());
    setRegL(state.getRegL());
    setRegAx(state.getRegAx());
    setRegFx(state.getRegFx());
    setRegBx(state.getRegBx());
    setRegCx(state.getRegCx());
    setRegDx(state.getRegDx());
    setRegEx(state.getRegEx());
    setRegHx(state.getRegHx());
    setRegLx(state.getRegLx());
    setRegIX(state.getRegIX());
    setRegIY(state.getRegIY());
    setRegSP(state.getRegSP());
    setRegPC(state.getRegPC());
    setRegI(state.getRegI());
    setRegR(state.getRegR());
    setMemptr(state.getMemPtr());
    setHalted(state.isHalted());
    setFfIFF1(state.isIFF1());
    setFfIFF2(state.isIFF2());
    setModeINT(state.getIM());
    setActiveINT(state.isINTLine());
    setPendingEI(state.isPendingEI());
    setActiveNMI(state.isNMI());
    setFlagQ(false);
    setLastFlagQ(state.isFlagQ());

//    getState().updateFromEmulator();
  }

  public State getState() {
    return state;
  }

  public final boolean isIFF1() {
    return isFfIFF1();
  }

  public final void setIFF1(boolean state) {
    setFfIFF1(state);
  }

  public final boolean isIFF2() {
    return isFfIFF2();
  }

  public final void setIFF2(boolean state) {
    setFfIFF2(state);
  }

  public final boolean isNMI() {
    return isActiveNMI();
  }

  public final void setNMI(boolean nmi) {
    setActiveNMI(nmi);
  }

  // La línea de NMI se activa por impulso, no por nivel
  public final void triggerNMI() {
    setActiveNMI(true);
  }

  // La línea INT se activa por nivel
  public final boolean isINTLine() {
    return isActiveINT();
  }

  public void setINTLine(boolean intLine) {
    setActiveINT(intLine);
  }

  // Acceso al modo de interrupción
  public final IntMode getIM() {
    return getModeINT();
  }

  public final void setIM(IntMode mode) {
    setModeINT(mode);
  }

  public final boolean isHalted() {
    return state.isHalted();
  }

  public void setHalted(boolean state) {
    this.state.setHalted(state);
  }

  public void setPinReset() {
    setPinReset(true);
  }

  public final boolean isPendingEI() {
    return state.isPendingEI();
  }

  public final void setPendingEI(boolean state) {
    this.state.setPendingEI(state);
  }

  public final Z80State getZ80State() {
    Z80State state = new Z80State();
    state.setRegA(getRegA());
    state.setRegF(getFlags());
    state.setRegB(getRegB());
    state.setRegC(getRegC());
    state.setRegD(getRegD());
    state.setRegE(getRegE());
    state.setRegH(getRegH());
    state.setRegL(getRegL());
    state.setRegAx(getRegAx());
    state.setRegFx(getRegFx());
    state.setRegBx(getRegBx());
    state.setRegCx(getRegCx());
    state.setRegDx(getRegDx());
    state.setRegEx(getRegEx());
    state.setRegHx(getRegHx());
    state.setRegLx(getRegLx());
    state.setRegIX(getRegIX());
    state.setRegIY(getRegIY());
    state.setRegSP(getRegSP());
    state.setRegPC(getRegPC());
    state.setRegI(getRegI());
    state.setRegR(getRegR());
    state.setMemPtr(getMemptr());
    state.setHalted(isHalted());
    state.setIFF1(isFfIFF1());
    state.setIFF2(isFfIFF2());
    state.setIM(getModeINT());
    state.setINTLine(isActiveINT());
    state.setPendingEI(isPendingEI());
    state.setNMI(isActiveNMI());
    state.setFlagQ(isLastFlagQ());
    return state;
  }

  public boolean isFlagQ() {
    return state.isFlagQ();
  }

  public void setFlagQ(boolean flagQ) {
    this.state.setFlagQ(flagQ);
    ;
  }

  public boolean isLastFlagQ() {
    return state.isFlagQ();
  }

  public void setLastFlagQ(boolean lastFlagQ) {
    this.state.setFlagQ(lastFlagQ);
  }

  public int getMemptr() {
    return state.getRegister(RegisterName.MEMPTR).read();
  }

  public void setMemptr(int memptr) {
    state.getRegister(RegisterName.MEMPTR).write(memptr & 0xffff);
  }

  public int getDE() {
    return state.getRegister(RegisterName.DE).read();
  }

  public void setDE(int DE) {
    state.getRegister(RegisterName.DE).write(DE & 0xffff);
  }

  public boolean isActiveINT() {
    return state.isIntLine();
  }

  public void setActiveINT(boolean activeINT) {
    this.state.setINTLine(activeINT);
  }

  public boolean isActiveNMI() {
    return state.isActiveNMI();
  }

  public void setActiveNMI(boolean activeNMI) {
    this.state.setActiveNMI(activeNMI);
  }

  public IntMode getModeINT() {
    return IntMode.values()[state.modeINT().ordinal()];
  }

  public void setModeINT(IntMode modeINT) {
    state.setIntMode(InterruptionMode.values()[modeINT.ordinal()]);
  }

  public boolean isFfIFF1() {
    return state.isIff1();
  }

  public void setFfIFF1(boolean ffIFF1) {
    this.state.setIff1(ffIFF1);
    ;
  }

  public boolean isFfIFF2() {
    return state.isIff2();
  }

  public void setFfIFF2(boolean ffIFF2) {
    this.state.setIff2(ffIFF2);
    ;
  }

  public boolean isPinReset() {
    return state.isPinReset();
  }

  public void setPinReset(boolean pinReset) {
    this.state.setPinReset(pinReset);
  }

  public void setState(State state) {
    this.state = state;
  }
}