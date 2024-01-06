
package z80core;

import com.fpetrola.z80.GraphFrame;
import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.State.IntMode2;
import com.fpetrola.z80.instructions.OpcodesSpy;
import com.fpetrola.z80.registers.RegisterName;

import machine.Clock;
import snapshots.Z80State;
import z80core.Z80.IntMode;

public class Z80B implements IZ80 {

  private MemIoOps MemIoImpl;
  private NotifyOps NotifyImpl;
  private boolean execDone;
  private StateImpl state;
  private OOZ80 z80;
  private Timer timer;
  private final Clock clock;
  private boolean activeINT;
  private boolean activeNMI;
  private boolean pendingEI;
  private int opCode;

  public Z80B(MemIoOps memory, NotifyOps notify, GraphFrame graph) {
    this.clock = Clock.getInstance();
    MemIoImpl = memory;
    NotifyImpl = notify;
    execDone = false;
    reset();

    OpcodesSpy spy = new OpcodesSpy();
    state = new StateImpl(this, spy);
    z80 = new com.fpetrola.z80.OOZ80(new MemoryImplementation(memory), new IOImplementation(memory), state, graph, spy);
    timer = new Timer("Z80");
  }

  public OOZ80 getZ80() {
    return z80;
  }

  public void setINTLine(boolean intLine) {
    activeINT = intLine;
  }

  private void interruption() {
    performInterruption2();
  }

  private void performInterruption2() {
    clock.addTstates(7);
    z80.interruption();
  }

