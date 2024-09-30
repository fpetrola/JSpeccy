package com.fpetrola.z80.minizx;

public interface SyncChecker {
  default int getByteFromEmu(Integer index) {
    return 0;
  }

  default void init(SpectrumApplication spectrumApplication) {
  }

  default void checkSyncEmu(int address, int value, int pc, boolean write) {
  }

  default void checkSyncJava(int address, int value, int pc) {
  }

  default void checkMatching(int pc, int address, boolean write) {
  }
}
