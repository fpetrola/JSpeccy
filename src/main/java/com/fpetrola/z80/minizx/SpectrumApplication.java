package com.fpetrola.z80.minizx;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.Arrays;

public class SpectrumApplication<T> {


  public int[] mem = new int[0x10000];
  public int carry;
  static public MiniZX.MiniZXIO io = new MiniZX.MiniZXIO();

  public SpectrumApplication() {
    Arrays.fill(mem, 0);
  }

  public int in(int port) {
    return io.in2(WordNumber.createValue(port)).intValue();
  }

  protected void checkSyncJava(int address, int value, int pc) {
  }

  public void lddr() {

  }

  public void cpdr() {

  }

  public int rrc(int a) {
    return ((a & 0xff) >> 1) | ((a & 0x01) << 7);
  }

  public int rlc(int a) {
    carry = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | (a & 0xFF) >> 7;
    return i;
  }

  public int rl(int a) {
    int lastCarry = carry & 0x01;
    carry = (a & 128) >> 7;
    int i = ((a << 1) & 0xfe) | lastCarry;
    return i;
  }



}
