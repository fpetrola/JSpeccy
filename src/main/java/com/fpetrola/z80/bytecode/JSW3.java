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

  public void $9133() {
    this.IX = 33024;

    while (true) {
      this.A = this.memory[this.IX + 0];
      this.F = this.A - 255;
      if (this.F == 0) {
        return;
      }

      this.A = this.A & 7;
      if (this.F != 0) {
        this.F = this.A - 3;
        this.F = this.A - 4;
        this.E = this.memory[this.IX + 3];
        this.D = 130;
        this.A = 70;
        this.L = this.A;
        this.A = this.memory[this.IX + 2];
        this.A = this.A & 31;
        this.A = this.A + this.L;
        this.L = this.A;
        this.A = this.E;
        this.A = 0;
        this.A = this.A & 1;
        this.A = this.A | 92;
        this.H = this.A;
        this.DE = 31;
        this.A = this.memory[this.IX + 1];
        this.A = this.A & 15;
        this.A = this.A + 56;
        this.A = this.A & 71;
        this.C = this.A;
        this.A = 0;
        this.A = this.A & 56;
        this.A = this.A ^ this.C;
        this.C = this.A;
        this.HL = 0;
        this.HL = this.HL + this.DE;
        this.HL = 0;
        this.A = this.memory[this.IX + 3];
        this.A = this.A & 14;
        if (this.F != 0) {
          this.HL = this.HL + this.DE;
          this.HL = 0;
        }

        this.C = 1;
        this.A = this.memory[this.IX + 1];
        this.A = this.A & this.memory[this.IX + 0];
        this.A = this.A | this.memory[this.IX + 2];
        this.A = this.A & 224;
        this.E = this.A;
        this.D = this.memory[this.IX + 5];
        this.H = 130;
        this.L = this.memory[this.IX + 3];
        this.A = this.memory[this.IX + 2];
        this.A = this.A & 31;
        this.A = this.A | 0;
        this.HL = 0;
        this.H = 0;
        this.L = this.A;
      }

      this.DE = 8;
      this.IX = this.IX + this.DE;
    }
  }

}
