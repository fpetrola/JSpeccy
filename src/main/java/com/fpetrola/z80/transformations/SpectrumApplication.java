package com.fpetrola.z80.transformations;

public class SpectrumApplication {
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
  public int VIRTUAL;
  public int MEMPTR;
  public int[] mem = new int[0xFFFF];

  public int mem(int address) {
    return mem[address];
  }

  public void wMem(int address, int value) {
    mem[address] = value;
  }

  public void ldir() {

  }

  public void lddr() {

  }

  public void cpir() {

  }

  public void cpdr() {

  }

  protected void AF(int value) {
    AF = value & 0xFFFF;
    A = AF >> 8;
    F = AF & 0xFF;
  }

  protected void BC(int value) {
    BC = value & 0xFFFF;
    B = BC >> 8;
    C = BC & 0xFF;
  }

  protected void DE(int value) {
    DE = value & 0xFFFF;
    D = DE >> 8;
    E = DE & 0xFF;
  }

  protected void HL(int value) {
    HL = value & 0xFFFF;
    H = HL >> 8;
    L = HL & 0xFF;
  }

  protected void IX(int value) {
    IX = value & 0xFFFF;
    IXH = IX >> 8;
    IXL = IX & 0xFF;
  }
  protected void IY(int value) {
    IY = value & 0xFFFF;
    IYH = IY >> 8;
    IYL = IY & 0xFF;
  }

  protected int AF() {
    return ((A & 0xFF) << 8) | (F & 0xFF);
  }

  protected int BC() {
    return ((B & 0xFF) << 8) | (C & 0xFF);
  }

  protected int DE() {
    return ((D & 0xFF) << 8) | (E & 0xFF);
  }

  protected int HL() {
    return ((H & 0xFF) << 8) | (L & 0xFF);
  }

  protected int IX() {
    return ((IXH & 0xFF) << 8) | (IXL & 0xFF);
  }

  protected int IY() {
    return ((IYH & 0xFF) << 8) | (IYL & 0xFF);
  }
}
