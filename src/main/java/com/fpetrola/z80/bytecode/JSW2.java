package com.fpetrola.z80.bytecode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class JSW2 {
  public int F;
  public int[] memory;

  public void $0() {
    int B_L1 = 3;
    int A_L2 = 1;
    int D_L3 = 2;
    int H_L4 = 7;
    int B_L9 = B_L1;
    int A_L8 = A_L2;
    int D_L7 = D_L3;
    int H_L5 = H_L4;

    int H_L7;
    boolean F_L13;
    do {
      ++H_L5;
      this.memory[1000] = H_L5;
      H_L7 = H_L5;
      D_L7 += H_L5;
      A_L8 ^= D_L7;
      int A_L9 = A_L8 + B_L9;
      this.memory[1002] = A_L9;
      A_L8 = A_L9;
      this.memory[1001] = D_L7;
      int B_L12 = B_L9 - 1;
      F_L13 = false;
    } while (F_L13);

    this.memory[1100] = H_L7;
  }
}
