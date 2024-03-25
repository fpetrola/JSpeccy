package com.fpetrola.z80.bytecode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class JSW2 {
  public int F;
  public int[] memory;

  public void $0() {
    int B_L12 = 3;
    int A_L8 = 1;
    int D_L7 = 2;
    int H_L5 = 7;
    do {
      this.memory[1000] = ++H_L5;
      D_L7 += H_L5;
      A_L8 = (A_L8 ^ D_L7) + B_L12;
      this.memory[1002] = A_L8;
      this.memory[1001] = D_L7;
      B_L12--;
    } while (B_L12 != 0);

    this.memory[1100] = H_L5;
  }
}
