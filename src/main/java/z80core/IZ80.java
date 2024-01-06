package z80core;

import snapshots.Z80State;
import z80core.Z80.IntMode;

public interface IZ80 {
  void setINTLine(boolean intLine);

  void execute(int statesLimit);

  void setZ80State(Z80State state);

  Z80State getZ80State();

  void setBreakpoint(int address, boolean state);

  void setPinReset();

  void reset();

  void triggerNMI();

  int getRegPC();

  void xor(int oper8);

  void cp(int oper8);

  void setFlags(int regF);

  void setRegDE(int word);

  int getRegDE();

  void setCarryFlag(boolean b);

  int getFlags();

  int getRegA();

  void setRegA(int b);

  void setRegB(int v);

  int getRegB();

  void setRegC(int v);

  int getRegC();

  void setRegD(int v);

  int getRegD();

  void setRegE(int v);

  int getRegE();

  void setRegH(int v);

  int getRegH();

  void setRegL(int v);

  int getRegL();

  int getRegAx();

  void setRegAx(int b);

  void setRegBx(int v);

  int getRegBx();

  void setRegCx(int v);

  int getRegCx();

  void setRegDx(int v);

  int getRegDx();

  void setRegEx(int v);

  int getRegEx();

  void setRegHx(int v);

  int getRegHx();

  void setRegLx(int v);

  int getRegLx();

  void setRegFx(int value);

  int getRegFx();

  void setRegIX(int word);

  int getRegIX();

  void setRegIY(int word);

  int getRegIY();

  int getRegI();

  void setRegI(int word);

  int getRegR();

  void setRegR(int word);

  int getRegSP();

  int getMemPtr();

  boolean isHalted();

  boolean isIFF1();

  boolean isIFF2();

  boolean isNMI();

  boolean isPendingEI();

  IntMode getIM();

  void setHalted(boolean b);

  void setIFF1(boolean b);

  void setIFF2(boolean b);

  void setIM(IntMode intMode);

  void update();

  void enableSpy(boolean b);

}