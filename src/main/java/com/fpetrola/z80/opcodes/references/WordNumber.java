package com.fpetrola.z80.opcodes.references;

public interface WordNumber {
  static <T> T createValue(int i) {
    return (T) new TraceableWordNumber(i);
  }

  <T extends WordNumber> T plus(int i);

  default <T extends WordNumber> T plus() {
    return (T) plus(1);
  }

  <T extends WordNumber> T minus();

  <T extends WordNumber> T left(int i);

  <T extends WordNumber> T right(int i);

  <T extends WordNumber> T or(int i);

  <T extends WordNumber> T and(int i);

  <T extends WordNumber> T xor(int i);

  <T extends WordNumber> T or(T wordNumber);

  <T extends WordNumber> T and(T wordNumber);

  <T extends WordNumber> T xor(T wordNumber);

  <T extends WordNumber> T plus(T wordNumber);

  boolean isNotZero();

  int intValue();

  void set(int read);

  <T extends WordNumber> void set(T other);
}
