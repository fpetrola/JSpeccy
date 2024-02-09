package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.spy.InstructionSpy;

import java.util.Set;

public interface WordNumber {
  static <T> T createValue(int i) {
    return (T) new TraceableWordNumber(i);
  }

  <T extends WordNumber> T plus(int i);

  <T extends WordNumber> T minus(int i);

  <T extends WordNumber> T plus(byte i);

  <T extends WordNumber> T left(int i);

  <T extends WordNumber> T right(int i);

  <T extends WordNumber> T or(int i);

  <T extends WordNumber> T and(int i);

  <T extends WordNumber> T xor(int i);

  <T extends WordNumber> T or(T wordNumber);

  <T extends WordNumber> T and(T wordNumber);

  <T extends WordNumber> T xor(T wordNumber);

  <T extends WordNumber> T plus(T wordNumber);

  boolean notEquals(int i);

  <T extends WordNumber> byte byteValue();

  int intValue();

  void set(int read);
}
