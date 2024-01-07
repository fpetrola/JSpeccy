package com.fpetrola.z80;

import snapshots.Z80State;
import z80core.Z80;
import z80core.Z80.IntMode;

public abstract class RegistersBase {

  protected static final int CARRY_MASK = 0x01;
  protected static final int SIGN_MASK = 0x80;
  protected boolean regRbit7;
  protected int regA;
  protected int regB;
  protected int regC;
  protected int regD;
  protected int regE;
  protected int regH;
  protected int regL;
  protected int sz5h3pnFlags;
  protected boolean carryFlag;
  protected boolean flagQ;
  protected boolean lastFlagQ;
  protected int regAx;
  protected int regFx;
  protected int regBx;
  protected int regCx;
  protected int regDx;
  protected int regEx;
  protected int regHx;
  protected int regLx;
  protected int regPC;
  protected int regIX;
  protected int regIY;
  protected int regSP;
  protected int regI;
  protected int regR;
  protected int memptr;
  protected int DE;

  boolean activeINT;
  boolean activeNMI;
  boolean pendingEI;

  IntMode modeINT = IntMode.IM0;
  boolean halted = false;
  boolean ffIFF1 = false;
  boolean ffIFF2 = false;
  private boolean pinReset = false;

  public RegistersBase() {
    super();
  }

  public void xor(int oper8) {

  }

  public void cp(int oper8) {
  }

  public final int getRegPC() {
    return regPC;
  }

  public final void setRegPC(int address) {
    regPC = address & 0xffff;
  }

  public void setFlags(int regF) {
  }

  public final void setRegDE(int word) {
    regD = (word >>> 8) & 0xff;
    regE = word & 0xff;
  }

  public final int getRegA() {
    return regA;
  }

  public final void setRegA(int value) {
    regA = value & 0xff;
  }

  public final int getRegB() {
    return regB;
  }

  public final void setRegB(int value) {
    regB = value & 0xff;
  }

  public final int getRegC() {
    return regC;
  }

  public final void setRegC(int value) {
    regC = value & 0xff;
  }

  public final int getRegD() {
    return regD;
  }

  public final void setRegD(int value) {
    regD = value & 0xff;
  }

  public final int getRegE() {
    return regE;
  }

  public final void setRegE(int value) {
    regE = value & 0xff;
  }

  public final int getRegH() {
    return regH;
  }

  public final void setRegH(int value) {
    regH = value & 0xff;
  }

  public final int getRegL() {
    return regL;
  }

  public final void setRegL(int value) {
    regL = value & 0xff;
  }

  public final int getRegAx() {
    return regAx;
  }

  public final void setRegAx(int value) {
    regAx = value & 0xff;
  }

  public final int getRegFx() {
    return regFx;
  }

  public final void setRegFx(int value) {
    regFx = value & 0xff;
  }

  public final int getRegBx() {
    return regBx;
  }

  public final void setRegBx(int value) {
    regBx = value & 0xff;
  }

  public final int getRegCx() {
    return regCx;
  }

  public final void setRegCx(int value) {
    regCx = value & 0xff;
  }

  public final int getRegDx() {
    return regDx;
  }

  public final void setRegDx(int value) {
    regDx = value & 0xff;
  }

  public final int getRegEx() {
    return regEx;
  }

  public final void setRegEx(int value) {
    regEx = value & 0xff;
  }

  public final int getRegHx() {
    return regHx;
  }

  public final void setRegHx(int value) {
    regHx = value & 0xff;
  }

  public final int getRegLx() {
    return regLx;
  }

  public final void setRegLx(int value) {
    regLx = value & 0xff;
  }

  public final int getRegAF() {
    return (regA << 8) | ((carryFlag ? sz5h3pnFlags | CARRY_MASK : sz5h3pnFlags) & 0xD7);
  }

  public final void setRegAF(int word) {
    regA = (word >>> 8) & 0xff;

    sz5h3pnFlags = word & 0xfe;
    carryFlag = (word & CARRY_MASK) != 0;
  }

  public final int getRegAFx() {
    return (regAx << 8) | regFx;
  }

  public final void setRegAFx(int word) {
    regAx = (word >>> 8) & 0xff;
    regFx = word & 0xff;
  }

  public final int getRegBC() {
    return (regB << 8) | regC;
  }

  public final void setRegBC(int word) {
    regB = (word >>> 8) & 0xff;
    regC = word & 0xff;
  }

  public final int getFlags() {
    return carryFlag ? sz5h3pnFlags | CARRY_MASK : sz5h3pnFlags;
  }

  public final int getRegHLx() {
    return (regHx << 8) | regLx;
  }

  public final void setRegHLx(int word) {
    regHx = (word >>> 8) & 0xff;
    regLx = word & 0xff;
  }

  public final int getRegSP() {
    return regSP;
  }

  public final void setRegSP(int word) {
    regSP = word & 0xffff;
  }

