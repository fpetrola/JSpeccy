package com.fpetrola.z80.minizx;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.Arrays;
import java.util.Stack;

public class SpectrumApplication<T> {
  protected SyncChecker syncChecker = new DummySyncChecker();
  protected int A;
  protected int F;
  protected int B;
  protected int C;
  protected int D;
  protected int E;
  protected int H;
  protected int L;
  protected int IXH;
  protected int IXL;
  protected int IYH;
  protected int IYL;

  protected int nextAddress;

  protected int[] mem = new int[0x10000];
  static protected MiniZX.MiniZXIO io = new MiniZX.MiniZXIO();


  private Stack<Integer> stack = new Stack<>();

  protected void exAF() {
    int temp1 = AFx();
    AFx(AF());
    AF(temp1);
  }

  protected void exHLDE() {
    int temp1 = HL();
    HL(DE());
    DE(temp1);
  }

  protected void push(int value) {
    stack.push(value);
  }

  protected int pop() {
    return stack.pop();
  }

  protected int carry() {
    return F & 1;
  }

  protected boolean isNextPC(int nextPC) {
    boolean matches = nextAddress == nextPC;
    if (matches)
      nextAddress = 0;
    return matches;
  }

  protected SpectrumApplication() {
    Arrays.fill(getMem(), 0);
  }

  protected int in(int port) {
    return io.in2(WordNumber.createValue(port)).intValue();
  }

  protected int mem(int address, int pc) {
    syncChecker.checkSyncJava(address, 0, pc);
    return getMem()[address] & 0xff;
  }

  protected void wMem(int address, int value, int pc) {
    syncChecker.checkSyncJava(address, value, pc);
    wMem(address, value);
  }

  protected void wMem16(int address, int value, int pc) {
    syncChecker.checkSyncJava(address, value, pc);
    getMem()[address] = value & 0xFF;
    syncChecker.checkSyncJava(address + 1, value, pc);
    getMem()[address + 1] = value >> 8;
  }

  protected int mem16(int address, int pc) {
    syncChecker.checkSyncJava(address, 0, pc);
    return mem(address + 1) * 256 + mem(address);
  }

  protected int mem(int address) {
    return getMem()[address] & 0xff;
  }

  protected void wMem(int address, int value) {
    long start = System.nanoTime();
    while (start + 4000 >= System.nanoTime()) ;
    getMem()[address] = value & 0xff;
  }

  protected void wMem16(int address, int value) {
    value = value & 0xffff;
    getMem()[address + 1] = value >> 8;
    getMem()[address] = value & 0xFF;
  }

  protected int mem16(int i) {
    return (mem(i + 1) * 256 + mem(i)) & 0xffff;
  }

  protected void ldir() {
    while (pair(B, C) != 0) {
      wMem(pair(D, E), mem(pair(H, L)));
      BC(pair(B, C) - 1);
      HL(pair(H, L) + 1);
      DE(pair(D, E) + 1);
    }
  }

  protected void lddr() {

  }

  protected void cpir() {
    int result = -1;
    while (pair(B, C) != 0 && result != A) {
      result = mem(pair(H, L));
      HL(pair(H, L) + 1);
      BC(pair(B, C) - 1);
    }
  }

  protected void cpdr() {

  }

  protected void AF(int value) {
    AF = value & 0xffff;
    A = AF >> 8;
    F = AF & 0xFF;
  }

  protected void BC(int value) {
    BC = value & 0xffff;
    B = BC >> 8;
    C = BC & 0xFF;
  }

  protected void DE(int value) {
    DE = value & 0xffff;
    D = DE >> 8;
    E = DE & 0xFF;
  }

  protected void HL(int value) {
    HL = value & 0xffff;
    H = HL >> 8;
    L = HL & 0xFF;
  }

  protected void IX(int value) {
    IX = value & 0xffff;
    IXH = IX >> 8;
    IXL = IX & 0xFF;
  }

  protected void IY(int value) {
    IY = value & 0xffff;
    IYH = IY >> 8;
    IYL = IY & 0xFF;
  }

  protected int pair(int a, int f) {
    return ((a & 0xFF) << 8) | (f & 0xFF);
  }

  protected int rrc(int a) {
    return ((a & 0xff) >> 1) | ((a & 0x01) << 7) & 0xff;
  }

  protected int rlc(int a) {
    F = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | (a & 0xFF) >> 7;
    return i & 0xff;
  }

  protected int rl(int a) {
    int lastCarry = carry() & 0x01;
    F = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | lastCarry;
    return i & 0xff;
  }

  public void update16Registers() {
    BC(pair(B, C));
    DE(pair(D, E));
    HL(pair(H, L));
    AF(pair(A, F));
    IX(pair(IXH, IXL));
    IY(pair(IYH, IYL));
  }


  protected int AF;
  protected int BC;
  protected int DE;
  protected int HL;
  protected int Ax;
  protected int Fx;
  protected int Bx;
  protected int Cx;
  protected int Dx;
  protected int Ex;
  protected int Hx;
  protected int Lx;
  protected int AFx;
  protected int BCx;
  protected int DEx;
  protected int HLx;
  protected int IX;
  protected int IY;
  protected int PC;
  protected int SP;
  protected int I;
  protected int R;
  protected int IR;
  protected int VIRTUAL;
  protected int MEMPTR;

  protected void AFx(int value) {
    AFx = value & 0xffff;
    Ax = AFx >> 8;
    Fx = AFx & 0xFF;
  }

  protected int AFx() {
    return ((Ax & 0xFF) << 8) | (Fx & 0xFF);
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

  protected void setSyncChecker(SyncChecker syncChecker) {
    this.syncChecker = syncChecker;
    syncChecker.init(this);
  }

  public int[] getMem() {
    return mem;
  }

  protected class DummySyncChecker implements SyncChecker {
    public int getByteFromEmu(Integer index) {
      return getMem()[index];
    }
  }
}
