package com.fpetrola.z80.bytecode;

import java.util.Arrays;

public class JSW2 {
  public int[] memory;

  public void $0() {
    int i = 14368;
    int i0 = 520;
    int i1 = 3;
    do {
      this.memory[i0] = this.memory[i];
      i++;
      i0 = (i0 >> 8) + 1 << 8 | i0 & 255;
    } while (--i1 == 0);
    this.memory[i] = this.memory[i] + 1;
  }

  public static void main(String[] args) {
    JSW2 jsw2 = new JSW2();
    jsw2.memory = new int[0x10000];
    Arrays.fill(jsw2.memory, 1);
    jsw2.$0();
  }
}