  public final int getRegIX() {
    return regIX;
  }

  public final void setRegIX(int word) {
    regIX = word & 0xffff;
  }

  public final int getRegIY() {
    return regIY;
  }

  public final void setRegIY(int word) {
    regIY = word & 0xffff;
  }

  public final int getRegI() {
    return regI;
  }

  public final void setRegI(int value) {
    regI = value & 0xff;
  }

  public final int getRegR() {
    return regRbit7 ? (regR & 0x7f) | SIGN_MASK : regR & 0x7f;
  }

  public final void setRegR(int value) {
    regR = value & 0x7f;
    regRbit7 = (value > 0x7f);
  }

  public final int getPairIR() {
    if (regRbit7) {
      return (regI << 8) | ((regR & 0x7f) | SIGN_MASK);
    }
    return (regI << 8) | (regR & 0x7f);
  }

  public final int getMemPtr() {
    return memptr & 0xffff;
  }

  public final void setMemPtr(int word) {
    memptr = word & 0xffff;
  }

  public final boolean isCarryFlag() {
    return carryFlag;
  }

  public final void setCarryFlag(boolean state) {
    carryFlag = state;
  }

  public final int getRegDE() {
    return (regD << 8) | regE;
  }

  public final void setZ80State(Z80State state) {
    regA = state.getRegA();
    setFlags(state.getRegF());
    regB = state.getRegB();
    regC = state.getRegC();
    regD = state.getRegD();
    regE = state.getRegE();
    regH = state.getRegH();
    regL = state.getRegL();
    regAx = state.getRegAx();
    regFx = state.getRegFx();
    regBx = state.getRegBx();
    regCx = state.getRegCx();
    regDx = state.getRegDx();
    regEx = state.getRegEx();
    regHx = state.getRegHx();
    regLx = state.getRegLx();
    regIX = state.getRegIX();
    regIY = state.getRegIY();
    regSP = state.getRegSP();
    regPC = state.getRegPC();
    regI = state.getRegI();
    setRegR(state.getRegR());
    memptr = state.getMemPtr();
    halted = state.isHalted();
    ffIFF1 = state.isIFF1();
    ffIFF2 = state.isIFF2();
    modeINT = state.getIM();
    activeINT = state.isINTLine();
    pendingEI = state.isPendingEI();
    activeNMI = state.isNMI();
    flagQ = false;
    lastFlagQ = state.isFlagQ();

    getState().updateFromEmulator();
  }

  abstract State getState();

  public final boolean isIFF1() {
    return ffIFF1;
  }

  public final void setIFF1(boolean state) {
    ffIFF1 = state;
  }

  public final boolean isIFF2() {
    return ffIFF2;
  }

  public final void setIFF2(boolean state) {
    ffIFF2 = state;
  }

  public final boolean isNMI() {
    return activeNMI;
  }

  public final void setNMI(boolean nmi) {
    activeNMI = nmi;
  }

  // La línea de NMI se activa por impulso, no por nivel
  public final void triggerNMI() {
    activeNMI = true;
  }

  // La línea INT se activa por nivel
  public final boolean isINTLine() {
    return activeINT;
  }

  public final void setINTLine(boolean intLine) {
    activeINT = intLine;
  }

  // Acceso al modo de interrupción
  public final IntMode getIM() {
    return modeINT;
  }

  public final void setIM(IntMode mode) {
    modeINT = mode;
  }

  public final boolean isHalted() {
    return halted;
  }

  public void setHalted(boolean state) {
    halted = state;
  }

  public void setPinReset() {
    pinReset = true;
  }

  public final boolean isPendingEI() {
    return pendingEI;
  }

  public final void setPendingEI(boolean state) {
    pendingEI = state;
  }

  public final Z80State getZ80State() {
    Z80State state = new Z80State();
    state.setRegA(regA);
    state.setRegF(getFlags());
    state.setRegB(regB);
    state.setRegC(regC);
    state.setRegD(regD);
    state.setRegE(regE);
    state.setRegH(regH);
    state.setRegL(regL);
    state.setRegAx(regAx);
    state.setRegFx(regFx);
    state.setRegBx(regBx);
    state.setRegCx(regCx);
    state.setRegDx(regDx);
    state.setRegEx(regEx);
    state.setRegHx(regHx);
    state.setRegLx(regLx);
    state.setRegIX(regIX);
    state.setRegIY(regIY);
    state.setRegSP(regSP);
    state.setRegPC(regPC);
    state.setRegI(regI);
    state.setRegR(getRegR());
    state.setMemPtr(memptr);
    state.setHalted(halted);
    state.setIFF1(ffIFF1);
    state.setIFF2(ffIFF2);
    state.setIM(modeINT);
    state.setINTLine(activeINT);
    state.setPendingEI(pendingEI);
    state.setNMI(activeNMI);
    state.setFlagQ(lastFlagQ);
    return state;
  }

}