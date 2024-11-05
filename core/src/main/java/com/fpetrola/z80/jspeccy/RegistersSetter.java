package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.opcodes.references.WordNumber;

public interface RegistersSetter<T extends WordNumber> {
  void setRegPC(int address);

  void setFlags(int regF);

  void setRegDE(int word);

  void setRegA(int value);

  void setRegB(int value);

  void setRegC(int value);

  void setRegD(int value);

  void setRegE(int value);

  void setRegH(int value);

  void setRegL(int value);

  void setRegAx(int value);

  void setRegFx(int value);

  void setRegBx(int value);

  void setRegCx(int value);

  void setRegDx(int value);

  void setRegEx(int value);

  void setRegHx(int value);

  void setRegLx(int value);

  void setRegAF(int word);

  void setRegBC(int word);

  void setRegHLx(int word);

  void setRegSP(int word);

  void setRegIX(int word);

  void setRegIY(int word);

  void setRegI(int value);

  void setRegR(int value);

  void setMemPtr(int word);

  void setCarryFlag(boolean carryState);

  void setIFF1(boolean state);

  void setIFF2(boolean state);

  void setNMI(boolean nmi);

  void setINTLine(boolean intLine);

  void setIM(int mode);

  void setHalted(boolean state);

  void setPendingEI(boolean state);

  void setFlagQ(boolean flagQ);

  void setLastFlagQ(boolean lastFlagQ);

  void setMemptr(int memptr);

  void setDE(int DE);

  void setActiveINT(boolean activeINT);

  void setActiveNMI(boolean activeNMI);

  void setModeINT(int modeINT);

  void setFfIFF1(boolean ffIFF1);

  void setFfIFF2(boolean ffIFF2);

  void setPinReset(boolean pinReset);
}
