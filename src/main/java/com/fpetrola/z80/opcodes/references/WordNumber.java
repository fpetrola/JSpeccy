package com.fpetrola.z80.opcodes.references;

public interface WordNumber {
  static <T> T createValue(int i) {
    return (T) new TraceableWordNumber(i);
  }

  <T extends WordNumber> T increment(int i);

  default <T extends WordNumber> T increment() {
    return (T) increment(1);
  }

  <T extends WordNumber> T decrement();

  <T extends WordNumber> T left(int i);

  <T extends WordNumber> T right(int i);

  <T extends WordNumber> T or(int i);

  <T extends WordNumber> T and(int i);

  <T extends WordNumber> T xor(int i);

  <T extends WordNumber> T or(T wordNumber);

  <T extends WordNumber> T and(T wordNumber);

  <T extends WordNumber> T xor(T wordNumber);

  <T extends WordNumber> T increment(T wordNumber);

  boolean isNotZero();

  int intValue();

  void set(int read);

  <T extends WordNumber> void set(T other);
}
