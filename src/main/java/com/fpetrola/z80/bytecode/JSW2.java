package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.minizx.MiniZX;

@SuppressWarnings("ALL")
public class JSW2 extends MiniZX {
  private static int initialRoom = 33;

  protected void customizeMemory() {
    this.getMem()[34795] = (byte) initialRoom;
  }

  @Override
  protected String getProgramBytes() {
    return "";
  }
}
