package com.fpetrola.z80.bytecode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class JSW2 {
  public int F;
  public int[] memory;

  public void $0() {
    int A_L8_0 = 1;
    int D_L7_0 = 2;
    int H_L5_0 = 7;

    int H_L5;
    boolean F_L13;
    do {
      H_L5 = H_L5_0 + 1;
      this.memory[0] = H_L5;
      H_L5_0 = H_L5;
      int D_L7 = D_L7_0 + H_L5;
      int A_L8 = A_L8_0 ^ D_L7;
      A_L8_0 = A_L8;
      this.memory[0] = A_L8 + 3;
      D_L7_0 = D_L7;
      this.memory[0] = D_L7;
      int var10000 = 3 - 1 - 10;
      F_L13 = false;
    } while (F_L13);

    this.memory[0] = H_L5;
  }
}
