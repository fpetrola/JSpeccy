package com.fpetrola.z80.opcodes.references;

public class WordNumber {
  private final int value;

  public WordNumber(int value) {
    this.value = value;
  }

  public <T extends WordNumber> T and(int i) {
    return (T) new WordNumber(value & i);
  }

  public <T extends WordNumber> T plus(int i) {
    return (T) new WordNumber(value + i);
  }

  public <T extends WordNumber> T minus(int i) {
    return (T) new WordNumber(value - i);
  }

  public <T extends WordNumber> T plus(byte i) {
    return (T) new WordNumber(value + i);
  }


  public <T extends WordNumber> T left(int i) {
    return (T) new WordNumber(value << i);
  }

  public <T extends WordNumber> T right(int i) {
    return (T) new WordNumber(value >>> i);
  }


  public <T extends WordNumber> T or(T wordNumber) {
    return (T) new WordNumber(value | wordNumber.intValue());
  }

  public <T extends WordNumber> T or(int i) {
    return (T) new WordNumber(value | i);
  }

  public boolean notEquals(int i) {
    return i != value;
  }

  public <T extends WordNumber> byte byteValue() {
    return (byte) value;
  }

  public int intValue() {
    return value;
  }
}
