package com.fpetrola.z80.minizx;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.Arrays;
import java.util.Stack;

public class SpectrumApplication<T> {
  protected SyncChecker syncChecker = new DummySyncChecker();
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

  public int nextAddress;

  public int[] mem = new int[0x10000];
  static public MiniZX.MiniZXIO io = new MiniZX.MiniZXIO();


  private Stack<Integer> stack = new Stack<>();

  public void exAF() {
    int temp1 = AFx();
    AFx(AF());
    AF(temp1);
  }

  public void exHLDE() {
    int temp1 = HL();
    HL(DE());
    DE(temp1);
  }

  public void push(int value) {
    stack.push(value);
  }

  public int pop() {
    return stack.pop();
  }

  public int carry() {
    return F & 1;
  }

  public boolean isNextPC(int nextPC) {
    boolean matches = nextAddress == nextPC;
    if (matches)
      nextAddress = 0;
    return matches;
  }

  public SpectrumApplication() {
    Arrays.fill(mem, 0);
  }

  public int in(int port) {
    return io.in2(WordNumber.createValue(port)).intValue();
  }

  public int mem(int address, int pc) {
    syncChecker.checkSyncJava(address, 0, pc);
    return mem[address];
  }

  public void wMem(int address, int value, int pc) {
    syncChecker.checkSyncJava(address, value, pc);
    wMem(address, value);
  }

  public void wMem16(int address, int value, int pc) {
    syncChecker.checkSyncJava(address, value, pc);
    mem[address] = value & 0xFF;
    syncChecker.checkSyncJava(address + 1, value, pc);
    mem[address + 1] = value >> 8;
  }

  public int mem16(int address, int pc) {
    syncChecker.checkSyncJava(address, 0, pc);
    return mem(address + 1) * 256 + mem(address);
  }

  public int mem(int address) {
    return mem[address];
  }

  public void wMem(int address, int value) {
    long start = System.nanoTime();
    while (start + 4000 >= System.nanoTime()) ;
    mem[address] = value & 0xff;
  }

  public void wMem16(int address, int value) {
    value = value & 0xffff;
    mem[address + 1] = value >> 8;
    mem[address] = value & 0xFF;
  }

  public int mem16(int i) {
    return (mem(i + 1) * 256 + mem(i)) & 0xffff;
  }

  public void ldir() {
    while (pair(B, C) != 0) {
      wMem(pair(D, E), mem(pair(H, L)));
      BC(pair(B, C) - 1);
      HL(pair(H, L) + 1);
      DE(pair(D, E) + 1);
    }
  }

  public void lddr() {

  }

  public void cpir() {
    int result = -1;
    while (pair(B, C) != 0 && result != A) {
      result = mem(pair(H, L));
      HL(pair(H, L) + 1);
      BC(pair(B, C) - 1);
    }
  }

  public void cpdr() {

  }

  public void AF(int value) {
    AF = value & 0xffff;
    A = AF >> 8;
    F = AF & 0xFF;
  }

  public void BC(int value) {
    BC = value & 0xffff;
    B = BC >> 8;
    C = BC & 0xFF;
  }

  public void DE(int value) {
    DE = value & 0xffff;
    D = DE >> 8;
    E = DE & 0xFF;
  }

  public void HL(int value) {
    HL = value & 0xffff;
    H = HL >> 8;
    L = HL & 0xFF;
  }

  public void IX(int value) {
    IX = value & 0xffff;
    IXH = IX >> 8;
    IXL = IX & 0xFF;
  }

  public void IY(int value) {
    IY = value & 0xffff;
    IYH = IY >> 8;
    IYL = IY & 0xFF;
  }

  public int pair(int a, int f) {
    return ((a & 0xFF) << 8) | (f & 0xFF);
  }

  public int rrc(int a) {
    return ((a & 0xff) >> 1) | ((a & 0x01) << 7) & 0xff;
  }

  public int rlc(int a) {
    F = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | (a & 0xFF) >> 7;
    return i & 0xff;
  }

  public int rl(int a) {
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
  public int IX;
  public int IY;
  public int PC;
  public int SP;
  public int I;
  public int R;
  public int IR;
  public int VIRTUAL;
  public int MEMPTR;

  public void AFx(int value) {
    AFx = value & 0xffff;
    Ax = AFx >> 8;
    Fx = AFx & 0xFF;
  }

  public int AFx() {
    return ((Ax & 0xFF) << 8) | (Fx & 0xFF);
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

  public void setSyncChecker(SyncChecker syncChecker) {
    this.syncChecker = syncChecker;
    syncChecker.init(this);
  }

  public class DummySyncChecker implements SyncChecker {
    public int getByteFromEmu(Integer index) {
      return mem[index];
    }
  }
}
