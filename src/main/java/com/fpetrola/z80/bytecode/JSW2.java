package com.fpetrola.z80.bytecode;

public class JSW2
{
  public int F;
  public int[] memory;

  public void $0() {
    final int D_L1 = 520;
    final int E_L1 = 520;
    final int A_L2 = 0;
    final int A_L4;
    final int A_L3 = A_L4 = A_L2;
    int D_L2 = D_L1;
    D_L2 += A_L4;
    final int A_L5 = A_L4;
    int E_L2 = E_L1;
    E_L2 += A_L5;
    final int B_L8 = 3;
    int D_L3 = D_L2;
    final int E_L3 = E_L2;
    int n = 0;
    final int A_L6 = n;
    int B_L9 = B_L8;
    int B_L10;
    do {
      n = this.memory[B_L9];
      final int A_L7 = A_L5;
      this.memory[1002] = A_L7;
      final int B_L11;
      B_L9 = (B_L11 = B_L8);
      this.memory[E_L3] = B_L11;
      ++D_L3;
      B_L9 = B_L8;
      B_L10 = B_L11;
    } while ((B_L9 = B_L10) != 0);
  }
}
