package com.fpetrola.z80.bytecode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class JSW2 {
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
  public int Ax;
  public int Fx;
  public int Bx;
  public int Cx;
  public int Dx;
  public int Ex;
  public int Hx;
  public int Lx;
  public int AFx;
  public int BCx;
  public int DEx;
  public int HLx;
  public int IXH;
  public int IXL;
  public int IYH;
  public int IYL;
  public int IX;
  public int IY;
  public int PC;
  public int SP;
  public int I;
  public int R;
  public int IR;
  public int MEMPTR;
  public int[] memory;

  public void $90C0() {
    this.IX = 33024;

    while (true) {
      this.A = this.memory[this.IX + 0];
      this.F = this.A - 255;
      if (this.F == 0) {
        return;
      }

      this.A = this.A & 3;
      if (this.F != 0) {
        this.F = this.A - 1;
        if (this.F != 0) {
        }

        this.memory[this.IX + 0] = 0;
        if (this.F == 0) {
          this.A = this.memory[this.IX + 0];
          this.A = this.A - 32;
          this.A = this.A & 127;
          this.memory[this.IX + 0] = this.A;
          this.F = this.A - 96;
          if (this.F >= 0) {
            this.A = this.memory[this.IX + 2];
            this.A = this.A & 31;
            this.F = this.A - this.memory[this.IX + 6];
            if (this.F == 0) {
              this.memory[this.IX + 0] = 129;
            }
          }
        } else {
          this.A = this.memory[this.IX + 0];
          this.A = this.A + 32;
          this.A = this.A | 128;
          this.memory[this.IX + 0] = this.A;
          this.F = this.A - 160;
          if (this.F < 0) {
            this.A = this.memory[this.IX + 2];
            this.A = this.A & 31;
            this.F = this.A - this.memory[this.IX + 7];
            if (this.F == 0) {
              this.memory[this.IX + 0] = 97;
            }
          }
        }
      }

      this.DE = 8;
      this.IX = this.IX + this.DE;
    }
  }

  public void $9456() {
    this.E = this.B;
  }

  public void $969B() {
    this.E = this.B;
  }

  public void $9668() {
    this.E = this.B;
  }

  public void $961E() {
    this.E = this.B;
  }

  public void $9691() {
    this.E = this.B;
  }

  public void $96C9() {
    this.E = this.B;
  }
}
