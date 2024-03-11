package com.fpetrola.z80.instructions.tests;

import java.util.stream.IntStream;

public class T1 {

  private static int[] mem = new int[0x10000];
  private static int i;

  public static void main(String[] args) {
    int f = 20;
    int b = 3;
    int h = 7;

    do {
      mem[1000 + i++] = ++h;
      b--;
    } while (b != 0);

    IntStream.range(1000, 1005).forEach(i -> System.out.println(mem[i]));
  }
}