  public void execute(int statesLimit) {
    while (clock.getTstates() < statesLimit) {

      if (activeNMI) {
        activeNMI = false;
//        lastFlagQ = false;
//        nmi();
        continue;
      }

      if (activeINT) {
        if (isIFF1() && !isPendingEI()) {
//          lastFlagQ = false;
          interruption();
        }
      }

      try {

        z80.execute(1);
        if (pendingEI && opCode != 0xFB) {
          pendingEI = false;
          z80.endInterruption();
        }

        if (execDone) {
          NotifyImpl.execDone();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } /* del while */
  }

  public void setZ80State(Z80State state) {
    this.state.updateFromEmulator();
  }

  public Z80State getZ80State() {
    return null;
  }

  public void setBreakpoint(int address, boolean state) {
  }

  public void setPinReset() {
  }

  public void reset() {
    z80.reset();
  }

  public void triggerNMI() {
    activeNMI = true;
  }

  public int getRegPC() {
    return readRegister(RegisterName.PC);
  }

  private int readRegister(RegisterName registerName) {
    return state.registers.get(registerName).read();
  }

  private void writeRegister(RegisterName registerName, int word) {
    state.registers.get(registerName).write(word);
  }

  private int readAlternativeRegister(RegisterName registerName) {
    return state.registers.getAlternate(registerName).read();
  }

  private void writeAlternativeRegister(RegisterName registerName, int word) {
    state.registers.getAlternate(registerName).write(word);
  }

  public void xor(int oper8) {

  }

  public void cp(int oper8) {
  }

  public void setFlags(int regF) {
  }

  public void setRegDE(int word) {
    writeRegister(RegisterName.DE, word);
  }

  public int getRegDE() {
    return readRegister(RegisterName.DE);
  }

  public void setCarryFlag(boolean b) {
    int register = readRegister(RegisterName.F);
    if (b)
      writeRegister(RegisterName.F, register | 0x01);
    else
      writeRegister(RegisterName.F, register & 0xFE);
  }

  public int getFlags() {
    return readRegister(RegisterName.F);
  }

  public int getRegA() {
    return readRegister(RegisterName.A);
  }

  public void setRegA(int b) {
    writeRegister(RegisterName.A, b);
  }

  public void setRegB(int v) {
    writeRegister(RegisterName.B, v);
  }

  public int getRegB() {
    return readRegister(RegisterName.B);
  }

  public void setRegC(int v) {
    writeRegister(RegisterName.C, v);
  }

  public int getRegC() {
    return readRegister(RegisterName.C);
  }

  public void setRegD(int v) {
    writeRegister(RegisterName.D, v);
  }

  public int getRegD() {
    return readRegister(RegisterName.D);
  }

  public void setRegE(int v) {
    writeRegister(RegisterName.E, v);
  }

  public int getRegE() {
    return readRegister(RegisterName.E);
  }

  public void setRegH(int v) {
    writeRegister(RegisterName.H, v);
  }

  public int getRegH() {
    return readRegister(RegisterName.H);
  }

  public void setRegL(int v) {
    writeRegister(RegisterName.L, v);
  }

  public int getRegL() {
    return readRegister(RegisterName.L);
  }

  public int getRegAx() {
    return readAlternativeRegister(RegisterName.A);
  }

  public void setRegAx(int b) {
    writeAlternativeRegister(RegisterName.A, b);
  }

  public void setRegBx(int v) {
    writeAlternativeRegister(RegisterName.B, v);
  }

  public int getRegBx() {
    return readAlternativeRegister(RegisterName.B);
  }

  public void setRegCx(int v) {
    writeAlternativeRegister(RegisterName.C, v);
  }

  public int getRegCx() {
    return readAlternativeRegister(RegisterName.C);
  }

  public void setRegDx(int v) {
    writeAlternativeRegister(RegisterName.D, v);
  }

  public int getRegDx() {
    return readAlternativeRegister(RegisterName.D);
  }

  public void setRegEx(int v) {
    writeAlternativeRegister(RegisterName.E, v);
  }

  public int getRegEx() {
    return readAlternativeRegister(RegisterName.E);
  }

  public void setRegHx(int v) {
    writeAlternativeRegister(RegisterName.H, v);
  }

  public int getRegHx() {
    return readAlternativeRegister(RegisterName.H);
  }

  public void setRegLx(int v) {
    writeAlternativeRegister(RegisterName.L, v);
  }

  public int getRegLx() {
    return readAlternativeRegister(RegisterName.L);
  }

  public void setRegFx(int value) {
    writeAlternativeRegister(RegisterName.F, value);
  }

  public int getRegFx() {
    return readAlternativeRegister(RegisterName.F);
  }

  public void setRegIX(int word) {
    writeRegister(RegisterName.IX, word);
  }

  public int getRegIX() {
    return readRegister(RegisterName.IX);
  }

  public void setRegIY(int word) {
    writeRegister(RegisterName.IY, word);
  }

  public int getRegIY() {
    return readRegister(RegisterName.IY);
  }

  public int getRegI() {
    return readRegister(RegisterName.I);
  }

  public void setRegI(int word) {
    writeRegister(RegisterName.I, word);
  }

  public int getRegR() {
    return readRegister(RegisterName.R);
  }

  public void setRegR(int word) {
    writeRegister(RegisterName.R, word);
  }

  public int getRegSP() {
    return readRegister(RegisterName.SP);
  }

  public int getMemPtr() {
    return readRegister(RegisterName.MEMPTR);
  }

  public boolean isHalted() {
    return state.isHalted();
  }

  public boolean isIFF1() {

    return state.isIff1();
  }

  public boolean isIFF2() {

    return state.isIff1();
  }

  public boolean isNMI() {

    return false;
  }

  public boolean isPendingEI() {

    return false;
  }

  public IntMode getIM() {

    return IntMode.values()[state.modeINT().ordinal()];
  }

  public void setHalted(boolean b) {
    state.setHalt(b);
  }

  public void setIFF1(boolean b) {
    state.setIff1(b);
  }

  public void setIFF2(boolean b) {
    state.setIff2(b);
  }

  public void setIM(IntMode intMode) {
    state.setIntMode(IntMode2.values()[intMode.ordinal()]);
  }
}