package com.fpetrola.z80.bytecode;

import java.util.Arrays;

public class JSW2 {
  public int[] memory;

  public void $0() {
    final int B_L0 = 2;
    final int IX_L1 = 1000;
    final int A_L2 = 100;
    int B_L2 = B_L0;
    int B_L3;
    do {
      int F_L3 = 13;
      final int F_L4;
      F_L3 = (F_L4 = B_L2 - 2);
      if (F_L4 != 0) {
        final int A_L3 = 13;
        final int IX_L2 = 13;
        this.memory[IX_L2 + 1] = A_L3;
      }
      else {
        final int A_L4 = A_L2;
        this.memory[IX_L1 + 2] = A_L4;
      }
      B_L3 = (B_L2 = B_L2);
    } while (B_L3 != 0);
  }

  public static void main(String[] args) {
    JSW2 jsw2 = new JSW2();
    jsw2.memory = new int[0x10000];
    Arrays.fill(jsw2.memory, 1);
    jsw2.$0();
  }
}
