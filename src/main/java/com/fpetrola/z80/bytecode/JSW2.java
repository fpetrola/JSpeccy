package com.fpetrola.z80.bytecode;

public class JSW2 {
  public int F;
  public int[] memory;

  public void $0() {
    int B_L1 = 3;
    int B_L4 = B_L1;

    int B_L7;
    do {
      int A_L6 = B_L4 + 1;
      B_L7 = B_L4 - 1;
      B_L4 = B_L7;
    } while(B_L7 != 0);
  }

  public static void main(String[] args) {
    new JSW2().$0();
  }
}
