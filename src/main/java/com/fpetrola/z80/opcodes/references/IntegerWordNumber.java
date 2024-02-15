package com.fpetrola.z80.opcodes.references;

public class IntegerWordNumber implements WordNumber {
  private int value;

  public IntegerWordNumber(int aValue) {
    this.value = aValue & 0xFFFF;
  }

  @Override
  public <T extends WordNumber> T plus(int i) {
    return (T) new IntegerWordNumber(value + i);
  }

  @Override
  public <T extends WordNumber> T minus1() {
    return (T) new IntegerWordNumber(value - 1);
  }

  @Override
  public <T extends WordNumber> T left(int i) {
    return (T) new IntegerWordNumber(value << i);
  }

  @Override
  public <T extends WordNumber> T right(int i) {
    return (T) new IntegerWordNumber(value >>> i);
  }

  @Override
  public <T extends WordNumber> T or(int i) {
    return (T) new IntegerWordNumber(value | i);
  }

  @Override
  public <T extends WordNumber> T and(int i) {
    return (T) new IntegerWordNumber(value & i);
  }

  @Override
  public <T extends WordNumber> T or(T wordNumber) {
    return or(wordNumber.intValue());
  }

  @Override
  public boolean isNotZero() {
    return value != 0;
  }

  @Override
  public int intValue() {
    return value;
  }

  public String toString() {
    return value + "";
  }
}
