package com.fpetrola.z80.minizx;

import java.util.Arrays;

public class SpectrumApplication<T> {
  public int A;
  public int F;
  public int B;
  public int C;
  public int D;
  public int E;
  public int H;
  public int L;
  public int IXH;
  public int IXL;
  public int IYH;
  public int IYL;
  public int[] mem = new int[0x10000];
  public int carry;
  protected MiniZX.MiniZXIO io = new MiniZX.MiniZXIO();

  public SpectrumApplication() {
    Arrays.fill(mem, 0);
  }

  public int in(int port) {
    return io.in(port);
  }

  public int mem(int address, int pc) {
    return mem[address];
  }

  public void wMem(int address, int value, int pc) {
    wMem(address, value);
  }

  public void wMem16(int address, int value, int pc) {
    mem[address + 1] = value >> 8;
    mem[address] = value & 0xFF;
  }

  public int mem16(int i, int pc) {
    return mem(i + 1) * 256 + mem(i);
  }

  public int mem(int address) {
    return mem[address];
  }

  public void wMem(int address, int value) {
    int a = 0;
    for (int i = 0; i < 100000L; i++) {
      for (int j = 0; j < 1000; j++) {
        a++;
      }
    }
    int b = a;
    if (value > 255)
      System.out.println("255!");
    mem[address] = value & 0xff;
  }

  public void wMem16(int address, int value) {
    mem[address + 1] = value >> 8;
    mem[address] = value & 0xFF;
  }

  public int mem16(int i) {
    return mem(i + 1) * 256 + mem(i);
  }

  public void ldir() {
    while (BC() != 0) {
      wMem(DE(), mem(HL()));
      BC(BC() - 1);
      HL(HL() + 1);
      DE(DE() + 1);
    }
  }

  public void lddr() {

  }

  public void cpir() {
    int result = -1;
    while (BC() != 0 && result != A) {
      result = mem(HL());
      HL(HL() + 1);
      BC(BC() - 1);
    }
  }

  public void cpdr() {

  }

  public void AF(int value) {
    int AF = value & 0xFFFF;
    A = AF >> 8;
    F = AF & 0xFF;
  }

  public void BC(int value) {
    int BC = value & 0xFFFF;
    B = BC >> 8;
    C = BC & 0xFF;
  }

  public void DE(int value) {
    int DE = value & 0xFFFF;
    D = DE >> 8;
    E = DE & 0xFF;
  }

  public void HL(int value) {
    int HL = value & 0xFFFF;
    H = HL >> 8;
    L = HL & 0xFF;
  }

  public void IX(int value) {
    int IX = value & 0xFFFF;
    IXH = IX >> 8;
    IXL = IX & 0xFF;
  }

  public void IY(int value) {
    int IY = value & 0xFFFF;
    IYH = IY >> 8;
    IYL = IY & 0xFF;
  }

  public int AF() {
    return ((A & 0xFF) << 8) | (F & 0xFF);
  }

  public int BC() {
    return ((B & 0xFF) << 8) | (C & 0xFF);
  }

  public int DE() {
    return ((D & 0xFF) << 8) | (E & 0xFF);
  }

  public int HL() {
    return ((H & 0xFF) << 8) | (L & 0xFF);
  }

  public int IX() {
    return ((IXH & 0xFF) << 8) | (IXL & 0xFF);
  }

  public int IY() {
    return ((IYH & 0xFF) << 8) | (IYL & 0xFF);
  }

  public int rrc(int a) {
    return ((a & 0xff) >> 1) | ((a & 0x01) << 7);
  }

  public int rlc(int a) {
    carry = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | (a & 0xFF) >> 7;
    return i;
  }
}
