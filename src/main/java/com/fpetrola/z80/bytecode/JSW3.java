package com.fpetrola.z80.bytecode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class JSW3 {
  public int A;
  public int F;
  public int B;
  public int C;
  public int D;
  public int E;
  public int H;
  public int L;
  public int AF;
  public int BC;
  public int DE;
  public int HL;
  public int IX;
  public int PC;
  public int SP;
  public int I;
  public int R;
  public int[] memory;

  public void $90C0() {
    IX = 33024;

    while(true) {
      A = memory[IX + 0];
      F = A - 255;
      if (F == 0) {
        return;
      }

      A = A & 3;
      if (F != 0) {
        label51: {
          F = A - 1;
          if (F != 0) {
            F = A - 2;
            if (F == 0) {
              A = memory[IX + 0];
              A = A ^ 8;
              memory[IX + 0] = A;
              A = A & 24;
              if (F != 0) {
                A = memory[IX + 0];
                A = A + 32;
                memory[IX + 0] = A;
              }

              A = memory[IX + 3];
              A = A + memory[IX + 4];
              memory[IX + 3] = A;
              F = A - memory[IX + 7];
              if (F < 0) {
                F = A - memory[IX + 6];
                if (F != 0 && F >= 0) {
                  break label51;
                }

                A = memory[IX + 6];
                memory[IX + 3] = A;
              }

              A = memory[IX + 4];
              A = 0;
              memory[IX + 4] = A;
              break label51;
            }
          }

          memory[IX + 0] = 0;
          if (F == 0) {
            A = memory[IX + 0];
            A = A - 32;
            A = A & 127;
            memory[IX + 0] = A;
            F = A - 96;
            if (F >= 0) {
              A = memory[IX + 2];
              A = A & 31;
              F = A - memory[IX + 6];
              if (F == 0) {
                memory[IX + 0] = 129;
              }
            }
          } else {
            A = memory[IX + 0];
            A = A + 32;
            A = A | 128;
            memory[IX + 0] = A;
            F = A - 160;
            if (F < 0) {
              A = memory[IX + 2];
              A = A & 31;
              F = A - memory[IX + 7];
              if (F == 0) {
                memory[IX + 0] = 97;
              }
            }
          }
        }
      }

      DE = 8;
      IX = IX + DE;
    }
  }
}
