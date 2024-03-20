package com.fpetrola.z80.opcodes.references;

import java.util.List;

public interface WordNumber {
  static <T> T createValue(int i) {
    return (T) new IntegerWordNumber(i);
  }

  <T extends WordNumber> T plus(int i);

  default <T extends WordNumber> T plus1() {
    return (T) plus(1);
  }

  <T extends WordNumber> T minus1();

  <T extends WordNumber> T left(int i);

  <T extends WordNumber> T right(int i);

  <T extends WordNumber> T or(int i);

  <T extends WordNumber> T xor(T wordNumber);

  <T extends WordNumber> T and(T wordNumber);

  <T extends WordNumber> T and(int i);

  <T extends WordNumber> T or(T wordNumber);

  boolean isNotZero();

  int intValue();

  <T extends WordNumber> T set(T value);

  WordNumber aluOperation2(WordNumber value1, WordNumber value2, String name);

  WordNumber aluOperation(WordNumber address, String name);

  <T extends WordNumber> T readOperation(T address, T value);

  <T extends WordNumber> List<T> getFirstReadOperation();
}
