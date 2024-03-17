package com.fpetrola.z80.registers.flag;

public class Alu8BitResult {
  public final int ans;
  public final int flag;

  public Alu8BitResult(int ans, int flag) {
    this.ans = ans;
    this.flag = flag;
  }
}
