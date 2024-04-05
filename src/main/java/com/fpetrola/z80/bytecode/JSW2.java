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
      i1--;
      if (i1 == 0) {
        int i3 = this.memory[i] + 1;
        this.memory[i] = i3;
        return;
      }
    } while (true);
  }

  public static void main(String[] args) {
    JSW2 jsw2 = new JSW2();
    jsw2.memory = new int[0x10000];
    Arrays.fill(jsw2.memory, 1);
    jsw2.$0();
  }
}
